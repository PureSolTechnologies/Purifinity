package com.puresol.coding.metrics.codedepth;

import java.util.List;

import com.puresol.coding.analysis.AnalysisRunImpl;
import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.evaluator.ProjectEvaluator;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;
import com.puresol.coding.quality.QualityCharacteristic;

public class CodeDepthMetricServiceFactory implements EvaluatorFactory,
	ProjectEvaluatorFactory {

    @Override
    public ProjectEvaluator create(AnalysisRunImpl projectAnalyzer) {
	return new ProjectCodeDepthMetric(projectAnalyzer);
    }

    @Override
    public String getDescription() {
	return CodeDepthMetric.DESCRIPTION;
    }

    @Override
    public String getName() {
	return CodeDepthMetric.NAME;
    }

    @Override
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return CodeDepthMetric.EVALUATED_QUALITY_CHARACTERISTICS;
    }
}
