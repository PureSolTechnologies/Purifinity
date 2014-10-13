package com.puresoltechnologies.purifinity.server.database.cassandra.utils;

import java.io.IOException;

import com.puresoltechnologies.purifinity.server.database.migration.DatabaseMigrationConnector;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationStep;

public abstract class AbstractCassandraMigrationStep implements MigrationStep {

    private final String keyspace;

    public AbstractCassandraMigrationStep(String keyspace) {
	super();
	this.keyspace = keyspace;
    }

    @Override
    public void migrate(DatabaseMigrationConnector connector)
	    throws IOException, MigrationException {
	CassandraMigrationConnector cassandraMigrationConnector = (CassandraMigrationConnector) connector;
	CassandraMigration.migrate(cassandraMigrationConnector.getCluster(),
		keyspace, getMetadata());
    }

}
