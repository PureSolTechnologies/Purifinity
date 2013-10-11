package com.puresol.purifinity.coding.metrics.cocomo.intermediate;

public enum SoftwareProject {

	ORGANIC(3.2, 1.05), //
	SEMI_DETACHED(3.0, 1.12), //
	EMBEDDED(2.8, 1.20), //
	;

	private final double ai;
	private final double bi;

	private SoftwareProject(double ai, double bi) {
		this.ai = ai;
		this.bi = bi;
	}

	public double getAi() {
		return ai;
	}

	public double getBi() {
		return bi;
	}
}
