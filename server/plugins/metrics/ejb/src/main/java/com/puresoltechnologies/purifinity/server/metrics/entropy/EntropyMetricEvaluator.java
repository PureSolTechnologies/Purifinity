package com.puresoltechnologies.purifinity.server.metrics.entropy;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.puresoltechnologies.commons.math.ConfigurationParameter;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.parsers.source.UnspecifiedSourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.FileMetrics;
import com.puresoltechnologies.purifinity.server.metrics.AbstractMetricEvaluator;
import com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetricEvaluatorStore;
import com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadQuality;
import com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadResult;

@Stateless
@Remote(Evaluator.class)
public class EntropyMetricEvaluator extends AbstractMetricEvaluator {

	private static final Set<ConfigurationParameter<?>> configurationParameters = new HashSet<>();

	@Inject
	private HalsteadMetricEvaluatorStore halsteadMetricEvaluatorStore;

	public EntropyMetricEvaluator() {
		super(EntropyMetric.ID, EntropyMetric.NAME, EntropyMetric.DESCRIPTION);
	}

	@Override
	public Set<ConfigurationParameter<?>> getAvailableConfigurationParameters() {
		return configurationParameters;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return EntropyMetricEvaluatorParameter.ALL;
	}

	@Override
	protected FileMetrics processFile(AnalysisRun analysisRun,
			CodeAnalysis analysis) throws InterruptedException,
			EvaluationStoreException {
		HashId hashId = analysis.getAnalysisInformation().getHashId();
		SourceCodeLocation sourceCodeLocation = analysisRun
				.findTreeNode(hashId).getSourceCodeLocation();

		EntropyFileResults results = new EntropyFileResults(hashId,
				sourceCodeLocation, new Date());

		for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
			HalsteadResult halstead = halsteadMetricEvaluatorStore
					.readCodeRangeResults(hashId, codeRange.getType(),
							codeRange.getCanonicalName());
			EntropyMetricResult result = calculateEntropyResult(halstead);
			results.add(new EntropyResult(sourceCodeLocation, codeRange
					.getType(), codeRange.getCanonicalName(), result,
					EntropyQuality.get(codeRange.getType(), result)));
		}
		return results;
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

	@Override
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return EntropyMetric.EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	protected DirectoryMetrics processDirectory(AnalysisRun analysisRun,
			AnalysisFileTree directory) throws InterruptedException,
			EvaluationStoreException {
		HalsteadResult halsteadDirectoryResults = halsteadMetricEvaluatorStore
				.readDirectoryResults(directory.getHashId());
		if (halsteadDirectoryResults == null) {
			return null;
		}
		EntropyMetricResult entropyResult = calculateEntropyResult(halsteadDirectoryResults);
		EntropyDirectoryResults directoryResults = new EntropyDirectoryResults(
				directory.getHashId(), new Date(),
				new UnspecifiedSourceCodeLocation(), CodeRangeType.DIRECTORY,
				directory.getName());
		for (AnalysisFileTree child : directory.getChildren()) {
			QualityLevel childLevel;
			if (child.isFile()) {
				HalsteadResult fileResults = halsteadMetricEvaluatorStore
						.readCodeRangeResults(child.getHashId(),
								CodeRangeType.FILE, child.getName());
				if (fileResults == null) {
					continue;
				}
				childLevel = new QualityLevel(HalsteadQuality.get(
						CodeRangeType.FILE, fileResults));
			} else {
				HalsteadResult childDirectoryResults = halsteadMetricEvaluatorStore
						.readDirectoryResults(child.getHashId());
				if (childDirectoryResults == null) {
					continue;
				}
				childLevel = new QualityLevel(HalsteadQuality.get(
						CodeRangeType.DIRECTORY, childDirectoryResults));
			}
			if (childLevel != null) {
				directoryResults.addQualityLevel(childLevel);
			}
		}
		directoryResults.setEntropyResult(entropyResult);
		return directoryResults;
	}

	@Override
	protected DirectoryMetrics processProject(AnalysisRun analysisRun,
			boolean enableReevaluation) throws InterruptedException,
			EvaluationStoreException {
		AnalysisFileTree directory = analysisRun.getFileTree();
		return processDirectory(analysisRun, directory);
	}

}
