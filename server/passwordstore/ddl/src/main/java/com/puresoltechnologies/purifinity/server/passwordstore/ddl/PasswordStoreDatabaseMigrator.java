package com.puresoltechnologies.purifinity.server.passwordstore.ddl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.types.Version;
import com.puresoltechnologies.purifinity.server.database.cassandra.migration.CassandraMigration;
import com.puresoltechnologies.purifinity.server.database.cassandra.migration.CassandraMigratorConnector;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.ReplicationStrategy;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationMetadata;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationSequence;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationStep;
import com.puresoltechnologies.purifinity.server.database.migration.Migrator;
import com.puresoltechnologies.purifinity.server.database.migration.spi.UniversalMigratorConnector;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordData;
import com.puresoltechnologies.purifinity.server.passwordstore.utils.encrypt.Encrypter1;

public class PasswordStoreDatabaseMigrator {

	private static final Logger logger = LoggerFactory
			.getLogger(PasswordStoreDatabaseMigrator.class);

	public static final String PASSWORD_STORE_KEYSPACE_NAME = "password_store";
	public static final String CASSANDRA_HOST = "localhost";
	public static final int CASSANDRA_CQL_PORT = 9042;
	public static final String PASSWORD_TABLE_NAME = "passwords";

	private final UniversalMigratorConnector connector;

	protected PasswordStoreDatabaseMigrator(UniversalMigratorConnector connector) {
		this.connector = connector;
	}

	private MigrationSequence migrateVersion100() throws MigrationException {
		Version v100 = new Version(1, 0, 0);
		MigrationSequence sequence = new MigrationSequence(
				new MigrationMetadata(v100, "Rick-Rainer Ludwig",
						"Password Store", "", "Version " + v100 + " sequence."));
		sequence.registerMigrationStep(CassandraMigration.createKeyspace(
				connector, PASSWORD_STORE_KEYSPACE_NAME, v100,
				"Rick-Rainer Ludwig", "Keeps the user passwords.",
				ReplicationStrategy.SIMPLE_STRATEGY, 3));

		String description = "This table contains the authentication data and the state of the account.";
		sequence.registerMigrationStep(CassandraMigration.createTable(
				connector, PASSWORD_STORE_KEYSPACE_NAME, v100,
				"Rick-Rainer Ludwig", description, "CREATE TABLE "
						+ PASSWORD_TABLE_NAME//
						+ " (created timestamp, " //
						+ "last_modified timestamp, " //
						+ "email varchar," //
						+ "password ascii, " //
						+ "state ascii, "
						+ "activation_key ascii, "//
						+ "PRIMARY KEY (email))" + "WITH comment='"
						+ description + "';"));
		sequence.registerMigrationStep(CassandraMigration.createIndex(
				connector, PASSWORD_STORE_KEYSPACE_NAME, v100,
				"Rick-Rainer Ludwig", "Secondary index on state.",
				PASSWORD_TABLE_NAME, "state"));
		sequence.registerMigrationStep(new MigrationStep() {

			@Override
			public void migrate() throws MigrationException {
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
			public MigrationMetadata getMetadata() {
				return new MigrationMetadata(v100, "Rick-Rainer Ludwig",
						PASSWORD_STORE_KEYSPACE_NAME, "create admin account.",
						"Creates the first administrators account.");
			}
		});
		return sequence;
	}

	public static void main(String[] args) throws Exception {
		try (CassandraMigratorConnector connector = new CassandraMigratorConnector(
				CASSANDRA_HOST, CASSANDRA_CQL_PORT)) {
			PasswordStoreDatabaseMigrator passwordStoreSchema = new PasswordStoreDatabaseMigrator(
					connector);
			try (Migrator migrator = new Migrator()) {
				migrator.runMigration(passwordStoreSchema.migrateVersion100());
			}
		}
	}

}
