package com.puresol.coding.metrics.halstead;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.impl.AnalysisRun;
import com.puresol.coding.analysis.impl.evaluation.Evaluator;
import com.puresol.coding.analysis.impl.evaluation.EvaluatorFactory;
import com.puresol.coding.analysis.impl.quality.QualityCharacteristic;

public class HalsteadMetricEvaluatorFactory implements EvaluatorFactory {

    @Override
    public String getDescription() {
	return HalsteadMetric.DESCRIPTION;
    }

    @Override
    public String getName() {
	return HalsteadMetric.NAME;
    }

    @Override
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return HalsteadMetric.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    public HalsteadMetricEvaluator create(AnalysisRun analysisRun) {
	return new HalsteadMetricEvaluator(analysisRun);
    }

    @Override
    public List<Class<? extends Evaluator>> getDependencies() {
	return new ArrayList<Class<? extends Evaluator>>();
    }
}
