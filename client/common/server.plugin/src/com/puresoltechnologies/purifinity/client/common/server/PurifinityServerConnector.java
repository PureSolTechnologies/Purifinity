package com.puresoltechnologies.purifinity.client.common.server;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;

public class PurifinityServerConnector {

	private static final WebSocketContainer webSocketContainer = ContainerProvider
			.getWebSocketContainer();
	private static PurifinityServerConnector instance = null;

	static void connect() throws DeploymentException, IOException,
			URISyntaxException {
		if (isConnected()) {
			throw new IllegalStateException("Server is already connceted.");
		}
		instance = new PurifinityServerConnector();
		instance.connectToServer();
	}

	static void disconnect() {
		if (!isConnected()) {
			throw new IllegalStateException("Server is not connected.");
		}
		instance = null;
	}

	public static boolean isConnected() {
		return instance != null;
	}

	public static PurifinityServerConnector getInstance() {
		return instance;
	}

	private PurifinityServerConnector() {
	}

	private void connectToServer() throws DeploymentException, IOException,
			URISyntaxException {
		webSocketContainer.connectToServer(
				PurifinityServerClientEndpoint.class, new URI(
						"ws://localhost:8080/purifinityserver.socket/socket"));
	}
}
