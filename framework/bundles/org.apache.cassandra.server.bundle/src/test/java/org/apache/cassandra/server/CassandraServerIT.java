package org.apache.cassandra.server;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CassandraServerIT {

	@Test
	public void test() throws Exception {
		System.setProperty("eclipse.home.location",
				"file:/home/ludwig/eclipse/Kepler-RCP-SR1");
		CassandraServer.start();
		assertTrue(CassandraServer.waitForStartup(15000));
		CassandraServer.stop();
	}
}
