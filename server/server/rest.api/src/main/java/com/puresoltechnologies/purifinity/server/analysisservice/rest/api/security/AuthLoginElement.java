package com.puresoltechnologies.purifinity.server.analysisservice.rest.api.security;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class AuthLoginElement implements Serializable {

    private static final long serialVersionUID = 8044441747125956593L;

    private final String email;
    private final String password;

    public AuthLoginElement() {
	email = null;
	password = null;
    }

    @JsonCreator
    public AuthLoginElement(
	    //
	    @JsonProperty("email") String email,
	    @JsonProperty("password") String password) {
	this.email = email;
	this.password = password;
    }

    public String getEmail() {
	return email;
    }

    public String getPassword() {
	return password;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((password == null) ? 0 : password.hashCode());
	result = prime * result + ((email == null) ? 0 : email.hashCode());
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
	AuthLoginElement other = (AuthLoginElement) obj;
	if (password == null) {
	    if (other.password != null)
		return false;
	} else if (!password.equals(other.password))
	    return false;
	if (email == null) {
	    if (other.email != null)
		return false;
	} else if (!email.equals(other.email))
	    return false;
	return true;
    }
}