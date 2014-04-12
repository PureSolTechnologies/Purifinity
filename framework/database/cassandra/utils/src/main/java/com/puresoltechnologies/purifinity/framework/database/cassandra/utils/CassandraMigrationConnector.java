package com.puresoltechnologies.purifinity.framework.database.cassandra.utils;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.framework.database.migration.DatabaseMigrationConnector;
import com.puresoltechnologies.purifinity.framework.database.migration.MigrationException;

public class CassandraMigrationConnector implements DatabaseMigrationConnector {

	private Cluster cluster = null;
	private Session session = null;

	private final String host;
	private final int port;
	private final String keyspace;

	public CassandraMigrationConnector(String host, int port, String keyspace) {
		super();
		this.host = host;
		this.port = port;
		this.keyspace = keyspace;
	}

	@Override
	public void initialize() throws MigrationException {
		cluster = Cluster.builder().addContactPoint(host).withPort(port)
				.build();
		CassandraMigration.initialize(cluster);
		session = cluster.connect(keyspace);
	}

	@Override
	public void startMigration() {
		// TODO Auto-generated method stub
	}

	@Override
	public void finishMigration() {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		try {
			session.close();
		} finally {
			cluster.close();
		}
	}

}
