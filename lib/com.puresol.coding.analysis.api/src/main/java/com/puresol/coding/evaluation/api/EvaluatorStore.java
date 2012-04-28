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
public interface EvaluatorStore<F extends FileResults, D extends DirectoryResults, P extends ProjectResults> {

    public void storeFileResults(HashId hashId, F results);

    public void storeDirectoryResults(HashId hashId, D results);

    public void storeProjectResults(AnalysisRun analysisRun, P results);

    public F readFileResults(HashId hashId);

    public D readDirectoryResults(HashId hashId);

    public P readProjectResults(AnalysisRun analysisRun);

}
