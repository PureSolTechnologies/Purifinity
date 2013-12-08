package com.puresoltechnologies.purifinity.coding.metrics.mccabe;

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

public class McCabeMetricEvaluator extends AbstractEvaluator {

	private static final long serialVersionUID = -5093217611195212999L;

	private static final Set<ConfigurationParameter<?>> configurationParameters = new HashSet<>();

	private final EvaluatorStore store;

	public McCabeMetricEvaluator(AnalysisRun analysisRun, HashIdFileTree path) {
		super(McCabeMetric.NAME, McCabeMetric.DESCRIPTION, analysisRun, path);
		store = createEvaluatorStore();
	}

	@Override
	public Set<ConfigurationParameter<?>> getAvailableConfigurationParameters() {
		return configurationParameters;
	}

	@Override
	protected void processFile(CodeAnalysis analysis)
			throws InterruptedException, UniversalSyntaxTreeEvaluationException {
		McCabeMetricFileResults results = new McCabeMetricFileResults();
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
						.getCanonicalName(), metric.getCyclomaticNumber(),
						metric.getQuality()));
			}
		} finally {
			IOUtils.closeQuietly(programmingLanguages);
		}
		store.storeFileResults(analysis.getAnalyzedFile().getHashId(), results);
	}

	@Override
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return McCabeMetric.EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	protected void processDirectory(HashIdFileTree directory)
			throws InterruptedException {
		HashId hashId = directory.getHashId();
		if (store.hasDirectoryResults(hashId)) {
			return;
		}
		McCabeMetricDirectoryResults finalResults = createDirectoryResults(directory);
		if (finalResults != null) {
			store.storeDirectoryResults(directory.getHashId(), finalResults);
		}
	}

	private McCabeMetricDirectoryResults createDirectoryResults(
			HashIdFileTree directory) {
		QualityLevel qualityLevel = null;
		McCabeMetricResult metricResults = null;
		for (HashIdFileTree child : directory.getChildren()) {
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

	private McCabeMetricResult combine(HashIdFileTree directory,
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
	protected void processProject() throws InterruptedException {
		if (store.hasProjectResults(getAnalysisRun())) {
			return;
		}
		HashIdFileTree directory = getAnalysisRun().getFileTree();
		McCabeMetricDirectoryResults finalResults = createDirectoryResults(directory);
		if (finalResults != null) {
			store.storeDirectoryResults(directory.getHashId(), finalResults);
		}
	}
}
