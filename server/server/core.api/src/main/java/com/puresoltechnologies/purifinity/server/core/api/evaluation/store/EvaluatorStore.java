package com.puresoltechnologies.purifinity.server.core.api.evaluation.store;

import java.util.UUID;

import com.puresoltechnologies.commons.os.HashId;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.FileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericDirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericFileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericProjectMetrics;

/**
 * This is a store for a single evaluator. THis stores can be changed depending
 * on deployment and usage scenario.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface EvaluatorStore {

	public boolean hasFileResults(HashId hashId, CodeRangeType codeRangeType,
			String codeRangeName, String evaluatorId, String parameterName)
			throws EvaluationStoreException;

	public boolean hasFileResults(HashId hashId, String evaluatorId)
			throws EvaluationStoreException;

	public boolean hasDirectoryResults(HashId hashId, String evaluatorId,
			String parameterName) throws EvaluationStoreException;

	public boolean hasDirectoryResults(HashId hashId, String evaluatorId)
			throws EvaluationStoreException;

	public boolean hasProjectResults(UUID projectUUID, UUID runUUID,
			String evaluatorId, String parameterName)
			throws EvaluationStoreException;

	public boolean hasProjectResults(UUID projectUUID, UUID runUUID,
			String evaluatorId) throws EvaluationStoreException;

	/**
	 * This method stores the results for a single file.
	 * 
	 * @param hashId
	 *            is the id of the file the results belong to.
	 * @param evaluator
	 *            is the evaluator which produced the results.
	 * @param codeAnalysis
	 *            is the analysis which was used as basis for the evaluation.
	 * @param results
	 *            is the {@link FileMetrics} object containing the results to be
	 *            stored.
	 * @throws EvaluationStoreException
	 */
	public void storeFileResults(AnalysisRun analysisRun,
			CodeAnalysis codeAnalysis, GenericFileMetrics metrics)
			throws EvaluationStoreException;

	/**
	 * This method stores the results for a single directory.
	 * 
	 * @param hashId
	 *            is the id of the directory the results belong to.
	 * @param results
	 *            is the {@link DirectoryMetrics} object containing the results
	 *            to be stored.
	 * @throws EvaluationStoreException
	 */
	public void storeDirectoryResults(AnalysisRun analysisRun,
			AnalysisFileTree directory, GenericDirectoryMetrics metrics)
			throws EvaluationStoreException;

	/**
	 * This method stores the results for a whole project.
	 * 
	 * @param analysisRun
	 *            is the {@link AnalysisRun} object of the project the results
	 *            belong to.
	 * @param results
	 *            is the {@link DirectoryMetrics} object containing the results
	 *            to be stored.
	 * @throws EvaluationStoreException
	 */
	public void storeProjectResults(AnalysisRun analysisRun,
			AnalysisFileTree directory, GenericProjectMetrics metrics)
			throws EvaluationStoreException;

	/**
	 * This method reads the results for a single file.
	 * 
	 * @param hashId
	 *            is the id of the file to be read.
	 * @return A {@link FileMetrics} object is returned containing the results.
	 *         <code>null</code> is returned if no results are available.
	 * @throws EvaluationStoreException
	 */
	public GenericFileMetrics readFileResults(HashId hashId, String evaluatorId)
			throws EvaluationStoreException;

	/**
	 * This method reads the results for a single directory.
	 * 
	 * @param hashId
	 *            specifies the directory.
	 * @return A {@link DirectoryMetrics} object is returned containing the
	 *         results. <code>null</code> is returned if no results are
	 *         available.
	 * @throws EvaluationStoreException
	 */
	public GenericDirectoryMetrics readDirectoryResults(HashId hashId,
			String evaluatorId) throws EvaluationStoreException;

	public GenericProjectMetrics readProjectResults(UUID projectUUID,
			UUID runUUID, String evaluatorId) throws EvaluationStoreException;

	public void storeMetricsInBigTable(AnalysisRun analysisRun,
			CodeAnalysis codeAnalysis, GenericFileMetrics metrics);

	void storeMetricsInBigTable(AnalysisRun analysisRun,
			AnalysisFileTree directory, GenericDirectoryMetrics metrics);

	void storeMetricsInBigTable(AnalysisRun analysisRun,
			AnalysisFileTree directory, GenericProjectMetrics metrics);
}
