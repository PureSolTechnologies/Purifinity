package com.puresoltechnologies.purifinity.framework.database.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.puresoltechnologies.purifinity.database.titan.utils.TitanConnection;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.CassandraConnection;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.CassandraConnectionException;

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

		CassandraConnection.connect();
		TitanConnection.connect();
	}

	@AfterClass
	public static void disconnect() throws CassandraConnectionException {
		TitanConnection.disconnect();
		CassandraConnection.disconnect();
	}

}