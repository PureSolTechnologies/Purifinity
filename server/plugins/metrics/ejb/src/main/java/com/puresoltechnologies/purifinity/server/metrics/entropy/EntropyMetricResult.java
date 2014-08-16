package com.puresoltechnologies.purifinity.server.metrics.entropy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;

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
    private final List<MetricValue<?>> results = new ArrayList<>();

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
	results.add(new MetricValue<Integer>(nDiff,
		EntropyMetricEvaluatorParameter.N_DIFF));
	results.add(new MetricValue<Integer>(nTotal,
		EntropyMetricEvaluatorParameter.N_TOTAL));
	results.add(new MetricValue<Double>(entropy,
		EntropyMetricEvaluatorParameter.S));
	results.add(new MetricValue<Double>(maxEntropy,
		EntropyMetricEvaluatorParameter.S_MAX));
	results.add(new MetricValue<Double>(normEntropy,
		EntropyMetricEvaluatorParameter.S_NORM));
	results.add(new MetricValue<Double>(entropyRedundancy,
		EntropyMetricEvaluatorParameter.RS));
	results.add(new MetricValue<Double>(redundancy,
		EntropyMetricEvaluatorParameter.R));
	results.add(new MetricValue<Double>(normalizedRedundancy,
		EntropyMetricEvaluatorParameter.R_NORM));
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

    public List<MetricValue<?>> getResults() {
	return results;
    }

}
