package com.puresol.coding.evaluation.api;

import com.puresol.coding.analysis.api.AnalysisRun;

public abstract class AbstractEvaluatorFactory implements EvaluatorFactory {

    @Override
    public final Evaluator create(AnalysisRun analysisRun) {
	return create(analysisRun, analysisRun.getFileTree());
    }

}
