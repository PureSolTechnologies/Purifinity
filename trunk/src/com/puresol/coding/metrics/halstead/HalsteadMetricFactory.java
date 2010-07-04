package com.puresol.coding.metrics.halstead;

import java.util.List;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.evaluator.CodeRangeEvaluatorFactory;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.utils.Property;

public class HalsteadMetricFactory implements CodeRangeEvaluatorFactory {

	@Override
	public CodeRangeEvaluator create(CodeRange codeRange) {
		return new HalsteadMetric(codeRange);
	}

	@Override
	public String getDescription() {
		return HalsteadMetric.DESCRIPTION;
	}

	@Override
	public String getName() {
		return HalsteadMetric.NAME;
	}

	@Override
	public List<Property> getEvaluatorProperties() {
		return HalsteadMetric.SUPPORTED_PROPERTIES;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return HalsteadMetric.EVALUATED_QUALITY_CHARACTERISTICS;
	}

}
