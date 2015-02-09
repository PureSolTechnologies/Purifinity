package com.puresoltechnologies.purifinity.server.passwordstore.domain;

/**
 * This exception is thrown in cases of password reset issues.
 * 
 * @author Rick-Rainer Ludwig
 */
public class PasswordResetException extends Exception {

    private static final long serialVersionUID = -9117885627499535086L;

    public PasswordResetException(String message, Throwable cause) {
	super(message, cause);
    }

    public PasswordResetException(String message) {
	super(message);
    }
}
