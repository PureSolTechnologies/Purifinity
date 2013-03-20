package com.puresol.coding.metrics.maintainability;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.evaluation.api.AbstractEvaluatorFactory;
import com.puresol.coding.evaluation.api.Evaluator;
import com.puresol.coding.evaluation.api.QualityCharacteristic;
import com.puresol.coding.metrics.halstead.HalsteadMetricEvaluator;
import com.puresol.coding.metrics.mccabe.McCabeMetricEvaluator;
import com.puresol.coding.metrics.sloc.SLOCEvaluator;

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
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return MaintainabilityIndexEvaluator.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    public MaintainabilityIndexEvaluator create(AnalysisRun analysisRun,
	    HashIdFileTree path) {
	return new MaintainabilityIndexEvaluator(analysisRun, path);
    }

    @Override
    public List<Class<? extends Evaluator>> getDependencies() {
	List<Class<? extends Evaluator>> dependencies = new ArrayList<Class<? extends Evaluator>>();
	dependencies.add(SLOCEvaluator.class);
	dependencies.add(McCabeMetricEvaluator.class);
	dependencies.add(HalsteadMetricEvaluator.class);
	return dependencies;
    }

}
