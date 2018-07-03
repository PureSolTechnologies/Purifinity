package com.puresoltechnologies.toolshed.server.api.nodes;

public enum OS {

    LINUX;

    public static OS local() {
	return byName(System.getProperty("os.name"));
    }

    public static OS byName(String name) {
	if ("LINUX".equalsIgnoreCase(name)) {
	    return LINUX;
	}
	return null;
    }

}
