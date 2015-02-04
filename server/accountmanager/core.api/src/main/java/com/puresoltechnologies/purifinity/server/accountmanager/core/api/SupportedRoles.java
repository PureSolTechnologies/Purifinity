package com.puresoltechnologies.purifinity.server.accountmanager.core.api;

/**
 * This enum contains the available roles.
 * 
 * @author Rick-Rainer Ludwig
 */
public enum SupportedRoles {

    ADMINISTRATOR("administrator", "Administrator"), ENGINEER("engineer",
	    "Engineer"), UNPRIVILEGED("unprivileged", "Unprivileged User");

    private final String id;
    private final String name;

    private SupportedRoles(String id, String name) {
	this.id = id;
	this.name = name;
    }

    public String getId() {
	return id;
    }

    public String getName() {
	return name;
    }

}
