package com.puresol.coding.metrics.normmaint;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.evaluation.api.Evaluator;
import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.evaluation.api.EvaluatorResults;
import com.puresol.coding.metrics.halstead.HalsteadMetricEvaluator;
import com.puresol.coding.metrics.mccabe.McCabeMetricEvaluator;
import com.puresol.coding.metrics.sloc.SLOCEvaluator;
import com.puresol.coding.quality.api.QualityCharacteristic;

public class NormalizedMaintainabilityIndexEvaluatorFactory implements
	EvaluatorFactory {

    @Override
    public String getDescription() {
	return NormalizedMaintainabilityIndex.DESCRIPTION;
    }

    @Override
    public String getName() {
	return NormalizedMaintainabilityIndex.NAME;
    }

    @Override
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return NormalizedMaintainabilityIndex.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    public NormalizedMaintainabilityIndexEvaluator create(
	    AnalysisRun analysisRun) {
	return new NormalizedMaintainabilityIndexEvaluator(analysisRun);
    }

    @Override
    public List<Class<? extends Evaluator<? extends EvaluatorResults>>> getDependencies() {
	List<Class<? extends Evaluator<? extends EvaluatorResults>>> dependencies = new ArrayList<Class<? extends Evaluator<? extends EvaluatorResults>>>();
	dependencies.add(SLOCEvaluator.class);
	dependencies.add(McCabeMetricEvaluator.class);
	dependencies.add(HalsteadMetricEvaluator.class);
	return dependencies;
    }
}
