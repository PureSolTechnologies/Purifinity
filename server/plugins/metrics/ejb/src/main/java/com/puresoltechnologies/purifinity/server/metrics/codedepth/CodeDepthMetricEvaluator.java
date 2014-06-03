package com.puresoltechnologies.purifinity.server.metrics.codedepth;

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
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.codedepth.CodeDepthDirectoryResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.codedepth.CodeDepthFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.codedepth.CodeDepthResult;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.server.core.api.analysis.ProgrammingLanguages;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluator;

/**
 * This evaluator calculates the nesting depth of the source code. A too deep
 * nesting leads into a hard understandable code and a maintainability
 * nightmare.
 * 
 * @author Rick-Rainer Ludwig
 */
public class CodeDepthMetricEvaluator extends AbstractEvaluator {

	private static final long serialVersionUID = -5093217611195212999L;

	private static final Set<ConfigurationParameter<?>> configurationParameters = new HashSet<>();

	private final EvaluatorStore store;

	public CodeDepthMetricEvaluator(AnalysisRun analysisRun,
			AnalysisFileTree path) {
		super(CodeDepthMetric.NAME, CodeDepthMetric.DESCRIPTION, analysisRun,
				path);
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
			ProgrammingLanguage language = programmingLanguages.findByName(
					analysis.getLanguageName(), analysis.getLanguageVersion());

			CodeDepthFileResults results = new CodeDepthFileResults();
			HashId hashId = analysis.getAnalysisInformation().getHashId();
			SourceCodeLocation sourceCodeLocation = getAnalysisRun()
					.findTreeNode(hashId).getSourceCodeLocation();
			for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
				CodeDepthMetric metric = new CodeDepthMetric(getAnalysisRun(),
						language, codeRange);
				execute(metric);
				SourceCodeQuality quality = metric.getQuality();
				results.add(new CodeDepthResult(sourceCodeLocation, codeRange
						.getType(), codeRange.getCanonicalName(), metric
						.getMaxDepth(), quality));
			}
			return results;
		}
	}

	@Override
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return CodeDepthMetric.EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	protected MetricDirectoryResults processDirectory(AnalysisFileTree directory)
			throws InterruptedException, EvaluationStoreException {
		CodeDepthDirectoryResults directoryResults = new CodeDepthDirectoryResults(
				new UnspecifiedSourceCodeLocation(), CodeRangeType.DIRECTORY,
				directory.getName());
		int maxDepth = 0;
		for (AnalysisFileTree child : directory.getChildren()) {
			QualityLevel childLevel;
			if (child.isFile()) {
				CodeDepthFileResults fileResults = (CodeDepthFileResults) store
						.readFileResults(child.getHashId());
				if (fileResults == null) {
					continue;
				}
				childLevel = fileResults.getQualityLevel();
				for (CodeDepthResult result : fileResults.getResults()) {
					maxDepth = Math.max(maxDepth, result.getMaxDepth());
				}
			} else {
				CodeDepthDirectoryResults childDirectoryResults = (CodeDepthDirectoryResults) store
						.readDirectoryResults(child.getHashId());
				if (childDirectoryResults == null) {
					continue;
				}
				childLevel = childDirectoryResults.getQualityLevel();
				maxDepth = Math.max(maxDepth,
						childDirectoryResults.getMaxDepth());
			}
			if (childLevel != null) {
				directoryResults.addQualityLevel(childLevel);
				directoryResults.setMaxDepth(maxDepth);
			}
		}
		return directoryResults;
	}

	@Override
	protected MetricDirectoryResults processProject()
			throws InterruptedException, EvaluationStoreException {
		AnalysisFileTree directory = getAnalysisRun().getFileTree();
		return processDirectory(directory);
	}
}
