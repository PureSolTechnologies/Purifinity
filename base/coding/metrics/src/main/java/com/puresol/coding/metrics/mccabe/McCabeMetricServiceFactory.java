package com.puresol.coding.metrics.mccabe;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.impl.AnalysisRun;
import com.puresol.coding.analysis.impl.evaluation.Evaluator;
import com.puresol.coding.analysis.impl.evaluation.EvaluatorFactory;
import com.puresol.coding.analysis.impl.quality.QualityCharacteristic;

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

    @Override
    public List<Class<? extends Evaluator>> getDependencies() {
	return new ArrayList<Class<? extends Evaluator>>();
    }
}
