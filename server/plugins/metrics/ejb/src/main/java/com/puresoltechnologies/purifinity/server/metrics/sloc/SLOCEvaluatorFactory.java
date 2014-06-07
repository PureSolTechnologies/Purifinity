package com.puresoltechnologies.purifinity.server.metrics.sloc;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.sloc.SLOCEvaluatorParameter;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluatorFactory;

public class SLOCEvaluatorFactory extends AbstractEvaluatorFactory {

	@Override
	public String getDescription() {
		return SLOCMetricCalculator.DESCRIPTION;
	}

	@Override
	public String getName() {
		return SLOCMetricCalculator.NAME;
	}

	@Override
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return SLOCMetricCalculator.EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	public SLOCEvaluator create() {
		return new SLOCEvaluator();
	}

	@Override
	public Set<Class<? extends Evaluator>> getDependencies() {
		return new HashSet<Class<? extends Evaluator>>();
	}

	@Override
	public Class<? extends Evaluator> getEvaluatorClass() {
		return SLOCEvaluator.class;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return SLOCEvaluatorParameter.ALL;
	}
}
