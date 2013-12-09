package com.puresoltechnologies.purifinity.coding.metrics.normmaint;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.HashIdFileTree;
import com.puresoltechnologies.purifinity.coding.evaluation.impl.AbstractEvaluatorFactory;
import com.puresoltechnologies.purifinity.coding.metrics.maintainability.MaintainabilityIndexEvaluator;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;

public class NormalizedMaintainabilityIndexEvaluatorFactory extends
		AbstractEvaluatorFactory {

	@Override
	public String getDescription() {
		return NormalizedMaintainabilityIndexEvaluator.DESCRIPTION;
	}

	@Override
	public String getName() {
		return NormalizedMaintainabilityIndexEvaluator.NAME;
	}

	@Override
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return NormalizedMaintainabilityIndexEvaluator.EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	public NormalizedMaintainabilityIndexEvaluator create(
			AnalysisRun analysisRun, HashIdFileTree path) {
		return new NormalizedMaintainabilityIndexEvaluator(analysisRun, path);
	}

	@Override
	public Set<Class<? extends Evaluator>> getDependencies() {
		Set<Class<? extends Evaluator>> dependencies = new HashSet<Class<? extends Evaluator>>();
		dependencies.add(MaintainabilityIndexEvaluator.class);
		return dependencies;
	}

	@Override
	public Class<? extends Evaluator> getEvaluatorClass() {
		return NormalizedMaintainabilityIndexEvaluator.class;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return NormalizedMaintainabilityIndexEvaluatorParameter.ALL;
	}
}
