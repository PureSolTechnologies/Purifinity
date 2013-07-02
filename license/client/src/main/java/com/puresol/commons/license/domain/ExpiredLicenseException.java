package com.puresol.commons.license.domain;

/**
 * This exception is thrown in cases of expired licenses.
 * 
 * @author Rick-Rainer Ludwig
 */

public class ExpiredLicenseException extends LicenseException {

	private static final long serialVersionUID = -8963269188296233550L;

	public ExpiredLicenseException(String message) {
		super(message);
	}

	public ExpiredLicenseException(String message, Throwable cause) {
		super(message, cause);
	}

}
