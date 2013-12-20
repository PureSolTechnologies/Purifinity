package com.puresoltechnologies.purifinity.framework.evaluation.metrics.cocomo.intermediate;

public enum SoftwareProject {

	ORGANIC(3.2, 1.05, 2.5, 0.38), //
	SEMI_DETACHED(3.0, 1.12, 2.5, 0.35), //
	EMBEDDED(2.8, 1.20, 2.5, 0.32), //
	;

	private final double ai;
	private final double bi;
	private final double ci;
	private final double di;

	private SoftwareProject(double ai, double bi, double ci, double di) {
		this.ai = ai;
		this.bi = bi;
		this.ci = ci;
		this.di = di;
	}

	public double getAi() {
		return ai;
	}

	public double getBi() {
		return bi;
	}

	public double getCi() {
		return ci;
	}

	public double getDi() {
		return di;
	}
}
