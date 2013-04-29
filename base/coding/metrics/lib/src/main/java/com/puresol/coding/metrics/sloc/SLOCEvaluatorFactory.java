package com.puresol.coding.metrics.sloc;

import java.util.HashSet;
import java.util.Set;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.evaluation.api.AbstractEvaluatorFactory;
import com.puresol.coding.evaluation.api.Evaluator;
import com.puresol.coding.evaluation.api.QualityCharacteristic;
import com.puresol.utils.math.Parameter;

public class SLOCEvaluatorFactory extends AbstractEvaluatorFactory {

    @Override
    public String getDescription() {
	return SLOCMetricCalculator.DESCRIPTION;
    }

    @Override
    public String getName() {
	return SLOCMetricCalculator.NAME;
    }

    @Override
    public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return SLOCMetricCalculator.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    public SLOCEvaluator create(AnalysisRun analysisRun, HashIdFileTree path) {
	return new SLOCEvaluator(analysisRun, path);
    }

    @Override
    public Set<Class<? extends Evaluator>> getDependencies() {
	return new HashSet<Class<? extends Evaluator>>();
    }

    @Override
    public Class<? extends Evaluator> getEvaluatorClass() {
	return SLOCEvaluator.class;
    }

    @Override
    public Set<Parameter<?>> getParameters() {
	return SLOCEvaluatorParameter.ALL;
    }
}
