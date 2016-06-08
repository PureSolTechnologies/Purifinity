package com.puresoltechnologies.purifinity.server.rest.impl;

import javax.inject.Inject;

import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.RunMetrics;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.metrics.EvaluatorMetricsStore;
import com.puresoltechnologies.purifinity.server.rest.api.EvaluatorStoreRestInterface;

public class EvaluatorStoreRestService implements EvaluatorStoreRestInterface {

    @Inject
    private EvaluatorMetricsStore evaluatorStore;

    @Override
    public RunMetrics getRunMetrics(String projectId, long runId, String evaluatorId) throws EvaluationStoreException {
	return evaluatorStore.readRunResults(projectId, runId, evaluatorId);
    }
}
