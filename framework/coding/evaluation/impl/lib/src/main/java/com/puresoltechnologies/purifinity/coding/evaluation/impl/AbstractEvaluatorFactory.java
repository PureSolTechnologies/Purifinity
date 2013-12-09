package com.puresoltechnologies.purifinity.coding.evaluation.impl;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;

public abstract class AbstractEvaluatorFactory implements EvaluatorFactory {

	@Override
	public final Evaluator create(AnalysisRun analysisRun) {
		return create(analysisRun, analysisRun.getFileTree());
	}

}
