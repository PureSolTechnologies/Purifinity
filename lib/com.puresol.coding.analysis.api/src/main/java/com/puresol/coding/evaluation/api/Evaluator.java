package com.puresol.coding.evaluation.api;

import com.puresol.coding.analysis.api.Analysis;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.utils.HashId;

/**
 * This is the central interface for an evaluator. An evaluator is responsible
 * to evaluate files, directories (summary of all files in directory) and the
 * project as a whole.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Evaluator {

    /**
     * This method returns the evaluator meta data which describes the evaluator
     * in more detail.
     * 
     * @return
     */
    public EvaluatorMetaData getMetaData();

    public Object getFileEvaluation(Analysis analysis, AnalysisRun analysisRun,
	    HashId hashId);

    public Object getDirectoryEvaluation(Analysis analysis,
	    AnalysisRun analysisRun, HashId hashId);

    public Object getProjectEvaluation(Analysis analysis,
	    AnalysisRun analysisRun, HashId hashId);

}
