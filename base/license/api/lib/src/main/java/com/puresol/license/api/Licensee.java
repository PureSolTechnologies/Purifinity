package com.puresol.license.api;

/**
 * This value object represents a single licensee.
 * 
 * @author Rick-Rainer Ludwig
 */
public class Licensee {

    private final String customerId;
    private final String name;

    public Licensee(String customerId, String name) {
	super();
	this.customerId = customerId;
	this.name = name;
    }

    public String getCustomerId() {
	return customerId;
    }

    public String getName() {
	return name;
    }

    @Override
    public String toString() {
	return name + " (" + customerId + ")";
    }
}
