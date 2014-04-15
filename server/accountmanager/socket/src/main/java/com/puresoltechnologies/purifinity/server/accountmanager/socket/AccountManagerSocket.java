package com.puresoltechnologies.purifinity.server.accountmanager.socket;

import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.puresoltechnologies.purifinity.server.systemmonitor.events.Event;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventLogger;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventSeverity;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventType;

@ServerEndpoint(value = "/socket")
public class AccountManagerSocket {

	@Inject
	private EventLogger eventLogger;

	@OnOpen
	public void invokeOnOpen(Session session, EndpointConfig config) {
		eventLogger.logEvent(new Event("AccountManager", 0x01,
				EventType.SYSTEM, EventSeverity.INFO, "Websocket was opened."));
	}

	@OnClose
	public void invokeOnClose(Session session, CloseReason reason) {
		eventLogger.logEvent(new Event("AccountManager", 0x02,
				EventType.SYSTEM, EventSeverity.INFO, "Websocket was closed."));
	}

	@OnMessage
	public void invokeOnMessage(Session session, String message) {
		eventLogger.logEvent(new Event("AccountManager", 0x03,
				EventType.SYSTEM, EventSeverity.INFO, "Message: " + message));
	}

	@OnError
	public void invokeOnError(Session session, Throwable throwable) {
		eventLogger.logEvent(new Event("AccountManager", 0x04,
				EventType.SYSTEM_EXCEPTION, EventSeverity.ERROR,
				"Websocket was closed.").setThrowable(throwable));
	}
}
