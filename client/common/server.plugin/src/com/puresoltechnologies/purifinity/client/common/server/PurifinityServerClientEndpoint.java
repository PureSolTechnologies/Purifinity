package com.puresoltechnologies.purifinity.client.common.server;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientEndpoint
public class PurifinityServerClientEndpoint {

	private static final Logger logger = LoggerFactory
			.getLogger(PurifinityServerClientEndpoint.class);

	@OnOpen
	public void open(Session session, EndpointConfig endpointConfig) {
		logger.info("Connection Purifinity server is opening...");
	}

	@OnClose
	public void close(Session session, CloseReason closeReason) {
		logger.info("Connection Purifinity server is closing...");
	}

	@OnMessage
	public void handleMessage(Session session, String message) {
		logger.info("Purifinity server sent message: '" + message + "'");
	}

	@OnError
	public void handleError(Session session, Throwable throwable) {
		logger.error("Purifinity server sent error.", throwable);
	}

}
