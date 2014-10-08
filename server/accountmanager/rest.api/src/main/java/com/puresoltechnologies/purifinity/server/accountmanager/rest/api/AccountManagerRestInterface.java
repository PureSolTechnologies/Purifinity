package com.puresoltechnologies.purifinity.server.accountmanager.rest.api;

import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.puresoltechnologies.purifinity.server.accountmanager.domain.Role;
import com.puresoltechnologies.purifinity.server.accountmanager.domain.User;

@Path("/")
public interface AccountManagerRestInterface {

    @GET
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<User> getUsers();

    @GET
    @Path("roles")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Role> getRoles();

}
