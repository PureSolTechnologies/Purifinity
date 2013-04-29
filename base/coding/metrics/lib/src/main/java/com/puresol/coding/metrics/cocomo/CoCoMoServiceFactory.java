package com.puresol.coding.metrics.cocomo;

import java.util.HashSet;
import java.util.Set;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.evaluation.api.AbstractEvaluatorFactory;
import com.puresol.coding.evaluation.api.Evaluator;
import com.puresol.coding.evaluation.api.QualityCharacteristic;
import com.puresol.coding.metrics.sloc.SLOCEvaluator;
import com.puresol.utils.math.Parameter;

public class CoCoMoServiceFactory extends AbstractEvaluatorFactory {

    @Override
    public String getDescription() {
	return CoCoMoEvaluator.DESCRIPTION;
    }

    @Override
    public String getName() {
	return CoCoMoEvaluator.NAME;
    }

    /**
     * {@inheritDoc}
     * 
     * For CoCoMo there is no quality characteristics assigned. It's a pure
     * economic evaluation.
     */
    @Override
    public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return new HashSet<QualityCharacteristic>();
    }

    @Override
    public CoCoMoEvaluator create(AnalysisRun analysisRun, HashIdFileTree path) {
	return new CoCoMoEvaluator(analysisRun, path);
    }

    @Override
    public Set<Class<? extends Evaluator>> getDependencies() {
	Set<Class<? extends Evaluator>> dependencies = new HashSet<Class<? extends Evaluator>>();
	dependencies.add(SLOCEvaluator.class);
	return dependencies;
    }

    @Override
    public Class<? extends Evaluator> getEvaluatorClass() {
	return CoCoMoEvaluator.class;
    }

    @Override
    public Set<Parameter<?>> getParameters() {
	return CoCoMoEvaluatorParameter.ALL;
    }
}
