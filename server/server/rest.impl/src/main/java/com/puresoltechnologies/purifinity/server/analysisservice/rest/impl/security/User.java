package com.puresoltechnologies.purifinity.server.analysisservice.rest.impl.security;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = -4543036705156996795L;

    private final String username;
    private final String authRole;

    public User(String username, String authRole) {
	super();
	this.username = username;
	this.authRole = authRole;
    }

    public String getUsername() {
	return username;
    }

    public String getAuthRole() {
	return authRole;
    }

}
