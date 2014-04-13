package com.puresoltechnologies.purifinity.server.evaluationservice.ddl;

import java.io.IOException;

import com.puresoltechnologies.purifinity.framework.commons.utils.Version;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.CassandraMigration;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.CassandraMigrationConnector;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.ReplicationStrategy;
import com.puresoltechnologies.purifinity.framework.database.migration.AbstractDatabaseMigrator;
import com.puresoltechnologies.purifinity.framework.database.migration.DatabaseMigrationConnector;
import com.puresoltechnologies.purifinity.framework.database.migration.MigrationException;

public class EvaluationServiceDatabaseMigrator extends AbstractDatabaseMigrator {

	public static final String PASSWORD_STORE_KEYSPACE_NAME = "password_store";
	public static final String CASSANDRA_HOST = "localhost";
	public static final int CASSANDRA_CQL_PORT = 9042;
	public static final String PASSWORD_TABLE_NAME = "passwords";

	protected EvaluationServiceDatabaseMigrator(DatabaseMigrationConnector connector) {
		super(connector);
	}

	private static void migrateVersion100(EvaluationServiceDatabaseMigrator migrator)
			throws MigrationException {
		Version v100 = new Version(1, 0, 0);
		migrator.registerMigrationStep(CassandraMigration.createKeyspace(
				PASSWORD_STORE_KEYSPACE_NAME, v100, "Rick-Rainer Ludwig",
				"Keeps the user passwords.",
				ReplicationStrategy.SIMPLE_STRATEGY, 3));

		String description = "This table contains the authentication data and the state of the account.";
		migrator.registerMigrationStep(CassandraMigration.createTable(
				PASSWORD_STORE_KEYSPACE_NAME, v100, "Rick-Rainer Ludwig",
				description, "CREATE TABLE "
						+ PASSWORD_TABLE_NAME//
						+ " (created timestamp, " //
						+ "last_modified timestamp, " //
						+ "email varchar," //
						+ "password ascii, " //
						+ "state ascii, "
						+ "activation_key ascii, "//
						+ "PRIMARY KEY (email))" + "WITH comment='"
						+ description + "';"));
		migrator.registerMigrationStep(CassandraMigration.createIndex(
				PASSWORD_STORE_KEYSPACE_NAME, v100, "Rick-Rainer Ludwig",
				"Secondary index on state.", PASSWORD_TABLE_NAME, "state"));
	}

	public static void main(String[] args) {
		CassandraMigrationConnector connector = new CassandraMigrationConnector(
				CASSANDRA_HOST, CASSANDRA_CQL_PORT);
		try {
			EvaluationServiceDatabaseMigrator migrator = new EvaluationServiceDatabaseMigrator(
					connector);
			migrateVersion100(migrator);
			migrator.migrate();
		} catch (IOException | MigrationException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
