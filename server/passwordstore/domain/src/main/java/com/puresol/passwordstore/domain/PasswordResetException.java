package com.puresol.passwordstore.domain;


/**
 * This exception is thrown in cases of password reset issues.
 * 
 * @author Rick-Rainer Ludwig
 */
public class PasswordResetException extends Exception {

	private static final long serialVersionUID = -5765310131309262189L;

	public PasswordResetException(String message) {
		super(message);
	}

}
