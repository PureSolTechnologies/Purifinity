package com.puresol.coding.metrics.normmaint;

public class NormalizedMaintainabilityIndexResult {

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

}
