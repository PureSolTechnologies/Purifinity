package com.puresol.coding.metrics.normmaint;

import java.util.List;

import com.puresol.coding.analysis.AnalysisRunImpl;
import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.evaluator.ProjectEvaluator;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;
import com.puresol.coding.quality.QualityCharacteristic;

public class NormalizedMaintainabilityIndexServiceFactory implements
	EvaluatorFactory, ProjectEvaluatorFactory {

    @Override
    public ProjectEvaluator create(AnalysisRunImpl projectAnalyzer) {
	return new ProjectNormalizedMaintainabilityIndex(projectAnalyzer);
    }

    @Override
    public String getDescription() {
	return NormalizedMaintainabilityIndex.DESCRIPTION;
    }

    @Override
    public String getName() {
	return NormalizedMaintainabilityIndex.NAME;
    }

    @Override
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return NormalizedMaintainabilityIndex.EVALUATED_QUALITY_CHARACTERISTICS;
    }
}
