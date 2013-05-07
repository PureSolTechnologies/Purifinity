package com.puresol.license.api.exception;

/**
 * This is the base exception for the license management.
 * 
 * @author Rick-Rainer Ludwig
 */
public class LicenseException extends RuntimeException {

	private static final long serialVersionUID = 8497895449692330569L;

	public LicenseException(String message) {
		super(message);
	}

	public LicenseException(String message, Throwable cause) {
		super(message, cause);
	}

}
