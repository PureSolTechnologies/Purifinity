package com.puresoltechnologies.purifinity.server.accountmanager.socket;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ServerEndpoint("/echo")
public class ReverseEchoWebSocketServerEndpoint {

	private final Logger logger = LoggerFactory.getLogger(this.getClass()
			.getName());

	@OnOpen
	public void onConnectionOpen(Session session) {
		logger.info("Connection opened ... " + session.getId());
	}

	@OnMessage
	public String onMessage(String message) {
		if (StringUtils.isBlank(message)) {
			return "Please send message";
		}
		return StringUtils.reverse(message);
	}

	@OnClose
	public void onConnectionClose(Session session) {
		logger.info("Connection close .... " + session.getId());
	}
}