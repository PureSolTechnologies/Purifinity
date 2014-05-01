package com.puresoltechnologies.purifinity.framework.store;

import org.junit.Before;
import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.store.db.CassandraConnection;
import com.puresoltechnologies.purifinity.framework.store.db.CassandraConnectionException;

public class CassandraConnectionIT {

	@Before
	public void checkForDisconnected() {
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

	@Test
	public void testDoubleConnect() throws CassandraConnectionException {
		CassandraConnection.connect();
		CassandraConnection.connect();
	}

	@Test
	public void testDoubleDisconnect() throws CassandraConnectionException {
		CassandraConnection.connect();
		CassandraConnection.disconnect();
		CassandraConnection.disconnect();
	}

}
