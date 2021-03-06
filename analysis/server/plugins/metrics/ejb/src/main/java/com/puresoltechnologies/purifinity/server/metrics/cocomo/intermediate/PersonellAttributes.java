package com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate;

import java.util.HashSet;
import java.util.Set;

public class PersonellAttributes extends IntermediateCOCOMOAttribute {

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

    public static PersonellAttributes valueOf(String name) {
	for (PersonellAttributes attribute : getAttributes()) {
	    if (attribute.getName().equals(name)) {
		return attribute;
	    }
	}
	return null;
    }

    public static Set<PersonellAttributes> getAttributes() {
	Set<PersonellAttributes> attributes = new HashSet<>();
	attributes.add(ANALYST_CAPABILITY);
	attributes.add(APPLICATION_EXPERIENCE);
	attributes.add(SOFTWARE_ENGINEER_CAPABILITY);
	attributes.add(VIRTUAL_MACHINE_EXPERIENCE);
	attributes.add(PROGRAMMING_EXPERIENCE);
	return attributes;
    }

    protected PersonellAttributes(String name, Double veryLow, Double low,
	    Double nominal, Double high, Double veryHigh, Double extraHigh) {
	super(name, veryLow, low, nominal, high, veryHigh, extraHigh);
    }
}
