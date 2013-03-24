package com.puresol.coding.metrics.sloc;

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

public class SLOCEvaluator extends AbstractEvaluator {

	private static final long serialVersionUID = -5093217611195212999L;

	private final EvaluatorStore store;

	public SLOCEvaluator(AnalysisRun analysisRun, HashIdFileTree path) {
		super(SLOCMetricCalculator.NAME, SLOCMetricCalculator.DESCRIPTION,
				analysisRun, path);
		store = getEvaluatorStore();
	}

	@Override
	protected void processFile(CodeAnalysis analysis)
			throws InterruptedException, EvaluationException {
		SLOCResults results = new SLOCResults();
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
						.getName(), metric.getSLOCResult(), metric.getQuality()));
			}
		} finally {
			IOUtils.closeQuietly(programmingLanguages);
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
		SLOCResult results = null;
		for (HashIdFileTree child : directory.getChildren()) {
			if (child.isFile()) {
				results = processFile(directory, results, child);
			} else {
				results = processSubDirectory(directory, results, child);
			}
		}
		SLOCResults finalResults = new SLOCResults();
		finalResults.add(results);
		store.storeDirectoryResults(directory.getHashId(), finalResults);
	}

	private SLOCResult processFile(HashIdFileTree directory,
			SLOCResult results, HashIdFileTree child) {
		SLOCResults slocResults = (SLOCResults) store
				.readFileResults(child.getHashId());
		for (SLOCResult result : slocResults.getResults()) {
			if (result.getCodeRangeType() == CodeRangeType.FILE) {
				results = combine(directory, results, result);
				break;
			}
		}
		return results;
	}

	private SLOCResult processSubDirectory(HashIdFileTree directory,
			SLOCResult results, HashIdFileTree child) {
		SLOCResults slocResults = (SLOCResults) store
				.readDirectoryResults(child.getHashId());
		if (slocResults.getResults().size() != 1) {
			throw new RuntimeException(
					"A directory should only have one dataset with aggregated SLOC.");
		}
		results = combine(directory, results, slocResults.getResults()
				.get(0));
		return results;
	}

	private SLOCResult combine(HashIdFileTree directory, SLOCResult results,
			SLOCResult result) {
		if (results == null) {
			results = new SLOCResult(new UnspecifiedSourceCodeLocation(),
					CodeRangeType.DIRECTORY, directory.getName(),
					result.getSLOCMetric(), result.getQuality());
		} else {
			results = SLOCResult.combine(results, result);
		}
		return results;
	}

	@Override
	protected void processProject() throws InterruptedException {
		// TODO Auto-generated method stub
	}
}
