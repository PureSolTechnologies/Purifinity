package com.puresoltechnologies.purifinity.server.analysisservice.rest.api.security;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class AuthLoginElement implements Serializable {

    private static final long serialVersionUID = 8044441747125956593L;

    private final String username;
    private final String password;

    public AuthLoginElement(
	    //
	    @JsonProperty("username") String username,
	    @JsonProperty("password") String password) {
	this.username = username;
	this.password = password;
    }

    public String getUsername() {
	return username;
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
	result = prime * result
		+ ((username == null) ? 0 : username.hashCode());
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
	if (username == null) {
	    if (other.username != null)
		return false;
	} else if (!username.equals(other.username))
	    return false;
	return true;
    }
}