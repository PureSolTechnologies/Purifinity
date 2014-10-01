package com.puresoltechnologies.purifinity.server.analysisservice.rest.impl.security;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.ejb.Singleton;
import javax.security.auth.login.LoginException;

@Singleton
public class AuthServiceBean implements AuthService {

	private final Map<String, String> usersStorage = new HashMap<>();

	// An authentication token storage which stores <auth_token>.
	private final Map<UUID, String> authorizationTokensStorage = new HashMap<>();

	public AuthServiceBean() {
		// The usersStorage pretty much represents a user table in the database
		usersStorage.put("username1", "passwordForUser1");
		usersStorage.put("username2", "passwordForUser2");

		usersStorage.put("username3", "passwordForUser3");
	}

	@Override
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
				UUID authToken = UUID.randomUUID();
				authorizationTokensStorage.put(authToken, username);
				return authToken.toString();
			}
		}
		throw new LoginException("Don't Come Here Again!");
	}

	@Override
	public void logout(String username, UUID authToken) throws LoginException {
		if (usersStorage.containsKey(username)
				|| authorizationTokensStorage.containsKey(authToken)) {
			authorizationTokensStorage.remove(authToken);
		} else {
			throw new LoginException(
					"User '"
							+ username
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

	private User findByUsernameAndAuthToken(String username, UUID authToken) {
		String userId = authorizationTokensStorage.get(authToken);
		if ((userId != null) && (userId.equals(username))) {
			return new User(username, "admin");
		}
		return null;
	}

	@Override
	public boolean isAuthorized(String authId, UUID authToken,
			Set<String> rolesAllowed) {
		User user = findByUsernameAndAuthToken(authId, authToken);
		if (user != null) {
			return rolesAllowed.contains(user.getAuthRole());
		} else {
			return false;
		}
	}

	@Override
	public boolean isAuthorizedAdministrator(String authId, UUID authToken) {
		User user = findByUsernameAndAuthToken(authId, authToken);
		return user.getAuthRole().equals("admin");
	}

}