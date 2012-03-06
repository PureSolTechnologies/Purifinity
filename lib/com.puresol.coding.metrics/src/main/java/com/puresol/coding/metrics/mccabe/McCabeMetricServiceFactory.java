package com.puresol.coding.metrics.mccabe;

import java.util.List;

import com.puresol.coding.CodeRange;
import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.evaluator.CodeRangeEvaluatorFactory;
import com.puresol.coding.evaluator.ProjectEvaluator;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;
import com.puresol.coding.quality.QualityCharacteristic;

public class McCabeMetricServiceFactory implements CodeRangeEvaluatorFactory,
	ProjectEvaluatorFactory {

    @Override
    public CodeRangeEvaluator create(ProgrammingLanguage language,
	    CodeRange codeRange) {
	return new McCabeMetric(language, codeRange);
    }

    @Override
    public ProjectEvaluator create(ProjectAnalyzer projectAnalyzer) {
	return new ProjectMcCabeMetric(projectAnalyzer);
    }

    @Override
    public Class<? extends ProjectEvaluator> getProjectEvaluatorClass() {
	return ProjectMcCabeMetric.class;
    }

    @Override
    public Class<? extends CodeRangeEvaluator> getCodeRangeEvaluatorClass() {
	return McCabeMetric.class;
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
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return McCabeMetric.EVALUATED_QUALITY_CHARACTERISTICS;
    }

}
