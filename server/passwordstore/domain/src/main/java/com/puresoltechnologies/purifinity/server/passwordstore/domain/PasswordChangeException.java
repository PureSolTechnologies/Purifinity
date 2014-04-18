package com.puresoltechnologies.purifinity.server.passwordstore.domain;

/**
 * This exception is thrown in cases of password change issues.
 * 
 * @author Rick-Rainer Ludwig
 */
public class PasswordChangeException extends Exception {

	private static final long serialVersionUID = 1392998301414837383L;

	private final long eventId;

	public PasswordChangeException(long eventId, String message, Throwable cause) {
		super(message, cause);
		this.eventId = eventId;
	}

	public PasswordChangeException(long eventId, String message) {
		super(message);
		this.eventId = eventId;
	}

	public long getEventId() {
		return eventId;
	}

}
