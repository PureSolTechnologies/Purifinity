package com.puresol.coding.evaluation.api;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.utils.HashId;

/**
 * This is a store for a single evaluator. THis stores can be changed depending
 * on deployment and usage scenario.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface EvaluatorStore {

    public void storeFileResults(HashId hashId, FileResults results);

    public void storeDirectoryResults(HashId hashId, DirectoryResults results);

    public void storeProjectResults(AnalysisRun analysisRun,
	    ProjectResults results);

    public FileResults readFileResults(HashId hashId);

    public DirectoryResults readDirectoryResults(HashId hashId);

    public ProjectResults readProjectResults(AnalysisRun analysisRun);

}
