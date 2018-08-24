package com.puresoltechnologies.purifinity.server.systemmonitor.core.impl.events;

import com.puresoltechnologies.server.systemmonitor.core.api.events.Event;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventSeverity;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventType;

/**
 * This class contains the events for the EventLogger which are logged to
 * EventLogger.
 * 
 * @author Rick-Rainer Ludwig
 */
public class EventLoggerEvents {

    private static final String COMPONENT = "EventLogger";

    /**
     * Private constructor to avoid instantiation.
     */
    private EventLoggerEvents() {
    }

    /**
     * Event for user account creation.
     * 
     * @return The {@link Event} is returned.
     */
    public static Event createStartEvent() {
	return new Event(COMPONENT, 0x01, EventType.SYSTEM, EventSeverity.INFO, "EventLogger was started up.");
    }

    public static Event createStopEvent() {
	return new Event(COMPONENT, 0x02, EventType.SYSTEM, EventSeverity.INFO,
		"EventLogger is about to be shut down...");
    }
}
