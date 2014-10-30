package com.puresoltechnologies.purifinity.server.passwordstore.rest.api;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import com.puresoltechnologies.commons.misc.types.EmailAddress;

public class PasswordActivationEntity implements Serializable {

    private static final long serialVersionUID = 4098680267429328089L;

    private final EmailAddress email;
    private final String activationKey;

    /**
     * This default constructor is needed for Jackson JSON serialization.
     */
    public PasswordActivationEntity() {
	email = null;
	activationKey = null;
    }

    @JsonCreator
    public PasswordActivationEntity(
	    @JsonProperty("emailAddress") EmailAddress email,
	    @JsonProperty("activationKey") String activationKey) {
	super();
	this.email = email;
	this.activationKey = activationKey;
    }

    public EmailAddress getEmail() {
	return email;
    }

    public String getActivationKey() {
	return activationKey;
    }

}
