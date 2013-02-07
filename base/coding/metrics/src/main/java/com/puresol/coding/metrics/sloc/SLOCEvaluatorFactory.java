package com.puresol.coding.metrics.sloc;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.impl.AnalysisRun;
import com.puresol.coding.analysis.impl.evaluation.Evaluator;
import com.puresol.coding.analysis.impl.evaluation.EvaluatorFactory;
import com.puresol.coding.analysis.impl.quality.QualityCharacteristic;

public class SLOCEvaluatorFactory implements EvaluatorFactory {

    @Override
    public String getDescription() {
	return SLOCMetricCalculator.DESCRIPTION;
    }

    @Override
    public String getName() {
	return SLOCMetricCalculator.NAME;
    }

    @Override
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return SLOCMetricCalculator.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    public SLOCEvaluator create(AnalysisRun analysisRun) {
	return new SLOCEvaluator(analysisRun);
    }

    @Override
    public List<Class<? extends Evaluator>> getDependencies() {
	return new ArrayList<Class<? extends Evaluator>>();
    }
}
