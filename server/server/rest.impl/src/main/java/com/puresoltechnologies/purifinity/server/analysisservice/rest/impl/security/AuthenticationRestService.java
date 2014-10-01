package com.puresoltechnologies.purifinity.server.analysisservice.rest.impl.security;

import java.util.UUID;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.security.AuthElement;

@Path("/auth")
public class AuthenticationRestService {

	@Inject
	private AuthService authService;

	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public AuthElement login(
			//
			@FormParam("username") String username,
			@FormParam("password") String password) {
		try {
			String token = authService.login(username, password);
			return new AuthElement(username, token, "permission", "User '"
					+ username + "' was successfully authenticated.");
		} catch (LoginException e) {
			return new AuthElement(username, "", "", "User '" + username
					+ "' could not be authenticated. (Message: "
					+ e.getMessage() + ")");
		}
	}

	@POST
	@Path("logout")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
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
