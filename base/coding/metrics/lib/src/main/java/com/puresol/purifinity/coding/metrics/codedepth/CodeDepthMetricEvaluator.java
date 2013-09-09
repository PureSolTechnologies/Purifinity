package com.puresol.purifinity.coding.metrics.codedepth;

import java.util.Set;

import org.apache.commons.io.IOUtils;

import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.analysis.api.CodeAnalysis;
import com.puresol.purifinity.coding.analysis.api.CodeRange;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresol.purifinity.coding.analysis.api.ProgrammingLanguages;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStore;
import com.puresol.purifinity.coding.evaluation.api.QualityLevel;
import com.puresol.purifinity.coding.evaluation.api.SourceCodeQuality;
import com.puresol.purifinity.coding.evaluation.impl.AbstractEvaluator;
import com.puresol.purifinity.coding.evaluation.iso9126.QualityCharacteristic;
import com.puresol.purifinity.coding.lang.api.ProgrammingLanguage;
import com.puresol.purifinity.uhura.ust.eval.UniversalSyntaxTreeEvaluationException;

public class CodeDepthMetricEvaluator extends AbstractEvaluator {

	private static final long serialVersionUID = -5093217611195212999L;

	private final EvaluatorStore store;

	public CodeDepthMetricEvaluator(AnalysisRun analysisRun, HashIdFileTree path) {
		super(CodeDepthMetric.NAME, CodeDepthMetric.DESCRIPTION, analysisRun,
				path);
		store = createEvaluatorStore();
	}

	@Override
	protected void processFile(CodeAnalysis analysis)
			throws InterruptedException, UniversalSyntaxTreeEvaluationException {
		ProgrammingLanguages programmingLanguages = ProgrammingLanguages
				.createInstance();
		try {
			CodeDepthFileResults results = new CodeDepthFileResults();
			ProgrammingLanguage language = programmingLanguages.findByName(
					analysis.getLanguageName(), analysis.getLanguageVersion());
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
			IOUtils.closeQuietly(programmingLanguages);
		}
	}

	@Override
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return CodeDepthMetric.EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	protected void processDirectory(HashIdFileTree directory)
			throws InterruptedException {
		CodeDepthDirectoryResults directoryResults = new CodeDepthDirectoryResults();
		for (HashIdFileTree child : directory.getChildren()) {
			QualityLevel childLevel;
			if (child.isFile()) {
				CodeDepthFileResults fileResults = (CodeDepthFileResults) store
						.readFileResults(child.getHashId());
				if (fileResults == null) {
					continue;
				}
				childLevel = fileResults.getQualityLevel();
			} else {
				CodeDepthDirectoryResults childDirectoryResults = (CodeDepthDirectoryResults) store
						.readDirectoryResults(child.getHashId());
				if (childDirectoryResults == null) {
					continue;
				}
				childLevel = childDirectoryResults.getQualityLevel();
			}
			directoryResults.addQualityLevel(childLevel);
		}
		store.storeDirectoryResults(directory.getHashId(), directoryResults);
	}

	@Override
	protected void processProject() throws InterruptedException {
		// intentionally left blank
	}
}
