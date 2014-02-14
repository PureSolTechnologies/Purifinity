package com.puresoltechnologies.purifinity.framework.store.db;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore("Check this test!")
public class CassandraConnectionIT {

	@Before
	public void checkForDisconnected() throws CassandraConnectionException {
		if (CassandraConnection.isConnected()) {
			CassandraConnection.disconnect();
		}
	}

	@Test
	public void testNormalConnectDisconnect()
			throws CassandraConnectionException {
		CassandraConnection.connect();
		CassandraConnection.disconnect();
	}

	@Test(expected = CassandraConnectionException.class)
	public void testDoubleConnect() throws CassandraConnectionException {
		CassandraConnection.connect();
		CassandraConnection.connect();
	}

	@Test(expected = CassandraConnectionException.class)
	public void testDoubleDisconnect() throws CassandraConnectionException {
		CassandraConnection.connect();
		CassandraConnection.disconnect();
		CassandraConnection.disconnect();
	}

}
