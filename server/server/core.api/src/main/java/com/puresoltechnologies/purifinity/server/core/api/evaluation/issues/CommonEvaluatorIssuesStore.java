package com.puresoltechnologies.purifinity.server.core.api.evaluation.issues;

import java.util.Map;

import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.Classification;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.DirectoryIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.FileIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.ProjectIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.RunIssues;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorStore;

/**
 * This is a store for a single evaluator. This stores can be changed depending
 * on deployment and usage scenario.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface CommonEvaluatorIssuesStore
	extends EvaluatorStore<FileIssues, DirectoryIssues, ProjectIssues, RunIssues> {

    /**
     * This method returns a histogram over the severity with the actual number
     * of issues in each category.
     * 
     * @param projectId
     *            defines for which project the summary is to be returned.
     * @param runId
     *            defines for which run the summary is to be returned.
     * @return A {@link Map} is returned containing the defect number over
     *         {@link Severity}.
     * @throws EvaluationStoreException
     */
    public Map<Severity, Integer> getRunIssuesByServerity(String projectId, long runId)
	    throws EvaluationStoreException;

    /**
     * This method returns a histogram over the issue type with the actual
     * number of issues in each category.
     * 
     * @param projectId
     *            defines for which project the summary is to be returned.
     * @param runId
     *            defines for which run the summary is to be returned.
     * @return A {@link Map} is returned containing the defect number over
     *         {@link IssueType}.
     * @throws EvaluationStoreException
     */
    public Map<Classification, Integer> getRunIssuesByClassification(String projectId, long runId)
	    throws EvaluationStoreException;

    /**
     * This method returns for a defined run the severity distribution for
     * architecture issues.
     * 
     * @param projectId
     *            is the id of the project.
     * @param runId
     *            is the id of the run.
     * @return A {@link Map} is returned which holds for every {@link Severity}
     *         the number of issues found.
     * @throws EvaluationStoreException
     *             is thrown in case of EvaluatorStore issues.
     */
    public Map<Severity, Integer> getRunIssueArchitectureSeverities(String projectId, long runId)
	    throws EvaluationStoreException;

    /**
     * This method returns for a defined run the severity distribution for
     * design issues.
     * 
     * @param projectId
     *            is the id of the project.
     * @param runId
     *            is the id of the run.
     * @return A {@link Map} is returned which holds for every {@link Severity}
     *         the number of issues found.
     * @throws EvaluationStoreException
     *             is thrown in case of EvaluatorStore issues.
     */
    public Map<Severity, Integer> getRunIssueDesignSeverities(String projectId, long runId)
	    throws EvaluationStoreException;

    /**
     * This method returns for a defined run the severity distribution for
     * defects.
     * 
     * @param projectId
     *            is the id of the project.
     * @param runId
     *            is the id of the run.
     * @return A {@link Map} is returned which holds for every {@link Severity}
     *         the number of issues found.
     * @throws EvaluationStoreException
     *             is thrown in case of EvaluatorStore issues.
     */
    public Map<Severity, Integer> getRunIssueDefectSeverities(String projectId, long runId)
	    throws EvaluationStoreException;

    /**
     * This method returns for a defined run the severity distribution for style
     * issues.
     * 
     * @param projectId
     *            is the id of the project.
     * @param runId
     *            is the id of the run.
     * @return A {@link Map} is returned which holds for every {@link Severity}
     *         the number of issues found.
     * @throws EvaluationStoreException
     *             is thrown in case of EvaluatorStore issues.
     */
    public Map<Severity, Integer> getRunIssueStyleSeverities(String projectId, long runId)
	    throws EvaluationStoreException;
}
