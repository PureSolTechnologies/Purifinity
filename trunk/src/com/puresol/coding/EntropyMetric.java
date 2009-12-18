package com.puresol.coding;

import java.util.Hashtable;

/**
 * This class calculates entropy and redundancy for a code range. The entropy is
 * only calculated for operands due to the fact that only they are chosen by the
 * programmer. The entropy gives therefore a hint how well the programmed.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EntropyMetric extends HalsteadMetric {

	/**
	 * entropy
	 */
	private double Entropy;
	/**
	 * maximum entropy
	 */
	private double maxEntropy;
	/**
	 * normalized entropy
	 */
	private double normEntropy;
	private double EntropyRedundancy;
	private double Redundancy;
	private double normalizedRedundancy;

	public EntropyMetric(CodeRange codeRange) {
		super(codeRange);
		calculate();
	}

	private void calculate() {
		Hashtable<String, Integer> operands = getOperands();

		maxEntropy = -Math.log(1 / (double) get_n2()) / Math.log(2.0);
		Entropy = 0.0;
		for (String operand : operands.keySet()) {
			int count = operands.get(operand);
			Entropy += (double) count / (double) get_N2()
					* Math.log((double) count / (double) get_N2())
					/ Math.log(2.0);
		}
		Entropy *= -1.0;
		normEntropy = Entropy / maxEntropy;
		EntropyRedundancy = maxEntropy - Entropy;
		Redundancy = EntropyRedundancy * get_n2() / maxEntropy;
		normalizedRedundancy = Redundancy / get_n2();
	}

	public double getMaxEntropy() {
		return maxEntropy;
	}

	public double getEntropy() {
		return Entropy;
	}

	public double getNormEntropy() {
		return normEntropy;
	}

	public double getEntropyRedundancy() {
		return EntropyRedundancy;
	}

	public double getRedundancy() {
		return Redundancy;
	}

	public double getNormRedundancy() {
		return normalizedRedundancy;
	}

	public String getResultsAsString() {
		String result = "number of symbols = " + get_n() + "\n";
		result += "max entropy = " + maxEntropy + "\n";
		result += "entropy = " + Entropy + "\n";
		result += "normalized entropy = " + normEntropy + "\n";
		result += "entropy redundance = " + EntropyRedundancy + "\n";
		result += "redundancy = " + Redundancy + "\n";
		result += "normalized redundancy = " + normalizedRedundancy + "\n";
		return result;
	}

	public void print() {
		System.out.println(getResultsAsString());
	}

	@Override
	public QualityLevel getQualityLevel() {
		if (!CodeEvaluationSystem.isEvaluateEntropyMetric()) {
			return QualityLevel.HIGH;
		}
		if (normalizedRedundancy > 0.40) {
			return QualityLevel.LOW;
		}
		if (normalizedRedundancy > 0.20) {
			return QualityLevel.MEDIUM;
		}
		return QualityLevel.HIGH;
	}
}
