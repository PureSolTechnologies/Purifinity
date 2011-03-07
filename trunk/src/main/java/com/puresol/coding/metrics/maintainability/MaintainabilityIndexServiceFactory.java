package com.puresol.coding.metrics.maintainability;

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

public class MaintainabilityIndexServiceFactory implements
		CodeRangeEvaluatorFactory, ProjectEvaluatorFactory {

	@Override
	public CodeRangeEvaluator create(ProgrammingLanguage language,
			CodeRange codeRange) {
		return new MaintainabilityIndex(language, codeRange);
	}

	@Override
	public ProjectEvaluator create(ProjectAnalyzer projectAnalyzer,
			Configuration configuration) {
		return new ProjectMaintainabilityIndex(projectAnalyzer);
	}

	@Override
	public Class<? extends ProjectEvaluator> getProjectEvaluatorClass() {
		return ProjectMaintainabilityIndex.class;
	}

	@Override
	public Class<? extends CodeRangeEvaluator> getCodeRangeEvaluatorClass() {
		return MaintainabilityIndex.class;
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
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return MaintainabilityIndex.EVALUATED_QUALITY_CHARACTERISTICS;
	}
}
