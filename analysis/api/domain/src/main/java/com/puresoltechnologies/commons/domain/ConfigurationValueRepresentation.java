package com.puresoltechnologies.commons.domain;

/**
 * This enum is used to describe a configuration value in a more abstract
 * manner.
 * 
 * @author Rick-Rainer Ludwig
 */
public enum ConfigurationValueRepresentation {

	/**
	 * The configuration value is a string.
	 */
	STRING,
	/**
	 * The configuration value is an integer number (no digits behind the dot).
	 */
	INTEGER,
	/**
	 * The configuration value is a number.
	 */
	DECIMAL,
	/**
	 * The configuration value is a boolean type and only either true (on,
	 * enabled) or false (off, disabled).
	 */
	BOOLEAN;

}
