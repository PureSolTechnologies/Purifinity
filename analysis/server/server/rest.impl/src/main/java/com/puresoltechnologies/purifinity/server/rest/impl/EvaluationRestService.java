package com.puresoltechnologies.purifinity.server.rest.impl;

import java.io.IOException;
import java.util.Collection;

import javax.inject.Inject;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluationService;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;
import com.puresoltechnologies.purifinity.server.rest.api.EvaluationRestInterface;

public class EvaluationRestService implements EvaluationRestInterface {

    @Inject
    private EvaluationService evaluationService;

    @Override
    public Collection<EvaluatorServiceInformation> getEvaluators() throws IOException {
	return evaluationService.getEvaluators();
    }

    @Override
    public EvaluatorServiceInformation getEvaluator(String evaluatorId) {
	return evaluationService.getEvaluator(evaluatorId);
    }

    @Override
    public ConfigurationParameter<?>[] getConfiguration(String evaluatorId) {
	return evaluationService.getConfiguration(evaluatorId);
    }

    @Override
    public boolean isEnabled(String evaluatorId) {
	return evaluationService.isEnabled(evaluatorId);
    }

    @Override
    public void enable(String evaluatorId) {
	evaluationService.setActive(evaluatorId, true);
    }

    @Override
    public void disable(String evaluatorId) {
	evaluationService.setActive(evaluatorId, false);
    }
}
