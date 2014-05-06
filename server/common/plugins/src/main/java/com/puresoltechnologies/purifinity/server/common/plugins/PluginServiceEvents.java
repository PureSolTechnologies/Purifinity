package com.puresoltechnologies.purifinity.server.common.plugins;

import com.puresoltechnologies.purifinity.server.systemmonitor.events.Event;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventSeverity;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventType;

/**
 * This class provides factory methods for AnalyzerRegistration events.
 * 
 * @author Rick-Rainer Ludwig
 */
public class PluginServiceEvents {

	private static final String COMPONENT = "AnalyzerRegistration";

	/**
	 * Private constructor to avoid instantiation.
	 */
	private PluginServiceEvents() {
	}

	public static Event createStartupEvent(String pluginServiceName) {
		return new Event(COMPONENT, 1l, EventType.SYSTEM, EventSeverity.INFO,
				"Plugin Service '" + pluginServiceName + "' was started.");
	}

	public static Event createShutdownEvent(String pluginServiceName) {
		return new Event(COMPONENT, 2l, EventType.SYSTEM, EventSeverity.INFO,
				"Plugin Service '" + pluginServiceName
						+ "'  is going to shut down.");
	}

}
