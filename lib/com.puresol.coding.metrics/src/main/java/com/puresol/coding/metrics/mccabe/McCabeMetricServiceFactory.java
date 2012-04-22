package com.puresol.coding.metrics.mccabe;

import java.util.List;

import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;
import com.puresol.coding.quality.QualityCharacteristic;

public class McCabeMetricServiceFactory implements EvaluatorFactory,
	ProjectEvaluatorFactory {

    @Override
    public String getDescription() {
	return McCabeMetric.DESCRIPTION;
    }

    @Override
    public String getName() {
	return McCabeMetric.NAME;
    }

    @Override
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return McCabeMetric.EVALUATED_QUALITY_CHARACTERISTICS;
    }

}
