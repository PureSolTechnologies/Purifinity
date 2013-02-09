package com.puresol.coding.analysis.api.storage;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.evaluation.DirectoryResults;
import com.puresol.coding.analysis.api.evaluation.FileResults;
import com.puresol.coding.analysis.api.evaluation.ProjectResults;
import com.puresol.utils.HashId;

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
	public void storeFileResults(HashId hashId, FileResults results);

	/**
	 * This method stores the results for a single directory.
	 * 
	 * @param hashId
	 * @param results
	 */
	public void storeDirectoryResults(HashId hashId, DirectoryResults results);

	/**
	 * This method stores the results for a whole project.
	 * 
	 * @param hashId
	 * @param results
	 */
	public void storeProjectResults(AnalysisRun analysisRun,
			ProjectResults results);

	/**
	 * This method reads the results for a single file.
	 * 
	 * @param hashId
	 * @param results
	 */
	public FileResults readFileResults(HashId hashId);

	/**
	 * This method reads the results for a single directory.
	 * 
	 * @param hashId
	 * @param results
	 */
	public DirectoryResults readDirectoryResults(HashId hashId);

	/**
	 * This method reads the results for a whole project.
	 * 
	 * @param hashId
	 * @param results
	 */
	public ProjectResults readProjectResults(AnalysisRun analysisRun);

}
