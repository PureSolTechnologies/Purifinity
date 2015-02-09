package com.puresoltechnologies.purifinity.server.passwordstore.domain;

/**
 * This exception is thrown in cases of account activation issues.
 * 
 * @author Rick-Rainer Ludwig
 */
public class PasswordActivationException extends Exception {

    private static final long serialVersionUID = 5052037298425784708L;

    public PasswordActivationException(String message, Throwable cause) {
	super(message, cause);
    }

    public PasswordActivationException(String message) {
	super(message);
    }
}
