package com.puresoltechnologies.purifinity.server.accountmanager.domain;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.server.systemmonitor.core.api.events.Event;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventSeverity;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventType;

/**
 * This class contains factory methods for AccountManager events which are to be
 * logged in EventReporter.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AccountManagerEvents {

    private static final String COMPONENT = "AccountManager";

    /**
     * Private constructor to avoid instantiation.
     */
    private AccountManagerEvents() {
    }

    /**
     * Event for user account creation.
     * 
     * @param email
     *            is the {@link EmailAddress} of the user.
     * @return The {@link Event} is returned.
     */
    public static Event createAccountCreationEvent(EmailAddress email) {
	return new Event(COMPONENT, 0x01, EventType.USER_ACTION, EventSeverity.INFO,
		"User account created for '" + email + "'.").setUserEmail(email);
    }

    public static Event alterAccountCreationEvent(EmailAddress email) {
	return new Event(COMPONENT, 0x01, EventType.USER_ACTION, EventSeverity.INFO,
		"User account altered for '" + email + "'.").setUserEmail(email);
    }
}
