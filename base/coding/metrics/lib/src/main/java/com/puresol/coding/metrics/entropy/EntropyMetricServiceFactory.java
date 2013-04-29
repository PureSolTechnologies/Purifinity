package com.puresol.coding.metrics.entropy;

import java.util.HashSet;
import java.util.Set;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.evaluation.api.AbstractEvaluatorFactory;
import com.puresol.coding.evaluation.api.Evaluator;
import com.puresol.coding.evaluation.api.QualityCharacteristic;
import com.puresol.coding.metrics.halstead.HalsteadMetricEvaluator;
import com.puresol.utils.math.Parameter;

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
    public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return EntropyMetric.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    public EntropyMetricEvaluator create(AnalysisRun analysisRun,
	    HashIdFileTree path) {
	return new EntropyMetricEvaluator(analysisRun, path);
    }

    @Override
    public Set<Class<? extends Evaluator>> getDependencies() {
	Set<Class<? extends Evaluator>> dependencies = new HashSet<Class<? extends Evaluator>>();
	dependencies.add(HalsteadMetricEvaluator.class);
	return dependencies;
    }

    @Override
    public Class<? extends Evaluator> getEvaluatorClass() {
	return EntropyMetricEvaluator.class;
    }

    @Override
    public Set<Parameter<?>> getParameters() {
	return EntropyMetricEvaluatorParameter.ALL;
    }
}
