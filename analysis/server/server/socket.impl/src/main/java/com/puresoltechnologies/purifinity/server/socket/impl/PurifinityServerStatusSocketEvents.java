package com.puresoltechnologies.purifinity.server.socket.impl;

import com.puresoltechnologies.server.systemmonitor.core.api.events.Event;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventSeverity;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventType;

/**
 * This class contains the events which are sent by
 * {@link PurifinityServerStatusSocket}.
 * 
 * @author Rick-Rainer Ludwig
 */
public class PurifinityServerStatusSocketEvents {

    public static final String COMPONENT = "PurifinityServerStatusSocket";

    /**
     * Private constructor to avoid instantiation.
     */
    private PurifinityServerStatusSocketEvents() {
    }

    public static Event createSocketOpenedEvent() {
	return new Event(COMPONENT, 0x01, EventType.SYSTEM, EventSeverity.INFO,
		"Socket was opened.");
    }

    public static Event createSocketCloseEvent(String reason) {
	return new Event(COMPONENT, 0x02, EventType.SYSTEM, EventSeverity.INFO,
		"Socket is closing of reason '" + reason + "'.");
    }

    public static Event createSocketErrorEvent(Throwable throwable) {
	return new Event(COMPONENT, 0x03, EventType.SYSTEM,
		EventSeverity.ERROR, "Socket got error.")
		.setThrowable(throwable);
    }

}
