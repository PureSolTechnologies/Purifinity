package com.puresoltechnologies.purifinity.client.common.server;


public class PurifinityServerConnector {

	private static PurifinityServerConnector instance = null;

	static void connect() {
		if (isConnected()) {
			throw new IllegalStateException("Server is already connceted.");
		}
		instance = new PurifinityServerConnector();
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
}
