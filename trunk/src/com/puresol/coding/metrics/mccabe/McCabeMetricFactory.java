package com.puresol.coding.metrics.mccabe;

import java.util.List;

import com.puresol.coding.CodeRange;
import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.evaluator.CodeRangeEvaluatorFactory;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.utils.Property;

public class McCabeMetricFactory implements CodeRangeEvaluatorFactory {

	@Override
	public CodeRangeEvaluator create(ProgrammingLanguage language,
			CodeRange codeRange) {
		return new McCabeMetric(language, codeRange);
	}

	@Override
	public String getDescription() {
		return McCabeMetric.DESCRIPTION;
	}

	@Override
	public String getName() {
		return McCabeMetric.NAME;
	}

	@Override
	public List<Property> getEvaluatorProperties() {
		return McCabeMetric.SUPPORTED_PROPERTIES;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return McCabeMetric.EVALUATED_QUALITY_CHARACTERISTICS;
	}

}
