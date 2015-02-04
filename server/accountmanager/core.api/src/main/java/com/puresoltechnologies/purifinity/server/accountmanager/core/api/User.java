package com.puresoltechnologies.purifinity.server.accountmanager.core.api;

import com.puresoltechnologies.commons.types.EmailAddress;

/**
 * Represents a single user account.
 * 
 * @author Rick-Rainer Ludwig
 */
public class User {

    private final EmailAddress email;
    private final String name;
    private final Role role;

    public User(EmailAddress email, String name, Role role) {
	super();
	this.email = email;
	this.name = name;
	this.role = role;
    }

    public EmailAddress getEmail() {
	return email;
    }

    public String getName() {
	return name;
    }

    public Role getRole() {
	return role;
    }
}
