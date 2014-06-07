package com.puresoltechnologies.purifinity.server.metrics.sloc;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.puresoltechnologies.commons.misc.ConfigurationParameter;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.parsers.source.UnspecifiedSourceCodeLocation;
import com.puresoltechnologies.parsers.ust.eval.UniversalSyntaxTreeEvaluationException;
import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguage;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricFileResults;
import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.sloc.SLOCDirectoryResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.sloc.SLOCFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.sloc.SLOCResult;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.server.core.api.analysis.ProgrammingLanguages;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluator;

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
@Stateless
@Remote(Evaluator.class)
public class SLOCEvaluator extends AbstractEvaluator {

	private static final long serialVersionUID = -5093217611195212999L;

	private static final Set<ConfigurationParameter<?>> configurationParameters = new HashSet<>();

	private final EvaluatorStore store;

	public SLOCEvaluator() {
		super(SLOCMetricCalculator.NAME, SLOCMetricCalculator.DESCRIPTION);
		store = getEvaluatorStore();
	}

	@Override
	public Set<ConfigurationParameter<?>> getAvailableConfigurationParameters() {
		return configurationParameters;
	}

	@Override
	protected MetricFileResults processFile(AnalysisRun analysisRun,
			CodeAnalysis analysis) throws InterruptedException,
			UniversalSyntaxTreeEvaluationException, EvaluationStoreException {
		AnalysisInformation analyzedFile = analysis.getAnalysisInformation();
		HashId hashId = analyzedFile.getHashId();
		if (store.hasFileResults(hashId)) {
			return null;
		}

		try (ProgrammingLanguages programmingLanguages = ProgrammingLanguages
				.createInstance()) {
			ProgrammingLanguage language = programmingLanguages.findByName(
					analysis.getLanguageName(), analysis.getLanguageVersion());

			SLOCFileResults results = new SLOCFileResults();
			SourceCodeLocation sourceCodeLocation = analysisRun.findTreeNode(
					hashId).getSourceCodeLocation();
			for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
				SLOCMetricCalculator metric = new SLOCMetricCalculator(
						analysisRun, language, codeRange);
				execute(metric);

				results.add(new SLOCResult(sourceCodeLocation, codeRange
						.getType(), codeRange.getCanonicalName(), metric
						.getSLOCResult(), metric.getQuality()));
			}
			return results;
		}
	}

	@Override
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return SLOCMetricCalculator.EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	protected MetricDirectoryResults processDirectory(AnalysisRun analysisRun,
			AnalysisFileTree directory) throws InterruptedException,
			EvaluationStoreException {
		QualityLevel qualityLevel = null;
		SLOCResult metricResults = null;
		for (AnalysisFileTree child : directory.getChildren()) {
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

	private SLOCResult combine(AnalysisFileTree directory, SLOCResult results,
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
	protected MetricDirectoryResults processProject(AnalysisRun analysisRun)
			throws InterruptedException, EvaluationStoreException {
		AnalysisFileTree directory = analysisRun.getFileTree();
		return processDirectory(analysisRun, directory);
	}
}
