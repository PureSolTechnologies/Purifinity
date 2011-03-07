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
import com.puresol.config.Configuration;

public class NormalizedMaintainabilityIndexServiceFactory implements
		CodeRangeEvaluatorFactory, ProjectEvaluatorFactory {

	@Override
	public CodeRangeEvaluator create(ProgrammingLanguage language,
			CodeRange codeRange) {
		return new NormalizedMaintainabilityIndex(language, codeRange);
	}

	@Override
	public ProjectEvaluator create(ProjectAnalyzer projectAnalyzer,
			Configuration configuration) {
		return new ProjectNormalizedMaintainabilityIndex(projectAnalyzer);
	}

	@Override
	public Class<? extends ProjectEvaluator> getProjectEvaluatorClass() {
		return ProjectNormalizedMaintainabilityIndex.class;
	}

	@Override
	public Class<? extends CodeRangeEvaluator> getCodeRangeEvaluatorClass() {
		return NormalizedMaintainabilityIndex.class;
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
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return NormalizedMaintainabilityIndex.EVALUATED_QUALITY_CHARACTERISTICS;
	}
}
