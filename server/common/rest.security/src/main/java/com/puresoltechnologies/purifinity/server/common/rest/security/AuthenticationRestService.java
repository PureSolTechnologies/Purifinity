package com.puresoltechnologies.purifinity.server.common.rest.security;

import javax.inject.Inject;
import javax.security.auth.login.LoginException;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.commons.types.Password;

public class AuthenticationRestService implements AuthenticationRestInterface {

    @Inject
    private AuthService authService;

    @Override
    public AuthElement login(AuthLoginElement login) {
	EmailAddress email = new EmailAddress(login.getEmail());
	try {
	    String token = authService.login(email,
		    new Password(login.getPassword()));
	    return new AuthElement(email.getAddress(), token, "permission",
		    "User '" + email + "' was successfully authenticated.");
	} catch (LoginException e) {
	    return new AuthElement(email.getAddress(), "", "", "User '" + email
		    + "' could not be authenticated. (Message: "
		    + e.getMessage() + ")");
	}
    }

    @Override
    public AuthElement logout(AuthLogoutElement logout) {
	EmailAddress email = new EmailAddress(logout.getAuthId());
	try {
	    authService.logout(email, logout.getToken());
	    return new AuthElement(email.getAddress(), "", "", "User '" + email
		    + "' was successfully logged out.");
	} catch (LoginException e) {
	    return new AuthElement(email.getAddress(), "", "", "User '" + email
		    + "' could not be logged out. (Message: " + e.getMessage()
		    + ")");
	}
    }
}
