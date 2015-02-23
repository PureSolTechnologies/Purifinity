package com.puresoltechnologies.purifinity.server.rest.api;

import java.io.IOException;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;

@Path("evaluation")
public interface EvaluationRestInterface {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("evaluators")
    public Collection<EvaluatorServiceInformation> getEvaluators()
	    throws IOException;

}
