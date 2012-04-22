package com.puresol.coding.metrics.codedepth;

import java.util.List;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.evaluation.api.Evaluator;
import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.quality.api.QualityCharacteristic;

public class CodeDepthMetricServiceFactory implements EvaluatorFactory {

    @Override
    public String getDescription() {
	return CodeDepthMetric.DESCRIPTION;
    }

    @Override
    public String getName() {
	return CodeDepthMetric.NAME;
    }

    @Override
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return CodeDepthMetric.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    public Evaluator create(AnalysisRun analysisRun) {
	return new CodeDepthEvaluator(analysisRun);
    }
}
