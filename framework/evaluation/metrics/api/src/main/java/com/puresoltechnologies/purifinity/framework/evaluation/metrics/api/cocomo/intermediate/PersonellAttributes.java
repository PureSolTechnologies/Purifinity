package com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.intermediate;


public class PersonellAttributes extends IntermediateCOCOMOAttribute {

	protected PersonellAttributes(String name, Double veryLow, Double low,
			Double nominal, Double high, Double veryHigh, Double extraHigh) {
		super(name, veryLow, low, nominal, high, veryHigh, extraHigh);
		// TODO Auto-generated constructor stub
	}

	public static final PersonellAttributes ANALYST_CAPABILITY = new PersonellAttributes(
			"Analyst Capability",//
			1.46, 1.19, 1.00, 0.86, 0.71, null);
	public static final PersonellAttributes APPLICATION_EXPERIENCE = new PersonellAttributes(
			"Application Experience",//
			1.29, 1.13, 1.00, 0.91, 0.82, null);
	public static final PersonellAttributes SOFTWARE_ENGINEER_CAPABILITY = new PersonellAttributes(
			"Software Engineering Capability",//
			1.42, 1.17, 1.00, 0.86, 0.70, null);
	public static final PersonellAttributes VIRTUAL_MACHINE_EXPERIENCE = new PersonellAttributes(
			"Virtual Machine Experience",//
			1.21, 1.10, 1.00, 0.90, null, null);
	public static final PersonellAttributes PROGRAMMING_EXPERIENCE = new PersonellAttributes(
			"Programming Experience",//
			1.14, 1.07, 1.00, 0.95, null, null);
}
