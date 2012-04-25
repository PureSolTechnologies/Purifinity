package com.puresol.coding.metrics.mccabe;

import java.util.List;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.quality.api.QualityCharacteristic;

public class McCabeMetricServiceFactory implements EvaluatorFactory {

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
    public McCabeMetricEvaluator create(AnalysisRun analysisRun) {
	return new McCabeMetricEvaluator(analysisRun);
    }

}
