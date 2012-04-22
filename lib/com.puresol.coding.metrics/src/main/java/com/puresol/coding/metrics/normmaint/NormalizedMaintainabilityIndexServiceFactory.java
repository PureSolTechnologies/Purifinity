package com.puresol.coding.metrics.normmaint;

import java.util.List;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.evaluation.api.Evaluator;
import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.quality.QualityCharacteristic;

public class NormalizedMaintainabilityIndexServiceFactory implements
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
    public Evaluator create(AnalysisRun analysisRun) {
	return new NormalizedMaintainabilityIndexEvaluator(analysisRun);
    }
}
