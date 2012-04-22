package com.puresol.coding.metrics.halstead;

import java.util.List;

import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.quality.QualityCharacteristic;

public class HalsteadMetricServiceFactory implements EvaluatorFactory {

    @Override
    public String getDescription() {
	return HalsteadMetric.DESCRIPTION;
    }

    @Override
    public String getName() {
	return HalsteadMetric.NAME;
    }

    @Override
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return HalsteadMetric.EVALUATED_QUALITY_CHARACTERISTICS;
    }

}
