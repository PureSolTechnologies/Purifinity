package com.puresol.coding.metrics.entropy;

import java.util.List;
import java.util.Map;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.CodeAnalysis;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.coding.evaluation.api.QualityCharacteristic;
import com.puresol.coding.evaluation.impl.AbstractEvaluator;
import com.puresol.coding.metrics.halstead.HalsteadMetricEvaluator;
import com.puresol.coding.metrics.halstead.HalsteadMetricResult;
import com.puresol.coding.metrics.halstead.HalsteadMetricFileResults;
import com.puresol.coding.metrics.halstead.HalsteadResult;
import com.puresol.utils.HashId;

public class EntropyMetricEvaluator extends AbstractEvaluator {

	private static final long serialVersionUID = -5093217611195212999L;

	private final EvaluatorStore store;
	private final EvaluatorStore halsteadStore;

	public EntropyMetricEvaluator(AnalysisRun analysisRun, HashIdFileTree path) {
		super(EntropyMetric.NAME, EntropyMetric.DESCRIPTION, analysisRun, path);
		store = createEvaluatorStore();
		halsteadStore = EvaluatorStoreFactory.getFactory().createInstance(
				HalsteadMetricEvaluator.class);
	}

	@Override
	protected void processFile(CodeAnalysis analysis)
			throws InterruptedException {
		HashId hashId = analysis.getAnalyzedFile().getHashId();
		HalsteadMetricFileResults halsteadFileResults = (HalsteadMetricFileResults) halsteadStore
				.readFileResults(hashId);

		EntropyFileResults results = new EntropyFileResults();

		for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
			HalsteadMetricResult halsteadFileResult = findFileResult(
					halsteadFileResults, codeRange);
			HalsteadResult halstead = halsteadFileResult.getHalsteadResult();

			Map<String, Integer> operands = halstead.getOperands();

			double maxEntropy = Math.log(halstead.getDifferentOperands())
					/ Math.log(2.0);
			double entropy = 0.0;
			for (String operant : operands.keySet()) {
				int count = operands.get(operant);
				entropy += count
						/ (double) halstead.getTotalOperands()
						* Math.log(count / (double) halstead.getTotalOperands())
						/ Math.log(2.0);
			}
			entropy *= -1.0;
			double normEntropy = entropy / maxEntropy;
			double entropyRedundancy = maxEntropy - entropy;
			double redundancy = entropyRedundancy
					* halstead.getDifferentOperands() / maxEntropy;
			double normalizedRedundancy = redundancy
					/ halstead.getDifferentOperands();
			EntropyMetricResult result = new EntropyMetricResult(
					halstead.getVocabularySize(), halstead.getProgramLength(),
					entropy, maxEntropy, normEntropy, entropyRedundancy,
					redundancy, normalizedRedundancy);

			results.add(new EntropyResult(analysis.getAnalyzedFile()
					.getSourceLocation(), codeRange.getType(), codeRange
					.getName(), result, EntropyQuality.get(codeRange.getType(),
					result)));
		}
		store.storeFileResults(hashId, results);
	}

	private HalsteadMetricResult findFileResult(
			HalsteadMetricFileResults halsteadFileResults, CodeRange codeRange) {
		for (HalsteadMetricResult t : halsteadFileResults.getResults()) {
			if ((t.getCodeRangeType() == codeRange.getType())
					&& (t.getCodeRangeName().equals(codeRange.getName()))) {
				return t;
			}
		}
		return null;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return EntropyMetric.EVALUATED_QUALITY_CHARACTERISTICS;
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
