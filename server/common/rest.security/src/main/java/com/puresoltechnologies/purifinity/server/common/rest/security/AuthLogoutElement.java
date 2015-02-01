package com.puresoltechnologies.purifinity.server.common.rest.security;

import java.io.Serializable;
import java.util.UUID;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class AuthLogoutElement implements Serializable {

    private static final long serialVersionUID = 8044441747125956593L;

    private final String authId;
    private final UUID token;

    public AuthLogoutElement() {
	authId = null;
	token = null;
    }

    @JsonCreator
    public AuthLogoutElement(
	    //
	    @JsonProperty("authId") String authId,
	    @JsonProperty("token") UUID token) {
	this.authId = authId;
	this.token = token;
    }

    public String getAuthId() {
	return authId;
    }

    public UUID getToken() {
	return token;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((token == null) ? 0 : token.hashCode());
	result = prime * result + ((authId == null) ? 0 : authId.hashCode());
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
	if (authId == null) {
	    if (other.authId != null)
		return false;
	} else if (!authId.equals(other.authId))
	    return false;
	return true;
    }
}