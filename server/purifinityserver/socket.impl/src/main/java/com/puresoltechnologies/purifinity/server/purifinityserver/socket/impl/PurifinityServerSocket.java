package com.puresoltechnologies.purifinity.server.purifinityserver.socket.impl;

import java.io.IOException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;

import com.puresoltechnologies.purifinity.server.purifinityserver.core.api.PurifinityServer;
import com.puresoltechnologies.purifinity.server.purifinityserver.socket.api.PurifinityServerStatusEncoder;
import com.puresoltechnologies.purifinity.server.purifinityserver.socket.api.PurifinityServerStatusRequest;
import com.puresoltechnologies.purifinity.server.purifinityserver.socket.api.PurifinityServerStatusRequestDecoder;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventLogger;

@ServerEndpoint(value = "/socket", //
encoders = { PurifinityServerStatusEncoder.class }, //
decoders = { PurifinityServerStatusRequestDecoder.class })
@Stateless
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
		String reasonPhrase = reason != null ? reason.getReasonPhrase()
				: "<no reason provided>";
		eventLogger.logEvent(PurifinityServerSocketEvents
				.createSocketCloseEvent(reasonPhrase));
	}

	@OnMessage
	public Object recieveMessage(Session session,
			PurifinityServerStatusRequest request) throws IOException,
			EncodeException {
		logger.info("Got request for status.");
		return purifinityServer.getStatus();
	}

	@OnError
	public void handleError(Session session, Throwable throwable) {
		eventLogger.logEvent(PurifinityServerSocketEvents
				.createSocketErrorEvent(throwable));
	}
}
