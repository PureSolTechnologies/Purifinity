package com.puresoltechnologies.purifinity.server.rest.impl;

import javax.inject.Inject;

import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericRunMetrics;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.store.EvaluatorStoreService;
import com.puresoltechnologies.purifinity.server.rest.api.EvaluatorStoreRestInterface;

public class EvaluatorStoreRestService implements EvaluatorStoreRestInterface {

    @Inject
    private EvaluatorStoreService evaluatorStore;

    @Override
    public GenericRunMetrics getRunMetrics(String projectId, long runId,
	    String evaluatorId) throws EvaluationStoreException {
	return evaluatorStore.readRunMetrics(projectId, runId, evaluatorId);
    }
}