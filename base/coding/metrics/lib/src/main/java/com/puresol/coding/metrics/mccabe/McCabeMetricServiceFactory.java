package com.puresol.coding.metrics.mccabe;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.evaluation.api.AbstractEvaluatorFactory;
import com.puresol.coding.evaluation.api.Evaluator;
import com.puresol.coding.evaluation.api.QualityCharacteristic;
import com.puresol.utils.math.Parameter;

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
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return McCabeMetric.EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	public McCabeMetricEvaluator create(AnalysisRun analysisRun,
			HashIdFileTree path) {
		return new McCabeMetricEvaluator(analysisRun, path);
	}

	@Override
	public List<Class<? extends Evaluator>> getDependencies() {
		return new ArrayList<Class<? extends Evaluator>>();
	}

	@Override
	public Class<? extends Evaluator> getEvaluatorClass() {
		return McCabeMetricEvaluator.class;
	}

	@Override
	public List<Parameter<?>> getParameters() {
		return McCabeMetricEvaluatorParameter.ALL;
	}
}
