package com.puresoltechnologies.purifinity.server.analysisservice.rest.api.security;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/auth")
public interface AuthenticationRestInterface {

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public AuthElement login(AuthLoginElement login);

    @POST
    @Path("logout")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public AuthElement logout(@HeaderParam("auth-id") String authId,
	    @HeaderParam("auth-token") String authToken);

}
