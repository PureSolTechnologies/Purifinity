package com.puresol.coding.metrics.codedepth;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.evaluation.api.AbstractEvaluatorFactory;
import com.puresol.coding.evaluation.api.Evaluator;
import com.puresol.coding.evaluation.api.QualityCharacteristic;

public class CodeDepthMetricEvaluatorFactory extends AbstractEvaluatorFactory {

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
	public CodeDepthMetricEvaluator create(AnalysisRun analysisRun,
			HashIdFileTree path) {
		return new CodeDepthMetricEvaluator(analysisRun, path);
	}

	@Override
	public List<Class<? extends Evaluator>> getDependencies() {
		return new ArrayList<Class<? extends Evaluator>>();
	}

	@Override
	public Class<? extends Evaluator> getEvaluatorClass() {
		return CodeDepthMetricEvaluator.class;
	}
}
