package com.puresoltechnologies.purifinity.server.common.utils;

import java.text.MessageFormat;

/**
 * This is just a simple exception implementation which can be thrown in
 * situation of not implemented functions.
 * 
 * This exception is used to have a unique error message and treatment on this
 * issue.
 * 
 * @author Rick-Rainer Ludwig
 */
public class NotImplementedException extends RuntimeException {

	private static final long serialVersionUID = -7045357047167877512L;

	/**
	 * This constructor takes a name of the function and put it into the
	 * exception's message.
	 * 
	 * @param nameOfFunction
	 */
	public NotImplementedException(String nameOfFunction) {
		super(MessageFormat.format(
				"The function '{0}' is not implemented, yet!", nameOfFunction));
	}
}
