package com.puresoltechnologies.purifinity.server.analysisservice.rest.impl.security;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.ejb.Singleton;
import javax.security.auth.login.LoginException;

import com.puresoltechnologies.purifinity.server.passwordstore.client.PasswordStoreClient;

@Singleton
public class AuthServiceBean implements AuthService {

    // An authentication token storage which stores <auth_token>.
    private final Map<UUID, String> authorizationTokensStorage = new HashMap<>();

    private final PasswordStoreClient passwordStore = new PasswordStoreClient();

    @Override
    public String login(String email, String password) throws LoginException {
	if (passwordStore.authenticate(email, password)) {
	    /**
	     * Once all params are matched, the authToken will be generated and
	     * will be stored in the authorizationTokensStorage. The authToken
	     * will be needed for every REST API invocation and is only valid
	     * within the login session
	     */
	    UUID authToken = UUID.randomUUID();
	    authorizationTokensStorage.put(authToken, email);
	    return authToken.toString();
	}
	throw new LoginException("Don't Come Here Again!");
    }

    @Override
    public void logout(String email, UUID authToken) throws LoginException {
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

    private User findByEmailAndAuthToken(String email, UUID authToken) {
	String userId = authorizationTokensStorage.get(authToken);
	if ((userId != null) && (userId.equals(email))) {
	    return new User(email, "admin");
	}
	return null;
    }

    @Override
    public boolean isAuthorized(String authId, UUID authToken,
	    Set<String> rolesAllowed) {
	User user = findByEmailAndAuthToken(authId, authToken);
	if (user != null) {
	    return rolesAllowed.contains(user.getAuthRole());
	} else {
	    return false;
	}
    }

    @Override
    public boolean isAuthorizedAdministrator(String authId, UUID authToken) {
	User user = findByEmailAndAuthToken(authId, authToken);
	return (user != null) && (user.getAuthRole().equals("admin"));
    }

}