package com.puresoltechnologies.purifinity.server.accountmanager.rest.api;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents a single role.
 * 
 * @author Rick-Rainer Ludwig
 */
public class Role {

    private final String id;
    private final String name;
    private final int hashcode;

    @JsonCreator
    public Role(@JsonProperty("id") String id, @JsonProperty("name") String name) {
	super();
	this.id = id;
	this.name = name;
	hashcode = Objects.hash(id, name);
    }

    public String getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    @Override
    public int hashCode() {
	return hashcode;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Role other = (Role) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return name + " (" + id + ")";
    }
}
