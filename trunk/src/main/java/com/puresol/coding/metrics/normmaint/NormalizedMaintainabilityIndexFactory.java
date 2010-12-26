package com.puresol.coding.metrics.normmaint;

import java.util.List;

import com.puresol.coding.CodeRange;
import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.evaluator.CodeRangeEvaluatorFactory;
import com.puresol.coding.evaluator.ProjectEvaluator;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.utils.Property;

public class NormalizedMaintainabilityIndexFactory implements CodeRangeEvaluatorFactory,
		ProjectEvaluatorFactory {

	@Override
	public CodeRangeEvaluator create(ProgrammingLanguage language,
			CodeRange codeRange) {
		return new NormalizedMaintainabilityIndex(language, codeRange);
	}

	@Override
	public ProjectEvaluator create(ProjectAnalyzer projectAnalyzer) {
		return new ProjectNormalizedMaintainabilityIndex(projectAnalyzer);
	}

	@Override
	public String getDescription() {
		return NormalizedMaintainabilityIndex.DESCRIPTION;
	}

	@Override
	public String getName() {
		return NormalizedMaintainabilityIndex.NAME;
	}

	@Override
	public List<Property> getEvaluatorProperties() {
		return NormalizedMaintainabilityIndex.SUPPORTED_PROPERTIES;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return NormalizedMaintainabilityIndex.EVALUATED_QUALITY_CHARACTERISTICS;
	}

}
