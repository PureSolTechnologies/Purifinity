package com.puresoltechnologies.purifinity.server.rest.impl;

import javax.inject.Inject;

import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.RunIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.RunMetrics;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.issues.EvaluatorIssuesStore;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.metrics.EvaluatorMetricsStore;
import com.puresoltechnologies.purifinity.server.rest.api.EvaluatorStoreRestInterface;

public class EvaluatorStoreRestService implements EvaluatorStoreRestInterface {

    @Inject
    private EvaluatorMetricsStore evaluatorMetricsStore;

    @Inject
    private EvaluatorIssuesStore evaluatorIssuesStore;

    @Override
    public RunMetrics getRunMetrics(String projectId, long runId, String evaluatorId) throws EvaluationStoreException {
	return evaluatorMetricsStore.readRunResults(projectId, runId, evaluatorId);
    }

    @Override
    public RunIssues getRunIssues(String projectId, long runId, String evaluatorId) throws EvaluationStoreException {
	return evaluatorIssuesStore.readRunResults(projectId, runId, evaluatorId);
    }
}
