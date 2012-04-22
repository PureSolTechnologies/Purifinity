package com.puresol.coding.metrics.sloc;

import java.util.List;

import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.quality.QualityCharacteristic;

public class SLOCMetricServiceFactory implements EvaluatorFactory {

    @Override
    public String getDescription() {
	return SLOCMetric.DESCRIPTION;
    }

    @Override
    public String getName() {
	return SLOCMetric.NAME;
    }

    @Override
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return SLOCMetric.EVALUATED_QUALITY_CHARACTERISTICS;
    }
}
