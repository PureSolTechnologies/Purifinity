package com.puresoltechnologies.purifinity.framework.store;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.puresoltechnologies.purifinity.framework.store.DbStoreTestHelper;
import com.puresoltechnologies.purifinity.framework.store.db.CassandraConnectionException;

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
