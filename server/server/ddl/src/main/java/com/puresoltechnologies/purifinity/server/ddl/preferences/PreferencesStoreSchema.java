package com.puresoltechnologies.purifinity.server.ddl.preferences;

import static com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraMigration.createKeyspace;
import static com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraMigration.createTable;

import com.puresoltechnologies.commons.misc.Version;
import com.puresoltechnologies.purifinity.server.database.cassandra.PreferencesStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.ReplicationStrategy;
import com.puresoltechnologies.purifinity.server.database.migration.DatabaseMigrator;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;

public class PreferencesStoreSchema {

	private static final String PREFERENCES_TABLE = "preferences";

	private static final Version v100 = new Version(1, 0, 0);

	public static void createSequence(DatabaseMigrator migrator)
			throws MigrationException {
		checkAndCreateKeyspaces(migrator);
		checkAndCreateAnalysisTables(migrator);
	}

	private static void checkAndCreateKeyspaces(DatabaseMigrator migrator)
			throws MigrationException {
		migrator.registerMigrationStep(createKeyspace(
				PreferencesStoreKeyspace.NAME, v100, "Rick-Rainer Ludwig",
				"Keyspace for preferences",
				ReplicationStrategy.SIMPLE_STRATEGY, 1));
	}

	private static void checkAndCreateAnalysisTables(DatabaseMigrator migrator)
			throws MigrationException {
		migrator.registerMigrationStep(createTable(
				PreferencesStoreKeyspace.NAME,
				v100,
				"Rick-Rainer Ludwig",
				"Keeps states about the running analysis processes.",
				"CREATE TABLE "
						+ PREFERENCES_TABLE
						+ " (changed timestamp, changed_by text, group text, name text, value text, "
						+ "PRIMARY KEY(group, name));"));
	}

}
