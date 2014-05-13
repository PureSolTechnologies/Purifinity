package com.puresoltechnologies.purifinity.server.databaseconnector.cassandra.utils;

import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.misc.HashUtilities;
import com.puresoltechnologies.purifinity.framework.commons.utils.Version;
import com.puresoltechnologies.purifinity.framework.database.migration.DatabaseMigrationConnector;
import com.puresoltechnologies.purifinity.framework.database.migration.MigrationException;
import com.puresoltechnologies.purifinity.framework.database.migration.MigrationStep;
import com.puresoltechnologies.purifinity.framework.database.migration.MigrationStepMetadata;

public class CassandraMigration {

	private static final Logger logger = LoggerFactory
			.getLogger(CassandraMigration.class);

	private static final String KEYSPACE_NAME = "cluster_migration";
	public static final String CHANGELOG_TABLE = "changelog";

	private static PreparedStatement preparedInsertStatement = null;
	private static PreparedStatement preparedSelectStatement = null;

	/**
	 * Initializes the migration tables for the given cluster.
	 * 
	 * @param cluster
	 *            is the {@link Cluster} to connect to for the initialization.
	 * @throws MigrationException
	 */
	public static void initialize(Cluster cluster) throws MigrationException {
		Session session = CassandraUtils.connectToCluster(cluster);
		try {
			Metadata clusterMetadata = cluster.getMetadata();
			KeyspaceMetadata keyspaceMetadata = clusterMetadata
					.getKeyspace(KEYSPACE_NAME);
			if (keyspaceMetadata == null) {
				logger.info("Keyspace for Cassandra migration is missing. Needs to be created...");
				session.execute("CREATE KEYSPACE " + KEYSPACE_NAME
						+ " WITH replication " + "= {'class':'"
						+ ReplicationStrategy.SIMPLE_STRATEGY.getStrategyName()
						+ "', 'replication_factor':3};");
				keyspaceMetadata = clusterMetadata.getKeyspace(KEYSPACE_NAME);
				if (keyspaceMetadata == null) {
					throw new MigrationException("Could not create keyspace '"
							+ KEYSPACE_NAME + "'.");
				}
				logger.info("Keyspace for Cassandra migration created.");
			}
			if (keyspaceMetadata.getTable(CHANGELOG_TABLE) == null) {
				logger.info("ChangeLog table for Cassandra migration is missing. Needs to be created...");
				session.execute("CREATE TABLE " + KEYSPACE_NAME + "."
						+ CHANGELOG_TABLE + " (time timestamp, "
						+ "version varchar, " + "developer varchar, "
						+ "keyspace_name varchar, " + "command varchar, "
						+ "hashid varchar, " + "comment varchar, "
						+ "PRIMARY KEY(version, keyspace_name, command));");
				logger.info("ChangeLog table for Cassandra migration created.");
			}
		} finally {
			session.close();
		}
	}

	public static MigrationStep createKeyspace(final String keyspace,
			final Version version, final String developer,
			final String comment,
			final ReplicationStrategy replicationStrategy,
			final int replicationFactor) throws MigrationException {
		return new MigrationStep() {

			@Override
			public void migrate(DatabaseMigrationConnector connector)
					throws IOException, MigrationException {
				CassandraMigration.migrate(
						((CassandraMigrationConnector) connector).getCluster(),
						null, getMetadata());
			}

			@Override
			public MigrationStepMetadata getMetadata() {
				return new MigrationStepMetadata(version, developer, keyspace,
						"CREATE KEYSPACE " + keyspace + " WITH replication "
								+ "= {'class':'"
								+ replicationStrategy.getStrategyName()
								+ "', 'replication_factor':"
								+ replicationFactor + "};", comment);
			}
		};
	}

	public static void dropKeyspace(Cluster cluster, String keyspace,
			Version version, String developer, String comment)
			throws MigrationException {
		migrate(cluster, null, version, developer, "DROP KEYSPACE " + keyspace
				+ ";", comment);
	}

	public static MigrationStep createTable(final String keyspace,
			final Version version, final String developer,
			final String comment, final String creationStatement)
			throws MigrationException {
		return new MigrationStep() {

			@Override
			public void migrate(DatabaseMigrationConnector connector)
					throws IOException, MigrationException {
				CassandraMigration.migrate(
						((CassandraMigrationConnector) connector).getCluster(),
						keyspace, getMetadata());
			}

			@Override
			public MigrationStepMetadata getMetadata() {

				return new MigrationStepMetadata(version, developer,
						creationStatement, creationStatement, comment);
			}
		};

	}

	public static MigrationStep createIndex(final String keyspace,
			final Version version, final String developer,
			final String comment, final String table, final String column)
			throws MigrationException {
		return new MigrationStep() {

			@Override
			public void migrate(DatabaseMigrationConnector connector)
					throws IOException, MigrationException {
				CassandraMigration.migrate(
						((CassandraMigrationConnector) connector).getCluster(),
						keyspace, getMetadata());
			}

			@Override
			public MigrationStepMetadata getMetadata() {
				return new MigrationStepMetadata(version, developer, keyspace,
						"CREATE INDEX idx_" + table + "_" + column + " ON "
								+ table + " (" + column + ");", comment);
			}
		};
	}

	public static void migrate(Cluster cluster, String keyspace,
			MigrationStepMetadata metadata) throws MigrationException {
		migrate(cluster, keyspace, metadata.getVersion(),
				metadata.getDeveloper(), metadata.getCommand(),
				metadata.getComment());
	}

	public static void migrate(Cluster cluster, String keyspace,
			Version version, String developer, String command, String comment)
			throws MigrationException {
		Session session = CassandraUtils.connectToCluster(cluster);
		try {
			Session keyspaceSession = CassandraUtils.connectToCluster(cluster,
					keyspace);
			try {
				if (!wasMigrated(session, version, keyspace, command)) {
					logger.info("Cassandra migration is needed:\n\"" + command
							+ "\"");
					keyspaceSession.execute(command);
					writeLog(session, version, developer, keyspace, command,
							comment);
					logger.info("Cassandra migration performed successfully.");
				}
			} finally {
				keyspaceSession.close();
			}
		} finally {
			session.close();
		}
	}

	public static void migrate(Cluster cluster, String keyspace,
			Version version, String developer, MigrationStep migrationStep,
			String comment) throws MigrationException {
		Session session = CassandraUtils.connectToCluster(cluster, keyspace);
		try {
			String command = migrationStep.getClass().getCanonicalName();
			if (!wasMigrated(session, version, keyspace, command)) {
				logger.info("Cassandra migration is needed:\n\"" + command
						+ "\"");
				// FIXME migrationStep.migrate(cluster);
				writeLog(session, version, developer, keyspace, command,
						comment);
				logger.info("Cassandra migration performed successfully.");
			}
		} finally {
			session.close();
		}
	}

	/**
	 * This method checks whether a migration was performed already or not.
	 * 
	 * @param session
	 *            is the {@link Session} of a key space to be checked.
	 * @param version
	 *            is the version to be checked for.
	 * @param keyspace
	 *            is the name of the keyspace to be checked.
	 * @param command
	 *            is the command
	 * @return
	 */
	private static boolean wasMigrated(Session session, Version version,
			String keyspace, String command) {
		if (preparedSelectStatement == null) {
			createPreparedStatements(session);
		}
		String keyspaceName = keyspace == null ? "" : keyspace;
		BoundStatement boundStatement = preparedSelectStatement.bind(
				version.toString(), keyspaceName, command);
		ResultSet result = session.execute(boundStatement);
		return result.iterator().hasNext();
	}

	private static void writeLog(Session session, Version version,
			String developer, String keyspace, String command, String comment)
			throws MigrationException {
		if (preparedInsertStatement == null) {
			createPreparedStatements(session);
		}
		try {
			HashId hashId = HashUtilities.createHashId(command);
			BoundStatement boundStatement = preparedInsertStatement.bind(
					new Date(), version.toString(), developer,
					keyspace == null ? "" : keyspace, command, hashId
							.toString(), comment);
			session.execute(boundStatement);
		} catch (IOException e) {
			throw new MigrationException(
					"Could not perform Cassandra migration.", e);
		}
	}

	private static synchronized void createPreparedStatements(Session session) {
		if (preparedInsertStatement == null) {
			preparedInsertStatement = session
					.prepare("INSERT INTO "
							+ KEYSPACE_NAME
							+ "."
							+ CHANGELOG_TABLE
							+ " (time, version, developer, keyspace_name, command, hashid, comment)"
							+ " VALUES (?, ?, ?, ?, ?, ?, ?);");
		}
		if (preparedSelectStatement == null) {
			preparedSelectStatement = session
					.prepare("SELECT version, keyspace_name, command FROM "
							+ KEYSPACE_NAME + "." + CHANGELOG_TABLE
							+ " WHERE version=?" + " AND keyspace_name=?"
							+ " AND command=?" + ";");
		}
	}
}
