package com.puresoltechnologies.purifinity.server.accountmanager.domain;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * This class represents a single role.
 * 
 * @author Rick-Rainer Ludwig
 */
public class Role {

    private final String roleId;
    private final String name;

    @JsonCreator
    public Role(@JsonProperty("roleId") String roleId,
	    @JsonProperty("name") String name) {
	super();
	this.roleId = roleId;
	this.name = name;
    }

    public String getRoleId() {
	return roleId;
    }

    public String getName() {
	return name;
    }

}
