package com.puresoltechnologies.purifinity.server.systemmonitor.metrics;

import com.puresoltechnologies.purifinity.server.systemmonitor.events.Event;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventSeverity;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventType;

/**
 * This class contains the events for the MetricLogger which are logged to
 * EventLogger.
 * 
 * @author Rick-Rainer Ludwig
 */
public class MetricLoggerEvents {

	private static final String COMPONENT = "MetricLogger";

	/**
	 * Private constructor to avoid instantiation.
	 */
	private MetricLoggerEvents() {
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
				"MetricLogger was started up.");
	}

	public static Event createStopEvent() {
		return new Event(COMPONENT, 0x02, EventType.SYSTEM, EventSeverity.INFO,
				"MetricLogger is about to be shut down...");
	}
}
