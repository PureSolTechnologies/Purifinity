package com.puresol.purifinity.coding.metrics.halstead;

import java.util.Set;

import org.apache.commons.io.IOUtils;

import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
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

public class HalsteadMetricEvaluator extends AbstractEvaluator {

	private static final long serialVersionUID = -5093217611195212999L;

	private final EvaluatorStore store;

	public HalsteadMetricEvaluator(AnalysisRun analysisRun, HashIdFileTree path) {
		super(HalsteadMetric.NAME, HalsteadMetric.DESCRIPTION, analysisRun,
				path);
		store = createEvaluatorStore();
	}

	@Override
	protected void processFile(CodeAnalysis analysis)
			throws InterruptedException, UniversalSyntaxTreeEvaluationException {
		HalsteadMetricFileResults results = new HalsteadMetricFileResults();
		ProgrammingLanguages programmingLanguages = ProgrammingLanguages
				.createInstance();
		try {
			ProgrammingLanguage language = programmingLanguages.findByName(
					analysis.getLanguageName(), analysis.getLanguageVersion());

			for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
				HalsteadMetric metric = new HalsteadMetric(getAnalysisRun(),
						language, codeRange);
				execute(metric);
				results.add(new HalsteadMetricResult(analysis.getAnalyzedFile()
						.getSourceLocation(), codeRange.getType(), codeRange
						.getCanonicalName(), metric.getHalsteadResults(),
						metric.getQuality()));
			}
		} finally {
			IOUtils.closeQuietly(programmingLanguages);
		}
		store.storeFileResults(analysis.getAnalyzedFile().getHashId(), results);
	}

	@Override
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return HalsteadMetric.EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	protected void processDirectory(HashIdFileTree directory)
			throws InterruptedException {
		if (store.hasDirectoryResults(directory.getHashId())) {
			return;
		}
		HalsteadMetricResult results = null;
		for (HashIdFileTree child : directory.getChildren()) {
			if (child.isFile()) {
				results = processFile(directory, results, child);
			} else {
				results = processSubDirectory(directory, results, child);
			}
		}
		HalsteadMetricDirectoryResults finalResults = new HalsteadMetricDirectoryResults(
				results);
		store.storeDirectoryResults(directory.getHashId(), finalResults);
	}

	private HalsteadMetricResult processFile(HashIdFileTree directory,
			HalsteadMetricResult results, HashIdFileTree child) {
		if (store.hasFileResults(child.getHashId())) {
			HalsteadMetricFileResults fileResults = (HalsteadMetricFileResults) store
					.readFileResults(child.getHashId());
			for (HalsteadMetricResult result : fileResults.getResults()) {
				if (result.getCodeRangeType() == CodeRangeType.FILE) {
					results = combine(directory, results, result);
					break;
				}
			}
		}
		return results;
	}

	private HalsteadMetricResult processSubDirectory(HashIdFileTree directory,
			HalsteadMetricResult results, HashIdFileTree child) {
		if (store.hasDirectoryResults(child.getHashId())) {
			HalsteadMetricDirectoryResults halsteadResults = (HalsteadMetricDirectoryResults) store
					.readDirectoryResults(child.getHashId());
			results = combine(directory, results, halsteadResults.getResult());
		}
		return results;
	}

	private HalsteadMetricResult combine(HashIdFileTree directory,
			HalsteadMetricResult results, HalsteadMetricResult result) {
		if (result != null) {
			if (results == null) {
				results = new HalsteadMetricResult(
						new UnspecifiedSourceCodeLocation(),
						CodeRangeType.DIRECTORY, directory.getName(),
						result.getHalsteadResult(), result.getQuality());
			} else {
				results = HalsteadMetricResult.combine(results, result);
			}
		}
		return results;
	}

	@Override
	protected void processProject() throws InterruptedException {
		// intentionally left blank
	}
}
