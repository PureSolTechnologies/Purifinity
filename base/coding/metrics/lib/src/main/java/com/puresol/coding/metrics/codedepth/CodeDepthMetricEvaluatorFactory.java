package com.puresol.coding.metrics.codedepth;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.evaluation.api.Evaluator;
import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.evaluation.api.QualityCharacteristic;

public class CodeDepthMetricEvaluatorFactory implements EvaluatorFactory {

	@Override
	public String getDescription() {
		return CodeDepthMetric.DESCRIPTION;
	}

	@Override
	public String getName() {
		return CodeDepthMetric.NAME;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return CodeDepthMetric.EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	public CodeDepthEvaluator create(AnalysisRun analysisRun) {
		return new CodeDepthEvaluator(analysisRun);
	}

	@Override
	public List<Class<? extends Evaluator>> getDependencies() {
		return new ArrayList<Class<? extends Evaluator>>();
	}
}
