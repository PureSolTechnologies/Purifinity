package com.puresoltechnologies.purifinity.server.analysisservice.rest.api;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("rest")
public interface AnalysisServiceRestInterface {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("analyzers")
	public AvailableAnalyzers getAnalyzers() throws IOException;

}