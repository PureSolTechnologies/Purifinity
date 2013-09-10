package com.puresol.purifinity.coding.metrics.sloc;

import java.util.Set;

import org.apache.commons.io.IOUtils;

import com.puresol.commons.utils.HashId;
import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.analysis.api.AnalyzedCode;
import com.puresol.purifinity.coding.analysis.api.CodeAnalysis;
import com.puresol.purifinity.coding.analysis.api.CodeRange;
import com.puresol.purifinity.coding.analysis.api.CodeRangeType;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresol.purifinity.coding.analysis.api.ProgrammingLanguages;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStore;
import com.puresol.purifinity.coding.evaluation.impl.AbstractEvaluator;
import com.puresol.purifinity.coding.evaluation.iso9126.QualityCharacteristic;
import com.puresol.purifinity.coding.lang.api.ProgrammingLanguage;
import com.puresol.purifinity.uhura.source.UnspecifiedSourceCodeLocation;
import com.puresol.purifinity.uhura.ust.eval.UniversalSyntaxTreeEvaluationException;

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
			throws InterruptedException, UniversalSyntaxTreeEvaluationException {
		AnalyzedCode analyzedFile = analysis.getAnalyzedFile();
		HashId hashId = analyzedFile.getHashId();
		if (store.hasFileResults(hashId)) {
			return;
		}
		ProgrammingLanguages programmingLanguages = ProgrammingLanguages
				.createInstance();
		try {
			ProgrammingLanguage language = programmingLanguages.findByName(
					analysis.getLanguageName(), analysis.getLanguageVersion());

			SLOCFileResults results = new SLOCFileResults();
			for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
				SLOCMetricCalculator metric = new SLOCMetricCalculator(
						getAnalysisRun(), language, codeRange);
				execute(metric);
				results.add(new SLOCResult(analyzedFile.getSourceLocation(),
						codeRange.getType(), codeRange.getCanonicalName(),
						metric.getSLOCResult(), metric.getQuality()));
			}
			store.storeFileResults(hashId, results);
		} finally {
			IOUtils.closeQuietly(programmingLanguages);
		}
	}

	@Override
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return SLOCMetricCalculator.EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	protected void processDirectory(HashIdFileTree directory)
			throws InterruptedException {
		HashId hashId = directory.getHashId();
		if (store.hasDirectoryResults(hashId)) {
			return;
		}
		SLOCDirectoryResults finalResults = createDirectoryResults(directory);
		if (finalResults != null) {
			store.storeDirectoryResults(hashId, finalResults);
		}
	}

	private SLOCDirectoryResults createDirectoryResults(HashIdFileTree directory) {
		SLOCResult results = null;
		for (HashIdFileTree child : directory.getChildren()) {
			if (child.isFile()) {
				results = processFile(directory, results, child);
			} else {
				results = processSubDirectory(directory, results, child);
			}
		}
		return new SLOCDirectoryResults(results);
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
		if (store.hasProjectResults(getAnalysisRun())) {
			return;
		}
		HashIdFileTree directory = getAnalysisRun().getFileTree();
		SLOCDirectoryResults finalResults = createDirectoryResults(directory);
		if (finalResults != null) {
			store.storeProjectResults(getAnalysisRun(), finalResults);
		}
	}
}
