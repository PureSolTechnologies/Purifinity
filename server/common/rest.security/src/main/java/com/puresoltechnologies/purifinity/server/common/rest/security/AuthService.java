package com.puresoltechnologies.purifinity.server.common.rest.security;

import java.util.Set;
import java.util.UUID;

import javax.security.auth.login.LoginException;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.commons.types.Password;

/**
 * This is the interface for the authenication service. This service is a proxy
 * for the actual implementation of authenication and authorization.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface AuthService {

    public String login(EmailAddress email, Password password)
	    throws LoginException;

    public void logout(EmailAddress email, UUID authToken)
	    throws LoginException;

    /**
     * This method checks whether a user was authenticated or not and whether it
     * has the correct role.
     * 
     * @param email
     *            is the username of the user to be authenticated.
     * @param authToken
     *            is the token which was
     * @param rolesAllowed
     * @return
     */
    public boolean isAuthorized(EmailAddress email, UUID authToken,
	    Set<String> rolesAllowed);

    public boolean isAuthorizedAdministrator(EmailAddress email, UUID authToken);

}
