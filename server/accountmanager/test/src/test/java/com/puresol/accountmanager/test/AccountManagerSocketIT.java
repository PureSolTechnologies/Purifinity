package com.puresol.accountmanager.test;

import java.net.URI;

import javax.websocket.ClientEndpoint;

import org.glassfish.tyrus.client.ClientManager;
import org.junit.Test;

public class AccountManagerSocketIT extends AbstractAccountManagerClientTest {

	@ClientEndpoint
	public static class AccountManagerClientEndpoint {

	}

	@Test
	public void test() throws Exception {

		ClientManager client = new ClientManager();
		client.connectToServer(AccountManagerClientEndpoint.class, new URI(
				"ws://localhost:8080/accountmanager-socket/socket"));
	}
}
