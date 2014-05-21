package com.puresoltechnologies.purifinity.server.core.impl.evaluation.common;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;

public abstract class AbstractEvaluatorFactory implements EvaluatorFactory {

	@Override
	public final Evaluator create(AnalysisRun analysisRun) {
		return create(analysisRun, analysisRun.getFileTree());
	}

}
