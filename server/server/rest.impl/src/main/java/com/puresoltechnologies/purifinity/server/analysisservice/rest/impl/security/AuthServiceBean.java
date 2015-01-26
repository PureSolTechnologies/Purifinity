package com.puresoltechnologies.purifinity.server.analysisservice.rest.impl.security;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.commons.types.Password;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.AccountManager;

@Singleton
public class AuthServiceBean implements AuthService {

    // An authentication token storage which stores <auth_token>.
    private final Map<UUID, EmailAddress> authorizationTokensStorage = new HashMap<>();

    @Inject
    private AccountManager accountManager;

    @Override
    public String login(EmailAddress email, Password password)
	    throws LoginException {
	if (accountManager.authenticate(email, password)) {
	    /*
	     * Once all parameters are matched, the authToken will be generated
	     * and will be stored in the authorizationTokensStorage. The
	     * authToken will be needed for every REST API invocation and is
	     * only valid within the login session
	     */
	    UUID authToken = UUID.randomUUID();
	    authorizationTokensStorage.put(authToken, email);
	    return authToken.toString();
	}
	throw new LoginException("Authentication information are invalid.");
    }

    @Override
    public void logout(EmailAddress email, UUID authToken)
	    throws LoginException {
	if (authorizationTokensStorage.containsKey(authToken)
		&& (authorizationTokensStorage.get(authToken).equals(email))) {
	    authorizationTokensStorage.remove(authToken);
	} else {
	    throw new LoginException(
		    "User '"
			    + email
			    + "' could not be logged out. User was either not logged in or is unknown.");
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
    public boolean isAuthTokenValid(String authToken) {
	if (authorizationTokensStorage.containsKey(authToken)) {
	    return true;
	}
	return false;
    }

    public void logout(String authToken) throws GeneralSecurityException {
	if (authorizationTokensStorage.containsKey(authToken)) {
	    authorizationTokensStorage.remove(authToken);
	    return;
	}
	throw new GeneralSecurityException("Invalid authorization token.");
    }

    private User findByEmailAndAuthToken(EmailAddress email, UUID authToken) {
	EmailAddress userId = authorizationTokensStorage.get(authToken);
	if ((userId != null) && (userId.equals(email))) {
	    return new User(email, "admin");
	}
	return null;
    }

    @Override
    public boolean isAuthorized(EmailAddress email, UUID authToken,
	    Set<String> rolesAllowed) {
	User user = findByEmailAndAuthToken(email, authToken);
	if (user != null) {
	    return rolesAllowed.contains(user.getAuthRole());
	} else {
	    return false;
	}
    }

    @Override
    public boolean isAuthorizedAdministrator(EmailAddress email, UUID authToken) {
	User user = findByEmailAndAuthToken(email, authToken);
	return (user != null) && (user.getAuthRole().equals("admin"));
    }

}