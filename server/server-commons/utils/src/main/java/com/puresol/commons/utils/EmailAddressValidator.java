package com.puresol.commons.utils;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * This class provides an email address validator. The validation is based on
 * the Java Mail API.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EmailAddressValidator {

	/**
	 * This method performs the actual validation.
	 * 
	 * @param email
	 *            is the email address under test as {@link String}.
	 * @return True is returned if the email address is conforming. Otherwise,
	 *         false is returned.
	 */
	public static boolean validate(String email) {
		try {
			if (email == null) {
				return false;
			}
			new InternetAddress(email).validate();
			return true;
		} catch (AddressException e) {
			return false;
		}
	}

}
