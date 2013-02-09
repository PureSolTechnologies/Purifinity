package com.puresol.coding.metrics.codedepth;

import java.util.List;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.CodeAnalysis;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.analysis.api.ProgrammingLanguage;
import com.puresol.coding.analysis.api.quality.QualityCharacteristic;
import com.puresol.coding.analysis.api.storage.EvaluatorStore;

public class CodeDepthEvaluator extends AbstractEvaluator {

	private static final long serialVersionUID = -5093217611195212999L;

	private final EvaluatorStore store;

	public CodeDepthEvaluator(AnalysisRun analysisRun) {
		super(CodeDepthMetric.NAME, CodeDepthMetric.DESCRIPTION, analysisRun);
		store = getEvaluatorStore();
	}

	@Override
	protected void processFile(CodeAnalysis analysis)
			throws InterruptedException {
		CodeDepthFileResults results = new CodeDepthFileResults();
		ProgrammingLanguage language = ProgrammingLanguages.findByName(
				analysis.getLanguageName(), analysis.getLanguageVersion());
		for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
			CodeDepthMetric metric = new CodeDepthMetric(getAnalysisRun(),
					language, codeRange);
			metric.schedule();
			metric.join();
			results.add(new CodeDepthFileResult(analysis.getAnalyzedFile()
					.getLocation(), codeRange.getType(), codeRange.getName(),
					metric.getMaxDepth(), metric.getQuality()));
		}
		store.storeFileResults(analysis.getAnalyzedFile().getHashId(), results);
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return CodeDepthMetric.EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	protected void processDirectory(HashIdFileTree directory)
			throws InterruptedException {
		// intentionally left blank
	}

	@Override
	protected void processProject() throws InterruptedException {
		// intentionally left blank
	}
}
