package com.puresoltechnologies.purifinity.server.analysisservice.rest.impl.security;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.security.AuthAccessElement;

@Path("/auth")
public class AuthenticationRestService {

    @Inject
    private Authenticator userService;

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public AuthAccessElement login(@FormParam("username") String username,
	    @FormParam("password") String password) {
	try {
	    String token = userService.login(username, password);
	    return new AuthAccessElement(username, token, "permission");
	} catch (LoginException e) {
	    throw new RuntimeException("Not authenticated!", e);
	}
    }

}
