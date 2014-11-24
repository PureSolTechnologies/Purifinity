package com.puresoltechnologies.purifinity.server.ddl;

import static com.puresoltechnologies.purifinity.server.database.cassandra.migration.CassandraMigration.createKeyspace;
import static com.puresoltechnologies.purifinity.server.database.cassandra.migration.CassandraMigration.createTable;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.types.Version;
import com.puresoltechnologies.purifinity.server.database.cassandra.PreferencesStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.migration.CassandraMigratorConnector;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.ReplicationStrategy;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationMetadata;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationSequence;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationStep;

public class PreferencesStoreDatabaseMigrator {

	private static final String PREFERENCES_TABLE = "preferences";
	private static final String USER_PREFERENCES_TABLE = "user_preferences";
	private static final String PROJECT_PREFERENCES_TABLE = "project_preferences";
	private static final String SERVICE_ACTIVATION_TABLE = "service_activation";
	private static final String SERVICE_PROJECT_ACTIVATION_TABLE = "service_project_activation";

	private static final Version v100 = new Version(1, 0, 0);

	private final CassandraMigratorConnector connector;

	public PreferencesStoreDatabaseMigrator(CassandraMigratorConnector connector)
			throws MigrationException {
		this.connector = connector;
	}

	public void drop() {
		Cluster cluster = connector.getCluster();
		Session session = cluster.connect();
		try {
			session.execute("DROP KEYSPACE " + PreferencesStoreKeyspace.NAME);
		} finally {
			session.close();
		}
	}

	public MigrationSequence getSequence() throws MigrationException {
		MigrationSequence sequence = new MigrationSequence(
				new MigrationMetadata(v100, "Rick-Rainer Ludwig",
						"Preferences Store", "", "Version " + v100
								+ " sequence."));
		sequence.registerMigrationSteps(checkAndCreateKeyspaces());
		sequence.registerMigrationSteps(checkAndCreateAnalysisTables());
		return sequence;
	}

	private List<MigrationStep> checkAndCreateKeyspaces()
			throws MigrationException {
		List<MigrationStep> steps = new ArrayList<>();
		steps.add(createKeyspace(connector, PreferencesStoreKeyspace.NAME,
				v100, "Rick-Rainer Ludwig", "Keyspace for preferences",
				ReplicationStrategy.SIMPLE_STRATEGY, 1));
		return steps;
	}

	private List<MigrationStep> checkAndCreateAnalysisTables()
			throws MigrationException {
		List<MigrationStep> steps = new ArrayList<>();
		steps.add(createTable(
				connector,
				PreferencesStoreKeyspace.NAME,
				v100,
				"Rick-Rainer Ludwig",
				"Keeps preferences for the system wide settings.",
				"CREATE TABLE "
						+ PREFERENCES_TABLE
						+ " (changed timestamp, changed_by text, group text, name text, value text, "
						+ "PRIMARY KEY(group, name));"));
		steps.add(createTable(
				connector,
				PreferencesStoreKeyspace.NAME,
				v100,
				"Rick-Rainer Ludwig",
				"Keeps preferences for the user specific settings.",
				"CREATE TABLE "
						+ USER_PREFERENCES_TABLE
						+ " (changed timestamp, changed_by text, user text, group text, name text, value text, "
						+ "PRIMARY KEY(user, group, name));"));
		steps.add(createTable(
				connector,
				PreferencesStoreKeyspace.NAME,
				v100,
				"Rick-Rainer Ludwig",
				"Keeps preferences for the project specific settings.",
				"CREATE TABLE "
						+ PROJECT_PREFERENCES_TABLE
						+ " (changed timestamp, changed_by text, project_uuid uuid, group text, name text, value text, "
						+ "PRIMARY KEY(project_uuid, group, name));"));
		steps.add(createTable(
				connector,
				PreferencesStoreKeyspace.NAME,
				v100,
				"Rick-Rainer Ludwig",
				"Keeps the activation states of services.",
				"CREATE TABLE "
						+ SERVICE_ACTIVATION_TABLE
						+ " (changed timestamp, changed_by text, service_id text, active boolean, "
						+ "PRIMARY KEY(service_id));"));
		steps.add(createTable(
				connector,
				PreferencesStoreKeyspace.NAME,
				v100,
				"Rick-Rainer Ludwig",
				"Keeps the activation states of services.",
				"CREATE TABLE "
						+ SERVICE_PROJECT_ACTIVATION_TABLE
						+ " (changed timestamp, changed_by text, project_uuid uuid, service_id text, active boolean, "
						+ "PRIMARY KEY(project_uuid, service_id));"));
		return steps;
	}

}
