package com.puresoltechnologies.purifinity.server.database.cassandra.migration;

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
import com.puresoltechnologies.commons.os.HashId;
import com.puresoltechnologies.commons.os.HashUtilities;
import com.puresoltechnologies.commons.types.Version;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraUtils;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.ReplicationStrategy;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;
import com.puresoltechnologies.purifinity.server.database.migration.spi.UniversalMigratorTracker;

/**
 * This is the default migration tracker for Purifinity which puts everything
 * into Cassandra.
 * 
 * @author Rick-Rainer Ludwig
 */
public class CassandraMigrationTracker implements UniversalMigratorTracker {

	private static final Logger logger = LoggerFactory
			.getLogger(CassandraMigrationTracker.class);

	public static final String KEYSPACE_NAME = "cluster_migration";
	public static final String CHANGELOG_TABLE = "changelog";

	private static PreparedStatement preparedInsertStatement = null;
	private static PreparedStatement preparedSelectStatement = null;

	private Cluster cluster = null;
	private Session session = null;

	@Override
	public void open() throws MigrationException {
		connect();
		prepareKeyspace();
	}

	private void connect() {
		if (cluster != null) {
			throw new IllegalStateException(
					"Cluster for migration tracker was already connected.");
		}
		cluster = Cluster.builder().addContactPoint("localhost").withPort(9042)
				.build();
		if (session != null) {
			throw new IllegalStateException(
					"Session for migration tracker was already opened.");
		}
		session = CassandraUtils.connectToCluster(cluster);
	}

	private void prepareKeyspace() throws MigrationException {
		Metadata clusterMetadata = cluster.getMetadata();
		KeyspaceMetadata keyspaceMetadata = clusterMetadata
				.getKeyspace(CassandraMigrationTracker.KEYSPACE_NAME);
		if (keyspaceMetadata == null) {
			logger.info("Keyspace for Cassandra migration is missing. Needs to be created...");
			session.execute("CREATE KEYSPACE "
					+ CassandraMigrationTracker.KEYSPACE_NAME
					+ " WITH replication " + "= {'class':'"
					+ ReplicationStrategy.SIMPLE_STRATEGY.getStrategyName()
					+ "', 'replication_factor':3};");
			keyspaceMetadata = clusterMetadata
					.getKeyspace(CassandraMigrationTracker.KEYSPACE_NAME);
			if (keyspaceMetadata == null) {
				throw new MigrationException("Could not create keyspace '"
						+ CassandraMigrationTracker.KEYSPACE_NAME + "'.");
			}
			logger.info("Keyspace for Cassandra migration created.");
		}
		if (keyspaceMetadata
				.getTable(CassandraMigrationTracker.CHANGELOG_TABLE) == null) {
			logger.info("ChangeLog table for Cassandra migration is missing. Needs to be created...");
			session.execute("CREATE TABLE "
					+ CassandraMigrationTracker.KEYSPACE_NAME + "."
					+ CassandraMigrationTracker.CHANGELOG_TABLE
					+ " (time timestamp, " + "version varchar, "
					+ "developer varchar, " + "component varchar, "
					+ "command varchar, " + "hashid varchar, "
					+ "comment varchar, "
					+ "PRIMARY KEY(version, component, command));");
			logger.info("ChangeLog table for Cassandra migration created.");
		}
	}

	@Override
	public void close() {
		if (session == null) {
			throw new IllegalStateException(
					"Session for migration tracker was not opened.");
		}
		session.close();
		session = null;
		if (cluster == null) {
			throw new IllegalStateException(
					"Cluster for migration tracker was not connected.");
		}
		cluster.close();
		cluster = null;
	}

	@Override
	public void trackMigration(Version version, String developer,
			String component, String command, String comment)
			throws MigrationException {
		if (preparedInsertStatement == null) {
			createPreparedStatements(session);
		}
		try {
			HashId hashId = HashUtilities.createHashId(command);
			BoundStatement boundStatement = preparedInsertStatement.bind(
					new Date(), version.toString(), developer,
					component == null ? "" : component, command,
					hashId.toString(), comment);
			session.execute(boundStatement);
		} catch (IOException e) {
			throw new MigrationException("Could not track migration step.", e);
		}
	}

	private static synchronized void createPreparedStatements(Session session) {
		if (preparedInsertStatement == null) {
			preparedInsertStatement = session
					.prepare("INSERT INTO "
							+ KEYSPACE_NAME
							+ "."
							+ CHANGELOG_TABLE
							+ " (time, version, developer, component, command, hashid, comment)"
							+ " VALUES (?, ?, ?, ?, ?, ?, ?);");
		}
		if (preparedSelectStatement == null) {
			preparedSelectStatement = session
					.prepare("SELECT version, component, command FROM "
							+ KEYSPACE_NAME + "." + CHANGELOG_TABLE
							+ " WHERE version=?" + " AND component=?"
							+ " AND command=?" + ";");
		}
	}

	@Override
	public boolean wasMigrated(Version version, String component, String command) {
		if (preparedSelectStatement == null) {
			createPreparedStatements(session);
		}
		String keyspaceName = component == null ? "" : component;
		BoundStatement boundStatement = preparedSelectStatement.bind(
				version.toString(), keyspaceName, command);
		ResultSet result = session.execute(boundStatement);
		return result.iterator().hasNext();
	}

}
