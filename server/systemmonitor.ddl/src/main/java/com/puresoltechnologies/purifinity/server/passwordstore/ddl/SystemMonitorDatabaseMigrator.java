package com.puresoltechnologies.purifinity.server.passwordstore.ddl;

import com.puresoltechnologies.commons.versioning.Version;
import com.puresoltechnologies.purifinity.server.database.cassandra.migration.CassandraMigration;
import com.puresoltechnologies.purifinity.server.database.cassandra.migration.CassandraMigratorConnector;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.ReplicationStrategy;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationMetadata;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationSequence;
import com.puresoltechnologies.purifinity.server.database.migration.Migrator;
import com.puresoltechnologies.purifinity.server.database.migration.spi.UniversalMigratorConnector;

public class SystemMonitorDatabaseMigrator {

	public static final String SYSTEM_MONITOR_KEYSPACE_NAME = "system_monitor";
	public static final String CASSANDRA_HOST = "localhost";
	public static final int CASSANDRA_CQL_PORT = 9042;
	public static final String PASSWORD_TABLE_NAME = "passwords";

	private static final String EVENTS_TABLE_NAME = "events";
	private static final String METRICS_TABLE_NAME = "metrics";

	private final UniversalMigratorConnector connector;

	protected SystemMonitorDatabaseMigrator(UniversalMigratorConnector connector) {
		this.connector = connector;
	}

	private MigrationSequence migrateVersion100() throws MigrationException {
		Version v100 = new Version(1, 0, 0);
		MigrationSequence sequence = new MigrationSequence(
				new MigrationMetadata(v100, "Rick-Rainer Ludwig",
						"System Monitor", "", "Version " + v100 + " sequence."));
		sequence.registerMigrationStep(CassandraMigration.createKeyspace(
				connector, SYSTEM_MONITOR_KEYSPACE_NAME, v100,
				"Rick-Rainer Ludwig",
				"Keeps the system status information and event logs.",
				ReplicationStrategy.SIMPLE_STRATEGY, 3));

		String description = "This is the table for the event log.";
		sequence.registerMigrationStep(CassandraMigration
				.createTable(
						connector,
						SYSTEM_MONITOR_KEYSPACE_NAME,
						v100,
						"Rick-Rainer Ludwig",
						description,
						"CREATE TABLE "
								+ EVENTS_TABLE_NAME //
								+ " (time timestamp, " //
								+ "component ascii," //
								+ "event_id bigint," //
								+ "server ascii," //
								+ "type ascii, " //
								+ "severity ascii, "
								+ "message text, "//
								+ "user varchar, "
								+ "user_id bigint," //
								+ "client ascii, " //
								+ "exception_message ascii, "
								+ "exception_stacktrace ascii, "//
								+ "PRIMARY KEY (server, time, severity, type, component, event_id, message))"
								+ "WITH comment='" + description + "';"));

		description = "This is the table for metrics and KPIs.";
		sequence.registerMigrationStep(CassandraMigration.createTable(
				connector, SYSTEM_MONITOR_KEYSPACE_NAME, v100,
				"Rick-Rainer Ludwig", description, "CREATE TABLE "
						+ METRICS_TABLE_NAME //
						+ " (time timestamp, " //
						+ "server ascii," //
						+ "name varchar," //
						+ "unit varchar, " //
						+ "type ascii, "
						+ "description text, "//
						+ "decimal_value decimal, "//
						+ "integer_value varint, "//
						+ "level_of_measurement ascii, "
						+ "PRIMARY KEY (server, time, name))"
						+ "WITH comment='" + description + "';"));
		return sequence;
	}

	public static void main(String[] args) throws Exception {
		try (CassandraMigratorConnector connector = new CassandraMigratorConnector(
				CASSANDRA_HOST, CASSANDRA_CQL_PORT)) {
			SystemMonitorDatabaseMigrator systemMonitorSchema = new SystemMonitorDatabaseMigrator(
					connector);
			try (Migrator migrator = new Migrator()) {
				migrator.runMigration(systemMonitorSchema.migrateVersion100());
			}
		}
	}
}
