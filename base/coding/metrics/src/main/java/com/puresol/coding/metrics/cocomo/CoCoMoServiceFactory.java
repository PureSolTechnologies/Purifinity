package com.puresol.coding.metrics.cocomo;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.evaluation.Evaluator;
import com.puresol.coding.analysis.api.evaluation.EvaluatorFactory;
import com.puresol.coding.analysis.api.quality.QualityCharacteristic;
import com.puresol.coding.metrics.sloc.SLOCEvaluator;

public class CoCoMoServiceFactory implements EvaluatorFactory {

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
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return new ArrayList<QualityCharacteristic>();
    }

    @Override
    public CoCoMoEvaluator create(AnalysisRun analysisRun) {
	return new CoCoMoEvaluator(analysisRun);
    }

    @Override
    public List<Class<? extends Evaluator>> getDependencies() {
	List<Class<? extends Evaluator>> dependencies = new ArrayList<Class<? extends Evaluator>>();
	dependencies.add(SLOCEvaluator.class);
	return dependencies;
    }
}
