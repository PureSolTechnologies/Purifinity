package com.puresoltechnologies.purifinity.server.database.cassandra.migration;

import com.datastax.driver.core.Cluster;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;
import com.puresoltechnologies.purifinity.server.database.migration.spi.UniversalMigratorConnector;

public class CassandraMigratorConnector implements UniversalMigratorConnector {

	private final boolean closeCluster;
	private final Cluster cluster;

	public CassandraMigratorConnector(String host, int port) {
		super();
		cluster = Cluster.builder().addContactPoint(host).withPort(port)
				.build();
		closeCluster = true;
	}

	public CassandraMigratorConnector(Cluster cluster) {
		super();
		this.cluster = cluster;
		closeCluster = false;
	}

	@Override
	public void initialize() throws MigrationException {
		// intentionally left empty
	}

	@Override
	public void startMigration() {
		// intentionally left empty
	}

	@Override
	public void finishMigration() {
		// intentionally left empty
	}

	@Override
	public void close() {
		if (closeCluster) {
			cluster.close();
		}
	}

	public Cluster getCluster() {
		return cluster;
	}

}
