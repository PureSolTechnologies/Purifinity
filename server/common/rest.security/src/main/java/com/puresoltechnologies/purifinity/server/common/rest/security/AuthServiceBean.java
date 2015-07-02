package com.puresoltechnologies.purifinity.server.common.rest.security;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;

import org.slf4j.Logger;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.commons.types.Password;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.AccountManager;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.SupportedRoles;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.User;
import com.puresoltechnologies.purifinity.server.core.api.PurifinityConfiguration;
import com.puresoltechnologies.purifinity.server.core.api.preferences.PreferencesStore;
import com.puresoltechnologies.purifinity.server.core.api.preferences.SystemPreferenceChange;
import com.puresoltechnologies.purifinity.server.core.api.preferences.SystemPreferenceChangeEvent;

@Singleton
public class AuthServiceBean implements AuthService {

    private static final int MINUTES_TO_MILLISECONDS = 60000;
    @Inject
    private Logger logger;

    @Inject
    private AccountManager accountManager;

    @Inject
    private PreferencesStore preferencesStore;

    // An authentication token storage which stores <auth_token>.
    private final Map<UUID, EmailAddress> authorizationTokensStorage = new HashMap<>();
    private final Map<UUID, Date> sessionStarts = new HashMap<>();
    private final Map<UUID, Date> lastActivities = new HashMap<>();

    private int userInactivityTimeout = PurifinityConfiguration.USER_INACTIVITY_TIMEOUT.getDefaultValue();
    private int userSessionTimeout = PurifinityConfiguration.USER_SESSION_TIMEOUT.getDefaultValue();

    @PostConstruct
    public void postConstruct() {
	userInactivityTimeout = preferencesStore.getSystemPreference(PurifinityConfiguration.USER_INACTIVITY_TIMEOUT)
		.getValue();
	userSessionTimeout = preferencesStore.getSystemPreference(PurifinityConfiguration.USER_SESSION_TIMEOUT)
		.getValue();
    }

    @Override
    public void onSystemPreferenceChange(@Observes @SystemPreferenceChange SystemPreferenceChangeEvent event) {
	if (event.getConfigurationParameter().equals(PurifinityConfiguration.USER_INACTIVITY_TIMEOUT)) {
	    userInactivityTimeout = (Integer) event.getValue();
	    logger.info("UserInactivityTimeout was reconfigured to " + userInactivityTimeout + "s.");
	} else if (event.getConfigurationParameter().equals(PurifinityConfiguration.USER_SESSION_TIMEOUT)) {
	    userSessionTimeout = (Integer) event.getValue();
	    logger.info("UserSessionTimeout was reconfigured to " + userSessionTimeout + "s.");
	}
    }

    @Override
    @Lock(LockType.WRITE)
    public String login(EmailAddress email, Password password) throws LoginException {
	if (accountManager.authenticate(email, password)) {
	    /*
	     * Once all parameters are matched, the authToken will be generated
	     * and will be stored in the authorizationTokensStorage. The
	     * authToken will be needed for every REST API invocation and is
	     * only valid within the login session
	     */
	    UUID authToken = UUID.randomUUID();
	    authorizationTokensStorage.put(authToken, email);
	    Date time = new Date();
	    sessionStarts.put(authToken, time);
	    lastActivities.put(authToken, time);
	    return authToken.toString();
	}
	throw new LoginException("Authentication information are invalid.");
    }

    @Override
    @Lock(LockType.WRITE)
    public void logout(EmailAddress email, UUID authToken) throws LoginException {
	if (authorizationTokensStorage.containsKey(authToken)
		&& (authorizationTokensStorage.get(authToken).equals(email))) {
	    authorizationTokensStorage.remove(authToken);
	    lastActivities.remove(authToken);
	    sessionStarts.remove(authToken);
	} else {
	    throw new LoginException(
		    "User '" + email + "' could not be logged out. User was either not logged in or is unknown.");
	}
    }

    /**
     * The method that pre-validates if the client which invokes the REST API is
     * from a authorized and authenticated source.
     *
     * @param authToken
     *            The authorization token generated after login
     * @return TRUE for acceptance and FALSE for denied.
     */
    @Lock(LockType.READ)
    public boolean isAuthTokenValid(String authToken) {
	if (authorizationTokensStorage.containsKey(authToken)) {
	    return true;
	}
	return false;
    }

    @Lock(LockType.WRITE)
    public void logout(UUID authToken) throws GeneralSecurityException {
	if (authorizationTokensStorage.containsKey(authToken)) {
	    authorizationTokensStorage.remove(authToken);
	    lastActivities.remove(authToken);
	    sessionStarts.remove(authToken);
	    return;
	}
	throw new GeneralSecurityException("Invalid authorization token.");
    }

    private User findByEmailAndAuthToken(EmailAddress email, UUID authToken) {
	EmailAddress userId = authorizationTokensStorage.get(authToken);
	if ((userId != null) && (userId.equals(email))) {
	    return accountManager.getUser(email);
	}
	return null;
    }

    @Override
    @Lock(LockType.READ)
    public boolean isAuthorized(EmailAddress email, UUID authToken, Set<String> rolesAllowed) {
	User user = findByEmailAndAuthToken(email, authToken);
	if (user != null) {
	    return rolesAllowed.contains(user.getRole().getId());
	} else {
	    return false;
	}
    }

    @Override
    @Lock(LockType.READ)
    public boolean isAuthorizedAdministrator(EmailAddress email, UUID authToken) {
	User user = findByEmailAndAuthToken(email, authToken);
	if (user == null) {
	    return false;
	}
	return SupportedRoles.ADMINISTRATOR.getId().equals(user.getRole().getId());
    }

    @Override
    public boolean isAuthenticated(EmailAddress email, UUID authToken) {
	try {
	    EmailAddress emailAddress = authorizationTokensStorage.get(authToken);
	    if (emailAddress == null) {
		return false;
	    }
	    if (!emailAddress.equals(email)) {
		logout(authToken);
		return false;
	    }
	    Date time = new Date();
	    Date sessionStart = sessionStarts.get(authToken);
	    if ((time.getTime() - sessionStart.getTime()) > (userSessionTimeout * MINUTES_TO_MILLISECONDS)) {
		logout(authToken);
		return false;
	    }
	    Date lastActivity = lastActivities.get(authToken);
	    if ((time.getTime() - lastActivity.getTime()) > (userInactivityTimeout * MINUTES_TO_MILLISECONDS)) {
		logout(authToken);
		return false;
	    }
	    return true;
	} catch (GeneralSecurityException e) {
	    return false;
	}
    }

    @Override
    public void updateActivity(EmailAddress email, UUID authToken) {
	if (isAuthenticated(email, authToken)) {
	    lastActivities.put(authToken, new Date());
	}
    }

}