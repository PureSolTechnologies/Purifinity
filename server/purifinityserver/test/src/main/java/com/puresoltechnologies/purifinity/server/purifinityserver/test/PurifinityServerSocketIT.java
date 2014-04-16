package com.puresoltechnologies.purifinity.server.purifinityserver.test;

import java.net.URI;

import javax.websocket.CloseReason;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.ContainerProvider;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.junit.Test;

import com.puresoltechnologies.purifinity.server.purifinityserver.client.PurifinityServerClientEndpoint;

public class PurifinityServerSocketIT extends
		AbstractPurifinityServerClientTest {

	@Test
	public void test() throws Exception {
		WebSocketContainer webSocketContainer = ContainerProvider
				.getWebSocketContainer();
		Session session = webSocketContainer.connectToServer(
				new PurifinityServerClientEndpoint(), new URI(
						"ws://localhost:8080/purifinityserver.socket/socket"));
		try {
			System.err.println("Before");
			Basic basic = session.getBasicRemote();
			basic.sendText("Hallo, Socket!");
			System.err.println("After");
		} finally {
			session.close(new CloseReason(CloseCodes.GOING_AWAY,
					"We are done..."));
		}
		Thread.sleep(5000);
	}
}
