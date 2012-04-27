package com.puresol.coding.metrics.sloc;

import java.util.List;

import com.puresol.coding.ProgrammingLanguages;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.FileAnalysis;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.analysis.api.ProgrammingLanguage;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.quality.api.QualityCharacteristic;

public class SLOCEvaluator extends AbstractEvaluator<SLOCEvaluatorResults> {

    private static final long serialVersionUID = -5093217611195212999L;

    public SLOCEvaluator(AnalysisRun analysisRun) {
	super(SLOCMetricCalculator.NAME, SLOCMetricCalculator.DESCRIPTION,
		analysisRun);
    }

    @Override
    protected void processFile(FileAnalysis analysis)
	    throws InterruptedException {
	SLOCFileResult results = new SLOCFileResult();
	ProgrammingLanguage language = ProgrammingLanguages.findByName(
		analysis.getLanguageName(), analysis.getLanguageVersion());

	for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
	    SLOCMetricCalculator metric = new SLOCMetricCalculator(
		    getAnalysisRun(), language, codeRange);
	    metric.schedule();
	    metric.join();
	    results.put(
		    analysis.getAnalyzedFile().getFile().getPath() + ": "
			    + codeRange.getType().getName() + " '"
			    + codeRange.getName() + "'", metric.getQuality());
	}
    }

    @Override
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return SLOCMetricCalculator.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    public SLOCEvaluatorResults getResults() {
	// TODO Auto-generated method stub
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
