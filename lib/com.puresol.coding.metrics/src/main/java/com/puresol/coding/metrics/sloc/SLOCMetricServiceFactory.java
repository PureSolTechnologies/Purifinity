package com.puresol.coding.metrics.sloc;

import java.util.List;

import com.puresol.coding.analysis.FileSystemAnalysisRun;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.ProgrammingLanguage;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.evaluator.CodeRangeEvaluatorFactory;
import com.puresol.coding.evaluator.ProjectEvaluator;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;
import com.puresol.coding.quality.QualityCharacteristic;

public class SLOCMetricServiceFactory implements ProjectEvaluatorFactory,
	CodeRangeEvaluatorFactory {

    @Override
    public CodeRangeEvaluator create(ProgrammingLanguage language,
	    CodeRange codeRange) {
	return new SLOCMetric(language, codeRange);
    }

    @Override
    public ProjectEvaluator create(FileSystemAnalysisRun projectAnalyzer) {
	return new ProjectSLOCMetric(projectAnalyzer);
    }

    @Override
    public Class<? extends ProjectEvaluator> getProjectEvaluatorClass() {
	return ProjectSLOCMetric.class;
    }

    @Override
    public Class<? extends CodeRangeEvaluator> getCodeRangeEvaluatorClass() {
	return SLOCMetric.class;
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
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return SLOCMetric.EVALUATED_QUALITY_CHARACTERISTICS;
    }
}
