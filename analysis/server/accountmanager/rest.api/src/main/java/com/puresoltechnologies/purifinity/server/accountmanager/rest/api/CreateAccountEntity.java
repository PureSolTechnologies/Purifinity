package com.puresoltechnologies.purifinity.server.accountmanager.rest.api;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateAccountEntity implements Serializable {

    private static final long serialVersionUID = -6166761612809264873L;

    private final String email;
    private final String name;
    private final String password;
    private final String roleId;

    /**
     * Default constructor needed by JSON.
     */
    public CreateAccountEntity() {
	email = null;
	name = null;
	password = null;
	roleId = null;
    }

    public CreateAccountEntity(String email, String password, String roleId) {
	this(email, email, password, roleId);
    }

    @JsonCreator
    public CreateAccountEntity(@JsonProperty("email") String email,
	    @JsonProperty("name") String name,
	    @JsonProperty("password") String password,
	    @JsonProperty("roleId") String roleId) {
	super();
	this.email = email;
	this.name = name;
	this.password = password;
	this.roleId = roleId;
    }

    public String getEmail() {
	return email;
    }

    public String getName() {
	return name;
    }

    public String getPassword() {
	return password;
    }

    public String getRoleId() {
	return roleId;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result
		+ ((password == null) ? 0 : password.hashCode());
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
	CreateAccountEntity other = (CreateAccountEntity) obj;
	if (email == null) {
	    if (other.email != null)
		return false;
	} else if (!email.equals(other.email))
	    return false;
	if (password == null) {
	    if (other.password != null)
		return false;
	} else if (!password.equals(other.password))
	    return false;
	if (roleId == null) {
	    if (other.roleId != null)
		return false;
	} else if (!roleId.equals(other.roleId))
	    return false;
	return true;
    }

}
