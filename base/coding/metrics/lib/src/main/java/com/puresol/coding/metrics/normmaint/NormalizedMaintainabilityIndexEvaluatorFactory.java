package com.puresol.coding.metrics.normmaint;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.evaluation.Evaluator;
import com.puresol.coding.analysis.api.evaluation.EvaluatorFactory;
import com.puresol.coding.analysis.api.quality.QualityCharacteristic;
import com.puresol.coding.metrics.maintainability.MaintainabilityIndexEvaluator;

public class NormalizedMaintainabilityIndexEvaluatorFactory implements
		EvaluatorFactory {

	@Override
	public String getDescription() {
		return NormalizedMaintainabilityIndexEvaluator.DESCRIPTION;
	}

	@Override
	public String getName() {
		return NormalizedMaintainabilityIndexEvaluator.NAME;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return NormalizedMaintainabilityIndexEvaluator.EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	public NormalizedMaintainabilityIndexEvaluator create(
			AnalysisRun analysisRun) {
		return new NormalizedMaintainabilityIndexEvaluator(analysisRun);
	}

	@Override
	public List<Class<? extends Evaluator>> getDependencies() {
		List<Class<? extends Evaluator>> dependencies = new ArrayList<Class<? extends Evaluator>>();
		dependencies.add(MaintainabilityIndexEvaluator.class);
		return dependencies;
	}
}
