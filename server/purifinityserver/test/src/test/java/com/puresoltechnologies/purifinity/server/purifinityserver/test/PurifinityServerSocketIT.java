package com.puresoltechnologies.purifinity.server.purifinityserver.test;

import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.ContainerProvider;
import javax.websocket.OnMessage;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.junit.Test;

@ClientEndpoint
public class PurifinityServerSocketIT extends
		AbstractPurifinityServerClientTest {

	private final static WebSocketContainer webSocketContainer = ContainerProvider
			.getWebSocketContainer();

	@Test
	public void test() throws Exception {
		Session session = webSocketContainer.connectToServer(this, new URI(
				"ws://localhost:8080/purifinityserver/socket"));
		try {
			Basic basic = session.getBasicRemote();
			basic.sendText("getStatus");
		} finally {
			session.close(new CloseReason(CloseCodes.GOING_AWAY,
					"We are done..."));
		}
		Thread.sleep(5000);
	}

	@OnMessage
	public void onMessage(Session session, String message) {
		System.out.println(message);
	}
}
