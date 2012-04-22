package com.puresol.coding.metrics.maintainability;

import java.util.List;

import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.quality.QualityCharacteristic;

public class MaintainabilityIndexServiceFactory implements EvaluatorFactory {

    @Override
    public String getDescription() {
	return MaintainabilityIndex.DESCRIPTION;
    }

    @Override
    public String getName() {
	return MaintainabilityIndex.NAME;
    }

    @Override
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return MaintainabilityIndex.EVALUATED_QUALITY_CHARACTERISTICS;
    }
}
