package com.puresoltechnologies.purifinity.server.passwordstore.ddl;

import java.io.IOException;

import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.CassandraMigrationConnector;
import com.puresoltechnologies.purifinity.framework.database.migration.AbstractDatabaseMigrator;
import com.puresoltechnologies.purifinity.framework.database.migration.DatabaseMigrationConnector;
import com.puresoltechnologies.purifinity.framework.database.migration.MigrationException;
import com.puresoltechnologies.purifinity.framework.database.migration.MigrationStep;

public class PasswordStoreDatabaseMigrator extends AbstractDatabaseMigrator {

	public static final String PASSWORD_STORE_KEYSPACE_NAME = "password_store";
	public static final String CASSANDRA_HOST = "localhost";
	public static final int CASSANDRA_CQL_PORT = 9042;

	protected PasswordStoreDatabaseMigrator(DatabaseMigrationConnector connector) {
		super(connector);
	}

	public static void main(String[] args) {
		CassandraMigrationConnector connector = new CassandraMigrationConnector(
				CASSANDRA_HOST, CASSANDRA_CQL_PORT,
				PASSWORD_STORE_KEYSPACE_NAME);
		try {
			PasswordStoreDatabaseMigrator migrator = new PasswordStoreDatabaseMigrator(
					connector);
			migrator.registerMigrationStep(new MigrationStep() {

				@Override
				public void migrate() throws IOException, MigrationException {
					// TODO Auto-generated method stub

				}
			});
			migrator.migrate();
		} catch (IOException | MigrationException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
