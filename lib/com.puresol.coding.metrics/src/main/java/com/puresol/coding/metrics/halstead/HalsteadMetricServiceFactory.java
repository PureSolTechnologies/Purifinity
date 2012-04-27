package com.puresol.coding.metrics.halstead;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.evaluation.api.Evaluator;
import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.evaluation.api.EvaluatorResults;
import com.puresol.coding.quality.api.QualityCharacteristic;

public class HalsteadMetricServiceFactory implements EvaluatorFactory {

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
    public List<Class<? extends Evaluator<? extends EvaluatorResults>>> getDependencies() {
	return new ArrayList<Class<? extends Evaluator<? extends EvaluatorResults>>>();
    }
}
