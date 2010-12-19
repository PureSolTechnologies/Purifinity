package com.puresol.coding.metrics.entropy;

public class EntropyResult {

	/**
	 * entropy
	 */
	private double entropy;
	/**
	 * maximum entropy
	 */
	private double maxEntropy;
	private final double nDiff;
	private final double nTotal;
	private final double normEntropy;
	private final double EntropyRedundancy;
	private final double Redundancy;
	private final double normalizedRedundancy;

	public EntropyResult(double nDiff, double nTotal, double entropy,
			double maxEntropy, double normEntropy, double entropyRedundancy,
			double redundancy, double normalizedRedundancy) {
		super();
		this.nDiff = nDiff;
		this.nTotal = nTotal;
		this.normEntropy = normEntropy;
		EntropyRedundancy = entropyRedundancy;
		Redundancy = redundancy;
		this.normalizedRedundancy = normalizedRedundancy;
	}

	public double getNTotal() {
		return nTotal;
	}

	public double getNDiff() {
		return nDiff;
	}

	public double getEntropy() {
		return entropy;
	}

	public void setEntropy(double entropy) {
		this.entropy = entropy;
	}

	public double getMaxEntropy() {
		return maxEntropy;
	}

	public void setMaxEntropy(double maxEntropy) {
		this.maxEntropy = maxEntropy;
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

	public double getNormalizedRedundancy() {
		return normalizedRedundancy;
	}

}
