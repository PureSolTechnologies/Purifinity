package com.puresoltechnologies.purifinity.framework.store.db;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.PlainTextAuthProvider;
import com.datastax.driver.core.Session;

public class AbstractDbStoreTest {

	@BeforeClass
	public static void connect() throws CassandraConnectionException,
			TitanConnectionException {

		Cluster cluster = Cluster
				.builder()
				.addContactPoint(CassandraConnection.getHost())
				.withAuthProvider(
						new PlainTextAuthProvider("cassandra", "cassandra"))
				.build();
		Session session = cluster.connect();
		for (KeyspaceMetadata keyspace : cluster.getMetadata().getKeyspaces()) {
			if (keyspace.getName().contains("system")) {
				continue;
			}
			session.execute("DROP KEYSPACE " + keyspace.getName() + ";");
		}
		session.shutdown();
		cluster.shutdown();

		DbStoreTestHelper.connectDBs();
	}

	@AfterClass
	public static void disconnect() throws CassandraConnectionException,
			TitanConnectionException {
		DbStoreTestHelper.disconnectDBs();
	}

}
