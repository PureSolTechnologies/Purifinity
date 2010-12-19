package com.puresol.coding.metrics.maintainability;

public class MaintainabilityIndexResult {

	/**
	 * MaintainabilityIndex without comment.
	 */
	private final double MIwoc;
	/**
	 * MaintainabilityIndex comment weight
	 */
	private final double MIcw;
	/**
	 * MaintainabilityIndex
	 */
	private final double MI;

	public MaintainabilityIndexResult(double mIwoc, double mIcw) {
		super();
		MIwoc = mIwoc;
		MIcw = mIcw;
		MI = MIwoc + MIcw;
	}

	public double getMIwoc() {
		return MIwoc;
	}

	public double getMIcw() {
		return MIcw;
	}

	public double getMI() {
		return MI;
	}

}
