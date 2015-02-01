package com.puresoltechnologies.purifinity.server.common.rest.security;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class AuthElement implements Serializable {

    private static final long serialVersionUID = -2226392567855828037L;

    public static final String AUTH_ID_HEADER = "auth-id";
    public static final String AUTH_TOKEN_HEADER = "auth-token";

    private final String authId;
    private final String authToken;
    private final String authPermission;
    private final String authMessage;

    @JsonCreator
    public AuthElement(
	    //
	    @JsonProperty("authId") String authId,
	    @JsonProperty("authToken") String authToken,
	    @JsonProperty("authPermission") String authPermission,
	    @JsonProperty("authMessage") String authMessage) {
	this.authId = authId;
	this.authToken = authToken;
	this.authPermission = authPermission;
	this.authMessage = authMessage;
    }

    public String getAuthId() {
	return authId;
    }

    public String getAuthToken() {
	return authToken;
    }

    public String getAuthPermission() {
	return authPermission;
    }

    public String getAuthMessage() {
	return authMessage;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((authId == null) ? 0 : authId.hashCode());
	result = prime * result
		+ ((authMessage == null) ? 0 : authMessage.hashCode());
	result = prime * result
		+ ((authPermission == null) ? 0 : authPermission.hashCode());
	result = prime * result
		+ ((authToken == null) ? 0 : authToken.hashCode());
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
	AuthElement other = (AuthElement) obj;
	if (authId == null) {
	    if (other.authId != null)
		return false;
	} else if (!authId.equals(other.authId))
	    return false;
	if (authMessage == null) {
	    if (other.authMessage != null)
		return false;
	} else if (!authMessage.equals(other.authMessage))
	    return false;
	if (authPermission == null) {
	    if (other.authPermission != null)
		return false;
	} else if (!authPermission.equals(other.authPermission))
	    return false;
	if (authToken == null) {
	    if (other.authToken != null)
		return false;
	} else if (!authToken.equals(other.authToken))
	    return false;
	return true;
    }

}