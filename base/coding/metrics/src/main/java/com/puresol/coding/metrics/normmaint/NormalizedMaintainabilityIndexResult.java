package com.puresol.coding.metrics.normmaint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.impl.evaluation.Result;

public class NormalizedMaintainabilityIndexResult implements Serializable {

    private static final long serialVersionUID = -7298758864269099643L;

    private final List<Result> results = new ArrayList<Result>();

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
	results.add(new Result("nMIwoc",
		"Maintainability index without comments", nMIwoc, ""));
	results.add(new Result("nMIcw", "Maintainability index comment weight",
		nMIcw, ""));
	results.add(new Result("nMI", "Maintainability index without comments",
		nMI, ""));
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

    public List<Result> getResults() {
	return results;
    }
}
