package com.puresol.license.api;

/**
 * This value object represents a single licenser.
 * 
 * @author Rick-Rainer Ludwig
 */
public class Licenser {

    private final String name;
    private final String email;

    public Licenser(String name, String email) {
	super();
	this.name = name;
	this.email = email;
    }

    public String getName() {
	return name;
    }

    public String getEmail() {
	return email;
    }

}
