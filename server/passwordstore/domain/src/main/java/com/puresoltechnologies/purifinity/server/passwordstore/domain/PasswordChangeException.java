package com.puresoltechnologies.purifinity.server.passwordstore.domain;

/**
 * This exception is thrown in cases of password change issues.
 * 
 * @author Rick-Rainer Ludwig
 */
public class PasswordChangeException extends Exception {

    private static final long serialVersionUID = 1392998301414837383L;

    public PasswordChangeException(String message, Throwable cause) {
	super(message, cause);
    }

    public PasswordChangeException(String message) {
	super(message);
    }
}
