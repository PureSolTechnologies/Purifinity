package com.puresoltechnologies.purifinity.server.analysisservice.rest.api;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("rest")
public interface AnalysisServiceRestInterface {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("analyzers")
	public Response getAnalyzers() throws IOException;

}
