package com.puresoltechnologies.purifinity.server.database.cassandra.migration;

import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationStep;
import com.puresoltechnologies.purifinity.server.database.migration.spi.UniversalMigratorConnector;

public abstract class AbstractCassandraMigrationStep implements MigrationStep {

    private final UniversalMigratorConnector connector;
    private final String keyspace;

    public AbstractCassandraMigrationStep(UniversalMigratorConnector connector,
	    String keyspace) {
	super();
	this.connector = connector;
	this.keyspace = keyspace;
    }

    @Override
    public void migrate() throws MigrationException {
	CassandraMigratorConnector cassandraMigrationConnector = (CassandraMigratorConnector) connector;
	CassandraMigration.migrate(cassandraMigrationConnector.getCluster(),
		keyspace, getMetadata());
    }

}
