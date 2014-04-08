package com.puresol.passwordstore.domain;


/**
 * This exception is thrown in cases of password change issues.
 * 
 * @author Rick-Rainer Ludwig
 */
public class PasswordChangeException extends Exception {

	private static final long serialVersionUID = -5765310131309262189L;

	public PasswordChangeException(String message) {
		super(message);
	}

}
