package com.puresoltechnologies.purifinity.server.analysisservice.rest.impl.security;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.ejb.Singleton;
import javax.security.auth.login.LoginException;

@Singleton
public class Authenticator {

    private final Map<String, String> usersStorage = new HashMap<>();

    // An authentication token storage which stores <auth_token>.
    private final Map<String, String> authorizationTokensStorage = new HashMap<>();

    public Authenticator() {
	// The usersStorage pretty much represents a user table in the database
	usersStorage.put("username1", "passwordForUser1");
	usersStorage.put("username2", "passwordForUser2");

	usersStorage.put("username3", "passwordForUser3");
    }

    public String login(String username, String password) throws LoginException {
	if (usersStorage.containsKey(username)) {
	    String passwordMatch = usersStorage.get(username);
	    if (passwordMatch.equals(password)) {
		/**
		 * Once all params are matched, the authToken will be generated
		 * and will be stored in the authorizationTokensStorage. The
		 * authToken will be needed for every REST API invocation and is
		 * only valid within the login session
		 */
		String authToken = UUID.randomUUID().toString();
		authorizationTokensStorage.put(authToken, username);
		return authToken;
	    }
	}

	throw new LoginException("Don't Come Here Again!");
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

    public User findByUsernameAndAuthToken(String username, String authToken) {
	String userId = authorizationTokensStorage.get(authToken);
	if ((userId != null) && (userId.equals(username))) {
	    return new User(username, "admin");
	}
	return null;
    }

}