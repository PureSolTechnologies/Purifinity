package com.puresoltechnologies.purifinity.server.core.impl.evaluation.metrics.normmaint;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.server.core.impl.evaluation.common.AbstractEvaluatorFactory;
import com.puresoltechnologies.purifinity.server.core.impl.evaluation.metrics.maintainability.MaintainabilityIndexEvaluator;

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
			AnalysisRun analysisRun, AnalysisFileTree path) {
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
