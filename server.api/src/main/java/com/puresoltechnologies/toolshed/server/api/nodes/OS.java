package com.puresoltechnologies.toolshed.server.api.nodes;

public enum OS {

    LINUX("Linux");

    public static OS local() {
	return byName(System.getProperty("os.name"));
    }

    public static OS byName(String name) {
	if ("LINUX".equalsIgnoreCase(name)) {
	    return LINUX;
	}
	return null;
    }

    private final String displayName;

    private OS(String displayName) {
	this.displayName = displayName;
    }

    public String getDisplayName() {
	return displayName;
    }

}
