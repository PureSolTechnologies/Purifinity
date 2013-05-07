package com.puresol.license.api.exception;

/**
 * This exception is thrown in cases of illegal license files.
 * 
 * @author Rick-Rainer Ludwig
 */
public class IllegalLicenseFileException extends LicenseException {

	private static final long serialVersionUID = -6740278325342671539L;

	public IllegalLicenseFileException(String message) {
		super(message);
	}

	public IllegalLicenseFileException(String message, Throwable cause) {
		super(message, cause);
	}

}
