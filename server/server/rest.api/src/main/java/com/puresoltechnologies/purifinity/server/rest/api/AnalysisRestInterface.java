package com.puresoltechnologies.purifinity.server.rest.api;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;

@Path("analysis")
public interface AnalysisRestInterface {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("analyzers")
	public Collection<AnalyzerServiceInformation> getAnalyzers()
			throws IOException;

	@PUT
	@Path("projects/{project_id}")
	void triggerNewRun(@PathParam("project_id") String projectId);

	@PUT
	@Path("projects/{project_id}/abort")
	void abortCurrentRun(@PathParam("project_id") String projectId);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("analyzers/{id}")
	public AnalyzerServiceInformation getAnalyzer(
			@PathParam("id") String analyzerId);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("analyzers/{id}/configuration")
	public List<ConfigurationParameter<?>> getConfiguration(
			@PathParam("id") String analyzerId);

	@GET
	@Path("analyzers/{id}/enabled")
	public boolean isEnabled(@PathParam("id") String analyzerId);

	@PUT
	@Path("analyzers/{id}/enable")
	public void enable(@PathParam("id") String analyzerId);

	@PUT
	@Path("analyzers/{id}/disable")
	public void disable(@PathParam("id") String analyzerId);

}
