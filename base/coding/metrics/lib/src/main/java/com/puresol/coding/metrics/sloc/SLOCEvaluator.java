package com.puresol.coding.metrics.sloc;

import java.util.List;

import org.apache.commons.io.IOUtils;

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

public class SLOCEvaluator extends AbstractEvaluator {

	private static final long serialVersionUID = -5093217611195212999L;

	private final EvaluatorStore store;

	public SLOCEvaluator(AnalysisRun analysisRun, HashIdFileTree path) {
		super(SLOCMetricCalculator.NAME, SLOCMetricCalculator.DESCRIPTION,
				analysisRun, path);
		store = getEvaluatorStore();
	}

	@Override
	protected void processFile(CodeAnalysis analysis)
			throws InterruptedException, EvaluationException {
		SLOCResults results = new SLOCResults();
		ProgrammingLanguages programmingLanguages = ProgrammingLanguages
				.createInstance();
		try {
			ProgrammingLanguage language = programmingLanguages.findByName(
					analysis.getLanguageName(), analysis.getLanguageVersion());

			for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
				SLOCMetricCalculator metric = new SLOCMetricCalculator(
						getAnalysisRun(), language, codeRange);
				execute(metric);
				results.add(new SLOCResult(analysis.getAnalyzedFile()
						.getSourceLocation(), codeRange.getType(), codeRange
						.getName(), metric.getSLOCResult(), metric.getQuality()));
			}
		} finally {
			IOUtils.closeQuietly(programmingLanguages);
		}
		store.storeFileResults(analysis.getAnalyzedFile().getHashId(), results);
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return SLOCMetricCalculator.EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	protected void processDirectory(HashIdFileTree directory)
			throws InterruptedException {
		// TODO
	}

	@Override
	protected void processProject() throws InterruptedException {
		// TODO Auto-generated method stub

	}
}
