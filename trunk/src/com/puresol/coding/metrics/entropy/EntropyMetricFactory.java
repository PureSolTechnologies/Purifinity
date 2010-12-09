package com.puresol.coding.metrics.entropy;

import java.util.List;

import com.puresol.coding.CodeRange;
import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.evaluator.CodeRangeEvaluatorFactory;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.utils.Property;

public class EntropyMetricFactory implements CodeRangeEvaluatorFactory {

	@Override
	public CodeRangeEvaluator create(ProgrammingLanguage language,
			CodeRange codeRange) {
		return new EntropyMetric(language, codeRange);
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
