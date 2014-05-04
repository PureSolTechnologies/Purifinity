package com.puresoltechnologies.purifinity.server.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.wildfly.test.AbstractGlobalClientTest;

public abstract class AbstractFullServerClientTest extends
		AbstractGlobalClientTest {

	private static Cluster cluster;
	private static Session session;

	@BeforeClass
	public static void connectCassandra() {
		// cluster =
		// Cluster.builder().addContactPoint("localhost").withPort(9042)
		// .build();
		// session = cluster.connect("password_store");
		// cleanupPasswordStoreDatabase();
	}

	@AfterClass
	public static void disconnectCassandra() {
		// session.close();
		// cluster.close();
	}

	public static final void cleanupPasswordStoreDatabase() {
		// session.execute("TRUNCATE passwords;");
	}

}
