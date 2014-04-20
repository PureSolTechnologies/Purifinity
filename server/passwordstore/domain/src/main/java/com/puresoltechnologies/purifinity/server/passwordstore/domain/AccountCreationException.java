package com.puresoltechnologies.purifinity.server.passwordstore.domain;

/**
 * This exception is thrown in cases of account creation issues.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AccountCreationException extends Exception {

	private static final long serialVersionUID = -3016065614157320012L;

	private final long eventId;

	public AccountCreationException(long eventId, String message,
			Throwable cause) {
		super(message, cause);
		this.eventId = eventId;
	}

	public AccountCreationException(long eventId, String message) {
		super(message);
		this.eventId = eventId;
	}

	public long getEventId() {
		return eventId;
	}

}