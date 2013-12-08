package com.puresoltechnologies.purifinity.coding.metrics.halstead;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.IOUtils;

import com.puresoltechnologies.commons.configuration.ConfigurationParameter;
import com.puresoltechnologies.commons.utils.HashId;
import com.puresoltechnologies.parser.impl.source.UnspecifiedSourceCodeLocation;
import com.puresoltechnologies.parser.impl.ust.eval.UniversalSyntaxTreeEvaluationException;
import com.puresoltechnologies.purifinity.coding.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.coding.analysis.api.CodeAnalysis;
import com.puresoltechnologies.purifinity.coding.analysis.api.CodeRange;
import com.puresoltechnologies.purifinity.coding.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresoltechnologies.purifinity.coding.analysis.api.ProgrammingLanguages;
import com.puresoltechnologies.purifinity.coding.evaluation.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.coding.evaluation.api.QualityLevel;
import com.puresoltechnologies.purifinity.coding.evaluation.impl.AbstractEvaluator;
import com.puresoltechnologies.purifinity.coding.evaluation.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.coding.lang.api.ProgrammingLanguage;

public class HalsteadMetricEvaluator extends AbstractEvaluator {

	private static final long serialVersionUID = -5093217611195212999L;

	private static final Set<ConfigurationParameter<?>> configurationParameters = new HashSet<>();

	private final EvaluatorStore store;

	public HalsteadMetricEvaluator(AnalysisRun analysisRun, HashIdFileTree path) {
		super(HalsteadMetric.NAME, HalsteadMetric.DESCRIPTION, analysisRun,
				path);
		store = createEvaluatorStore();
	}

	@Override
	public Set<ConfigurationParameter<?>> getAvailableConfigurationParameters() {
		return configurationParameters;
	}

	@Override
	protected void processFile(CodeAnalysis analysis)
			throws InterruptedException, UniversalSyntaxTreeEvaluationException {
		ProgrammingLanguages programmingLanguages = ProgrammingLanguages
				.createInstance();
		try {
			ProgrammingLanguage language = programmingLanguages.findByName(
					analysis.getLanguageName(), analysis.getLanguageVersion());

			HalsteadMetricFileResults results = new HalsteadMetricFileResults();
			for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
				HalsteadMetric metric = new HalsteadMetric(getAnalysisRun(),
						language, codeRange);
				execute(metric);
				results.add(new HalsteadMetricResult(analysis.getAnalyzedFile()
						.getSourceLocation(), codeRange.getType(), codeRange
						.getCanonicalName(), metric.getHalsteadResults(),
						metric.getQuality()));
			}
			store.storeFileResults(analysis.getAnalyzedFile().getHashId(),
					results);
		} finally {
			IOUtils.closeQuietly(programmingLanguages);
		}
	}

	@Override
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return HalsteadMetric.EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	protected void processDirectory(HashIdFileTree directory)
			throws InterruptedException {
		HashId hashId = directory.getHashId();
		if (store.hasDirectoryResults(hashId)) {
			return;
		}
		HalsteadMetricDirectoryResults finalResults = createDirectoryResults(directory);
		if (finalResults != null) {
			store.storeDirectoryResults(hashId, finalResults);
		}
	}

	private HalsteadMetricDirectoryResults createDirectoryResults(
			HashIdFileTree directory) {
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
					qualityLevel = QualityLevel.combine(qualityLevel,
							results.getQualityLevel());
				}
			} else {
				if (store.hasDirectoryResults(child.getHashId())) {
					HalsteadMetricDirectoryResults results = (HalsteadMetricDirectoryResults) store
							.readDirectoryResults(child.getHashId());
					metricResults = combine(directory, metricResults,
							results.getResult());
					qualityLevel = QualityLevel.combine(qualityLevel,
							results.getQualityLevel());
				}
			}
		}
		if (metricResults == null) {
			return null;
		}
		HalsteadMetricDirectoryResults finalResults = new HalsteadMetricDirectoryResults(
				metricResults);
		finalResults.addQualityLevel(qualityLevel);
		return finalResults;
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
		if (store.hasProjectResults(getAnalysisRun())) {
			return;
		}
		HashIdFileTree directory = getAnalysisRun().getFileTree();
		HalsteadMetricDirectoryResults finalResults = createDirectoryResults(directory);
		if (finalResults != null) {
			store.storeDirectoryResults(directory.getHashId(), finalResults);
		}
	}
}
