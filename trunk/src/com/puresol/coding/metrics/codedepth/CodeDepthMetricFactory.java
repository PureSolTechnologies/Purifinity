package com.puresol.coding.metrics.codedepth;

import java.util.List;

import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.evaluator.CodeRangeEvaluatorFactory;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.uhura.ast.ParserTree;
import com.puresol.utils.Property;

public class CodeDepthMetricFactory implements CodeRangeEvaluatorFactory {

	@Override
	public CodeRangeEvaluator create(ProgrammingLanguage language,
			ParserTree syntaxTree) {
		return new CodeDepth(language, syntaxTree);
	}

	@Override
	public String getDescription() {
		return CodeDepth.DESCRIPTION;
	}

	@Override
	public String getName() {
		return CodeDepth.NAME;
	}

	@Override
	public List<Property> getEvaluatorProperties() {
		return CodeDepth.SUPPORTED_PROPERTIES;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return CodeDepth.EVALUATED_QUALITY_CHARACTERISTICS;
	}

}
