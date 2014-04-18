package com.puresoltechnologies.purifinity.server.passwordstore.domain;

/**
 * This exception is thrown in cases of password reset issues.
 * 
 * @author Rick-Rainer Ludwig
 */
public class PasswordResetException extends Exception {

	private static final long serialVersionUID = -9117885627499535086L;

	private final long eventId;

	public PasswordResetException(long eventId, String message, Throwable cause) {
		super(message, cause);
		this.eventId = eventId;
	}

	public PasswordResetException(long eventId, String message) {
		super(message);
		this.eventId = eventId;
	}

	public long getEventId() {
		return eventId;
	}

}
