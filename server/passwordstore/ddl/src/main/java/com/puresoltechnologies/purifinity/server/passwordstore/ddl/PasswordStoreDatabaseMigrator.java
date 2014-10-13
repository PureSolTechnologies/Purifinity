package com.puresoltechnologies.purifinity.server.passwordstore.ddl;

import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.misc.Version;
import com.puresoltechnologies.purifinity.server.database.cassandra.migration.CassandraMigration;
import com.puresoltechnologies.purifinity.server.database.cassandra.migration.CassandraMigratorConnector;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.ReplicationStrategy;
import com.puresoltechnologies.purifinity.server.database.migration.AbstractUniversalMigrator;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationStep;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationStepMetadata;
import com.puresoltechnologies.purifinity.server.database.migration.spi.UniversalMigratorConnector;
import com.puresoltechnologies.purifinity.server.database.migration.spi.UniversalMigratorTracker;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordData;
import com.puresoltechnologies.purifinity.server.passwordstore.utils.encrypt.Encrypter1;

public class PasswordStoreDatabaseMigrator extends AbstractUniversalMigrator {

	private static final Logger logger = LoggerFactory
			.getLogger(PasswordStoreDatabaseMigrator.class);

	public static final String PASSWORD_STORE_KEYSPACE_NAME = "password_store";
	public static final String CASSANDRA_HOST = "localhost";
	public static final int CASSANDRA_CQL_PORT = 9042;
	public static final String PASSWORD_TABLE_NAME = "passwords";

	protected PasswordStoreDatabaseMigrator(UniversalMigratorConnector connector) {
		super(connector);
	}

	private static void migrateVersion100(PasswordStoreDatabaseMigrator migrator)
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
		migrator.registerMigrationStep(new MigrationStep() {

			@Override
			public void migrate(UniversalMigratorTracker tracker,
					UniversalMigratorConnector connector) throws IOException,
					MigrationException {
				logger.info("Add first administrator account.");
				CassandraMigratorConnector con = (CassandraMigratorConnector) connector;
				Session session = con.getCluster().connect(
						PASSWORD_STORE_KEYSPACE_NAME);
				try {
					PreparedStatement preparedStatement = session
							.prepare("INSERT INTO "
									+ PASSWORD_TABLE_NAME//
									+ " (created, " //
									+ "last_modified, " //
									+ "email," //
									+ "password, " //
									+ "state, "
									+ "activation_key) VALUES (?,?,?,?,?,?) ");
					Date now = new Date();
					PasswordData passwordData = new PasswordData(1, Encrypter1
							.encrypt("password"));
					BoundStatement boundStatement = preparedStatement.bind(now,
							now, "ludwig@puresol-technologies.com",
							passwordData.toString(), "ACTIVE", "");
					session.execute(boundStatement);
				} finally {
					session.close();
				}
			}

			@Override
			public MigrationStepMetadata getMetadata() {
				return new MigrationStepMetadata(v100, "Rick-Rainer Ludwig",
						PASSWORD_STORE_KEYSPACE_NAME, "create admin account.",
						"Creates the first administrators account.");
			}
		});
	}

	public static void main(String[] args) {
		CassandraMigratorConnector connector = new CassandraMigratorConnector(
				CASSANDRA_HOST, CASSANDRA_CQL_PORT);
		try {
			PasswordStoreDatabaseMigrator migrator = new PasswordStoreDatabaseMigrator(
					connector);
			migrateVersion100(migrator);
			migrator.migrate();
		} catch (IOException | MigrationException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
