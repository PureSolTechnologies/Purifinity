package com.puresoltechnologies.purifinity.framework.evaluation.metrics.codedepth;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.HashIdFileTree;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.AbstractEvaluatorFactory;

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
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return CodeDepthMetric.EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	public CodeDepthMetricEvaluator create(AnalysisRun analysisRun,
			HashIdFileTree path) {
		return new CodeDepthMetricEvaluator(analysisRun, path);
	}

	@Override
	public Set<Class<? extends Evaluator>> getDependencies() {
		return new HashSet<Class<? extends Evaluator>>();
	}

	@Override
	public Class<? extends Evaluator> getEvaluatorClass() {
		return CodeDepthMetricEvaluator.class;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return CodeDepthMetricEvaluatorParameter.ALL;
	}
}
