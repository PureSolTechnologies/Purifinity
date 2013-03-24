package com.puresol.coding.metrics.mccabe;

import java.util.List;

import org.apache.commons.io.IOUtils;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.CodeAnalysis;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.analysis.api.ProgrammingLanguages;
import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.coding.evaluation.api.QualityCharacteristic;
import com.puresol.coding.evaluation.impl.AbstractEvaluator;
import com.puresol.coding.lang.api.ProgrammingLanguage;
import com.puresol.uhura.ust.eval.EvaluationException;

public class McCabeMetricEvaluator extends AbstractEvaluator {

    private static final long serialVersionUID = -5093217611195212999L;

    private final EvaluatorStore store;

    public McCabeMetricEvaluator(AnalysisRun analysisRun, HashIdFileTree path) {
	super(McCabeMetric.NAME, McCabeMetric.DESCRIPTION, analysisRun, path);
	store = getEvaluatorStore();
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
		results.add(new McCabeMetricResult(analysis
			.getAnalyzedFile().getSourceLocation(), codeRange
			.getType(), codeRange.getName(), metric
			.getCyclomaticNumber(), metric.getQuality()));
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
	// McCabeMetricDirectoryResults finalResults = new
	// McCabeMetricDirectoryResults();
	// for (HashIdFileTree child : directory.getChildren()) {
	// HashId hashId = child.getHashId();
	// if (child.isFile()) {
	// if (store.hasFileResults(hashId)) {
	// McCabeMetricFileResults fileResults = (McCabeMetricFileResults) store
	// .readFileResults(hashId);
	// for (McCabeMetricFileResult results : fileResults) {
	// if (results.getCodeRangeType() == CodeRangeType.FILE) {
	// finalResults.add(new McCabeMetricDirectoryResult(
	// results.getSourceCodeLocation(), results
	// .getCyclomaticComplexity()));
	// }
	// }
	// }
	// } else {
	// if (store.hasDirectoryResults(hashId)) {
	// McCabeMetricDirectoryResults directoryResults =
	// (McCabeMetricDirectoryResults) store
	// .readDirectoryResults(hashId);
	// int cyclomaticComplexity = 0;
	// for (McCabeMetricDirectoryResult result : directoryResults) {
	// cyclomaticComplexity += result.getvG();
	// }
	// finalResults.add(new McCabeMetricDirectoryResult(child
	// .getName(), cyclomaticComplexity));
	// }
	// }
	// }
	// store.storeDirectoryResults(directory.getHashId(), finalResults);
    }

    @Override
    protected void processProject() throws InterruptedException {
	// TODO Auto-generated method stub

    }
}
