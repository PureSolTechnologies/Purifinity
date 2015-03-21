package com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate;

import java.util.HashSet;
import java.util.Set;

public class HardwareAttributes extends IntermediateCOCOMOAttribute {

    public static final HardwareAttributes RUNTIME_PERFORMANCE_CONSTRAINTS = new HardwareAttributes(
	    "Runtime Performance Constraints", //
	    null, null, 1.00, 1.11, 1.30, 1.66);
    public static final HardwareAttributes MEMORY_CONSTRAINTS = new HardwareAttributes(
	    "Memory Constraints", //
	    null, null, 1.00, 1.06, 1.21, 1.56);
    public static final HardwareAttributes VOLATILITY_OF_THE_VIRTUAL_MACHINE_ENVIRONMENT = new HardwareAttributes(
	    "Volatility of the Virtual Machine Environment", //
	    null, 0.87, 1.00, 1.15, 1.30, null);
    public static final HardwareAttributes REQUIRED_TURNABOUT_TIME = new HardwareAttributes(
	    "Required Turnabout Time", //
	    null, 0.87, 1.00, 1.07, 1.15, null);

    public static HardwareAttributes valueOf(String name) {
	for (HardwareAttributes attribute : getAttributes()) {
	    if (attribute.getName().equals(name)) {
		return attribute;
	    }
	}
	return null;
    }

    public static Set<HardwareAttributes> getAttributes() {
	Set<HardwareAttributes> attributes = new HashSet<>();
	attributes.add(RUNTIME_PERFORMANCE_CONSTRAINTS);
	attributes.add(MEMORY_CONSTRAINTS);
	attributes.add(VOLATILITY_OF_THE_VIRTUAL_MACHINE_ENVIRONMENT);
	attributes.add(REQUIRED_TURNABOUT_TIME);
	return attributes;
    }

    protected HardwareAttributes(String name, Double veryLow, Double low,
	    Double nominal, Double high, Double veryHigh, Double extraHigh) {
	super(name, veryLow, low, nominal, high, veryHigh, extraHigh);
    }
}
