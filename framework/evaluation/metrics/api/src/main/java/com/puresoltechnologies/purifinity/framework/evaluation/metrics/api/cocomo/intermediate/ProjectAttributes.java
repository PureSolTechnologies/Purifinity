package com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.intermediate;


public class ProjectAttributes extends IntermediateCOCOMOAttribute {

	public static final ProjectAttributes APPLICATION_OF_SOFTWARE_ENGINEERING_METHODS = new ProjectAttributes(
			"Application of Software Engineering Methods",//
			1.24, 1.10, 1.00, 0.91, 0.82, null);
	public static final ProjectAttributes USE_OF_SOFTWARE_TOOLS = new ProjectAttributes(
			"Use of Software Tools",//
			1.24, 1.10, 1.00, 0.91, 0.83, null);
	public static final ProjectAttributes REQUIRED_DEVELOPMENT_SCHEDULE = new ProjectAttributes(
			"Required Development Schedule",//
			1.23, 1.08, 1.00, 1.04, 1.10, null);

	protected ProjectAttributes(String name, Double veryLow, Double low,
			Double nominal, Double high, Double veryHigh, Double extraHigh) {
		super(name, veryLow, low, nominal, high, veryHigh, extraHigh);
	}
}
