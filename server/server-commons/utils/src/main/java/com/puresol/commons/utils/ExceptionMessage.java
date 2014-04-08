package com.puresol.commons.utils;

import java.util.Locale;

/**
 * An exception message is used to bring a meaningful error message to the user
 * and to have an unique error id for customer support.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface ExceptionMessage {

	/**
	 * This method returns the id String for this message. Each message should
	 * be a unique identifier for error handling and customer support.
	 * 
	 * @return A {@link String} is returned.
	 */
	public String getId();

	/**
	 * This method returns the exception message localized.
	 * 
	 * @param locale
	 *            is the locale to use for the error message.
	 * @return Returns an internationalized string.
	 */
	public String getMessage(Locale locale);

}
