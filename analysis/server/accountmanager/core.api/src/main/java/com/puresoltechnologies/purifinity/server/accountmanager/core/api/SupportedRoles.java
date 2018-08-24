package com.puresoltechnologies.purifinity.server.accountmanager.core.api;

/**
 * This enum contains the available roles.
 * 
 * @author Rick-Rainer Ludwig
 */
public enum SupportedRoles {

    ADMINISTRATOR("administrator", "Administrator"), //
    ENGINEER(SupportedRoles.ENGINEER_ID, "Engineer"), //
    UNPRIVILEGED(SupportedRoles.UNPRIVILEGED_ID, "Unprivileged User");

    public static final String ENGINEER_ID = "engineer";
    public static final String UNPRIVILEGED_ID = "unprivileged";

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
