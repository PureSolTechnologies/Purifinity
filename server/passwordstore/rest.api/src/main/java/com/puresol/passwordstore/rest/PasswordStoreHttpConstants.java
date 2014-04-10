package com.puresol.passwordstore.rest;

public class PasswordStoreHttpConstants {

	/**
	 * This constant is used to mark the activation key header in REST
	 * responses.
	 */
	public static final String HTTP_HEADER_ACTIVATION_KEY = "activation-key";

	/**
	 * This constant is used to mark the new password header in REST responses.
	 */
	public static final String HTTP_HEADER_NEW_PASSWORD = "new-password";

	/**
	 * This constant marks the header for a REST response for user messages
	 * which are presented to show special conditions.
	 */
	public static final String HTTP_HEADER_EVENT_ID = "event-id";

	/**
	 * With this constant the response header is marked which contains the event
	 * message to be used for the message handling.
	 */
	public static final String HTTP_HEADER_EVENT_MESSAGE = "event-message";

	/**
	 * This constant contains the header key for the user-id which is returned
	 * from the password store to the client after successful activation.
	 */
	public static final String HTTP_HEADER_USER_EMAIL = "user-email";

}
