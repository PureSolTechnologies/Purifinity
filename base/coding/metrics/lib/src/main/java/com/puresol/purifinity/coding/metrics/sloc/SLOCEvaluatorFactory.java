package com.puresol.purifinity.coding.metrics.sloc;

import java.util.HashSet;
import java.util.Set;

import com.puresol.commons.math.Parameter;
import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresol.purifinity.coding.evaluation.api.AbstractEvaluatorFactory;
import com.puresol.purifinity.coding.evaluation.api.Evaluator;
import com.puresol.purifinity.coding.evaluation.iso9126.QualityCharacteristic;

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
	public SLOCEvaluator create(AnalysisRun analysisRun, HashIdFileTree path) {
		return new SLOCEvaluator(analysisRun, path);
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
