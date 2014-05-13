package com.puresoltechnologies.purifinity.server.ddl.evaluationservice;

import java.io.IOException;

import com.puresoltechnologies.purifinity.framework.database.migration.AbstractDatabaseMigrator;
import com.puresoltechnologies.purifinity.framework.database.migration.DatabaseMigrationConnector;
import com.puresoltechnologies.purifinity.framework.database.migration.MigrationException;
import com.puresoltechnologies.purifinity.server.databaseconnector.cassandra.utils.CassandraMigrationConnector;

public class EvaluationServiceDatabaseMigrator extends AbstractDatabaseMigrator {

	public static final String PASSWORD_STORE_KEYSPACE_NAME = "password_store";
	public static final String CASSANDRA_HOST = "localhost";
	public static final int CASSANDRA_CQL_PORT = 9042;
	public static final String PASSWORD_TABLE_NAME = "passwords";

	protected EvaluationServiceDatabaseMigrator(
			DatabaseMigrationConnector connector) {
		super(connector);
	}

	public static void main(String[] args) {
		CassandraMigrationConnector connector = new CassandraMigrationConnector(
				CASSANDRA_HOST, CASSANDRA_CQL_PORT);
		try {
			EvaluationServiceDatabaseMigrator migrator = new EvaluationServiceDatabaseMigrator(
					connector);
			EvaluationServiceSchema.createSequence(migrator);
			migrator.migrate();
		} catch (IOException | MigrationException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
