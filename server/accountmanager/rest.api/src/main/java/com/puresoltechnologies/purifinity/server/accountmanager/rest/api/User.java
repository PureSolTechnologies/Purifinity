package com.puresoltechnologies.purifinity.server.accountmanager.rest.api;

import java.util.Objects;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Represents a single user account.
 * 
 * @author Rick-Rainer Ludwig
 */
public class User {

    private final String email;
    private final String name;
    private final Role role;
    private final int hashcode;

    @JsonCreator
    public User(@JsonProperty("email") String email,
	    @JsonProperty("name") String name, @JsonProperty("role") Role role) {
	super();
	this.email = email;
	this.name = name;
	this.role = role;
	hashcode = Objects.hash(email, name, role);
    }

    public String getEmail() {
	return email;
    }

    public String getName() {
	return name;
    }

    public Role getRole() {
	return role;
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
	User other = (User) obj;
	if (email == null) {
	    if (other.email != null)
		return false;
	} else if (!email.equals(other.email))
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (role == null) {
	    if (other.role != null)
		return false;
	} else if (!role.equals(other.role))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return name + "/" + role + " (" + email + ")";
    }
}
