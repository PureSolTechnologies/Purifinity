package com.puresol.purifinity.coding.metrics.halstead;

import java.util.HashSet;
import java.util.Set;

import com.puresol.commons.utils.math.Parameter;
import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresol.purifinity.coding.evaluation.api.AbstractEvaluatorFactory;
import com.puresol.purifinity.coding.evaluation.api.Evaluator;
import com.puresol.purifinity.coding.evaluation.iso9126.QualityCharacteristic;

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
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
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
