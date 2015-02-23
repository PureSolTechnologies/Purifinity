package com.puresoltechnologies.purifinity.server.rest.impl;

import java.io.IOException;
import java.util.Collection;

import javax.inject.Inject;

import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluationService;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;
import com.puresoltechnologies.purifinity.server.rest.api.EvaluationRestInterface;

public class EvaluationRestService implements EvaluationRestInterface {

    @Inject
    private EvaluationService evaluationService;

    @Override
    public Collection<EvaluatorServiceInformation> getEvaluators()
	    throws IOException {
	return evaluationService.getEvaluators();
    }
}
