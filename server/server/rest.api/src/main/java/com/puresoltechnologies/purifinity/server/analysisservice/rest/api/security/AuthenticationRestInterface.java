package com.puresoltechnologies.purifinity.server.analysisservice.rest.api.security;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.puresoltechnologies.purifinity.server.common.rest.security.PermitAll;

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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public AuthElement logout(AuthLogoutElement logout);

}
