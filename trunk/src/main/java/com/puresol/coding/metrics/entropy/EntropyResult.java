package com.puresol.coding.metrics.entropy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.i18n4java.Translator;

import com.puresol.coding.evaluator.Result;

public class EntropyResult implements Serializable {

	private static final long serialVersionUID = 5578265179129688892L;

	private static final Translator translator = Translator
			.getTranslator(EntropyResult.class);

	private final double nDiff;
	private final double nTotal;
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

	public EntropyResult(double nDiff, double nTotal, double entropy,
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
		results.add(new Result("nDiff", translator
				.i18n("Number of different operators"), nDiff, ""));
		results.add(new Result("nTotal", translator
				.i18n("Total number of operators"), nTotal, ""));
		results.add(new Result("S", translator.i18n("Entropy"), entropy, ""));
		results.add(new Result("Smax", translator.i18n("Maximum entropy"),
				maxEntropy, ""));
		results.add(new Result("Snorm", translator.i18n("Normalized entropy"),
				normEntropy, ""));
		results.add(new Result("Rs", translator.i18n("Entropic redundancy"),
				entropyRedundancy, ""));
		results.add(new Result("R", translator.i18n("Redundancy"), redundancy,
				""));
		results.add(new Result("Rnorm", translator
				.i18n("Normalized redundancy"), normalizedRedundancy, ""));
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
