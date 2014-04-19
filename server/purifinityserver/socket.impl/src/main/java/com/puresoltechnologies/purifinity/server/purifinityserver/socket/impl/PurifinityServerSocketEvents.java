package com.puresoltechnologies.purifinity.server.purifinityserver.socket.impl;

import com.puresoltechnologies.purifinity.server.systemmonitor.events.Event;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventSeverity;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventType;

/**
 * This class contains the events which are sent by
 * {@link PurifinityServerSocket}.
 * 
 * @author Rick-Rainer Ludwig
 */
public class PurifinityServerSocketEvents {

	public static final String COMPONENT = "PurifinityServerSocket";

	/**
	 * Private constructor to avoid instantiation.
	 */
	private PurifinityServerSocketEvents() {
	}

	public static Event createSocketOpenedEvent() {
		return new Event(COMPONENT, 0x01, EventType.SYSTEM, EventSeverity.INFO,
				"Socket was opened.");
	}

	public static Event createSocketCloseEvent() {
		return new Event(COMPONENT, 0x02, EventType.SYSTEM, EventSeverity.INFO,
				"Socket is closing.");
	}

	public static Event createSocketErrorEvent(Throwable throwable) {
		return new Event(COMPONENT, 0x03, EventType.SYSTEM, EventSeverity.INFO,
				"Socket got error.");
	}

}
