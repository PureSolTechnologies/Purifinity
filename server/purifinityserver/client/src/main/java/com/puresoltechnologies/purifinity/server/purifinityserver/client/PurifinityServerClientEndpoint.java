package com.puresoltechnologies.purifinity.server.purifinityserver.client;

import java.util.Map.Entry;

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
		logger.info("Socket was opened.");
		for (Entry<String, Object> entry : endpointConfig.getUserProperties()
				.entrySet()) {
			System.out.println(entry.getKey() + " -> " + entry.getValue());
		}
	}

	@OnClose
	public void close(Session session, CloseReason closeReason) {
		logger.info("Socket is about to close. Reason: "
				+ closeReason.getReasonPhrase());
	}

	@OnMessage
	public void retrieveMessage(Session session, String message) {
		logger.info("Got message: " + message);
	}

	@OnError
	public void error(Session session, Throwable throwable) {
		logger.error("Got error.", throwable);
	}

}
