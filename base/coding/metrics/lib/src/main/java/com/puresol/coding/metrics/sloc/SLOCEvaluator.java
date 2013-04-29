package com.puresol.coding.metrics.sloc;

import java.util.Set;

import org.apache.commons.io.IOUtils;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.CodeAnalysis;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.analysis.api.ProgrammingLanguages;
import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.coding.evaluation.api.QualityCharacteristic;
import com.puresol.coding.evaluation.impl.AbstractEvaluator;
import com.puresol.coding.lang.api.ProgrammingLanguage;
import com.puresol.uhura.source.UnspecifiedSourceCodeLocation;
import com.puresol.uhura.ust.eval.EvaluationException;

public class SLOCEvaluator extends AbstractEvaluator {

    private static final long serialVersionUID = -5093217611195212999L;

    private final EvaluatorStore store;

    public SLOCEvaluator(AnalysisRun analysisRun, HashIdFileTree path) {
	super(SLOCMetricCalculator.NAME, SLOCMetricCalculator.DESCRIPTION,
		analysisRun, path);
	store = createEvaluatorStore();
    }

    @Override
    protected void processFile(CodeAnalysis analysis)
	    throws InterruptedException, EvaluationException {
	if (store.hasFileResults(analysis.getAnalyzedFile().getHashId())) {
	    return;
	}
	SLOCFileResults results = new SLOCFileResults();
	ProgrammingLanguages programmingLanguages = ProgrammingLanguages
		.createInstance();
	try {
	    ProgrammingLanguage language = programmingLanguages.findByName(
		    analysis.getLanguageName(), analysis.getLanguageVersion());

	    for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
		SLOCMetricCalculator metric = new SLOCMetricCalculator(
			getAnalysisRun(), language, codeRange);
		execute(metric);
		results.add(new SLOCResult(analysis.getAnalyzedFile()
			.getSourceLocation(), codeRange.getType(), codeRange
			.getCanonicalName(), metric.getSLOCResult(), metric
			.getQuality()));
	    }
	} finally {
	    IOUtils.closeQuietly(programmingLanguages);
	}
	store.storeFileResults(analysis.getAnalyzedFile().getHashId(), results);
    }

    @Override
    public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return SLOCMetricCalculator.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    protected void processDirectory(HashIdFileTree directory)
	    throws InterruptedException {
	if (store.hasDirectoryResults(directory.getHashId())) {
	    return;
	}
	SLOCResult results = null;
	for (HashIdFileTree child : directory.getChildren()) {
	    if (child.isFile()) {
		results = processFile(directory, results, child);
	    } else {
		results = processSubDirectory(directory, results, child);
	    }
	}
	SLOCDirectoryResults finalResults = new SLOCDirectoryResults(results);
	store.storeDirectoryResults(directory.getHashId(), finalResults);
    }

    private SLOCResult processFile(HashIdFileTree directory,
	    SLOCResult results, HashIdFileTree child) {
	if (store.hasFileResults(child.getHashId())) {
	    SLOCFileResults slocResults = (SLOCFileResults) store
		    .readFileResults(child.getHashId());
	    for (SLOCResult result : slocResults.getResults()) {
		if (result.getCodeRangeType() == CodeRangeType.FILE) {
		    results = combine(directory, results, result);
		    break;
		}
	    }
	}
	return results;
    }

    private SLOCResult processSubDirectory(HashIdFileTree directory,
	    SLOCResult results, HashIdFileTree child) {
	if (store.hasDirectoryResults(child.getHashId())) {
	    SLOCDirectoryResults slocResults = (SLOCDirectoryResults) store
		    .readDirectoryResults(child.getHashId());
	    results = combine(directory, results, slocResults.getResult());
	}
	return results;
    }

    private SLOCResult combine(HashIdFileTree directory, SLOCResult results,
	    SLOCResult result) {
	if (result != null) {
	    if (results == null) {
		results = new SLOCResult(new UnspecifiedSourceCodeLocation(),
			CodeRangeType.DIRECTORY, directory.getName(),
			result.getSLOCMetric(), result.getQuality());
	    } else {
		results = SLOCResult.combine(results, result);
	    }
	}
	return results;
    }

    @Override
    protected void processProject() throws InterruptedException {
	// TODO Auto-generated method stub
    }
}
