package com.puresol.coding.metrics.maintainability;

import java.util.List;

import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.evaluator.CodeRangeEvaluatorFactory;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.uhura.ast.ParserTree;
import com.puresol.utils.Property;

public class MaintainabilityIndexFactory implements CodeRangeEvaluatorFactory {

	@Override
	public CodeRangeEvaluator create(ProgrammingLanguage language,
			ParserTree codeRange) {
		return new MaintainabilityIndex(language, codeRange);
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
