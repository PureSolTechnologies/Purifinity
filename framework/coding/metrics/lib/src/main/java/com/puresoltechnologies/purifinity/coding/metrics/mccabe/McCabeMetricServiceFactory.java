package com.puresoltechnologies.purifinity.coding.metrics.mccabe;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.HashIdFileTree;
import com.puresoltechnologies.purifinity.coding.evaluation.impl.AbstractEvaluatorFactory;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;

public class McCabeMetricServiceFactory extends AbstractEvaluatorFactory {

	@Override
	public String getDescription() {
		return McCabeMetric.DESCRIPTION;
	}

	@Override
	public String getName() {
		return McCabeMetric.NAME;
	}

	@Override
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return McCabeMetric.EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	public McCabeMetricEvaluator create(AnalysisRun analysisRun,
			HashIdFileTree path) {
		return new McCabeMetricEvaluator(analysisRun, path);
	}

	@Override
	public Set<Class<? extends Evaluator>> getDependencies() {
		return new HashSet<Class<? extends Evaluator>>();
	}

	@Override
	public Class<? extends Evaluator> getEvaluatorClass() {
		return McCabeMetricEvaluator.class;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return McCabeMetricEvaluatorParameter.ALL;
	}
}
