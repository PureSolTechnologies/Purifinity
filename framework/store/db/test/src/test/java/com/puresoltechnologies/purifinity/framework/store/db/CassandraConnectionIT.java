package com.puresoltechnologies.purifinity.framework.store.db;

import org.junit.Before;
import org.junit.Test;

import com.puresoltechnologies.purifinity.commons.test.AbstractCassandraTest;

public class CassandraConnectionIT extends AbstractCassandraTest {

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
