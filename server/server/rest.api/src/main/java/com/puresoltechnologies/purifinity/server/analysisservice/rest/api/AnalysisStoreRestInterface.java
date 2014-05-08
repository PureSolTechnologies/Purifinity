package com.puresoltechnologies.purifinity.server.analysisservice.rest.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("store/rest")
public interface AnalysisStoreRestInterface {

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("projects")
	public Response createAnalysisProject(String string);

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("projects")
	public Response getProjects();
}
