package com.puresoltechnologies.purifinity.server.passwordstore.domain;


/**
 * This exception is thrown in cases of exception within the password encryption
 * subsystem.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class PasswordEncryptionException extends Exception {

	private static final long serialVersionUID = -8257771103909819998L;

	public PasswordEncryptionException(String message, Throwable cause) {
		super(message, cause);
	}

	public PasswordEncryptionException(String message) {
		super(message);
	}

}
