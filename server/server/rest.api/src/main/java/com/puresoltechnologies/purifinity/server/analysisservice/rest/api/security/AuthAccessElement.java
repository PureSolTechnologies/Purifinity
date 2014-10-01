package com.puresoltechnologies.purifinity.server.analysisservice.rest.api.security;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class AuthAccessElement implements Serializable {

    private static final long serialVersionUID = -2226392567855828037L;

    public static final String PARAM_AUTH_ID = "auth-id";
    public static final String PARAM_AUTH_TOKEN = "auth-token";

    private final String authId;
    private final String authToken;
    private final String authPermission;

    public AuthAccessElement(
	    //
	    @JsonProperty("authId") String authId,
	    @JsonProperty("authToken") String authToken,
	    @JsonProperty("authPermission") String authPermission) {
	this.authId = authId;
	this.authToken = authToken;
	this.authPermission = authPermission;
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

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((authId == null) ? 0 : authId.hashCode());
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
	AuthAccessElement other = (AuthAccessElement) obj;
	if (authId == null) {
	    if (other.authId != null)
		return false;
	} else if (!authId.equals(other.authId))
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