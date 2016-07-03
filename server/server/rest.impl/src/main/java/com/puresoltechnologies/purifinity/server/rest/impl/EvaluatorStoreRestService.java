package com.puresoltechnologies.purifinity.server.rest.impl;

import java.util.Collection;
import java.util.Map;

import javax.inject.Inject;

import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.Classification;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.RunIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.SingleIssue;
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
    public Collection<SingleIssue> getRunIssues(String projectId, long runId) throws EvaluationStoreException {
	return evaluatorIssuesStore.readRunResults(projectId, runId);
    }

    @Override
    public Collection<SingleIssue> getRunArchitectureIssues(String projectId, long runId)
	    throws EvaluationStoreException {
	return evaluatorIssuesStore.readRunResults(projectId, runId, Classification.ARCHITECTURE_ISSUE);
    }

    @Override
    public Collection<SingleIssue> getRunDesignIssues(String projectId, long runId) throws EvaluationStoreException {
	return evaluatorIssuesStore.readRunResults(projectId, runId, Classification.DESIGN_ISSUE);
    }

    @Override
    public Collection<SingleIssue> getRunDefectsIssues(String projectId, long runId) throws EvaluationStoreException {
	return evaluatorIssuesStore.readRunResults(projectId, runId, Classification.DEFECT);
    }

    @Override
    public Collection<SingleIssue> getRunStyleIssues(String projectId, long runId) throws EvaluationStoreException {
	return evaluatorIssuesStore.readRunResults(projectId, runId, Classification.STYLE_ISSUE);
    }

    @Override
    public RunIssues getRunIssues(String projectId, long runId, String evaluatorId) throws EvaluationStoreException {
	return evaluatorIssuesStore.readRunResults(projectId, runId, evaluatorId);
    }

    @Override
    public Map<Severity, Integer> getRunIssuesByServerity(String projectId, long runId)
	    throws EvaluationStoreException {
	return evaluatorIssuesStore.getRunIssuesByServerity(projectId, runId);
    }

    @Override
    public Map<Classification, Integer> getRunIssuesByClassification(String projectId, long runId)
	    throws EvaluationStoreException {
	return evaluatorIssuesStore.getRunIssuesByClassification(projectId, runId);
    }

    @Override
    public Map<Severity, Integer> getRunIssueArchitectureServerities(String projectId, long runId)
	    throws EvaluationStoreException {
	return evaluatorIssuesStore.getRunIssueSeverities(projectId, runId, Classification.ARCHITECTURE_ISSUE);
    }

    @Override
    public Map<String, Integer> getRunArchitectureIssuesByParameter(String projectId, long runId)
	    throws EvaluationStoreException {
	return evaluatorIssuesStore.getRunIssueClassificationParameters(projectId, runId,
		Classification.ARCHITECTURE_ISSUE);
    }

    @Override
    public Map<Severity, Integer> getRunIssueDesignServerities(String projectId, long runId)
	    throws EvaluationStoreException {
	return evaluatorIssuesStore.getRunIssueSeverities(projectId, runId, Classification.DESIGN_ISSUE);
    }

    @Override
    public Map<String, Integer> getRunDesignIssuesByParameter(String projectId, long runId)
	    throws EvaluationStoreException {
	return evaluatorIssuesStore.getRunIssueClassificationParameters(projectId, runId, Classification.DESIGN_ISSUE);
    }

    @Override
    public Map<Severity, Integer> getRunIssueDefectServerities(String projectId, long runId)
	    throws EvaluationStoreException {
	return evaluatorIssuesStore.getRunIssueSeverities(projectId, runId, Classification.DEFECT);
    }

    @Override
    public Map<String, Integer> getRunDefectIssuesByParameter(String projectId, long runId)
	    throws EvaluationStoreException {
	return evaluatorIssuesStore.getRunIssueClassificationParameters(projectId, runId, Classification.DEFECT);
    }

    @Override
    public Map<Severity, Integer> getRunIssueStyleServerities(String projectId, long runId)
	    throws EvaluationStoreException {
	return evaluatorIssuesStore.getRunIssueSeverities(projectId, runId, Classification.STYLE_ISSUE);
    }

    @Override
    public Map<String, Integer> getRunStyleIssuesByParameter(String projectId, long runId)
	    throws EvaluationStoreException {
	return evaluatorIssuesStore.getRunIssueClassificationParameters(projectId, runId, Classification.STYLE_ISSUE);
    }
}
