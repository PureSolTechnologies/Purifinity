package com.puresoltechnologies.purifinity.server.systemmonitor.events;

/**
 * This class contains the events for the EventLogger itself which are logged on
 * its on behalf.
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
	 * @param userId
	 * @param email
	 * @return
	 */
	public static Event createStartEvent() {
		return new Event(COMPONENT, 0x01, EventType.SYSTEM, EventSeverity.INFO,
				"EventLogger was started up.");
	}

	public static Event createStopEvent() {
		return new Event(COMPONENT, 0x02, EventType.SYSTEM, EventSeverity.INFO,
				"EventLogger is about to be shut down...");
	}
}
