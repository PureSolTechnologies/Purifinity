package com.puresol.passwordstore.core.impl;


public interface SecurityKeyGenerator {

	/**
	 * This function generates a security key for the password safe. This is the
	 * database identifier for secured objects.
	 * 
	 * @return
	 */
	public String generate();
}
