package com.puresoltechnologies.purifinity.server.ddl.analysisservice;

import java.io.IOException;

import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraMigrationConnector;
import com.puresoltechnologies.purifinity.server.database.migration.AbstractDatabaseMigrator;
import com.puresoltechnologies.purifinity.server.database.migration.DatabaseMigrationConnector;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;

public class AnalysisServiceDatabaseMigrator extends AbstractDatabaseMigrator {

	public static final String PASSWORD_STORE_KEYSPACE_NAME = "password_store";
	public static final String CASSANDRA_HOST = "localhost";
	public static final int CASSANDRA_CQL_PORT = 9042;
	public static final String PASSWORD_TABLE_NAME = "passwords";

	protected AnalysisServiceDatabaseMigrator(
			DatabaseMigrationConnector connector) {
		super(connector);
	}

	public static void main(String[] args) {
		CassandraMigrationConnector connector = new CassandraMigrationConnector(
				CASSANDRA_HOST, CASSANDRA_CQL_PORT);
		try {
			AnalysisServiceDatabaseMigrator migrator = new AnalysisServiceDatabaseMigrator(
					connector);
			AnalysisServiceSchema.createSequence(migrator);
			migrator.migrate();
		} catch (IOException | MigrationException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
