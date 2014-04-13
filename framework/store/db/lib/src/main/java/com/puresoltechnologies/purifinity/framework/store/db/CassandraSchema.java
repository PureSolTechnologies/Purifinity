package com.puresoltechnologies.purifinity.framework.store.db;

import java.io.IOException;

import com.datastax.driver.core.Cluster;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.CassandraMigrationConnector;
import com.puresoltechnologies.purifinity.framework.database.migration.AbstractDatabaseMigrator;
import com.puresoltechnologies.purifinity.framework.database.migration.DatabaseMigrationConnector;
import com.puresoltechnologies.purifinity.framework.database.migration.MigrationException;
import com.puresoltechnologies.purifinity.framework.store.db.internal.CassandraSchemaV100;

public class CassandraSchema extends AbstractDatabaseMigrator {

	public CassandraSchema(DatabaseMigrationConnector connector) {
		super(connector);
	}

	public static void migrate(Cluster cluster) throws MigrationException {
		try {
			CassandraMigrationConnector connector = new CassandraMigrationConnector(
					cluster);
			CassandraSchema migrator = new CassandraSchema(connector);

			CassandraSchemaV100.createSequence(migrator);
			migrator.migrate();
		} catch (IOException | MigrationException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
