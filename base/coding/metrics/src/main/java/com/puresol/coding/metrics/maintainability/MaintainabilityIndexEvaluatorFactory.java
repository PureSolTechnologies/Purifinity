package com.puresol.coding.metrics.maintainability;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.evaluation.Evaluator;
import com.puresol.coding.analysis.api.evaluation.EvaluatorFactory;
import com.puresol.coding.analysis.api.quality.QualityCharacteristic;
import com.puresol.coding.metrics.halstead.HalsteadMetricEvaluator;
import com.puresol.coding.metrics.mccabe.McCabeMetricEvaluator;
import com.puresol.coding.metrics.sloc.SLOCEvaluator;

public class MaintainabilityIndexEvaluatorFactory implements EvaluatorFactory {

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
    public MaintainabilityIndexEvaluator create(AnalysisRun analysisRun) {
	return new MaintainabilityIndexEvaluator(analysisRun);
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
