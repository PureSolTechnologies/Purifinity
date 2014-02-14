package com.puresoltechnologies.purifinity.framework.store.db;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore("Check this test!")
public class TitanConnectionIT {

	@Before
	public void checkForDisconnected() throws TitanConnectionException {
		if (TitanConnection.isConnected()) {
			TitanConnection.disconnect();
		}
	}

	@Test
	public void testNormalConnectDisconnect() throws TitanConnectionException {
		TitanConnection.connect();
		TitanConnection.disconnect();
	}

	@Test(expected = TitanConnectionException.class)
	public void testDoubleConnect() throws TitanConnectionException {
		TitanConnection.connect();
		TitanConnection.connect();
	}

	@Test(expected = TitanConnectionException.class)
	public void testDoubleDisconnect() throws TitanConnectionException {
		TitanConnection.connect();
		TitanConnection.disconnect();
		TitanConnection.disconnect();
	}

}
