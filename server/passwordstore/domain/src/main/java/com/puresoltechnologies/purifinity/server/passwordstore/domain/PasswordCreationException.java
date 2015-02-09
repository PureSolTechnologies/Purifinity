package com.puresoltechnologies.purifinity.server.passwordstore.domain;

/**
 * This exception is thrown in cases of account creation issues.
 * 
 * @author Rick-Rainer Ludwig
 */
public class PasswordCreationException extends Exception {

    private static final long serialVersionUID = -3016065614157320012L;

    public PasswordCreationException(String message, Throwable cause) {
	super(message, cause);
    }

    public PasswordCreationException(String message) {
	super(message);
    }

}
