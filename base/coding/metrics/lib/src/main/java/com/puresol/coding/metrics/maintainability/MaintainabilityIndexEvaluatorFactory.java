package com.puresol.coding.metrics.maintainability;

import java.util.HashSet;
import java.util.Set;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.evaluation.api.AbstractEvaluatorFactory;
import com.puresol.coding.evaluation.api.Evaluator;
import com.puresol.coding.evaluation.api.QualityCharacteristic;
import com.puresol.coding.metrics.halstead.HalsteadMetricEvaluator;
import com.puresol.coding.metrics.mccabe.McCabeMetricEvaluator;
import com.puresol.coding.metrics.sloc.SLOCEvaluator;
import com.puresol.utils.math.Parameter;

public class MaintainabilityIndexEvaluatorFactory extends
	AbstractEvaluatorFactory {

    @Override
    public String getDescription() {
	return MaintainabilityIndexEvaluator.DESCRIPTION;
    }

    @Override
    public String getName() {
	return MaintainabilityIndexEvaluator.NAME;
    }

    @Override
    public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return MaintainabilityIndexEvaluator.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    public MaintainabilityIndexEvaluator create(AnalysisRun analysisRun,
	    HashIdFileTree path) {
	return new MaintainabilityIndexEvaluator(analysisRun, path);
    }

    @Override
    public Set<Class<? extends Evaluator>> getDependencies() {
	Set<Class<? extends Evaluator>> dependencies = new HashSet<Class<? extends Evaluator>>();
	dependencies.add(SLOCEvaluator.class);
	dependencies.add(McCabeMetricEvaluator.class);
	dependencies.add(HalsteadMetricEvaluator.class);
	return dependencies;
    }

    @Override
    public Class<? extends Evaluator> getEvaluatorClass() {
	return MaintainabilityIndexEvaluator.class;
    }

    @Override
    public Set<Parameter<?>> getParameters() {
	return MaintainabilityIndexEvaluatorParameter.ALL;
    }
}
