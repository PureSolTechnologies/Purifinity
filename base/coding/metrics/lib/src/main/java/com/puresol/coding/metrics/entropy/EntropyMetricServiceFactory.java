package com.puresol.coding.metrics.entropy;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.evaluation.api.AbstractEvaluatorFactory;
import com.puresol.coding.evaluation.api.Evaluator;
import com.puresol.coding.evaluation.api.QualityCharacteristic;
import com.puresol.coding.metrics.halstead.HalsteadMetricEvaluator;

public class EntropyMetricServiceFactory extends AbstractEvaluatorFactory {

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
    public EntropyEvaluator create(AnalysisRun analysisRun, HashIdFileTree path) {
	return new EntropyEvaluator(analysisRun, path);
    }

    @Override
    public List<Class<? extends Evaluator>> getDependencies() {
	List<Class<? extends Evaluator>> dependencies = new ArrayList<Class<? extends Evaluator>>();
	dependencies.add(HalsteadMetricEvaluator.class);
	return dependencies;
    }

}
