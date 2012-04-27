package com.puresol.coding.metrics.normmaint;

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

public class NormalizedMaintainabilityIndexEvaluator extends
	AbstractEvaluator<NormalizedMaintainabilityIndexEvaluatorResults> {

    private static final long serialVersionUID = -5093217611195212999L;

    public NormalizedMaintainabilityIndexEvaluator(AnalysisRun analysisRun) {
	super(new EvaluatorInformation(NormalizedMaintainabilityIndex.NAME,
		NormalizedMaintainabilityIndex.DESCRIPTION), analysisRun);
    }

    @Override
    protected NormalizedMaintainabilityIndexFileResult processFile(
	    FileAnalysis analysis) throws InterruptedException {
	NormalizedMaintainabilityIndexFileResult results = new NormalizedMaintainabilityIndexFileResult();
	ProgrammingLanguage language = ProgrammingLanguages.findByName(
		analysis.getLanguageName(), analysis.getLanguageVersion());

	for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
	    NormalizedMaintainabilityIndex metric = new NormalizedMaintainabilityIndex(
		    getAnalysisRun(), language, codeRange);
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
	return NormalizedMaintainabilityIndex.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    public NormalizedMaintainabilityIndexEvaluatorResults getResults() {
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
