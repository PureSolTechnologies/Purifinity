package com.puresol.purifinity.coding.evaluation.api;

import com.puresol.commons.utils.HashId;
import com.puresol.purifinity.coding.analysis.api.AnalysisRun;

/**
 * This is a store for a single evaluator. THis stores can be changed depending
 * on deployment and usage scenario.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface EvaluatorStore {

	public boolean hasFileResults(HashId hashId);

	public boolean hasDirectoryResults(HashId hashId);

	public boolean hasProjectResults(AnalysisRun analysisRun);

	/**
	 * This method stores the results for a single file.
	 * 
	 * @param hashId
	 * @param results
	 */
	public void storeFileResults(HashId hashId, MetricFileResults results);

	/**
	 * This method stores the results for a single directory.
	 * 
	 * @param hashId
	 * @param results
	 */
	public void storeDirectoryResults(HashId hashId,
			MetricDirectoryResults results);

	/**
	 * This method stores the results for a whole project.
	 * 
	 * @param hashId
	 * @param results
	 */
	public void storeProjectResults(AnalysisRun analysisRun,
			MetricDirectoryResults results);

	/**
	 * This method reads the results for a single file.
	 * 
	 * @param hashId
	 * @param results
	 */
	public MetricFileResults readFileResults(HashId hashId);

	/**
	 * This method reads the results for a single directory.
	 * 
	 * @param hashId
	 * @param results
	 */
	public MetricDirectoryResults readDirectoryResults(HashId hashId);

	/**
	 * This method reads the results for a whole project.
	 * 
	 * @param hashId
	 * @param results
	 */
	public MetricDirectoryResults readProjectResults(AnalysisRun analysisRun);

}
