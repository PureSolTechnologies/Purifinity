package com.puresoltechnologies.purifinity.server.common.plugins;

import com.puresoltechnologies.server.systemmonitor.core.api.events.Event;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventSeverity;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventType;

/**
 * This class provides factory methods for AnalyzerRegistration events.
 * 
 * @author Rick-Rainer Ludwig
 */
public class ServiceManagerEvents {

    private static final String COMPONENT = "ServiceManager";

    /**
     * Private constructor to avoid instantiation.
     */
    private ServiceManagerEvents() {
    }

    public static Event createStartupEvent(String serviceManagerName) {
	return new Event(COMPONENT, 1l, EventType.SYSTEM, EventSeverity.INFO,
		"Service Manager'" + serviceManagerName + "' was started.");
    }

    public static Event createShutdownEvent(String pluginServiceName) {
	return new Event(COMPONENT, 2l, EventType.SYSTEM, EventSeverity.INFO,
		"Service Manager '" + pluginServiceName
			+ "'  is going to shut down.");
    }

}
