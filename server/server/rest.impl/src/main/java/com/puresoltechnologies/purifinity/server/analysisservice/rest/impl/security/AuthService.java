package com.puresoltechnologies.purifinity.server.analysisservice.rest.impl.security;

import java.util.Set;
import java.util.UUID;

import javax.security.auth.login.LoginException;

/**
 * This is the interface for the authenication service. This service is a proxy
 * for the actual implementation of authenication and authorization.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface AuthService {

	public String login(String username, String password) throws LoginException;

	public void logout(String username, UUID authToken) throws LoginException;

	/**
	 * This method checks whether a user was authenticated or not and whether it
	 * has the correct role.
	 * 
	 * @param username
	 *            is the username of the user to be authenticated.
	 * @param authToken
	 *            is the token which was
	 * @param rolesAllowed
	 * @return
	 */
	public boolean isAuthorized(String username, UUID authToken,
			Set<String> rolesAllowed);

	public boolean isAuthorizedAdministrator(String username, UUID authToken);

}
