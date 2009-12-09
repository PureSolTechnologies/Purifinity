package com.puresol.coding;

public class MaintainabilityIndex {

	private CodeRange codeRange;

	/**
	 * MaintainabilityIndex without comment.
	 */
	private double MIwoc;
	/**
	 * MaintainabilityIndex comment weight
	 */
	private double MIcw;
	/**
	 * MaintainabilityIndex
	 */
	private double MI;

	public MaintainabilityIndex(CodeRange codeRange) {
		this.codeRange = codeRange;
		calculate();
	}

	private void calculate() {
		SLOCMetric slocMetric = new SLOCMetric(codeRange);
		McCabeMetric mcCabeMetric = new McCabeMetric(codeRange);
		HalsteadMetric halsteadMetric = new HalsteadMetric(codeRange);

		MIwoc = 171.0 - 5.2 * Math.log(halsteadMetric.get_HV()) - 0.23
				* mcCabeMetric.getCyclomaticNumber() - 16.2
				* Math.log(slocMetric.getPhyLOC());
		MIcw = 50 * Math.sin(Math.sqrt(2.4 * (double) slocMetric.getComLOC()
				/ (double) slocMetric.getPhyLOC()));
		MI = MIwoc + MIcw;
	}

	public void print() {
		System.out.println("MIwoc = " + MIwoc);
		System.out.println("MIcw = " + MIcw);
		System.out.println("MI = " + MI);
	}

	public double getMIWoc() {
		return MIwoc;
	}

	public double getMIcw() {
		return MIcw;
	}

	public double getMI() {
		return MI;
	}
}
