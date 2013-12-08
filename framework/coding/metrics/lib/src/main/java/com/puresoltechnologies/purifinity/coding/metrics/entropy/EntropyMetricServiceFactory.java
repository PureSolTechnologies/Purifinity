package com.puresoltechnologies.purifinity.coding.metrics.entropy;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.purifinity.coding.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresoltechnologies.purifinity.coding.evaluation.api.AbstractEvaluatorFactory;
import com.puresoltechnologies.purifinity.coding.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.coding.evaluation.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricEvaluator;

public class EntropyMetricServiceFactory extends AbstractEvaluatorFactory {

	@Override
	public String getDescription() {
		return EntropyMetric.DESCRIPTION;
	}

	@Override
	public String getName() {
		return EntropyMetric.NAME;
	}

	@Override
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return EntropyMetric.EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	public EntropyMetricEvaluator create(AnalysisRun analysisRun,
			HashIdFileTree path) {
		return new EntropyMetricEvaluator(analysisRun, path);
	}

	@Override
	public Set<Class<? extends Evaluator>> getDependencies() {
		Set<Class<? extends Evaluator>> dependencies = new HashSet<Class<? extends Evaluator>>();
		dependencies.add(HalsteadMetricEvaluator.class);
		return dependencies;
	}

	@Override
	public Class<? extends Evaluator> getEvaluatorClass() {
		return EntropyMetricEvaluator.class;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return EntropyMetricEvaluatorParameter.ALL;
	}
}
