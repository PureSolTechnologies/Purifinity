package com.puresol.coding.metrics.halstead;

import java.util.List;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.CodeAnalysis;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.analysis.api.ProgrammingLanguages;
import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.coding.evaluation.api.QualityCharacteristic;
import com.puresol.coding.evaluation.impl.AbstractEvaluator;
import com.puresol.coding.lang.api.ProgrammingLanguage;
import com.puresol.uhura.ust.eval.EvaluationException;

public class HalsteadMetricEvaluator extends AbstractEvaluator {

	private static final long serialVersionUID = -5093217611195212999L;

	private final EvaluatorStore store;

	public HalsteadMetricEvaluator(AnalysisRun analysisRun) {
		super(HalsteadMetric.NAME, HalsteadMetric.DESCRIPTION, analysisRun);
		store = getEvaluatorStore();
	}

	@Override
	protected void processFile(CodeAnalysis analysis)
			throws InterruptedException, EvaluationException {
		HalsteadMetricFileResults results = new HalsteadMetricFileResults();
		ProgrammingLanguage language = ProgrammingLanguages.getInstance()
				.findByName(analysis.getLanguageName(),
						analysis.getLanguageVersion());

		for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
			HalsteadMetric metric = new HalsteadMetric(getAnalysisRun(),
					language, codeRange);
			execute(metric);
			results.add(new HalsteadMetricFileResult(analysis.getAnalyzedFile()
					.getSourceLocation(), codeRange.getType(), codeRange.getName(),
					metric.getHalsteadResults(), metric.getQuality()));
		}
		store.storeFileResults(analysis.getAnalyzedFile().getHashId(), results);
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return HalsteadMetric.EVALUATED_QUALITY_CHARACTERISTICS;
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
