package com.puresol.passwordstore.domain;

/**
 * This exception is thrown in cases of account activation issues.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AccountActivationException extends Exception {

	private static final long serialVersionUID = 5052037298425784708L;

	private final long eventId;

	public AccountActivationException(long eventId, String message,
			Throwable cause) {
		super(message, cause);
		this.eventId = eventId;
	}

	public AccountActivationException(long eventId, String message) {
		super(message);
		this.eventId = eventId;
	}

	public long getEventId() {
		return eventId;
	}

}
