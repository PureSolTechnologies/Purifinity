/***************************************************************************
 *
 *   EntropyMetric.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresoltechnologies.purifinity.coding.metrics.entropy;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.purifinity.coding.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.coding.analysis.api.CodeRange;
import com.puresoltechnologies.purifinity.coding.evaluation.api.SourceCodeQuality;
import com.puresoltechnologies.purifinity.coding.evaluation.impl.CodeRangeEvaluator;
import com.puresoltechnologies.purifinity.coding.evaluation.impl.Result;
import com.puresoltechnologies.purifinity.coding.evaluation.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetric;
import com.puresoltechnologies.purifinity.lang.api.ProgrammingLanguage;

/**
 * This class calculates entropy and redundancy for a code range. The entropy is
 * only calculated for operands due to the fact that only they are chosen by the
 * programmer. The entropy gives therefore a hint how well the programmed.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EntropyMetric extends CodeRangeEvaluator {

	public static final String NAME = "Entropy Metric";

	public static final String DESCRIPTION = "Entropy Metric calculation.";

	public static final Set<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new HashSet<QualityCharacteristic>();
	static {
		EVALUATED_QUALITY_CHARACTERISTICS
				.add(QualityCharacteristic.ANALYSABILITY);
	}

	private static final Logger logger = LoggerFactory
			.getLogger(EntropyMetric.class);

	private final AnalysisRun analysisRun;
	private final CodeRange codeRange;
	private final HalsteadMetric halstead;
	private EntropyMetricResult result;

	public EntropyMetric(AnalysisRun analysisRun, ProgrammingLanguage language,
			CodeRange codeRange) {
		super(NAME);
		this.analysisRun = analysisRun;
		this.codeRange = codeRange;
		halstead = new HalsteadMetric(analysisRun, language, getCodeRange());
	}

	@Override
	public AnalysisRun getAnalysisRun() {
		return analysisRun;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CodeRange getCodeRange() {
		return codeRange;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean call() {
		boolean retVal = calculate();
		recreateResultsList();
		return retVal;
	}

	private boolean calculate() {
		try {
			fireStarted("Start evaluation.", 2);

			ExecutorService executor = Executors.newSingleThreadExecutor();
			executor.submit(halstead);
			executor.shutdown();
			executor.awaitTermination(30, TimeUnit.SECONDS);
			fireUpdateWork("Finished Halstead metric.", 1);

			Hashtable<String, Integer> operands = halstead.getOperands();

			double maxEntropy = Math.log(halstead.getDifferentOperands())
					/ Math.log(2.0);
			double entropy = 0.0;
			for (String operant : operands.keySet()) {
				int count = operands.get(operant);
				entropy += (double) count
						/ (double) halstead.getTotalOperands()
						* Math.log((double) count
								/ (double) halstead.getTotalOperands())
						/ Math.log(2.0);
			}
			entropy *= -1.0;
			double normEntropy = entropy / maxEntropy;
			double entropyRedundancy = maxEntropy - entropy;
			double redundancy = entropyRedundancy
					* halstead.getDifferentOperands() / maxEntropy;
			double normalizedRedundancy = redundancy
					/ halstead.getDifferentOperands();
			result = new EntropyMetricResult(halstead.getVocabularySize(),
					halstead.getProgramLength(), entropy, maxEntropy,
					normEntropy, entropyRedundancy, redundancy,
					normalizedRedundancy);
			fireDone("Evaluation finished.", true);
			return true;
		} catch (InterruptedException e) {
			logger.warn("Evaluation was interrupted.", e);
			fireDone("Evaluation was interrupted.", false);
			return false;
		}
	}

	private void recreateResultsList() {

	}

	public double getNTotal() {
		return result.getNTotal();
	}

	public double getNDiff() {
		return result.getNDiff();
	}

	public double getMaxEntropy() {
		return result.getMaxEntropy();
	}

	public double getEntropy() {
		return result.getEntropy();
	}

	public double getNormEntropy() {
		return result.getNormEntropy();
	}

	public double getEntropyRedundancy() {
		return result.getEntropyRedundancy();
	}

	public double getRedundancy() {
		return result.getRedundancy();
	}

	public double getNormalizedRedundancy() {
		return result.getNormalizedRedundancy();
	}

	public String getResultsAsString() {
		String result = "number of symbols = " + getNTotal() + "\n";
		result += "max entropy = " + getMaxEntropy() + "\n";
		result += "entropy = " + getEntropy() + "\n";
		result += "normalized entropy = " + getNormEntropy() + "\n";
		result += "entropy redundance = " + getEntropyRedundancy() + "\n";
		result += "redundancy = " + getRedundancy() + "\n";
		result += "normalized redundancy = " + getNormalizedRedundancy() + "\n";
		return result;
	}

	public void print() {
		System.out.println(getResultsAsString());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SourceCodeQuality getQuality() {
		if (getNormalizedRedundancy() > 0.40) {
			return SourceCodeQuality.LOW;
		}
		if (getNormalizedRedundancy() > 0.20) {
			return SourceCodeQuality.MEDIUM;
		}
		return SourceCodeQuality.HIGH;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDescription() {
		return DESCRIPTION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	public List<Result> getResults() {
		return result.getResults();
	}
}
