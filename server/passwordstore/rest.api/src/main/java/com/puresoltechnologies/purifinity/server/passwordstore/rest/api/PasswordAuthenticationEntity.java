package com.puresoltechnologies.purifinity.server.passwordstore.rest.api;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class PasswordAuthenticationEntity implements Serializable {

    private static final long serialVersionUID = 4098680267429328089L;

    private final String email;
    private final String password;

    /**
     * This default constructor is needed for Jackson JSON serialization.
     */
    public PasswordAuthenticationEntity() {
	email = null;
	password = null;
    }

    @JsonCreator
    public PasswordAuthenticationEntity(@JsonProperty("email") String email,
	    @JsonProperty("password") String password) {
	super();
	this.email = email;
	this.password = password;
    }

    public String getEmail() {
	return email;
    }

    public String getPassword() {
	return password;
    }

}
