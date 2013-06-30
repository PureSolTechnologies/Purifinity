package com.puresol.purifinity.coding.metrics.entropy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.puresol.purifinity.coding.evaluation.impl.Result;

public class EntropyMetricResult implements Serializable {

	private static final long serialVersionUID = 5578265179129688892L;

	private final int nDiff;
	private final int nTotal;
	/**
	 * entropy
	 */
	private final double entropy;
	/**
	 * maximum entropy
	 */
	private final double maxEntropy;
	private final double normEntropy;
	private final double entropyRedundancy;
	private final double redundancy;
	private final double normalizedRedundancy;
	private final List<Result> results = new ArrayList<Result>();

	public EntropyMetricResult(int nDiff, int nTotal, double entropy,
			double maxEntropy, double normEntropy, double entropyRedundancy,
			double redundancy, double normalizedRedundancy) {
		super();
		this.nDiff = nDiff;
		this.nTotal = nTotal;
		this.entropy = entropy;
		this.maxEntropy = maxEntropy;
		this.normEntropy = normEntropy;
		this.entropyRedundancy = entropyRedundancy;
		this.redundancy = redundancy;
		this.normalizedRedundancy = normalizedRedundancy;
		createResultsList();
	}

	private void createResultsList() {
		results.add(new Result("nDiff", "Number of different operators", nDiff,
				""));
		results.add(new Result("nTotal", "Total number of operators", nTotal,
				""));
		results.add(new Result("S", "Entropy", entropy, ""));
		results.add(new Result("Smax", "Maximum entropy", maxEntropy, ""));
		results.add(new Result("Snorm", "Normalized entropy", normEntropy, ""));
		results.add(new Result("Rs", "Entropic redundancy", entropyRedundancy,
				""));
		results.add(new Result("R", "Redundancy", redundancy, ""));
		results.add(new Result("Rnorm", "Normalized redundancy",
				normalizedRedundancy, ""));
	}

	public int getNTotal() {
		return nTotal;
	}

	public int getNDiff() {
		return nDiff;
	}

	public double getEntropy() {
		return entropy;
	}

	public double getMaxEntropy() {
		return maxEntropy;
	}

	public double getNormEntropy() {
		return normEntropy;
	}

	public double getEntropyRedundancy() {
		return entropyRedundancy;
	}

	public double getRedundancy() {
		return redundancy;
	}

	public double getNormalizedRedundancy() {
		return normalizedRedundancy;
	}

	public List<Result> getResults() {
		return results;
	}

}
