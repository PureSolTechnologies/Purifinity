package com.puresol.passwordstore.domain;

import com.puresol.commons.utils.ExceptionMessage;
import com.puresol.commons.utils.ExtendedException;

/**
 * This exception is thrown in cases of account activation issues.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AccountActivationException extends ExtendedException {

	private static final long serialVersionUID = -5765310131309262189L;

	public AccountActivationException(ExceptionMessage message) {
		super(message);
	}

}
