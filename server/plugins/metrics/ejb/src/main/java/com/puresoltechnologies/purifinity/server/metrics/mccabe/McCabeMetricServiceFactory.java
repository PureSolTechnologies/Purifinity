package com.puresoltechnologies.purifinity.server.metrics.mccabe;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluatorFactory;

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
	public McCabeMetricEvaluator create() {
		return new McCabeMetricEvaluator();
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