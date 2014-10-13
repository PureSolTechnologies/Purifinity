package com.puresoltechnologies.purifinity.server.passwordstore.ddl;

import java.io.IOException;

import com.puresoltechnologies.commons.misc.Version;
import com.puresoltechnologies.purifinity.server.database.cassandra.migration.CassandraMigration;
import com.puresoltechnologies.purifinity.server.database.cassandra.migration.CassandraMigratorConnector;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.ReplicationStrategy;
import com.puresoltechnologies.purifinity.server.database.migration.AbstractUniversalMigrator;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;
import com.puresoltechnologies.purifinity.server.database.migration.spi.UniversalMigratorConnector;

public class SystemMonitorDatabaseMigrator extends AbstractUniversalMigrator {

	public static final String SYSTEM_MONITOR_KEYSPACE_NAME = "system_monitor";
	public static final String CASSANDRA_HOST = "localhost";
	public static final int CASSANDRA_CQL_PORT = 9042;
	public static final String PASSWORD_TABLE_NAME = "passwords";

	private static final String EVENTS_TABLE_NAME = "events";
	private static final String METRICS_TABLE_NAME = "metrics";

	protected SystemMonitorDatabaseMigrator(UniversalMigratorConnector connector) {
		super(connector);
	}

	private static void migrateVersion100(SystemMonitorDatabaseMigrator migrator)
			throws MigrationException {
		Version v100 = new Version(1, 0, 0);
		migrator.registerMigrationStep(CassandraMigration.createKeyspace(
				SYSTEM_MONITOR_KEYSPACE_NAME, v100, "Rick-Rainer Ludwig",
				"Keeps the system status information and event logs.",
				ReplicationStrategy.SIMPLE_STRATEGY, 3));

		String description = "This is the table for the event log.";
		migrator.registerMigrationStep(CassandraMigration
				.createTable(
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
		migrator.registerMigrationStep(CassandraMigration.createTable(
				SYSTEM_MONITOR_KEYSPACE_NAME, v100, "Rick-Rainer Ludwig",
				description, "CREATE TABLE "
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

	}

	public static void main(String[] args) {
		CassandraMigratorConnector connector = new CassandraMigratorConnector(
				CASSANDRA_HOST, CASSANDRA_CQL_PORT);
		try {
			SystemMonitorDatabaseMigrator migrator = new SystemMonitorDatabaseMigrator(
					connector);
			migrateVersion100(migrator);
			migrator.migrate();
		} catch (IOException | MigrationException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
