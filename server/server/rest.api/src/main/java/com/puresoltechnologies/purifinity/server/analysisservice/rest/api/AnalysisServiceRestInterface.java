package com.puresoltechnologies.purifinity.server.analysisservice.rest.api;

import java.io.IOException;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;

@Path("analysisservice")
public interface AnalysisServiceRestInterface {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("analyzers")
    public Collection<AnalyzerServiceInformation> getAnalyzers()
	    throws IOException;

    @PUT
    @Path("projects/{project_id}")
    void triggerNewRun(@PathParam("project_id") String projectId);

}
