package com.puresol.coding.metrics.codedepth;

import java.util.HashSet;
import java.util.Set;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.evaluation.api.AbstractEvaluatorFactory;
import com.puresol.coding.evaluation.api.Evaluator;
import com.puresol.coding.evaluation.api.QualityCharacteristic;
import com.puresol.utils.math.Parameter;

public class CodeDepthMetricEvaluatorFactory extends AbstractEvaluatorFactory {

    @Override
    public String getDescription() {
	return CodeDepthMetric.DESCRIPTION;
    }

    @Override
    public String getName() {
	return CodeDepthMetric.NAME;
    }

    @Override
    public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return CodeDepthMetric.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    public CodeDepthMetricEvaluator create(AnalysisRun analysisRun,
	    HashIdFileTree path) {
	return new CodeDepthMetricEvaluator(analysisRun, path);
    }

    @Override
    public Set<Class<? extends Evaluator>> getDependencies() {
	return new HashSet<Class<? extends Evaluator>>();
    }

    @Override
    public Class<? extends Evaluator> getEvaluatorClass() {
	return CodeDepthMetricEvaluator.class;
    }

    @Override
    public Set<Parameter<?>> getParameters() {
	return CodeDepthMetricEvaluatorParameter.ALL;
    }
}
