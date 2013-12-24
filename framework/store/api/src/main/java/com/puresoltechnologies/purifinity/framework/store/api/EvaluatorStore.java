package com.puresoltechnologies.purifinity.framework.store.api;

import com.puresoltechnologies.commons.misc.HashId;
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
	 * Returns whether there are results for the specified file available or
	 * not.
	 * 
	 * @param hashId
	 *            specifies the file to be looked up.
	 * @return <code>true</code> is returned in case results are available.
	 *         <code>false</code> is returned otherwise.
	 */
	public boolean hasFileResults(HashId hashId);

	/**
	 * Returns whether there are results for the specified directory available
	 * or not.
	 * 
	 * @param hashId
	 *            specifies the directory to be looked up.
	 * @return <code>true</code> is returned in case results are available.
	 *         <code>false</code> is returned otherwise.
	 */
	public boolean hasDirectoryResults(HashId hashId);

	/**
	 * Returns whether there are results for the specified project available or
	 * not.
	 * 
	 * @param analysisRun
	 *            specifies the project to be looked up.
	 * @return <code>true</code> is returned in case results are available.
	 *         <code>false</code> is returned otherwise.
	 */
	public boolean hasProjectResults(AnalysisRun analysisRun);

	/**
	 * This method stores the results for a single file.
	 * 
	 * @param hashId
	 *            is the id of the file the results belong to.
	 * @param results
	 *            is the {@link MetricFileResults} object containing the results
	 *            to be stored.
	 */
	public void storeFileResults(HashId hashId, MetricFileResults results);

	/**
	 * This method stores the results for a single directory.
	 * 
	 * @param hashId
	 *            is the id of the directory the results belong to.
	 * @param results
	 *            is the {@link MetricDirectoryResults} object containing the
	 *            results to be stored.
	 */
	public void storeDirectoryResults(HashId hashId,
			MetricDirectoryResults results);

	/**
	 * This method stores the results for a whole project.
	 * 
	 * @param analysisRun
	 *            is the {@link AnalysisRun} object of the project the results
	 *            belong to.
	 * @param results
	 *            is the {@link MetricDirectoryResults} object containing the
	 *            results to be stored.
	 */
	public void storeProjectResults(AnalysisRun analysisRun,
			MetricDirectoryResults results);

	/**
	 * This method reads the results for a single file.
	 * 
	 * @param hashId
	 *            is the id of the file to be read.
	 * @return A {@link MetricFileResults} object is returned containing the
	 *         results. <code>null</code> is returned if no results are
	 *         available.
	 */
	public MetricFileResults readFileResults(HashId hashId);

	/**
	 * This method reads the results for a single directory.
	 * 
	 * @param hashId
	 *            specifies the directory.
	 * @return A {@link MetricDirectoryResults} object is returned containing
	 *         the results. <code>null</code> is returned if no results are
	 *         available.
	 */
	public MetricDirectoryResults readDirectoryResults(HashId hashId);

	/**
	 * This method reads the results for a whole project.
	 * 
	 * @param analysisRun
	 *            is an {@link AnalysisRun} object specifying the project.
	 * @return A {@link MetricDirectoryResults} object is returned containing
	 *         the results. <code>null</code> is returned if no results are
	 *         available.
	 */
	public MetricDirectoryResults readProjectResults(AnalysisRun analysisRun);

}
