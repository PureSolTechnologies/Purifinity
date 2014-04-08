package com.puresol.commons.utils;

import java.util.Locale;

/**
 * This exception implementation is used to provide additional information than
 * the exceptions generally implemented in Java.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ExtendedException extends Exception {

	private static final long serialVersionUID = -5982763044437959199L;

	private final ExceptionMessage message;

	public ExtendedException(ExceptionMessage message, Throwable cause) {
		super(message.getMessage(Locale.US), cause);
		this.message = message;
	}

	public ExtendedException(ExceptionMessage message) {
		super(message.getMessage(Locale.US));
		this.message = message;
	}

	/**
	 * This method returns the exception message which provides a system wide
	 * unique exception id and localized and a user readable message.
	 * 
	 * @return The {@link ExceptionMessage} is returned.
	 */
	public final ExceptionMessage getExceptionMessage() {
		return message;
	}

}
