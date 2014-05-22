package com.puresoltechnologies.purifinity.server.analysisservice.rest.api;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerInformation;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryType;

@Path("/")
public interface AnalysisServiceRestInterface {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("analyzers")
	public Collection<AnalyzerInformation> getAnalyzers() throws IOException;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("repositories")
	public Collection<RepositoryType> getRepositoryTypes() throws IOException;

	@PUT
	@Path("triggerAnalysis/{project_uuid}")
	void triggerNewAnalysisRun(@PathParam("project_uuid") UUID projectUUID);

}
