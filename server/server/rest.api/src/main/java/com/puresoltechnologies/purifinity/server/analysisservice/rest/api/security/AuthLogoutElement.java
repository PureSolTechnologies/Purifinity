package com.puresoltechnologies.purifinity.server.analysisservice.rest.api.security;

import java.io.Serializable;
import java.util.UUID;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import com.puresoltechnologies.commons.misc.types.EmailAddress;

public class AuthLogoutElement implements Serializable {

    private static final long serialVersionUID = 8044441747125956593L;

    private final EmailAddress email;
    private final UUID token;

    public AuthLogoutElement() {
	email = null;
	token = null;
    }

    @JsonCreator
    public AuthLogoutElement(
	    //
	    @JsonProperty("email") EmailAddress email,
	    @JsonProperty("token") UUID token) {
	this.email = email;
	this.token = token;
    }

    public EmailAddress getEmail() {
	return email;
    }

    public UUID getToken() {
	return token;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((token == null) ? 0 : token.hashCode());
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
	AuthLogoutElement other = (AuthLogoutElement) obj;
	if (token == null) {
	    if (other.token != null)
		return false;
	} else if (!token.equals(other.token))
	    return false;
	if (email == null) {
	    if (other.email != null)
		return false;
	} else if (!email.equals(other.email))
	    return false;
	return true;
    }
}