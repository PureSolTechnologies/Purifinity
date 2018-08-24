package com.puresoltechnologies.purifinity.server.passwordstore.rest.api;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PasswordCreationEntity implements Serializable {

    private static final long serialVersionUID = 4098680267429328089L;

    private final String email;
    private final String password;

    /**
     * This default constructor is needed for Jackson JSON serialization.
     */
    public PasswordCreationEntity() {
	email = null;
	password = null;
    }

    @JsonCreator
    public PasswordCreationEntity(@JsonProperty("emailAddress") String email,
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
