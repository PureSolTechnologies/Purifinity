package com.puresoltechnologies.purifinity.server.core.impl.analysis;

import com.puresoltechnologies.server.systemmonitor.core.api.events.Event;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventSeverity;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventType;

/**
 * This class provides factory methods for AnalyzerRegistration events.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AnalysisServiceEvents {

    private static final String COMPONENT = "AnalyzerService";

    /**
     * Private constructor to avoid instantiation.
     */
    private AnalysisServiceEvents() {
    }

    public static Event createStartupEvent() {
	return new Event(COMPONENT, 1l, EventType.SYSTEM, EventSeverity.INFO,
		"AnalysisService was started.");
    }

    public static Event createShutdownEvent() {
	return new Event(COMPONENT, 2l, EventType.SYSTEM, EventSeverity.INFO,
		"AnalysisService is going to shut down.");
    }

}
