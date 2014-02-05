package com.puresoltechnologies.purifinity.framework.evaluation.metrics.halstead;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.misc.ConfigurationParameter;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.parsers.api.source.SourceCodeLocation;
import com.puresoltechnologies.parsers.impl.source.UnspecifiedSourceCodeLocation;
import com.puresoltechnologies.parsers.impl.ust.eval.UniversalSyntaxTreeEvaluationException;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguage;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;
import com.puresoltechnologies.purifinity.framework.analysis.impl.ProgrammingLanguages;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.AbstractEvaluator;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;

public class HalsteadMetricEvaluator extends AbstractEvaluator {

	private static final long serialVersionUID = -5093217611195212999L;

	private static final Set<ConfigurationParameter<?>> configurationParameters = new HashSet<>();

	private final EvaluatorStore store;

	public HalsteadMetricEvaluator(AnalysisRun analysisRun,
			AnalysisFileTree path) {
		super(HalsteadMetric.NAME, HalsteadMetric.DESCRIPTION, analysisRun,
				path);
		store = getEvaluatorStore();
	}

	@Override
	public Set<ConfigurationParameter<?>> getAvailableConfigurationParameters() {
		return configurationParameters;
	}

	@Override
	protected void processFile(CodeAnalysis analysis)
			throws InterruptedException,
			UniversalSyntaxTreeEvaluationException, EvaluationStoreException {
		try (ProgrammingLanguages programmingLanguages = ProgrammingLanguages
				.createInstance()) {
			ProgrammingLanguage language = programmingLanguages.findByName(
					analysis.getLanguageName(), analysis.getLanguageVersion());

			HalsteadMetricFileResults results = new HalsteadMetricFileResults();
			HashId hashId = analysis.getAnalysisInformation().getHashId();
			SourceCodeLocation sourceCodeLocation = getAnalysisRun()
					.findTreeNode(hashId).getSourceCodeLocation();
			for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
				HalsteadMetric metric = new HalsteadMetric(getAnalysisRun(),
						language, codeRange);
				execute(metric);
				results.add(new HalsteadMetricResult(sourceCodeLocation,
						codeRange.getType(), codeRange.getCanonicalName(),
						metric.getHalsteadResults(), metric.getQuality()));
			}
			store.storeFileResults(hashId, this, analysis, results);
		}
	}

	@Override
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return HalsteadMetric.EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	protected void processDirectory(AnalysisFileTree directory)
			throws InterruptedException, EvaluationStoreException {
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
			AnalysisFileTree directory) throws EvaluationStoreException {
		QualityLevel qualityLevel = null;
		HalsteadMetricResult metricResults = null;
		for (AnalysisFileTree child : directory.getChildren()) {
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

	private HalsteadMetricResult combine(AnalysisFileTree directory,
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
	protected void processProject() throws InterruptedException,
			EvaluationStoreException {
		if (store
				.hasProjectResults(getAnalysisRun().getInformation().getUUID())) {
			return;
		}
		AnalysisFileTree directory = getAnalysisRun().getFileTree();
		HalsteadMetricDirectoryResults finalResults = createDirectoryResults(directory);
		if (finalResults != null) {
			store.storeDirectoryResults(directory.getHashId(), finalResults);
		}
	}
}
