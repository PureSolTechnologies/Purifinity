package com.puresol.coding.metrics.sloc;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.evaluation.api.Evaluator;
import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.evaluation.api.EvaluatorResults;
import com.puresol.coding.quality.api.QualityCharacteristic;

public class SLOCMetricServiceFactory implements EvaluatorFactory {

    @Override
    public String getDescription() {
	return SLOCMetric.DESCRIPTION;
    }

    @Override
    public String getName() {
	return SLOCMetric.NAME;
    }

    @Override
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return SLOCMetric.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    public SLOCEvaluator create(AnalysisRun analysisRun) {
	return new SLOCEvaluator(analysisRun);
    }

    @Override
    public List<Class<? extends Evaluator<? extends EvaluatorResults>>> getDependencies() {
	return new ArrayList<Class<? extends Evaluator<? extends EvaluatorResults>>>();
    }
}
