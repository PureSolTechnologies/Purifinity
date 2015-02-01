package com.puresoltechnologies.purifinity.server.common.rest.security;

import java.io.Serializable;

import com.puresoltechnologies.commons.types.EmailAddress;

public class User implements Serializable {

    private static final long serialVersionUID = -4543036705156996795L;

    private final EmailAddress email;
    private final String authRole;

    public User(EmailAddress email, String authRole) {
	super();
	this.email = email;
	this.authRole = authRole;
    }

    public EmailAddress getEmail() {
	return email;
    }

    public String getAuthRole() {
	return authRole;
    }

}
