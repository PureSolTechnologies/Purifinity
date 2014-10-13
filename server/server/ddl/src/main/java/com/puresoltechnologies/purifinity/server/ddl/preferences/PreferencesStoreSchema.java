package com.puresoltechnologies.purifinity.server.ddl.preferences;

import static com.puresoltechnologies.purifinity.server.database.cassandra.migration.CassandraMigration.createKeyspace;
import static com.puresoltechnologies.purifinity.server.database.cassandra.migration.CassandraMigration.createTable;

import com.puresoltechnologies.commons.misc.Version;
import com.puresoltechnologies.purifinity.server.database.cassandra.PreferencesStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.ReplicationStrategy;
import com.puresoltechnologies.purifinity.server.database.migration.UniversalMigrator;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;

public class PreferencesStoreSchema {

	private static final String PREFERENCES_TABLE = "preferences";
	private static final String USER_PREFERENCES_TABLE = "user_preferences";
	private static final String PROJECT_PREFERENCES_TABLE = "project_preferences";
	private static final String SERVICE_ACTIVATION_TABLE = "service_activation";
	private static final String SERVICE_PROJECT_ACTIVATION_TABLE = "service_project_activation";

	private static final Version v100 = new Version(1, 0, 0);

	public static void createSequence(UniversalMigrator migrator)
			throws MigrationException {
		checkAndCreateKeyspaces(migrator);
		checkAndCreateAnalysisTables(migrator);
	}

	private static void checkAndCreateKeyspaces(UniversalMigrator migrator)
			throws MigrationException {
		migrator.registerMigrationStep(createKeyspace(
				PreferencesStoreKeyspace.NAME, v100, "Rick-Rainer Ludwig",
				"Keyspace for preferences",
				ReplicationStrategy.SIMPLE_STRATEGY, 1));
	}

	private static void checkAndCreateAnalysisTables(UniversalMigrator migrator)
			throws MigrationException {
		migrator.registerMigrationStep(createTable(
				PreferencesStoreKeyspace.NAME,
				v100,
				"Rick-Rainer Ludwig",
				"Keeps preferences for the system wide settings.",
				"CREATE TABLE "
						+ PREFERENCES_TABLE
						+ " (changed timestamp, changed_by text, group text, name text, value text, "
						+ "PRIMARY KEY(group, name));"));
		migrator.registerMigrationStep(createTable(
				PreferencesStoreKeyspace.NAME,
				v100,
				"Rick-Rainer Ludwig",
				"Keeps preferences for the user specific settings.",
				"CREATE TABLE "
						+ USER_PREFERENCES_TABLE
						+ " (changed timestamp, changed_by text, user text, group text, name text, value text, "
						+ "PRIMARY KEY(user, group, name));"));
		migrator.registerMigrationStep(createTable(
				PreferencesStoreKeyspace.NAME,
				v100,
				"Rick-Rainer Ludwig",
				"Keeps preferences for the project specific settings.",
				"CREATE TABLE "
						+ PROJECT_PREFERENCES_TABLE
						+ " (changed timestamp, changed_by text, project_uuid uuid, group text, name text, value text, "
						+ "PRIMARY KEY(project_uuid, group, name));"));
		migrator.registerMigrationStep(createTable(
				PreferencesStoreKeyspace.NAME,
				v100,
				"Rick-Rainer Ludwig",
				"Keeps the activation states of services.",
				"CREATE TABLE "
						+ SERVICE_ACTIVATION_TABLE
						+ " (changed timestamp, changed_by text, service_id text, active boolean, "
						+ "PRIMARY KEY(service_id));"));
		migrator.registerMigrationStep(createTable(
				PreferencesStoreKeyspace.NAME,
				v100,
				"Rick-Rainer Ludwig",
				"Keeps the activation states of services.",
				"CREATE TABLE "
						+ SERVICE_PROJECT_ACTIVATION_TABLE
						+ " (changed timestamp, changed_by text, project_uuid uuid, service_id text, active boolean, "
						+ "PRIMARY KEY(project_uuid, service_id));"));
	}

}
