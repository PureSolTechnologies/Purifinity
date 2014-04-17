package com.puresoltechnologies.purifinity.framework.store.db;

import org.junit.Before;
import org.junit.Test;

public class TitanConnectionIT {

	@Before
	public void checkForDisconnected() {
		if (TitanConnection.isConnected()) {
			TitanConnection.disconnect();
		}
	}

	@Test
	public void testNormalConnectDisconnect() {
		TitanConnection.connect();
		TitanConnection.disconnect();
	}

	@Test
	public void testDoubleConnect() {
		TitanConnection.connect();
		TitanConnection.connect();
	}

	/**
	 * 
	 */
	@Test
	public void testDoubleDisconnect() {
		TitanConnection.connect();
		TitanConnection.disconnect();
		TitanConnection.disconnect();
	}

}
