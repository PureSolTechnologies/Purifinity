package com.puresoltechnologies.purifinity.server.metrics.entropy;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.parsers.source.UnspecifiedSourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.FileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;
import com.puresoltechnologies.purifinity.server.metrics.AbstractMetricEvaluator;
import com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetricResult;
import com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadResult;
import com.puresoltechnologies.purifinity.server.metrics.halstead.db.HalsteadMetricsEvaluatorDAO;

@Stateless
@Remote(Evaluator.class)
public class EntropyMetricEvaluator extends AbstractMetricEvaluator {

	@Inject
	private Logger logger;

	@Inject
	private HalsteadMetricsEvaluatorDAO halsteadMetricEvaluatorDAO;

	public EntropyMetricEvaluator() {
		super(EntropyMetric.ID, EntropyMetric.NAME,
				EntropyMetric.PLUGIN_VERSION, EntropyMetric.DESCRIPTION);
	}

	@Override
	public List<ConfigurationParameter<?>> getConfigurationParameters() {
		return EntropyMetric.PARAMETERS;
	}

	@Override
	public Set<MetricParameter<?>> getParameters() {
		return EntropyMetricEvaluatorParameter.ALL;
	}

	@Override
	protected FileMetrics processFile(AnalysisRun analysisRun,
			CodeAnalysis analysis) throws InterruptedException,
			EvaluationStoreException {
		AnalysisInformation analysisInformation = analysis
				.getAnalysisInformation();
		HashId hashId = analysisInformation.getHashId();
		SourceCodeLocation sourceCodeLocation = analysisRun
				.findTreeNode(hashId).getSourceCodeLocation();

		EntropyFileResults entropyResults = new EntropyFileResults(hashId,
				sourceCodeLocation, new Date());

		List<HalsteadMetricResult> halsteadMetricResults = halsteadMetricEvaluatorDAO
				.readFileResults(hashId);
		if (halsteadMetricResults == null) {
			logger.warn("No Halstead Metric result available for '" + hashId
					+ "', yet.");
			return entropyResults;
		}
		for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
			for (HalsteadMetricResult halsteadMetricResult : halsteadMetricResults) {
				if (halsteadMetricResult.getCodeRangeType().equals(
						codeRange.getType())
						&& halsteadMetricResult.getCodeRangeName().equals(
								codeRange.getCanonicalName())) {
					EntropyMetricResult entropyResult = calculateEntropyResult(halsteadMetricResult
							.getHalsteadResult());
					entropyResults.add(new EntropyResult(sourceCodeLocation,
							codeRange.getType(), codeRange.getCanonicalName(),
							entropyResult));
				}
			}
		}
		return entropyResults;
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
		HalsteadMetricResult halsteadDirectoryResults = halsteadMetricEvaluatorDAO
				.readDirectoryResults(directory.getHashId());
		if (halsteadDirectoryResults == null) {
			return null;
		}
		EntropyMetricResult entropyResult = calculateEntropyResult(halsteadDirectoryResults
				.getHalsteadResult());
		EntropyDirectoryResults directoryResults = new EntropyDirectoryResults(
				directory.getHashId(), new Date(),
				new UnspecifiedSourceCodeLocation(), CodeRangeType.DIRECTORY,
				directory.getName());
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

	@Override
	public void setConfigurationParameter(ConfigurationParameter<?> parameter,
			Object value) {
		// Intentionally left empty.
		throw new IllegalArgumentException("Parameter '" + parameter
				+ "' is unknown.");
	}

	@Override
	public Object getConfigurationParameter(ConfigurationParameter<?> parameter) {
		// Intentionally left empty.
		throw new IllegalArgumentException("Parameter '" + parameter
				+ "' is unknown.");
	}
}
