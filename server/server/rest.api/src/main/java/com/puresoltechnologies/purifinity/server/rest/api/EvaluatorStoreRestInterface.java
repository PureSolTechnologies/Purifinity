package com.puresoltechnologies.purifinity.server.rest.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericRunMetrics;

@Path("evaluatorstore")
public interface EvaluatorStoreRestInterface {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("metrics/{project_id}/{run_id}/{evaluator_id}")
    public GenericRunMetrics getRunMetrics(
	    @PathParam("project_id") String projectId,
	    @PathParam("run_id") long runId,
	    @PathParam("evaluator_id") String evaluatorId)
	    throws EvaluationStoreException;

}
