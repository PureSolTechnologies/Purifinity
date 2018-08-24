package com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate;

import java.util.HashSet;
import java.util.Set;

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

    public static ProductAttributes valueOf(String name) {
	for (ProductAttributes attribute : getAttributes()) {
	    if (attribute.getName().equals(name)) {
		return attribute;
	    }
	}
	return null;
    }

    public static Set<ProductAttributes> getAttributes() {
	Set<ProductAttributes> attributes = new HashSet<>();
	attributes.add(REQUIRED_SOFTWARE_RELIABILITY);
	attributes.add(SIZE_OF_APPLICATION_DATABASE);
	attributes.add(COMPLEXITY_OF_THE_PRODUCT);
	return attributes;
    }

    protected ProductAttributes(String name, Double veryLow, Double low,
	    Double nominal, Double high, Double veryHigh, Double extraHigh) {
	super(name, veryLow, low, nominal, high, veryHigh, extraHigh);
    }
}
