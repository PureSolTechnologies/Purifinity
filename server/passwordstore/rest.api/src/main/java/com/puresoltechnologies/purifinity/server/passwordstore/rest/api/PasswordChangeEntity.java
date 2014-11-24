package com.puresoltechnologies.purifinity.server.passwordstore.rest.api;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.commons.types.Password;

public class PasswordChangeEntity implements Serializable {

    private static final long serialVersionUID = 4098680267429328089L;

    private final EmailAddress email;
    private final Password oldPassword;
    private final Password newPassword;

    /**
     * This default constructor is needed for Jackson JSON serialization.
     */
    public PasswordChangeEntity() {
	email = null;
	oldPassword = null;
	newPassword = null;
    }

    @JsonCreator
    public PasswordChangeEntity(
	    @JsonProperty("emailAddress") EmailAddress email,
	    @JsonProperty("oldPassword") Password oldPassword,
	    @JsonProperty("newPassword") Password newPassword) {
	super();
	this.email = email;
	this.oldPassword = oldPassword;
	this.newPassword = newPassword;
    }

    public EmailAddress getEmail() {
	return email;
    }

    public Password getOldPassword() {
	return oldPassword;
    }

    public Password getNewPassword() {
	return newPassword;
    }

}
