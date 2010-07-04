package com.puresol.coding.metrics.entropy;

import java.util.List;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.evaluator.CodeRangeEvaluatorFactory;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.utils.Property;

public class EntropyFactory implements CodeRangeEvaluatorFactory {

	@Override
	public CodeRangeEvaluator create(CodeRange codeRange) {
		return new EntropyMetric(codeRange);
	}

	@Override
	public String getDescription() {
		return EntropyMetric.DESCRIPTION;
	}

	@Override
	public String getName() {
		return EntropyMetric.NAME;
	}

	@Override
	public List<Property> getEvaluatorProperties() {
		return EntropyMetric.SUPPORTED_PROPERTIES;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return EntropyMetric.EVALUATED_QUALITY_CHARACTERISTICS;
	}

}
