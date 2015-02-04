package com.puresoltechnologies.purifinity.server.accountmanager.core.api;

import java.util.Objects;

/**
 * This class represents a single role.
 * 
 * @author Rick-Rainer Ludwig
 */
public class Role {

    private final String id;
    private final String name;
    private final int hashcode;

    public Role(String id, String name) {
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
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

}
