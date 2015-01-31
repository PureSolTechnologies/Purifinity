package com.puresoltechnologies.purifinity.server.passwordstore.core.impl;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordEncryptionException;
import com.puresoltechnologies.server.systemmonitor.core.api.events.Event;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventSeverity;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventType;

/**
 * This class provides factory methods for EventLogger events.
 * 
 * @author Rick-Rainer Ludwig
 */
public class PasswordStoreEvents {

    private static final String COMPONENT = "PasswordStore";

    /**
     * Private constructor to avoid instantiation.
     */
    private PasswordStoreEvents() {
    }

    /**
     * Event for user account creation.
     * 
     * @param userId
     * @param email
     * @return
     */
    public static Event createStartEvent() {
	return new Event(COMPONENT, 1, EventType.SYSTEM, EventSeverity.INFO,
		"EventLogger was started up.");
    }

    public static Event createStopEvent() {
	return new Event(COMPONENT, 2, EventType.SYSTEM, EventSeverity.INFO,
		"EventLogger is about to be shut down...");
    }

    public static Event createAccountCreationEvent(EmailAddress email,
	    String activationKey) {
	return new Event(COMPONENT, 3, EventType.USER_ACTION,
		EventSeverity.INFO, "An account was created for '" + email
			+ "' with activation key '" + activationKey + "'.")
		.setUserEmail(email);
    }

    public static Event createAccountActivatedEvent(EmailAddress email,
	    String activationKey) {
	return new Event(COMPONENT, 4, EventType.USER_ACTION,
		EventSeverity.INFO, "An account was activated for '" + email
			+ "' with activation key '" + activationKey + "'.")
		.setUserEmail(email);
    }

    public static Event createUserAuthenticatedEvent(EmailAddress email) {
	return new Event(COMPONENT, 5, EventType.USER_ACTION,
		EventSeverity.INFO, "User '" + email + "' was authenticated.")
		.setUserEmail(email);
    }

    public static Event createPasswordChangedEvent(EmailAddress email) {
	return new Event(COMPONENT, 6, EventType.USER_ACTION,
		EventSeverity.INFO, "Password for user '" + email
			+ "' was changed.").setUserEmail(email);
    }

    public static Event createPasswordResetEvent(EmailAddress email) {
	return new Event(COMPONENT, 7, EventType.USER_ACTION,
		EventSeverity.INFO, "Password for user '" + email
			+ "' was reset.").setUserEmail(email);
    }

    public static Event createPasswordTooWeakErrorEvent(EmailAddress email) {
	/*
	 * We log the email in the message, too, because it is part of the
	 * error.
	 */
	return new Event(COMPONENT, 10, EventType.USER_EXCEPTION,
		EventSeverity.ERROR, "The password given by the user '" + email
			+ "' was too weak to be accepted.").setUserEmail(email);
    }

    public static Event createInvalidEmailAddressErrorEvent(EmailAddress email) {
	/*
	 * Because it is an invalid email, we do not know the user, so we cannot
	 * set it.
	 */
	return new Event(COMPONENT, 11, EventType.USER_EXCEPTION,
		EventSeverity.ERROR, "Creation of the account '" + email
			+ "' failed. The provided email address is invalid.");
    }

    public static Event createAccountAlreadyExistsErrorEvent(EmailAddress email) {
	/*
	 * Because it is an email which was used for another account, we do not
	 * know the real user, so we cannot set it.
	 */
	return new Event(COMPONENT, 12, EventType.USER_EXCEPTION,
		EventSeverity.ERROR, "Creation of the account '" + email
			+ "' failed. The account exists already.");
    }

    public static Event createPasswordEncryptionErrorEvent(EmailAddress email,
	    PasswordEncryptionException exception) {
	return new Event(COMPONENT, 13, EventType.SYSTEM_EXCEPTION,
		EventSeverity.ERROR, "The encryption of the passsword for '"
			+ email + "' errored.").setUserEmail(email)
		.setThrowable(exception);
    }

    public static Event createAccountAlreadyActivatedEvent(EmailAddress email) {
	return new Event(COMPONENT, 14, EventType.USER_EXCEPTION,
		EventSeverity.WARNING, "The account '" + email
			+ "' was already activated.").setUserEmail(email);
    }

    public static Event createInvalidActivationKeyErrorEvent(EmailAddress email) {
	return new Event(COMPONENT, 15, EventType.USER_EXCEPTION,
		EventSeverity.ERROR, "Activation of account for user '" + email
			+ "' was failed! Activation key is not valied!")
		.setUserEmail(email);
    }

    public static Event createUserAuthenticationFailedEvent(EmailAddress email) {
	/*
	 * Because authentication failed, we cannot log the user, because we are
	 * not sure who was trying to login.
	 */
	return new Event(COMPONENT, 16, EventType.USER_EXCEPTION,
		EventSeverity.WARNING, "Authentication of user '" + email
			+ "' failed!");
    }

    public static Event createUserAuthenticationFailedAccountNotExistsEvent(
	    EmailAddress email) {
	/*
	 * Because authentication failed, we cannot log the user, because we are
	 * not sure who was trying to login.
	 */
	return new Event(COMPONENT, 17, EventType.USER_EXCEPTION,
		EventSeverity.ERROR, "Authentication for user '" + email
			+ "' failed! Account does not exist!");
    }

    public static Event createUserAuthenticationFailedAccountNotActiveEvent(
	    EmailAddress email) {
	/*
	 * Because authentication failed, we cannot log the user, because we are
	 * not sure who was trying to login.
	 */
	return new Event(COMPONENT, 18, EventType.USER_EXCEPTION,
		EventSeverity.ERROR, "Authentication for user '" + email
			+ "' failed! Account is not active!");
    }

    public static Event createPasswordChangeFailedNotAuthenticatedEvent(
	    EmailAddress email) {
	return new Event(COMPONENT, 19, EventType.USER_EXCEPTION,
		EventSeverity.ERROR, "Password change not possible for user '"
			+ email + "' due to failed authentication!");
    }

    public static Event createPasswordChangeFailedPasswordTooWeakEvent(
	    EmailAddress email) {
	return new Event(COMPONENT, 20, EventType.USER_EXCEPTION,
		EventSeverity.ERROR, "Password change not possible for user '"
			+ email + "' due to too weak password.");
    }

    public static Event createPasswordResetFailedUnknownAccountEvent(
	    EmailAddress email) {
	return new Event(COMPONENT, 21, EventType.USER_EXCEPTION,
		EventSeverity.ERROR, "Password reset not possible for user '"
			+ email + "' due to non exsiting account!");
    }

}
