package com.puresoltechnologies.purifinity.server.accountmanager.domain;

/**
 * This enum contains the available roles.
 * 
 * @author Rick-Rainer Ludwig
 */
public enum Roles {

    ADMINISTRATOR("administrator", "Administrator"), ENGINEER("engineer",
	    "Engineer"), UNPRIVILEGED("unprivileged", "Unprivileged User");

    private final String id;
    private final String name;

    private Roles(String id, String name) {
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
