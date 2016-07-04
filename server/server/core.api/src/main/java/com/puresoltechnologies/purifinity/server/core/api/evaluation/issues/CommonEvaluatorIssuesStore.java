package com.puresoltechnologies.purifinity.server.core.api.evaluation.issues;

import java.util.Collection;
import java.util.Map;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.Classification;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.DirectoryIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.FileIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.ProjectIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.RunIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.SingleIssue;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorStore;

/**
 * This is a store for a single evaluator. This stores can be changed depending
 * on deployment and usage scenario.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface CommonEvaluatorIssuesStore
	extends EvaluatorStore<FileIssues, DirectoryIssues, ProjectIssues, RunIssues, SingleIssue> {

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
    public Map<Severity, Integer> getRunIssuesByServerity(String projectId, long runId) throws EvaluationStoreException;

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
     * classified issues.
     * 
     * @param projectId
     *            is the id of the project.
     * @param runId
     *            is the id of the run.
     * @param classification
     *            is the issue classification to get the severity histogram for.
     * @return A {@link Map} is returned which holds for every {@link Severity}
     *         the number of issues found.
     * @throws EvaluationStoreException
     *             is thrown in case of EvaluatorStore issues.
     */
    public Map<Severity, Integer> getRunIssueSeverities(String projectId, long runId, Classification classification)
	    throws EvaluationStoreException;

    /**
     * This method returns for a defined run the parameter id distribution for
     * run issues.
     * 
     * @param projectId
     *            is the id of the project.
     * @param runId
     *            is the id of the run.
     * @param classification
     *            is the issue classification to get the parameter histogram
     *            for.
     * @return A {@link Map} is returned which holds for every issue id the
     *         number of issues found.
     * @throws EvaluationStoreException
     *             is thrown in case of EvaluatorStore issues.
     */
    public Map<String, Integer> getRunIssueClassificationParameters(String projectId, long runId,
	    Classification classification) throws EvaluationStoreException;

    public Collection<SingleIssue> readRunResults(String projectId, long runId, Classification classification)
	    throws EvaluationStoreException;

    public Collection<SingleIssue> getFileIssues(String projectId, long runId, HashId hashId)
	    throws EvaluationStoreException;
}
