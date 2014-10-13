package com.puresoltechnologies.purifinity.server.database.cassandra.migration;

import java.io.IOException;

import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationStep;
import com.puresoltechnologies.purifinity.server.database.migration.spi.UniversalMigratorConnector;
import com.puresoltechnologies.purifinity.server.database.migration.spi.UniversalMigratorTracker;

public abstract class AbstractCassandraMigrationStep implements MigrationStep {

	private final String keyspace;

	public AbstractCassandraMigrationStep(String keyspace) {
		super();
		this.keyspace = keyspace;
	}

	@Override
	public void migrate(UniversalMigratorTracker tracker,
			UniversalMigratorConnector connector) throws IOException,
			MigrationException {
		CassandraMigratorConnector cassandraMigrationConnector = (CassandraMigratorConnector) connector;
		CassandraMigration.migrate(tracker,
				cassandraMigrationConnector.getCluster(), keyspace,
				getMetadata());
	}

}
