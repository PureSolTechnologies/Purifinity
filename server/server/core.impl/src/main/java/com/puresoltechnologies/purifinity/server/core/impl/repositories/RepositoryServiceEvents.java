package com.puresoltechnologies.purifinity.server.core.impl.repositories;

import com.puresoltechnologies.server.systemmonitor.core.api.events.Event;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventSeverity;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventType;

/**
 * This class provides factory methods for AnalyzerRegistration events.
 * 
 * @author Rick-Rainer Ludwig
 */
public class RepositoryServiceEvents {

    private static final String COMPONENT = "RepositoryService";

    /**
     * Private constructor to avoid instantiation.
     */
    private RepositoryServiceEvents() {
    }

    public static Event createStartupEvent() {
	return new Event(COMPONENT, 1l, EventType.SYSTEM, EventSeverity.INFO,
		"RepositoryService was started.");
    }

    public static Event createShutdownEvent() {
	return new Event(COMPONENT, 2l, EventType.SYSTEM, EventSeverity.INFO,
		"RepositoryService is going to shut down.");
    }

}
