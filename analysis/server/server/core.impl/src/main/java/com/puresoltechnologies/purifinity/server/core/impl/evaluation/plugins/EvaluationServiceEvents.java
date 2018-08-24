package com.puresoltechnologies.purifinity.server.core.impl.evaluation.plugins;

import com.puresoltechnologies.server.systemmonitor.core.api.events.Event;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventSeverity;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventType;

/**
 * This class provides factory methods for AnalyzerRegistration events.
 * 
 * @author Rick-Rainer Ludwig
 */
public class EvaluationServiceEvents {

    private static final String COMPONENT = "EvaluationService";

    /**
     * Private constructor to avoid instantiation.
     */
    private EvaluationServiceEvents() {
    }

    public static Event createStartupEvent() {
	return new Event(COMPONENT, 1l, EventType.SYSTEM, EventSeverity.INFO,
		"EvaluationService was started.");
    }

    public static Event createShutdownEvent() {
	return new Event(COMPONENT, 2l, EventType.SYSTEM, EventSeverity.INFO,
		"EvaluationService is going to shut down.");
    }

}
