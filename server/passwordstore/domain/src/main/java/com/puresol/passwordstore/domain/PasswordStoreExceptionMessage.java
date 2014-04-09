package com.puresol.passwordstore.domain;

import java.util.Locale;

import com.puresol.commons.utils.ExceptionMessage;

public enum PasswordStoreExceptionMessage implements ExceptionMessage {

	PASSWORD_TOO_WEAK(1) {

		@Override
		public String getMessage(Locale locale) {
			return "Password is too weak!";
		}
	},
	ACCOUNT_ALREADY_EXISTS(2) {

		@Override
		public String getMessage(Locale locale) {
			return "Account does already exist!";
		}
	},
	INVALID_EMAIL_ADDRESS(3) {

		@Override
		public String getMessage(Locale locale) {
			return "Email address format is invalid!";
		}
	},
	INVALID_ACTIVATION_KEY(4) {

		@Override
		public String getMessage(Locale locale) {
			return "Activation key is invalid!";
		}
	},
	UNKNOWN_EMAIL_ADDRESS(5) {

		@Override
		public String getMessage(Locale locale) {
			return "Email address is unknown!";
		}
	},
	ACCOUNT_ALREADY_ACTIVATED(6) {
		@Override
		public String getMessage(Locale locale) {
			return "The account was already activated!";
		}
	};

	private final int id;

	private PasswordStoreExceptionMessage(int id) {
		this.id = id;
	}

	@Override
	public String getId() {
		return "PasswordStore-" + id;
	}

	/**
	 * This method is used to find the corresponding exception message to an id.
	 * 
	 * @param id
	 *            is the error id to be looked up.
	 * @return The exception message object is returned with the corresponding
	 *         id. An {@link IllegalStateException} is thrown in cases the id
	 *         could not be found.
	 */
	public static ExceptionMessage getFromId(String id) {
		for (PasswordStoreExceptionMessage exception : values()) {
			if (exception.getId().equals(id)) {
				return exception;
			}
		}
		throw new IllegalArgumentException("Unknown exception id '" + id + "'!");
	}
}
