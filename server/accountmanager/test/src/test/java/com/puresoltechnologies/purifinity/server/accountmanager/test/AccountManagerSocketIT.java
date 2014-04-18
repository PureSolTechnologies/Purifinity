package com.puresoltechnologies.purifinity.server.accountmanager.test;

import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.junit.Test;

public class AccountManagerSocketIT extends AbstractAccountManagerClientTest {

	@ClientEndpoint
	public static class AccountManagerClientEndpoint {

	}

	@Test
	public void test() throws Exception {

		WebSocketContainer webSocketContainer = ContainerProvider
				.getWebSocketContainer();
		Session session = webSocketContainer.connectToServer(
				new AccountManagerClientEndpoint(), new URI(
						"ws://localhost:8080/accountmanager.socket/socket"));
		session.getBasicRemote().sendText("Hallo, Socket!");
	}
}
