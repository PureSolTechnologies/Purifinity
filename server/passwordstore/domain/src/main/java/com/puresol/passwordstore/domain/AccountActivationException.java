package com.puresol.passwordstore.domain;

/**
 * This exception is thrown in cases of account activation issues.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AccountActivationException extends Exception {

	private static final long serialVersionUID = -5765310131309262189L;

	public AccountActivationException(String message) {
		super(message);
	}

}
