package com.puresoltechnologies.purifinity.framework.store.db;

import org.junit.AfterClass;
import org.junit.BeforeClass;

public abstract class AbstractDbStoreTest {

	@BeforeClass
	public static void connect() throws CassandraConnectionException {
		// Cluster cluster = Cluster
		// .builder()
		// .addContactPoint(CassandraConnection.getHost())
		// .withAuthProvider(
		// new PlainTextAuthProvider("cassandra", "cassandra"))
		// .build();
		// Session session = cluster.connect();
		// for (KeyspaceMetadata keyspace :
		// cluster.getMetadata().getKeyspaces()) {
		// if (keyspace.getName().contains("system")) {
		// continue;
		// }
		// session.execute("DROP KEYSPACE " + keyspace.getName() + ";");
		// }
		// session.shutdown();
		// cluster.shutdown();

		DbStoreTestHelper.connectDBs();
	}

	@AfterClass
	public static void disconnect() throws CassandraConnectionException {
		DbStoreTestHelper.disconnectDBs();
	}

}
