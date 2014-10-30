package com.puresoltechnologies.purifinity.server.analysisservice.rest.impl.security;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;

import com.puresoltechnologies.commons.misc.types.EmailAddress;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.security.AuthElement;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.security.AuthLoginElement;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.security.AuthLogoutElement;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.security.AuthenticationRestInterface;

@Stateful
@SessionScoped
public class AuthenticationRestService implements AuthenticationRestInterface {

    @Inject
    private AuthService authService;

    @Override
    public AuthElement login(AuthLoginElement login) {
	EmailAddress email = login.getEmail();
	try {
	    String token = authService.login(email, login.getPassword());
	    return new AuthElement(email, token, "permission", "User '" + email
		    + "' was successfully authenticated.");
	} catch (LoginException e) {
	    return new AuthElement(email, "", "", "User '" + email
		    + "' could not be authenticated. (Message: "
		    + e.getMessage() + ")");
	}
    }

    @Override
    public AuthElement logout(AuthLogoutElement logout) {
	EmailAddress email = logout.getEmail();
	try {
	    authService.logout(email, logout.getToken());
	    return new AuthElement(email, "", "", "User '" + email
		    + "' was successfully logged out.");
	} catch (LoginException e) {
	    return new AuthElement(email, "", "", "User '" + email
		    + "' could not be logged out. (Message: " + e.getMessage()
		    + ")");
	}
    }
}
