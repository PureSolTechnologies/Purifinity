package com.puresoltechnologies.purifinity.server.passwordstore.rest.api;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import com.puresoltechnologies.commons.misc.types.EmailAddress;
import com.puresoltechnologies.commons.misc.types.Password;

public class PasswordAuthenticationEntity implements Serializable {

    private static final long serialVersionUID = 4098680267429328089L;

    private final EmailAddress email;
    private final Password password;

    /**
     * This default constructor is needed for Jackson JSON serialization.
     */
    public PasswordAuthenticationEntity() {
	email = null;
	password = null;
    }

    @JsonCreator
    public PasswordAuthenticationEntity(
	    @JsonProperty("email") EmailAddress email,
	    @JsonProperty("password") Password password) {
	super();
	this.email = email;
	this.password = password;
    }

    public EmailAddress getEmail() {
	return email;
    }

    public Password getPassword() {
	return password;
    }

}
