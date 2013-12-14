package com.puresoltechnologies.purifinity.coding.metrics.entropy;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.commons.misc.ConfigurationParameter;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.parsers.impl.source.UnspecifiedSourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.api.CodeRange;
import com.puresoltechnologies.purifinity.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.analysis.api.HashIdFileTree;
import com.puresoltechnologies.purifinity.coding.evaluation.impl.AbstractEvaluator;
import com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricDirectoryResults;
import com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricEvaluator;
import com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricFileResults;
import com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricResult;
import com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadResult;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorStoreFactory;
import com.puresoltechnologies.purifinity.evaluation.api.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;

public class EntropyMetricEvaluator extends AbstractEvaluator {

	private static final long serialVersionUID = -5093217611195212999L;

	private static final Set<ConfigurationParameter<?>> configurationParameters = new HashSet<>();

	private final EvaluatorStore store;
	private final EvaluatorStore halsteadStore;

	public EntropyMetricEvaluator(AnalysisRun analysisRun, HashIdFileTree path) {
		super(EntropyMetric.NAME, EntropyMetric.DESCRIPTION, analysisRun, path);
		store = createEvaluatorStore();
		halsteadStore = EvaluatorStoreFactory.getFactory().createInstance(
				HalsteadMetricEvaluator.class);
	}

	@Override
	public Set<ConfigurationParameter<?>> getAvailableConfigurationParameters() {
		return configurationParameters;
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
			EntropyMetricResult result = calculateEntropyResult(halstead);
			results.add(new EntropyResult(analysis.getAnalyzedFile()
					.getSourceLocation(), codeRange.getType(), codeRange
					.getCanonicalName(), result, EntropyQuality.get(
					codeRange.getType(), result)));
		}
		store.storeFileResults(hashId, results);
	}

	/**
	 * This method calculates the entropy result from a {@link HalsteadResult}.
	 * 
	 * @param halstead
	 *            is a {@link HalsteadResult} object containing the Halstead
	 *            results.
	 * @return An {@link EntropyMetricResult} object is returned containing the
	 *         result calculation results.
	 */
	private EntropyMetricResult calculateEntropyResult(HalsteadResult halstead) {
		Map<String, Integer> operands = halstead.getOperands();

		double maxEntropy = Math.log(halstead.getDifferentOperands())
				/ Math.log(2.0);
		double entropy = 0.0;
		for (String operant : operands.keySet()) {
			int count = operands.get(operant);
			entropy += count / (double) halstead.getTotalOperands()
					* Math.log(count / (double) halstead.getTotalOperands())
					/ Math.log(2.0);
		}
		entropy *= -1.0;
		double normEntropy = entropy / maxEntropy;
		double entropyRedundancy = maxEntropy - entropy;
		double redundancy = entropyRedundancy * halstead.getDifferentOperands()
				/ maxEntropy;
		double normalizedRedundancy = redundancy
				/ halstead.getDifferentOperands();
		EntropyMetricResult result = new EntropyMetricResult(
				halstead.getVocabularySize(), halstead.getProgramLength(),
				entropy, maxEntropy, normEntropy, entropyRedundancy,
				redundancy, normalizedRedundancy);
		return result;
	}

	private HalsteadMetricResult findFileResult(
			HalsteadMetricFileResults halsteadFileResults, CodeRange codeRange) {
		for (HalsteadMetricResult t : halsteadFileResults.getResults()) {
			if ((t.getCodeRangeType() == codeRange.getType())
					&& (t.getCodeRangeName().equals(codeRange
							.getCanonicalName()))) {
				return t;
			}
		}
		return null;
	}

	@Override
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return EntropyMetric.EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	protected void processDirectory(HashIdFileTree directory)
			throws InterruptedException {
		EntropyDirectoryResults directoryResults = calculateDirectoryResults(directory);
		if (directoryResults != null) {
			store.storeDirectoryResults(directory.getHashId(), directoryResults);
		}
	}

	private EntropyDirectoryResults calculateDirectoryResults(
			HashIdFileTree directory) {
		EvaluatorStoreFactory factory = EvaluatorStoreFactory.getFactory();
		EvaluatorStore halsteadMetricResultStore = factory
				.createInstance(HalsteadMetricEvaluator.class);
		HalsteadMetricDirectoryResults halsteadDirectoryResults = (HalsteadMetricDirectoryResults) halsteadMetricResultStore
				.readDirectoryResults(directory.getHashId());
		if (halsteadDirectoryResults == null) {
			return null;
		}
		EntropyMetricResult entropyResult = calculateEntropyResult(halsteadDirectoryResults
				.getResult().getHalsteadResult());
		EntropyDirectoryResults directoryResults = new EntropyDirectoryResults(
				new UnspecifiedSourceCodeLocation(), CodeRangeType.DIRECTORY,
				directory.getName());
		for (HashIdFileTree child : directory.getChildren()) {
			QualityLevel childLevel;
			if (child.isFile()) {
				EntropyFileResults fileResults = (EntropyFileResults) store
						.readFileResults(child.getHashId());
				if (fileResults == null) {
					continue;
				}
				childLevel = fileResults.getQualityLevel();
			} else {
				EntropyDirectoryResults childDirectoryResults = (EntropyDirectoryResults) store
						.readDirectoryResults(child.getHashId());
				if (childDirectoryResults == null) {
					continue;
				}
				childLevel = childDirectoryResults.getQualityLevel();
			}
			if (childLevel != null) {
				directoryResults.addQualityLevel(childLevel);
			}
		}
		directoryResults.setEntropyResult(entropyResult);
		return directoryResults;
	}

	@Override
	protected void processProject() throws InterruptedException {
		HashIdFileTree directory = getAnalysisRun().getFileTree();
		EntropyDirectoryResults directoryResults = calculateDirectoryResults(directory);
		store.storeDirectoryResults(directory.getHashId(), directoryResults);
	}
}
