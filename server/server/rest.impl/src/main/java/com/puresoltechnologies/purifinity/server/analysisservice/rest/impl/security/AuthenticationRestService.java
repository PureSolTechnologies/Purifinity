package com.puresoltechnologies.purifinity.server.analysisservice.rest.impl.security;

import java.util.UUID;

import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import javax.ws.rs.HeaderParam;

import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.security.AuthElement;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.security.AuthLoginElement;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.security.AuthenticationRestInterface;

public class AuthenticationRestService implements AuthenticationRestInterface {

    @Inject
    private AuthService authService;

    @Override
    public AuthElement login(AuthLoginElement login) {
	String username = login.getUsername();
	try {
	    String token = authService.login(username, login.getPassword());
	    return new AuthElement(username, token, "permission", "User '"
		    + username + "' was successfully authenticated.");
	} catch (LoginException e) {
	    return new AuthElement(username, "", "", "User '" + username
		    + "' could not be authenticated. (Message: "
		    + e.getMessage() + ")");
	}
    }

    @Override
    public AuthElement logout(
	    //
	    @HeaderParam("auth-id") String authId,
	    @HeaderParam("auth-token") String authToken) {
	try {
	    authService.logout(authId, UUID.fromString(authToken));
	    return new AuthElement(authId, "", "", "User '" + authId
		    + "' was successfully logged out.");
	} catch (LoginException e) {
	    return new AuthElement(authId, "", "", "User '" + authId
		    + "' could not be logged out. (Message: " + e.getMessage()
		    + ")");
	}
    }
}
