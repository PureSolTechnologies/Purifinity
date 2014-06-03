package com.puresoltechnologies.purifinity.server.metrics.mccabe;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.misc.ConfigurationParameter;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.parsers.source.UnspecifiedSourceCodeLocation;
import com.puresoltechnologies.parsers.ust.eval.UniversalSyntaxTreeEvaluationException;
import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguage;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricFileResults;
import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.server.core.api.analysis.ProgrammingLanguages;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluator;

public class McCabeMetricEvaluator extends AbstractEvaluator {

	private static final long serialVersionUID = -5093217611195212999L;

	private static final Set<ConfigurationParameter<?>> configurationParameters = new HashSet<>();

	private final EvaluatorStore store;

	public McCabeMetricEvaluator(AnalysisRun analysisRun, AnalysisFileTree path) {
		super(McCabeMetric.NAME, McCabeMetric.DESCRIPTION, analysisRun, path);
		store = getEvaluatorStore();
	}

	@Override
	public Set<ConfigurationParameter<?>> getAvailableConfigurationParameters() {
		return configurationParameters;
	}

	@Override
	protected MetricFileResults processFile(CodeAnalysis analysis)
			throws InterruptedException,
			UniversalSyntaxTreeEvaluationException, EvaluationStoreException {
		try (ProgrammingLanguages programmingLanguages = ProgrammingLanguages
				.createInstance()) {
			McCabeMetricFileResults results = new McCabeMetricFileResults();
			HashId hashId = analysis.getAnalysisInformation().getHashId();
			ProgrammingLanguage language = programmingLanguages.findByName(
					analysis.getLanguageName(), analysis.getLanguageVersion());
			SourceCodeLocation sourceCodeLocation = getAnalysisRun()
					.findTreeNode(hashId).getSourceCodeLocation();
			for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
				McCabeMetric metric = new McCabeMetric(getAnalysisRun(),
						language, codeRange);
				execute(metric);
				results.add(new McCabeMetricResult(sourceCodeLocation,
						codeRange.getType(), codeRange.getCanonicalName(),
						metric.getCyclomaticNumber(), metric.getQuality()));
			}
			return results;
		}
	}

	@Override
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return McCabeMetric.EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	protected MetricDirectoryResults processDirectory(AnalysisFileTree directory)
			throws InterruptedException, EvaluationStoreException {
		QualityLevel qualityLevel = null;
		McCabeMetricResult metricResults = null;
		for (AnalysisFileTree child : directory.getChildren()) {
			if (child.isFile()) {
				if (store.hasFileResults(child.getHashId())) {
					McCabeMetricFileResults results = (McCabeMetricFileResults) store
							.readFileResults(child.getHashId());
					for (McCabeMetricResult result : results.getResults()) {
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
					McCabeMetricDirectoryResults results = (McCabeMetricDirectoryResults) store
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
		McCabeMetricDirectoryResults finalResults = new McCabeMetricDirectoryResults(
				metricResults);
		finalResults.addQualityLevel(qualityLevel);
		return finalResults;
	}

	private McCabeMetricResult combine(AnalysisFileTree directory,
			McCabeMetricResult results, McCabeMetricResult result) {
		if (result != null) {
			if (results == null) {
				results = new McCabeMetricResult(
						new UnspecifiedSourceCodeLocation(),
						CodeRangeType.DIRECTORY, directory.getName(),
						result.getCyclomaticComplexity(), result.getQuality());
			} else {
				results = McCabeMetricDirectoryResults.combine(results, result);
			}
		}
		return results;
	}

	@Override
	protected MetricDirectoryResults processProject()
			throws InterruptedException, EvaluationStoreException {
		AnalysisFileTree directory = getAnalysisRun().getFileTree();
		return processDirectory(directory);
	}
}
