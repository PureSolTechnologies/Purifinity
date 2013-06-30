package com.puresol.purifinity.coding.metrics.mccabe;

import java.util.HashSet;
import java.util.Set;

import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresol.purifinity.coding.evaluation.api.AbstractEvaluatorFactory;
import com.puresol.purifinity.coding.evaluation.api.Evaluator;
import com.puresol.purifinity.coding.evaluation.api.QualityCharacteristic;
import com.puresol.purifinity.utils.math.Parameter;

public class McCabeMetricServiceFactory extends AbstractEvaluatorFactory {

    @Override
    public String getDescription() {
	return McCabeMetric.DESCRIPTION;
    }

    @Override
    public String getName() {
	return McCabeMetric.NAME;
    }

    @Override
    public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return McCabeMetric.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    public McCabeMetricEvaluator create(AnalysisRun analysisRun,
	    HashIdFileTree path) {
	return new McCabeMetricEvaluator(analysisRun, path);
    }

    @Override
    public Set<Class<? extends Evaluator>> getDependencies() {
	return new HashSet<Class<? extends Evaluator>>();
    }

    @Override
    public Class<? extends Evaluator> getEvaluatorClass() {
	return McCabeMetricEvaluator.class;
    }

    @Override
    public Set<Parameter<?>> getParameters() {
	return McCabeMetricEvaluatorParameter.ALL;
    }
}
