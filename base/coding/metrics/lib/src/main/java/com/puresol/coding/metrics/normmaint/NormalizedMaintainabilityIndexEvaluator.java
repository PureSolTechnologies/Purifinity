package com.puresol.coding.metrics.normmaint;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.CodeAnalysis;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.coding.evaluation.api.QualityCharacteristic;
import com.puresol.coding.evaluation.impl.AbstractEvaluator;
import com.puresol.coding.metrics.maintainability.MaintainabilityIndexEvaluator;
import com.puresol.coding.metrics.maintainability.MaintainabilityIndexFileResult;
import com.puresol.coding.metrics.maintainability.MaintainabilityIndexFileResults;
import com.puresol.coding.metrics.maintainability.MaintainabilityIndexResult;
import com.puresol.utils.HashId;

public class NormalizedMaintainabilityIndexEvaluator extends AbstractEvaluator {

	private static final long serialVersionUID = -5093217611195212999L;

	public static final String NAME = "Normalized Maintainability Index";
	public static final String DESCRIPTION = "Normalized Maintainability Index calculation.";

	public static final List<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new ArrayList<QualityCharacteristic>();
	static {
		EVALUATED_QUALITY_CHARACTERISTICS
				.add(QualityCharacteristic.ANALYSABILITY);
		EVALUATED_QUALITY_CHARACTERISTICS
				.add(QualityCharacteristic.CHANGEABILITY);
		EVALUATED_QUALITY_CHARACTERISTICS
				.add(QualityCharacteristic.TESTABILITY);
	}

	private final EvaluatorStore store;
	private final EvaluatorStore maintainabilityStore;

	public NormalizedMaintainabilityIndexEvaluator(AnalysisRun analysisRun,
			HashIdFileTree path) {
		super(NAME, DESCRIPTION, analysisRun, path);
		store = getEvaluatorStore();

		maintainabilityStore = EvaluatorStoreFactory.getFactory()
				.createInstance(MaintainabilityIndexEvaluator.class);
	}

	@Override
	protected void processFile(CodeAnalysis analysis)
			throws InterruptedException {
		NormalizedMaintainabilityIndexFileResults results = new NormalizedMaintainabilityIndexFileResults();

		HashId hashId = analysis.getAnalyzedFile().getHashId();
		MaintainabilityIndexFileResults maintainabilityFileResults = (MaintainabilityIndexFileResults) maintainabilityStore
				.readFileResults(hashId);

		for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {

			MaintainabilityIndexFileResult maintainabilityIndexFileResult = findFileResult(
					maintainabilityFileResults, codeRange);

			MaintainabilityIndexResult maintainabilityIndex = maintainabilityIndexFileResult
					.getMaintainabilityIndexResult();
			NormalizedMaintainabilityIndexResult result = new NormalizedMaintainabilityIndexResult(
					maintainabilityIndex.getMIwoc(),
					maintainabilityIndex.getMIcw());

			results.add(new NormalizedMaintainabilityIndexFileResult(analysis
					.getAnalyzedFile().getSourceLocation(),
					codeRange.getType(), codeRange.getName(), result,
					NormalizedMaintainabilityQuality.get(codeRange.getType(),
							result)));
		}
		store.storeFileResults(hashId, maintainabilityFileResults);
	}

	private MaintainabilityIndexFileResult findFileResult(
			MaintainabilityIndexFileResults maintainabilityIndexFileResults,
			CodeRange codeRange) {
		for (MaintainabilityIndexFileResult t : maintainabilityIndexFileResults
				.getResults()) {
			if ((t.getCodeRangeType() == codeRange.getType())
					&& (t.getCodeRangeName().equals(codeRange.getName()))) {
				return t;
			}
		}
		return null;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	protected void processDirectory(HashIdFileTree directory)
			throws InterruptedException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void processProject() throws InterruptedException {
		// TODO Auto-generated method stub

	}
}
