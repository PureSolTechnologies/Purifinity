package com.puresoltechnologies.purifinity.framework.store.api;

import java.util.UUID;

import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricFileResults;

/**
 * This is a store for a single evaluator. THis stores can be changed depending
 * on deployment and usage scenario.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface EvaluatorStore {

	/**
	 * This method returns the store name which is used to be put into a
	 * database as value, to be used as table name or keyspace name depending on
	 * the database. The name should only consist of alpha numeric characters
	 * and underline. The maximum length is 48 for Cassandra.
	 * 
	 * @return
	 */
	public String getStoreName();

	/**
	 * Returns whether there are results for the specified file available or
	 * not.
	 * 
	 * @param hashId
	 *            specifies the file to be looked up.
	 * @return <code>true</code> is returned in case results are available.
	 *         <code>false</code> is returned otherwise.
	 * @throws EvaluationStoreException
	 */
	public boolean hasFileResults(HashId hashId)
			throws EvaluationStoreException;

	/**
	 * Returns whether there are results for the specified directory available
	 * or not.
	 * 
	 * @param hashId
	 *            specifies the directory to be looked up.
	 * @return <code>true</code> is returned in case results are available.
	 *         <code>false</code> is returned otherwise.
	 * @throws EvaluationStoreException
	 */
	public boolean hasDirectoryResults(HashId hashId)
			throws EvaluationStoreException;

	/**
	 * Returns whether there are results for the specified project available or
	 * not.
	 * 
	 * @param analysisRun
	 *            specifies the project to be looked up.
	 * @return <code>true</code> is returned in case results are available.
	 *         <code>false</code> is returned otherwise.
	 * @throws EvaluationStoreException
	 */
	public boolean hasProjectResults(UUID analysisRunUUID)
			throws EvaluationStoreException;

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
	 *            is the {@link MetricFileResults} object containing the results
	 *            to be stored.
	 * @throws EvaluationStoreException
	 */
	public void storeFileResults(CodeAnalysis codeAnalysis,
			Evaluator evaluator, MetricFileResults results)
			throws EvaluationStoreException;

	/**
	 * This method stores the results for a single directory.
	 * 
	 * @param hashId
	 *            is the id of the directory the results belong to.
	 * @param results
	 *            is the {@link MetricDirectoryResults} object containing the
	 *            results to be stored.
	 * @throws EvaluationStoreException
	 */
	public void storeDirectoryResults(AnalysisFileTree directory,
			Evaluator evaluator, MetricDirectoryResults results)
			throws EvaluationStoreException;

	/**
	 * This method stores the results for a whole project.
	 * 
	 * @param analysisRun
	 *            is the {@link AnalysisRun} object of the project the results
	 *            belong to.
	 * @param results
	 *            is the {@link MetricDirectoryResults} object containing the
	 *            results to be stored.
	 * @throws EvaluationStoreException
	 */
	public void storeProjectResults(UUID analysisRunUUID, Evaluator evaluator,
			AnalysisFileTree directory, MetricDirectoryResults results)
			throws EvaluationStoreException;

	/**
	 * This method reads the results for a single file.
	 * 
	 * @param hashId
	 *            is the id of the file to be read.
	 * @return A {@link MetricFileResults} object is returned containing the
	 *         results. <code>null</code> is returned if no results are
	 *         available.
	 * @throws EvaluationStoreException
	 */
	public MetricFileResults readFileResults(HashId hashId)
			throws EvaluationStoreException;

	/**
	 * This method reads the results for a single directory.
	 * 
	 * @param hashId
	 *            specifies the directory.
	 * @return A {@link MetricDirectoryResults} object is returned containing
	 *         the results. <code>null</code> is returned if no results are
	 *         available.
	 * @throws EvaluationStoreException
	 */
	public MetricDirectoryResults readDirectoryResults(HashId hashId)
			throws EvaluationStoreException;

	/**
	 * This method reads the results for a whole project.
	 * 
	 * @param analysisRun
	 *            is an {@link AnalysisRun} object specifying the project.
	 * @return A {@link MetricDirectoryResults} object is returned containing
	 *         the results. <code>null</code> is returned if no results are
	 *         available.
	 * @throws EvaluationStoreException
	 */
	public MetricDirectoryResults readProjectResults(UUID analysisRunUUID)
			throws EvaluationStoreException;
}
