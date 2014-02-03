package com.puresoltechnologies.purifinity.framework.database.cassandra.utils;

import java.io.IOException;
import java.util.Date;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SimpleStatement;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.misc.HashUtilities;

public class CassandraMigration {

	private static final String KEYSPACE_NAME = "cluster_migration";

	public static final String CHANGELOG_TABLE = "changelog";

	private static PreparedStatement preparedWasMigratedQuery = null;
	private static PreparedStatement preparedInsertStatement = null;

	/**
	 * Initializes the migration tables for the given cluster.
	 * 
	 * @param cluster
	 *            is the {@link Cluster} to connect to for the initialization.
	 * @throws MigrationException
	 */
	public static void initialize(Cluster cluster) throws MigrationException {
		String version = "0.0.0";
		String user = CassandraMigration.class.getName();
		createKeyspace(cluster, KEYSPACE_NAME, version, user,
				"Migration log keyspace", ReplicationStrategy.SIMPLE_STRATEGY,
				1);
		createTable(cluster, KEYSPACE_NAME, version, user, "Change log table",
				CHANGELOG_TABLE, "CREATE TABLE " + CHANGELOG_TABLE
						+ " (time timestamp, " + "version varchar, "
						+ "developer varchar, " + "keyspace varchar, "
						+ "command varchar, " + "hashid varchar, "
						+ "comment varchar, "
						+ "PRIMARY KEY(version, keyspace, command));");
	}

	public static void createKeyspace(Cluster cluster, String keyspace,
			String version, String developer, String comment,
			ReplicationStrategy replicationStrategy, int replicationFactor)
			throws MigrationException {
		migrate(cluster, null, version, developer, "CREATE KEYSPACE "
				+ keyspace + " WITH replication " + "= {'class':'"
				+ replicationStrategy.getStrategyName()
				+ "', 'replication_factor':" + replicationFactor + "};",
				comment);
	}

	public static void dropKeyspace(Cluster cluster, String keyspace,
			String version, String developer, String comment)
			throws MigrationException {
		migrate(cluster, null, version, developer, "DROP KEYSPACE " + keyspace
				+ ";", comment);
	}

	public static void createTable(Cluster cluster, String keyspace,
			String version, String developer, String comment,
			String creationStatement, String... additionalStatements)
			throws MigrationException {
		migrate(cluster, null, version, developer, creationStatement, comment);
		for (String statement : additionalStatements) {
			migrate(cluster, null, version, developer, statement, comment);
		}
	}

	public static void migrate(Cluster cluster, String keyspace,
			String version, String developer, String command, String comment)
			throws MigrationException {
		Session session = CassandraUtils.connectToCluster(cluster, keyspace);
		try {
			if (!wasMigrated(session, version, keyspace, command)) {
				session.execute(command);
				writeLog(session, version, developer, keyspace, command,
						comment);
			}
		} finally {
			session.shutdown();
		}
	}

	public static void migrate(Cluster cluster, String keyspace,
			String version, String developer,
			MigrationProcedure migrationProcedure, String comment)
			throws MigrationException {
		Session session = CassandraUtils.connectToCluster(cluster, keyspace);
		try {
			String command = migrationProcedure.getClass().getCanonicalName();
			if (!wasMigrated(session, version, keyspace, command)) {
				migrationProcedure.perform(cluster);
				writeLog(session, version, developer, keyspace, command,
						comment);
			}
		} finally {
			session.shutdown();
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
	private static boolean wasMigrated(Session session, String version,
			String keyspace, String command) {
		if (preparedWasMigratedQuery == null) {
			createPreparedWasMigratedQuery(session);
		}
		BoundStatement boundStatement = preparedWasMigratedQuery.bind(version,
				keyspace, command);
		ResultSet result = session.execute(boundStatement);
		return result.iterator().hasNext();
	}

	private static void createPreparedWasMigratedQuery(Session session) {
		if (preparedWasMigratedQuery == null) {
			preparedWasMigratedQuery = session
					.prepare(new SimpleStatement(
							"SELECT version, keyspace, command FROM "
									+ CHANGELOG_TABLE
									+ " WHERE version=?, keyspace=\"?\", command=\"?\";"));
		}
	}

	private static void writeLog(Session session, String version,
			String developer, String keyspace, String command, String comment)
			throws MigrationException {
		if (preparedInsertStatement == null) {
			createPreparedInsertStatement(session);
		}
		try {
			HashId hashId = HashUtilities.createHashId(command);
			BoundStatement boundStatement = preparedInsertStatement.bind(
					new Date(), version, developer, keyspace, command,
					hashId.toString(), comment);
			session.execute(boundStatement);
		} catch (IOException e) {
			throw new MigrationException(
					"Could not perform Cassandra migration.", e);
		}
	}

	private static synchronized void createPreparedInsertStatement(
			Session session) {
		if (preparedInsertStatement == null) {
			preparedInsertStatement = session
					.prepare(new SimpleStatement(
							"INSERT INTO "
									+ CHANGELOG_TABLE
									+ " (time, version, developer, keyspace, command, sha2, comment) VALUES (?, ?, ?, ?, ?, ?, ?)"));
		}
	}

}
