package com.puresoltechnologies.purifinity.server.rest.api;

import java.io.IOException;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.puresoltechnologies.purifinity.server.accountmanager.core.api.SupportedRoles;
import com.puresoltechnologies.purifinity.server.common.rest.security.RolesAllowed;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryServiceInformation;

@Path("repositorymanager")
public interface RepositoryManagerRestInterface {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("repositories")
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID })
    public Collection<RepositoryServiceInformation> getRepositories() throws IOException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("repositories/{id}")
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID })
    public RepositoryServiceInformation getRepository(@PathParam("id") String repositoryTypeId);

}
