package com.puresol.coding.metrics.maintainability;

import java.util.List;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.evaluator.CodeRangeEvaluatorFactory;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.utils.Property;

public class MaintainabilityIndexFactory implements CodeRangeEvaluatorFactory {

	@Override
	public CodeRangeEvaluator create(CodeRange codeRange) {
		return new MaintainabilityIndex(codeRange);
	}

	@Override
	public String getDescription() {
		return MaintainabilityIndex.DESCRIPTION;
	}

	@Override
	public String getName() {
		return MaintainabilityIndex.NAME;
	}

	@Override
	public List<Property> getEvaluatorProperties() {
		return MaintainabilityIndex.SUPPORTED_PROPERTIES;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return MaintainabilityIndex.EVALUATED_QUALITY_CHARACTERISTICS;
	}

}
