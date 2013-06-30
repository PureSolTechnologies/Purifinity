package com.puresol.purifinity.coding.metrics.normmaint;

import java.util.HashSet;
import java.util.Set;

import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresol.purifinity.coding.evaluation.api.AbstractEvaluatorFactory;
import com.puresol.purifinity.coding.evaluation.api.Evaluator;
import com.puresol.purifinity.coding.evaluation.api.QualityCharacteristic;
import com.puresol.purifinity.coding.metrics.maintainability.MaintainabilityIndexEvaluator;
import com.puresol.purifinity.utils.math.Parameter;

public class NormalizedMaintainabilityIndexEvaluatorFactory extends
	AbstractEvaluatorFactory {

    @Override
    public String getDescription() {
	return NormalizedMaintainabilityIndexEvaluator.DESCRIPTION;
    }

    @Override
    public String getName() {
	return NormalizedMaintainabilityIndexEvaluator.NAME;
    }

    @Override
    public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return NormalizedMaintainabilityIndexEvaluator.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    public NormalizedMaintainabilityIndexEvaluator create(
	    AnalysisRun analysisRun, HashIdFileTree path) {
	return new NormalizedMaintainabilityIndexEvaluator(analysisRun, path);
    }

    @Override
    public Set<Class<? extends Evaluator>> getDependencies() {
	Set<Class<? extends Evaluator>> dependencies = new HashSet<Class<? extends Evaluator>>();
	dependencies.add(MaintainabilityIndexEvaluator.class);
	return dependencies;
    }

    @Override
    public Class<? extends Evaluator> getEvaluatorClass() {
	return NormalizedMaintainabilityIndexEvaluator.class;
    }

    @Override
    public Set<Parameter<?>> getParameters() {
	return NormalizedMaintainabilityIndexEvaluatorParameter.ALL;
    }
}
