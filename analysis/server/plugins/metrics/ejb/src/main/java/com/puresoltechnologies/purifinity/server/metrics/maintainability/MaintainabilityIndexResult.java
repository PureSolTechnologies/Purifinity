package com.puresoltechnologies.purifinity.server.metrics.maintainability;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;

public class MaintainabilityIndexResult implements Serializable {

	private static final long serialVersionUID = 1924196234881066633L;

	private final List<MetricValue<?>> results = new ArrayList<>();

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
		results.add(new MetricValue<Double>(miwoc,
				MaintainabilityIndexEvaluatorParameter.MI_WOC));
		results.add(new MetricValue<Double>(micw,
				MaintainabilityIndexEvaluatorParameter.MI_CW));
		results.add(new MetricValue<Double>(mi,
				MaintainabilityIndexEvaluatorParameter.MI));
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

	public List<MetricValue<?>> getResults() {
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
