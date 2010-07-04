package com.puresol.coding.metrics.sloc;

import java.util.List;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.evaluator.CodeRangeEvaluatorFactory;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.utils.Property;

public class SLOCMetricFactory implements CodeRangeEvaluatorFactory {

	@Override
	public CodeRangeEvaluator create(CodeRange codeRange) {
		return new SLOCMetric(codeRange);
	}

	@Override
	public String getDescription() {
		return SLOCMetric.DESCRIPTION;
	}

	@Override
	public String getName() {
		return SLOCMetric.NAME;
	}

	@Override
	public List<Property> getEvaluatorProperties() {
		return SLOCMetric.SUPPORTED_PROPERTIES;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return SLOCMetric.EVALUATED_QUALITY_CHARACTERISTICS;
	}

}
