package com.puresoltechnologies.purifinity.server.purifinityserver.socket;

import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;

import com.puresoltechnologies.purifinity.server.purifinityserver.core.api.PurifinityServer;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventLogger;

@ServerEndpoint(value = "/socket")
public class PurifinityServerSocket {

	@Inject
	private Logger logger;

	@Inject
	private EventLogger eventLogger;

	@Inject
	private PurifinityServer purifinityServer;

	@OnOpen
	public void open(Session session, EndpointConfig config) {
		eventLogger.logEvent(PurifinityServerSocketEvents
				.createSocketOpenedEvent());
	}

	@OnClose
	public void close(Session session, CloseReason reason) {
		eventLogger.logEvent(PurifinityServerSocketEvents
				.createSocketCloseEvent());
	}

	@OnMessage
	public void recieveMessage(Session session, String message) {
		logger.info("Got message: " + message);
	}

	@OnError
	public void handleError(Session session, Throwable throwable) {
		eventLogger.logEvent(PurifinityServerSocketEvents
				.createSocketErrorEvent(throwable));
	}
}
