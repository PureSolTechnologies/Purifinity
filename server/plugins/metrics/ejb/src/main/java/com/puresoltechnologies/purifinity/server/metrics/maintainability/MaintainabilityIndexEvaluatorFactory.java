package com.puresoltechnologies.purifinity.server.metrics.maintainability;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.maintainability.MaintainabilityIndexEvaluatorParameter;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluatorFactory;
import com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetricEvaluator;
import com.puresoltechnologies.purifinity.server.metrics.mccabe.McCabeMetricEvaluator;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCEvaluator;

public class MaintainabilityIndexEvaluatorFactory extends
		AbstractEvaluatorFactory {

	@Override
	public String getDescription() {
		return MaintainabilityIndexEvaluator.DESCRIPTION;
	}

	@Override
	public String getName() {
		return MaintainabilityIndexEvaluator.NAME;
	}

	@Override
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return MaintainabilityIndexEvaluator.EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	public MaintainabilityIndexEvaluator create() {
		return new MaintainabilityIndexEvaluator();
	}

	@Override
	public Set<Class<? extends Evaluator>> getDependencies() {
		Set<Class<? extends Evaluator>> dependencies = new HashSet<Class<? extends Evaluator>>();
		dependencies.add(SLOCEvaluator.class);
		dependencies.add(McCabeMetricEvaluator.class);
		dependencies.add(HalsteadMetricEvaluator.class);
		return dependencies;
	}

	@Override
	public Class<? extends Evaluator> getEvaluatorClass() {
		return MaintainabilityIndexEvaluator.class;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return MaintainabilityIndexEvaluatorParameter.ALL_FILE;
	}
}
