package com.puresol.coding.metrics.entropy;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.impl.AnalysisRun;
import com.puresol.coding.analysis.impl.evaluation.Evaluator;
import com.puresol.coding.analysis.impl.evaluation.EvaluatorFactory;
import com.puresol.coding.analysis.impl.quality.QualityCharacteristic;
import com.puresol.coding.metrics.halstead.HalsteadMetricEvaluator;

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
    public EntropyEvaluator create(AnalysisRun analysisRun) {
	return new EntropyEvaluator(analysisRun);
    }

    @Override
    public List<Class<? extends Evaluator>> getDependencies() {
	List<Class<? extends Evaluator>> dependencies = new ArrayList<Class<? extends Evaluator>>();
	dependencies.add(HalsteadMetricEvaluator.class);
	return dependencies;
    }

}
