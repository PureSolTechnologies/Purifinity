package com.puresol.coding.metrics.entropy;

import java.util.List;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.evaluation.api.Evaluator;
import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.quality.QualityCharacteristic;

public class EntropyMetricServiceFactory implements EvaluatorFactory {

    @Override
    public String getDescription() {
	return EntropyMetric.DESCRIPTION;
    }

    @Override
    public String getName() {
	return EntropyMetric.NAME;
    }

    @Override
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return EntropyMetric.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    public Evaluator create(AnalysisRun analysisRun) {
	return new EntropyEvaluator(analysisRun);
    }

}
