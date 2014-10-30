package com.puresoltechnologies.commons.misc.types;

/**
 * This exception is thrown in cases of illegal email addresses.
 * 
 * @author Rick-Rainer Ludwig
 */
public class IllegalEmailAddressException extends IllegalArgumentException {

    private static final long serialVersionUID = -8116394166941389723L;

    private final String reason;

    public IllegalEmailAddressException(String emailAddress, String reason) {
	super("Email address '" + emailAddress + "' is invalid.\n" + reason);
	this.reason = reason;
    }

    public String getReason() {
	return reason;
    }

}
