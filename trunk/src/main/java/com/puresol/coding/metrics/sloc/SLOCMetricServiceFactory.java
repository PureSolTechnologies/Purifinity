package com.puresol.coding.metrics.sloc;

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

public class SLOCMetricServiceFactory implements ProjectEvaluatorFactory,
		CodeRangeEvaluatorFactory {

	@Override
	public CodeRangeEvaluator create(ProgrammingLanguage language,
			CodeRange codeRange) {
		return new SLOCMetric(language, codeRange);
	}

	@Override
	public ProjectEvaluator create(ProjectAnalyzer projectAnalyzer) {
		return new ProjectSLOCMetric(projectAnalyzer);
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
