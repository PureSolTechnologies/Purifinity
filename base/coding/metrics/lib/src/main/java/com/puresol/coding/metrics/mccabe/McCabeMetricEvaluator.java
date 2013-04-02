package com.puresol.coding.metrics.mccabe;

import java.util.List;

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

public class McCabeMetricEvaluator extends AbstractEvaluator {

    private static final long serialVersionUID = -5093217611195212999L;

    private final EvaluatorStore store;

    public McCabeMetricEvaluator(AnalysisRun analysisRun, HashIdFileTree path) {
	super(McCabeMetric.NAME, McCabeMetric.DESCRIPTION, analysisRun, path);
	store = createEvaluatorStore();
    }

    @Override
    protected void processFile(CodeAnalysis analysis)
	    throws InterruptedException, EvaluationException {
	McCabeMetricResults results = new McCabeMetricResults();
	ProgrammingLanguages programmingLanguages = ProgrammingLanguages
		.createInstance();
	try {
	    ProgrammingLanguage language = programmingLanguages.findByName(
		    analysis.getLanguageName(), analysis.getLanguageVersion());

	    for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
		McCabeMetric metric = new McCabeMetric(getAnalysisRun(),
			language, codeRange);
		execute(metric);
		results.add(new McCabeMetricResult(analysis.getAnalyzedFile()
			.getSourceLocation(), codeRange.getType(), codeRange
			.getName(), metric.getCyclomaticNumber(), metric
			.getQuality()));
	    }
	} finally {
	    IOUtils.closeQuietly(programmingLanguages);
	}
	store.storeFileResults(analysis.getAnalyzedFile().getHashId(), results);
    }

    @Override
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return McCabeMetric.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    protected void processDirectory(HashIdFileTree directory)
	    throws InterruptedException {
	if (store.hasDirectoryResults(directory.getHashId())) {
	    return;
	}
	McCabeMetricResult results = null;
	for (HashIdFileTree child : directory.getChildren()) {
	    if (child.isFile()) {
		results = processFile(directory, results, child);
	    } else {
		results = processSubDirectory(directory, results, child);
	    }
	}
	McCabeMetricResults finalResults = new McCabeMetricResults();
	if (results != null) {
	    finalResults.add(results);
	}
	store.storeDirectoryResults(directory.getHashId(), finalResults);
    }

    private McCabeMetricResult processFile(HashIdFileTree directory,
	    McCabeMetricResult results, HashIdFileTree child) {
	if (store.hasFileResults(child.getHashId())) {
	    McCabeMetricResults mcCabeResults = (McCabeMetricResults) store
		    .readFileResults(child.getHashId());
	    for (McCabeMetricResult result : mcCabeResults.getResults()) {
		if (result.getCodeRangeType() == CodeRangeType.FILE) {
		    results = combine(directory, results, result);
		    break;
		}
	    }
	}
	return results;
    }

    private McCabeMetricResult processSubDirectory(HashIdFileTree directory,
	    McCabeMetricResult results, HashIdFileTree child) {
	if (store.hasDirectoryResults(child.getHashId())) {
	    McCabeMetricResults mcCabeResults = (McCabeMetricResults) store
		    .readDirectoryResults(child.getHashId());
	    if (mcCabeResults.getResults().size() == 1) {
		results = combine(directory, results, mcCabeResults
			.getResults().get(0));
	    } else if (mcCabeResults.getResults().size() > 1) {
		throw new RuntimeException(
			"A directory should only have one dataset with aggregated SLOC.");
	    }
	}
	return results;
    }

    private McCabeMetricResult combine(HashIdFileTree directory,
	    McCabeMetricResult results, McCabeMetricResult result) {
	if (results == null) {
	    results = new McCabeMetricResult(
		    new UnspecifiedSourceCodeLocation(),
		    CodeRangeType.DIRECTORY, directory.getName(),
		    result.getCyclomaticComplexity(), result.getQuality());
	} else {
	    results = McCabeMetricResults.combine(results, result);
	}
	return results;
    }

    @Override
    protected void processProject() throws InterruptedException {
	// TODO Auto-generated method stub

    }
}
