package com.puresoltechnologies.purifinity.server.core.api.evaluation;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.FileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericDirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericFileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericProjectMetrics;

public interface EvaluatorStore<FileResults, DirectoryResults, GenericFileResults, GenericDirectoryResults, GenericProjectResults, GenericRunResults> {

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
     * @param metrics
     *            is the {@link GenericFileMetrics} object containing the
     *            results to be stored.
     * @throws EvaluationStoreException
     *             is thrown in case of issue in evaluation store.
     */
    public void storeFileResults(AnalysisRun analysisRun, CodeAnalysis codeAnalysis, GenericFileResults metrics)
	    throws EvaluationStoreException;

    /**
     * This method stores the results for a single directory.
     * 
     * @param analysisRun
     *            is the {@link AnalysisRun} object of the project the results
     *            belong to.
     * @param directory
     *            is the {@link AnalysisFileTree} object of the directory.
     * @param metrics
     *            is the {@link GenericDirectoryMetrics} object containing the
     *            results to be stored.
     * @throws EvaluationStoreException
     *             is thrown in case of issue in evaluation store.
     */
    public void storeDirectoryResults(AnalysisRun analysisRun, AnalysisFileTree directory,
	    GenericDirectoryResults metrics) throws EvaluationStoreException;

    /**
     * This method stores the results for a whole project.
     * 
     * @param analysisRun
     *            is the {@link AnalysisRun} object of the project the results
     *            belong to.
     * @param directory
     *            is the {@link AnalysisFileTree} object of the directory.
     * @param metrics
     *            is the {@link GenericProjectMetrics} object containing the
     *            results to be stored.
     * @throws EvaluationStoreException
     *             is thrown in case of issue in evaluation store.
     */
    public void storeProjectResults(AnalysisRun analysisRun, AnalysisFileTree directory, GenericProjectResults metrics)
	    throws EvaluationStoreException;

    /**
     * This method reads the results for a single file.
     * 
     * @param hashId
     *            is the id of the file to be read.
     * @param evaluatorId
     *            is the id of the evaluator.
     * @return A {@link FileMetrics} object is returned containing the results.
     *         <code>null</code> is returned if no results are available.
     * @throws EvaluationStoreException
     *             is thrown in case of issue in evaluation store.
     */
    public GenericFileResults readFileResults(HashId hashId, String evaluatorId) throws EvaluationStoreException;

    /**
     * This method reads the results for a single directory.
     * 
     * @param hashId
     *            specifies the directory.
     * @param evaluatorId
     *            is the id of the evaluator.
     * @return A {@link DirectoryMetrics} object is returned containing the
     *         results. <code>null</code> is returned if no results are
     *         available.
     * @throws EvaluationStoreException
     *             is thrown in case of issue in evaluation store.
     */
    public GenericDirectoryResults readDirectoryResults(HashId hashId, String evaluatorId)
	    throws EvaluationStoreException;

    public GenericProjectResults readProjectResults(String projectId, long runId, String evaluatorId)
	    throws EvaluationStoreException;

    public void storeFileMetricsInBigTable(AnalysisRun analysisRun, CodeAnalysis codeAnalysis,
	    GenericFileResults metrics) throws EvaluationStoreException;

    public void storeDirectoryMetricsInBigTable(AnalysisRun analysisRun, AnalysisFileTree directory,
	    GenericDirectoryResults metrics) throws EvaluationStoreException;

    public void storeProjectMetricsInBigTable(AnalysisRun analysisRun, AnalysisFileTree directory,
	    GenericProjectResults metrics) throws EvaluationStoreException;

    public GenericRunResults readRunMetrics(String projectId, long runId, String evaluatorId)
	    throws EvaluationStoreException;

}
