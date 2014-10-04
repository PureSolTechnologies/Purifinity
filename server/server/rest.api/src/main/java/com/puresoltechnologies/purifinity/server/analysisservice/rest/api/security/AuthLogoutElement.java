package com.puresoltechnologies.purifinity.server.analysisservice.rest.api.security;

import java.io.Serializable;
import java.util.UUID;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class AuthLogoutElement implements Serializable {

    private static final long serialVersionUID = 8044441747125956593L;

    private final String username;
    private final UUID token;

    public AuthLogoutElement() {
	username = null;
	token = null;
    }

    @JsonCreator
    public AuthLogoutElement(
	    //
	    @JsonProperty("username") String username,
	    @JsonProperty("token") UUID token) {
	this.username = username;
	this.token = token;
    }

    public String getUsername() {
	return username;
    }

    public UUID getToken() {
	return token;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((token == null) ? 0 : token.hashCode());
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
	AuthLogoutElement other = (AuthLogoutElement) obj;
	if (token == null) {
	    if (other.token != null)
		return false;
	} else if (!token.equals(other.token))
	    return false;
	if (username == null) {
	    if (other.username != null)
		return false;
	} else if (!username.equals(other.username))
	    return false;
	return true;
    }
}