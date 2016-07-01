package com.puresoltechnologies.purifinity.server.core.api.evaluation;

import java.util.Collection;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;

public interface EvaluatorStore<FileResults, DirectoryResults, ProjectResults, RunResults, SingleResult> {

    public boolean hasFileResults(HashId hashId, CodeRangeType codeRangeType, String codeRangeName, String evaluatorId,
	    String parameterName) throws EvaluationStoreException;

    public boolean hasFileResults(HashId hashId, String evaluatorId) throws EvaluationStoreException;

    public boolean hasDirectoryResults(HashId hashId, String evaluatorId, String parameterName)
	    throws EvaluationStoreException;

    public boolean hasDirectoryResults(HashId hashId, String evaluatorId) throws EvaluationStoreException;

    public boolean hasProjectResults(String projectId, long runId, String evaluatorId, String parameterName)
	    throws EvaluationStoreException;

    public boolean hasProjectResults(String projectId, long runId, String evaluatorId) throws EvaluationStoreException;

    /**
     * This method stores the results for a single file.
     * 
     * @param analysisRun
     *            is the analysis.
     * @param codeAnalysis
     *            is the analysis which was used as basis for the evaluation.
     * @param results
     *            is the GenericFileResults object containing the results to be
     *            stored.
     * @throws EvaluationStoreException
     *             is thrown in case of issue in evaluation store.
     */
    public void storeFileResults(AnalysisRun analysisRun, AnalysisInformation analysisInformation, FileResults results)
	    throws EvaluationStoreException;

    /**
     * This method stores the results for a single directory.
     * 
     * @param analysisRun
     *            is the {@link AnalysisRun} object of the project the results
     *            belong to.
     * @param directory
     *            is the {@link AnalysisFileTree} object of the directory.
     * @param results
     *            is the GenericDirectoryResults object containing the results
     *            to be stored.
     * @throws EvaluationStoreException
     *             is thrown in case of issue in evaluation store.
     */
    public void storeDirectoryResults(AnalysisRun analysisRun, AnalysisFileTree directory, DirectoryResults results)
	    throws EvaluationStoreException;

    /**
     * This method stores the results for a whole project.
     * 
     * @param analysisRun
     *            is the {@link AnalysisRun} object of the project the results
     *            belong to.
     * @param directory
     *            is the {@link AnalysisFileTree} object of the directory.
     * @param results
     *            is the GenericProjectResults object containing the results to
     *            be stored.
     * @throws EvaluationStoreException
     *             is thrown in case of issue in evaluation store.
     */
    public void storeProjectResults(AnalysisRun analysisRun, AnalysisFileTree directory, ProjectResults results)
	    throws EvaluationStoreException;

    /**
     * This method reads the results for a single file.
     * 
     * @param hashId
     *            is the id of the file to be read.
     * @param evaluatorId
     *            is the id of the evaluator.
     * @return A FileResults object is returned containing the results.
     *         <code>null</code> is returned if no results are available.
     * @throws EvaluationStoreException
     *             is thrown in case of issue in evaluation store.
     */
    public FileResults readFileResults(HashId hashId, String evaluatorId) throws EvaluationStoreException;

    /**
     * This method reads the results for a single directory.
     * 
     * @param hashId
     *            specifies the directory.
     * @param evaluatorId
     *            is the id of the evaluator.
     * @return A DirectoryResults object is returned containing the results.
     *         <code>null</code> is returned if no results are available.
     * @throws EvaluationStoreException
     *             is thrown in case of issue in evaluation store.
     */
    public DirectoryResults readDirectoryResults(HashId hashId, String evaluatorId) throws EvaluationStoreException;

    public ProjectResults readProjectResults(String projectId, long runId, String evaluatorId)
	    throws EvaluationStoreException;

    public void storeFileResultsInBigTable(AnalysisRun analysisRun, AnalysisInformation analysisInformation,
	    FileResults results) throws EvaluationStoreException;

    public void storeDirectoryResultsInBigTable(AnalysisRun analysisRun, AnalysisFileTree directory,
	    DirectoryResults results) throws EvaluationStoreException;

    public void storeProjectResultsInBigTable(AnalysisRun analysisRun, AnalysisFileTree directory,
	    ProjectResults results) throws EvaluationStoreException;

    public Collection<SingleResult> readRunResults(String projectId, long runId) throws EvaluationStoreException;

    public RunResults readRunResults(String projectId, long runId, String evaluatorId) throws EvaluationStoreException;

}
