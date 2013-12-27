package com.puresoltechnologies.purifinity.framework.evaluation.metrics.codedepth;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.misc.ConfigurationParameter;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.parsers.impl.source.UnspecifiedSourceCodeLocation;
import com.puresoltechnologies.parsers.impl.ust.eval.UniversalSyntaxTreeEvaluationException;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguage;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.analysis.domain.HashIdFileTree;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;
import com.puresoltechnologies.purifinity.framework.analysis.impl.ProgrammingLanguages;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.AbstractEvaluator;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;

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

	public CodeDepthMetricEvaluator(AnalysisRun analysisRun, HashIdFileTree path) {
		super(CodeDepthMetric.NAME, CodeDepthMetric.DESCRIPTION, analysisRun,
				path);
		store = getEvaluatorStore();
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

			CodeDepthFileResults results = new CodeDepthFileResults();
			for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
				CodeDepthMetric metric = new CodeDepthMetric(getAnalysisRun(),
						language, codeRange);
				execute(metric);
				SourceCodeQuality quality = metric.getQuality();
				results.add(new CodeDepthResult(analysis.getAnalyzedFile()
						.getSourceLocation(), codeRange.getType(), codeRange
						.getCanonicalName(), metric.getMaxDepth(), quality));
			}
			store.storeFileResults(analysis.getAnalyzedFile().getHashId(),
					results);
		} finally {
			try {
				programmingLanguages.close();
			} catch (IOException e) {
				// intentionally left blank
			}
		}
	}

	@Override
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return CodeDepthMetric.EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	protected void processDirectory(HashIdFileTree directory)
			throws InterruptedException {
		HashId hashId = directory.getHashId();
		if (store.hasDirectoryResults(hashId)) {
			return;
		}
		CodeDepthDirectoryResults directoryResults = calculateDirectoryResults(directory);
		if (directoryResults != null) {
			store.storeDirectoryResults(hashId, directoryResults);
		}
	}

	private CodeDepthDirectoryResults calculateDirectoryResults(
			HashIdFileTree directory) {
		CodeDepthDirectoryResults directoryResults = new CodeDepthDirectoryResults(
				new UnspecifiedSourceCodeLocation(), CodeRangeType.DIRECTORY,
				directory.getName());
		int maxDepth = 0;
		for (HashIdFileTree child : directory.getChildren()) {
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
	protected void processProject() throws InterruptedException {
		if (store.hasProjectResults(getAnalysisRun())) {
			return;
		}
		HashIdFileTree directory = getAnalysisRun().getFileTree();
		CodeDepthDirectoryResults directoryResults = calculateDirectoryResults(directory);
		if (directoryResults != null) {
			store.storeDirectoryResults(directory.getHashId(), directoryResults);
		}
	}
}