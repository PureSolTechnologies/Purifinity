package com.puresoltechnologies.purifinity.server.accountmanager.domain;

import com.puresoltechnologies.commons.misc.types.EmailAddress;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.Event;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventSeverity;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventType;

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
     * @param userId
     * @param email
     * @return
     */
    public static Event createAccountCreationEvent(EmailAddress email) {
	return new Event(COMPONENT, 0x01, EventType.USER_ACTION,
		EventSeverity.INFO, "User account created for '" + email + "'.")
		.setUserEmail(email);
    }
}
