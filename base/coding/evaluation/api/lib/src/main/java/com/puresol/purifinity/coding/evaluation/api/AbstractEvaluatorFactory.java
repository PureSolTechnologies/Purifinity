package com.puresol.purifinity.coding.evaluation.api;

import com.puresol.purifinity.coding.analysis.api.AnalysisRun;

public abstract class AbstractEvaluatorFactory implements EvaluatorFactory {

	@Override
	public final Evaluator create(AnalysisRun analysisRun) {
		return create(analysisRun, analysisRun.getFileTree());
	}

}
