package com.puresol.coding.metrics.codedepth;

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

public class CodeDepthMetricServiceFactory implements
		CodeRangeEvaluatorFactory, ProjectEvaluatorFactory {

	@Override
	public CodeRangeEvaluator create(ProgrammingLanguage language,
			CodeRange syntaxTree) {
		return new CodeDepthMetric(language, syntaxTree);
	}

	@Override
	public Class<? extends ProjectEvaluator> getProjectEvaluatorClass() {
		return ProjectCodeDepthMetric.class;
	}

	@Override
	public Class<? extends CodeRangeEvaluator> getCodeRangeEvaluatorClass() {
		return CodeDepthMetric.class;
	}

	@Override
	public ProjectEvaluator create(ProjectAnalyzer projectAnalyzer,
			Configuration configuration) {
		return new ProjectCodeDepthMetric(projectAnalyzer);
	}

	@Override
	public String getDescription() {
		return CodeDepthMetric.DESCRIPTION;
	}

	@Override
	public String getName() {
		return CodeDepthMetric.NAME;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return CodeDepthMetric.EVALUATED_QUALITY_CHARACTERISTICS;
	}
}
