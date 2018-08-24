package com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate;

import java.util.HashSet;
import java.util.Set;

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

    public static ProjectAttributes valueOf(String name) {
	for (ProjectAttributes attribute : getAttributes()) {
	    if (attribute.getName().equals(name)) {
		return attribute;
	    }
	}
	return null;
    }

    public static Set<ProjectAttributes> getAttributes() {
	Set<ProjectAttributes> attributes = new HashSet<>();
	attributes.add(APPLICATION_OF_SOFTWARE_ENGINEERING_METHODS);
	attributes.add(USE_OF_SOFTWARE_TOOLS);
	attributes.add(REQUIRED_DEVELOPMENT_SCHEDULE);
	return attributes;
    }

    protected ProjectAttributes(String name, Double veryLow, Double low,
	    Double nominal, Double high, Double veryHigh, Double extraHigh) {
	super(name, veryLow, low, nominal, high, veryHigh, extraHigh);
    }
}
