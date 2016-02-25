package com.puresoltechnologies.purifinity.server.common.rest.security;

import java.util.Set;
import java.util.UUID;

import javax.enterprise.event.Observes;
import javax.security.auth.login.LoginException;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.commons.types.Password;
import com.puresoltechnologies.purifinity.server.core.api.preferences.SystemPreferenceChange;
import com.puresoltechnologies.purifinity.server.core.api.preferences.SystemPreferenceChangeEvent;

/**
 * This is the interface for the authenication service. This service is a proxy
 * for the actual implementation of authenication and authorization.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface AuthService {

    public void onSystemPreferenceChange(@Observes @SystemPreferenceChange SystemPreferenceChangeEvent event);

    public String login(EmailAddress email, Password password) throws LoginException;

    public void logout(EmailAddress email, UUID authToken) throws LoginException;

    /**
     * This method checks whether a user was authenticated or not and whether it
     * has the correct role.
     * 
     * @param email
     *            is the {@link EmailAddress} of the user to be authenticated.
     * @param authToken
     *            is the {@link UUID} token.
     * @param rolesAllowed
     *            is a {@link Set} of role names.
     * @return <code>true</code> is returned if the user was authorized.
     *         <code>false</code> is returned otherwise.
     */
    public boolean isAuthorized(EmailAddress email, UUID authToken, Set<String> rolesAllowed);

    public boolean isAuthorizedAdministrator(EmailAddress email, UUID authToken);

    public boolean isAuthenticated(EmailAddress email, UUID authToken);

    public void updateActivity(EmailAddress email, UUID authToken);

}
