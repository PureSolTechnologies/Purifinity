package com.puresol.coding.metrics.halstead;

import java.util.List;

import com.puresol.coding.ProgrammingLanguages;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.FileAnalysis;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.analysis.api.ProgrammingLanguage;
import com.puresol.coding.evaluation.api.EvaluatorInformation;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.quality.api.QualityCharacteristic;

public class HalsteadMetricEvaluator extends
	AbstractEvaluator<HalsteadMetricEvaluatorResults> {

    private static final long serialVersionUID = -5093217611195212999L;

    public HalsteadMetricEvaluator(AnalysisRun analysisRun) {
	super(new EvaluatorInformation(HalsteadMetric.NAME,
		HalsteadMetric.DESCRIPTION), analysisRun);
    }

    @Override
    protected HalsteadMetricFileResult processFile(FileAnalysis analysis)
	    throws InterruptedException {
	HalsteadMetricFileResult results = new HalsteadMetricFileResult();
	ProgrammingLanguage language = ProgrammingLanguages.findByName(
		analysis.getLanguageName(), analysis.getLanguageVersion());

	for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
	    HalsteadMetric metric = new HalsteadMetric(getAnalysisRun(),
		    language, codeRange);
	    metric.schedule();
	    metric.join();
	    results.put(
		    analysis.getAnalyzedFile().getFile().getPath() + ": "
			    + codeRange.getType().getName() + " '"
			    + codeRange.getName() + "'", metric.getQuality());
	}
	return results;
    }

    @Override
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return HalsteadMetric.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    public HalsteadMetricEvaluatorResults getResults() {
	return null;
    }

    @Override
    protected void processDirectory(HashIdFileTree directory)
	    throws InterruptedException {
	// TODO Auto-generated method stub

    }

    @Override
    protected void processProject() throws InterruptedException {
	// TODO Auto-generated method stub

    }
}
