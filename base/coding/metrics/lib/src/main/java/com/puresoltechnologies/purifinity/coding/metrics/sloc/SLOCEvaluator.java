package com.puresoltechnologies.purifinity.coding.metrics.sloc;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.IOUtils;

import com.puresoltechnologies.commons.configuration.ConfigurationParameter;
import com.puresoltechnologies.commons.utils.HashId;
import com.puresoltechnologies.purifinity.coding.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.coding.analysis.api.AnalyzedCode;
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
import com.puresoltechnologies.purifinity.uhura.source.UnspecifiedSourceCodeLocation;
import com.puresoltechnologies.purifinity.uhura.ust.eval.UniversalSyntaxTreeEvaluationException;

/**
 * This evaluator evaluates the Source Lines Of Code metrics which counts the
 * lines in source files and distinguishes between physical lines (all lines),
 * productive lines (lines which contain compilable code), comment lines (lines
 * which contain at least a bit of comment) and blank lines which neither
 * contain comments nor productive code.
 * 
 * The different counts of line types need to have a certain ratio between each
 * other to represent a well balance amount of comments, blank lines and
 * productive lines.
 * 
 * @author Rick-Rainer Ludwig
 */
public class SLOCEvaluator extends AbstractEvaluator {

	private static final long serialVersionUID = -5093217611195212999L;

	private static final Set<ConfigurationParameter<?>> configurationParameters = new HashSet<>();

	private final EvaluatorStore store;

	public SLOCEvaluator(AnalysisRun analysisRun, HashIdFileTree path) {
		super(SLOCMetricCalculator.NAME, SLOCMetricCalculator.DESCRIPTION,
				analysisRun, path);
		store = createEvaluatorStore();
	}

	@Override
	public Set<ConfigurationParameter<?>> getAvailableConfigurationParameters() {
		return configurationParameters;
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
		QualityLevel qualityLevel = null;
		SLOCResult metricResults = null;
		for (HashIdFileTree child : directory.getChildren()) {
			if (child.isFile()) {
				if (store.hasFileResults(child.getHashId())) {
					SLOCFileResults results = (SLOCFileResults) store
							.readFileResults(child.getHashId());
					for (SLOCResult result : results.getResults()) {
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
					SLOCDirectoryResults results = (SLOCDirectoryResults) store
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
		SLOCDirectoryResults finalResults = new SLOCDirectoryResults(
				metricResults);
		finalResults.addQualityLevel(qualityLevel);
		return finalResults;
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
