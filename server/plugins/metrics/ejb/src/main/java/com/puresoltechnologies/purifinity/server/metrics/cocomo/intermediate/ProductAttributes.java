package com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate;

public class ProductAttributes extends IntermediateCOCOMOAttribute {

	public static final ProductAttributes REQUIRED_SOFTWARE_RELIABILITY = new ProductAttributes(
			"Required Software Reliability",//
			0.75, 0.88, 1.00, 1.15, 1.40, null);
	public static final ProductAttributes SIZE_OF_APPLICATION_DATABASE = new ProductAttributes(
			"Size of Application Database",//
			null, 0.94, 1.00, 1.08, 1.16, null);
	public static final ProductAttributes COMPLEXITY_OF_THE_PRODUCT = new ProductAttributes(
			"Complexity of the Product",//
			0.70, 0.85, 1.00, 1.15, 1.30, 1.65);

	protected ProductAttributes(String name, Double veryLow, Double low,
			Double nominal, Double high, Double veryHigh, Double extraHigh) {
		super(name, veryLow, low, nominal, high, veryHigh, extraHigh);
	}

}
