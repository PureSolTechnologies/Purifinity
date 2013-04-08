package com.puresol.coding.metrics.halstead;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.evaluation.api.AbstractEvaluatorFactory;
import com.puresol.coding.evaluation.api.Evaluator;
import com.puresol.coding.evaluation.api.QualityCharacteristic;
import com.puresol.utils.math.Parameter;

public class HalsteadMetricEvaluatorFactory extends AbstractEvaluatorFactory {

	@Override
	public String getDescription() {
		return HalsteadMetric.DESCRIPTION;
	}

	@Override
	public String getName() {
		return HalsteadMetric.NAME;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return HalsteadMetric.EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	public HalsteadMetricEvaluator create(AnalysisRun analysisRun,
			HashIdFileTree path) {
		return new HalsteadMetricEvaluator(analysisRun, path);
	}

	@Override
	public Set<Class<? extends Evaluator>> getDependencies() {
		return new HashSet<Class<? extends Evaluator>>();
	}

	@Override
	public Class<? extends Evaluator> getEvaluatorClass() {
		return HalsteadMetricEvaluator.class;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return HalsteadMetricEvaluatorParameter.ALL;
	}
}
