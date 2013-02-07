package com.puresol.coding.metrics.sloc;

import java.util.List;

import com.puresol.coding.analysis.api.CodeAnalysis;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.analysis.impl.AnalysisRun;
import com.puresol.coding.analysis.impl.ProgrammingLanguage;
import com.puresol.coding.analysis.impl.ProgrammingLanguages;
import com.puresol.coding.analysis.impl.evaluation.AbstractEvaluator;
import com.puresol.coding.analysis.impl.evaluation.EvaluatorStore;
import com.puresol.coding.analysis.impl.quality.QualityCharacteristic;

public class SLOCEvaluator extends AbstractEvaluator {

    private static final long serialVersionUID = -5093217611195212999L;

    private final EvaluatorStore store;

    public SLOCEvaluator(AnalysisRun analysisRun) {
	super(SLOCMetricCalculator.NAME, SLOCMetricCalculator.DESCRIPTION,
		analysisRun);
	store = getEvaluatorStore();
    }

    @Override
    protected void processFile(CodeAnalysis analysis)
	    throws InterruptedException {
	SLOCFileResults results = new SLOCFileResults();
	ProgrammingLanguage language = ProgrammingLanguages.findByName(
		analysis.getLanguageName(), analysis.getLanguageVersion());

	for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
	    SLOCMetricCalculator metric = new SLOCMetricCalculator(
		    getAnalysisRun(), language, codeRange);
	    metric.schedule();
	    metric.join();
	    results.add(new SLOCFileResult(analysis.getAnalyzedFile()
		    .getLocation(), codeRange.getType(), codeRange.getName(),
		    metric.getSLOCResult(), metric.getQuality()));
	}
	store.storeFileResults(analysis.getAnalyzedFile().getHashId(), results);
    }

    @Override
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return SLOCMetricCalculator.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    protected void processDirectory(HashIdFileTree directory)
	    throws InterruptedException {
	SLOCDirectoryResults finalResults = new SLOCDirectoryResults();
	for (HashIdFileTree child : directory.getChildren()) {
	    if (child.isFile()) {
		addFileSLOC(finalResults, child);
	    } else {
		addDirectorySLOC(finalResults, child);
	    }
	}
	store.storeDirectoryResults(directory.getHashId(), finalResults);
    }

    private void addFileSLOC(SLOCDirectoryResults finalResults,
	    HashIdFileTree child) {
	if (store.hasFileResults(child.getHashId())) {
	    SLOCFileResults fileResults = (SLOCFileResults) store
		    .readFileResults(child.getHashId());
	    for (SLOCFileResult results : fileResults) {
		if (results.getCodeRangeType() == CodeRangeType.FILE) {
		    finalResults.add(new SLOCDirectoryResult(results
			    .getSourceCodeLocation(), results.getSLOCResult()));
		    break;
		}
	    }
	}
    }

    private void addDirectorySLOC(SLOCDirectoryResults finalResults,
	    HashIdFileTree child) {
	throw new RuntimeException("Not implemented, yet!");
	// if (store.hasDirectoryResults(child.getHashId())) {
	// SLOCDirectoryResults directoryResults = (SLOCDirectoryResults) store
	// .readDirectoryResults(child.getHashId());
	// SLOCResult combinedSLOC = new SLOCResult(0, 0, 0, 0, null);
	// for (SLOCDirectoryResult results : directoryResults) {
	// SLOCResult slocResult = results.getSLOCResult();
	// if (slocResult != null) {
	// combinedSLOC = SLOCResult.combine(combinedSLOC, slocResult);
	// }
	// }
	// finalResults.add(new SLOCDirectoryResult(child.getName(),
	// combinedSLOC));
	// }
    }

    @Override
    protected void processProject() throws InterruptedException {
	// TODO Auto-generated method stub

    }
}
