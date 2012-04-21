package com.puresol.coding.metrics.normmaint;

import java.util.List;

import com.puresol.coding.analysis.AnalysisRunImpl;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.evaluator.CodeRangeEvaluatorFactory;
import com.puresol.coding.evaluator.ProjectEvaluator;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;
import com.puresol.coding.quality.QualityCharacteristic;

public class NormalizedMaintainabilityIndexServiceFactory implements
	CodeRangeEvaluatorFactory, ProjectEvaluatorFactory {

    @Override
    public ProjectEvaluator create(AnalysisRunImpl projectAnalyzer) {
	return new ProjectNormalizedMaintainabilityIndex(projectAnalyzer);
    }

    @Override
    public Class<? extends ProjectEvaluator> getProjectEvaluatorClass() {
	return ProjectNormalizedMaintainabilityIndex.class;
    }

    @Override
    public Class<? extends CodeRangeEvaluator> getCodeRangeEvaluatorClass() {
	return NormalizedMaintainabilityIndex.class;
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
