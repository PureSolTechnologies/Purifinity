package com.puresoltechnologies.purifinity.server.rest.impl;

import java.util.Map;

import javax.inject.Inject;

import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.Classification;
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

    @Override
    public Map<Severity, Integer> getRunIssueSummaryByServerity(String projectId, long runId)
	    throws EvaluationStoreException {
	return evaluatorIssuesStore.getRunIssueSummaryByServerity(projectId, runId);
    }

    @Override
    public Map<Classification, Integer> getRunIssueSummaryByClassification(String projectId, long runId)
	    throws EvaluationStoreException {
	return evaluatorIssuesStore.getRunIssueSummaryByClassification(projectId, runId);
    }

    @Override
    public Map<Severity, Integer> getRunIssueArchitectureServerities(String projectId, long runId)
	    throws EvaluationStoreException {
	return evaluatorIssuesStore.getRunIssueArchitectureSeverities(projectId, runId);
    }

    @Override
    public Map<Severity, Integer> getRunIssueDesignServerities(String projectId, long runId)
	    throws EvaluationStoreException {
	return evaluatorIssuesStore.getRunIssueDesignSeverities(projectId, runId);
    }

    @Override
    public Map<Severity, Integer> getRunIssueDefectServerities(String projectId, long runId)
	    throws EvaluationStoreException {
	return evaluatorIssuesStore.getRunIssueDefectSeverities(projectId, runId);
    }

    @Override
    public Map<Severity, Integer> getRunIssueStyleServerities(String projectId, long runId)
	    throws EvaluationStoreException {
	return evaluatorIssuesStore.getRunIssueStyleSeverities(projectId, runId);
    }
}
