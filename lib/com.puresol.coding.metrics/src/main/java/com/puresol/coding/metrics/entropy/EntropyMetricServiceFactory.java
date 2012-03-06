package com.puresol.coding.metrics.entropy;

import java.util.List;

import com.puresol.coding.CodeRange;
import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.evaluator.CodeRangeEvaluatorFactory;
import com.puresol.coding.evaluator.ProjectEvaluator;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;
import com.puresol.coding.quality.QualityCharacteristic;

public class EntropyMetricServiceFactory implements CodeRangeEvaluatorFactory,
	ProjectEvaluatorFactory {

    @Override
    public CodeRangeEvaluator create(ProgrammingLanguage language,
	    CodeRange codeRange) {
	return new EntropyMetric(language, codeRange);
    }

    @Override
    public ProjectEvaluator create(ProjectAnalyzer projectAnalyzer) {
	return new ProjectEntropyMetric(projectAnalyzer);
    }

    @Override
    public Class<? extends ProjectEvaluator> getProjectEvaluatorClass() {
	return ProjectEntropyMetric.class;
    }

    @Override
    public Class<? extends CodeRangeEvaluator> getCodeRangeEvaluatorClass() {
	return EntropyMetric.class;
    }

    @Override
    public String getDescription() {
	return EntropyMetric.DESCRIPTION;
    }

    @Override
    public String getName() {
	return EntropyMetric.NAME;
    }

    @Override
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return EntropyMetric.EVALUATED_QUALITY_CHARACTERISTICS;
    }

}
