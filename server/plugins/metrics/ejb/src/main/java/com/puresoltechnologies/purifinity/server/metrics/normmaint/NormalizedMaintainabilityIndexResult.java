package com.puresoltechnologies.purifinity.server.metrics.normmaint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;

public class NormalizedMaintainabilityIndexResult implements Serializable {

    private static final long serialVersionUID = -7298758864269099643L;

    private final List<MetricValue<?>> results = new ArrayList<>();

    /**
     * MaintainabilityIndex without comment.
     */
    private final double nMIwoc;
    /**
     * MaintainabilityIndex comment weight
     */
    private final double nMIcw;
    /**
     * MaintainabilityIndex
     */
    private final double nMI;

    public NormalizedMaintainabilityIndexResult(double nMIwoc, double nMIcw) {
	super();
	this.nMIwoc = Math.max(0, nMIwoc / 171.0);
	this.nMIcw = Math.max(0, nMIcw / 50.0);
	this.nMI = Math.max(0, (nMIwoc + nMIcw) / 221.0);
	createResultsList();
    }

    private void createResultsList() {
	results.add(new MetricValue<Double>(nMIwoc,
		NormalizedMaintainabilityIndexEvaluatorParameter.NORM_MI_WOC));
	results.add(new MetricValue<Double>(nMIcw,
		NormalizedMaintainabilityIndexEvaluatorParameter.NORM_MI_CW));
	results.add(new MetricValue<Double>(nMI,
		NormalizedMaintainabilityIndexEvaluatorParameter.NORM_MI));
    }

    public double getNMIwoc() {
	return nMIwoc;
    }

    public double getNMIcw() {
	return nMIcw;
    }

    public double getNMI() {
	return nMI;
    }

    public List<MetricValue<?>> getResults() {
	return results;
    }
}
