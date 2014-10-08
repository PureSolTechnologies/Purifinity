package com.puresoltechnologies.purifinity.server.accountmanager.domain;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Represents a single user account.
 * 
 * @author Rick-Rainer Ludwig
 */
public class User {

    private final String email;
    private final String name;

    public User() {
	email = null;
	name = null;
    }

    @JsonCreator
    public User(@JsonProperty("email") String email,
	    @JsonProperty("name") String name) {
	super();
	this.email = email;
	this.name = name;
    }

    public String getEmail() {
	return email;
    }

    public String getName() {
	return name;
    }

}
