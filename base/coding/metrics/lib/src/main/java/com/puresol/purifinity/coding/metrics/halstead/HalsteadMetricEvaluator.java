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
import com.puresol.purifinity.coding.evaluation.api.QualityLevel;
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
		HalsteadMetricDirectoryResults finalResults = createDirectoryResults(directory);
		if (finalResults != null) {
			store.storeDirectoryResults(directory.getHashId(), finalResults);
		}
	}

	private HalsteadMetricDirectoryResults createDirectoryResults(
			HashIdFileTree directory) {
		if (store.hasDirectoryResults(directory.getHashId())) {
			return null;
		}
		QualityLevel qualityLevel = null;
		HalsteadMetricResult metricResults = null;
		for (HashIdFileTree child : directory.getChildren()) {
			if (child.isFile()) {
				if (store.hasFileResults(child.getHashId())) {
					HalsteadMetricFileResults results = (HalsteadMetricFileResults) store
							.readFileResults(child.getHashId());
					for (HalsteadMetricResult result : results.getResults()) {
						if (result.getCodeRangeType() == CodeRangeType.FILE) {
							metricResults = combine(directory, metricResults,
									result);
							break;
						}
					}
					qualityLevel = combine(qualityLevel,
							results.getQualityLevel());
				}
			} else {
				if (store.hasDirectoryResults(child.getHashId())) {
					HalsteadMetricDirectoryResults results = (HalsteadMetricDirectoryResults) store
							.readDirectoryResults(child.getHashId());
					metricResults = combine(directory, metricResults,
							results.getResult());
					qualityLevel = combine(qualityLevel,
							results.getQualityLevel());
				}
			}
		}
		HalsteadMetricDirectoryResults finalResults = new HalsteadMetricDirectoryResults(
				metricResults);
		finalResults.addQualityLevel(qualityLevel);
		return finalResults;
	}

	private QualityLevel combine(QualityLevel overallQualityLevel,
			QualityLevel qualityLevel) {
		if (overallQualityLevel == null) {
			return qualityLevel;
		}
		overallQualityLevel.add(qualityLevel);
		return overallQualityLevel;
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
		HashIdFileTree directory = getAnalysisRun().getFileTree();
		HalsteadMetricDirectoryResults finalResults = createDirectoryResults(directory);
		if (finalResults != null) {
			store.storeDirectoryResults(directory.getHashId(), finalResults);
		}
	}
}
