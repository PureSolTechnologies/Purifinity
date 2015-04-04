package com.puresoltechnologies.purifinity.server.rest.api;

import java.io.IOException;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryServiceInformation;

@Path("repositories")
public interface RepositoryRestInterface {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("types")
    public Collection<RepositoryServiceInformation> getRepositories()
	    throws IOException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("types/{id}")
    public RepositoryServiceInformation getRepository(
	    @PathParam("id") String repositoryTypeId);

}
