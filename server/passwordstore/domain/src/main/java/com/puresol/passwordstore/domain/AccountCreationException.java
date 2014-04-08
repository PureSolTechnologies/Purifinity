package com.puresol.passwordstore.domain;


/**
 * This exception is thrown in cases of account creation issues.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AccountCreationException extends Exception {

	private static final long serialVersionUID = -5765310131309262189L;

	public AccountCreationException(String message) {
		super(message);
	}

}
