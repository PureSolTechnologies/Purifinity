package com.puresoltechnologies.purifinity.server.accountmanager.rest.api;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EditAccountEntity implements Serializable {

    private static final long serialVersionUID = -6166761612809264873L;

    private final String name;
    private final String roleId;

    /**
     * Default constructor needed by JSON.
     */
    public EditAccountEntity() {
	name = null;
	roleId = null;
    }

    @JsonCreator
    public EditAccountEntity(@JsonProperty("name") String name,
	    @JsonProperty("roleId") String roleId) {
	super();
	this.name = name;
	this.roleId = roleId;
    }

    public String getName() {
	return name;
    }

    public String getRoleId() {
	return roleId;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	EditAccountEntity other = (EditAccountEntity) obj;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (roleId == null) {
	    if (other.roleId != null)
		return false;
	} else if (!roleId.equals(other.roleId))
	    return false;
	return true;
    }

}
