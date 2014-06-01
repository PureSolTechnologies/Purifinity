package com.puresoltechnologies.purifinity.server.core.impl.evaluation.metrics.halstead;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.halstead.HalsteadMetricEvaluatorParameter;
import com.puresoltechnologies.purifinity.server.core.impl.evaluation.common.AbstractEvaluatorFactory;

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
			AnalysisFileTree path) {
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