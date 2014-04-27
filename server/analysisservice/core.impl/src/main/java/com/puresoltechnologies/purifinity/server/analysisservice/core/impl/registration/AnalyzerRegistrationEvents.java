package com.puresoltechnologies.purifinity.server.analysisservice.core.impl.registration;

import com.puresoltechnologies.purifinity.server.systemmonitor.events.Event;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventSeverity;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventType;

/**
 * This class provides factory methods for AnalyzerRegistration events.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AnalyzerRegistrationEvents {

	private static final String COMPONENT = "AnalyzerRegistration";

	/**
	 * Private constructor to avoid instantiation.
	 */
	private AnalyzerRegistrationEvents() {
	}

	public static Event createStartupEvent() {
		return new Event(COMPONENT, 1l, EventType.SYSTEM, EventSeverity.INFO,
				"AnalyzerRegistration was started.");
	}

	public static Event createShutdownEvent() {
		return new Event(COMPONENT, 2l, EventType.SYSTEM, EventSeverity.INFO,
				"AnalyzerRegistration is going to shut down.");
	}

}
