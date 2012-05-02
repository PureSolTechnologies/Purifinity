package com.puresol.coding.metrics.maintainability;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.evaluation.api.Result;

public class MaintainabilityIndexResult implements Serializable {

    private static final long serialVersionUID = 1924196234881066633L;

    private final List<Result> results = new ArrayList<Result>();

    /**
     * MaintainabilityIndex without comment.
     */
    private final double miwoc;
    /**
     * MaintainabilityIndex comment weight
     */
    private final double micw;
    /**
     * MaintainabilityIndex
     */
    private final double mi;

    public MaintainabilityIndexResult(double mIwoc, double mIcw) {
	super();
	miwoc = mIwoc;
	micw = mIcw;
	mi = miwoc + micw;
	createResultsList();
    }

    private void createResultsList() {
	results.add(new Result("MIwoc",
		"Maintainability index without comments", miwoc, ""));
	results.add(new Result("MIcw", "Maintainability index comment weight",
		micw, ""));
	results.add(new Result("MI", "Maintainability index without comments",
		mi, ""));
    }

    public double getMIwoc() {
	return miwoc;
    }

    public double getMIcw() {
	return micw;
    }

    public double getMI() {
	return mi;
    }

    public List<Result> getResults() {
	return results;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("MI     \t");
	builder.append(mi);
	builder.append("\n");
	builder.append("MIwoc\t");
	builder.append(miwoc);
	builder.append("\n");
	builder.append("MIcw \t");
	builder.append(micw);
	return builder.toString();
    }

}
