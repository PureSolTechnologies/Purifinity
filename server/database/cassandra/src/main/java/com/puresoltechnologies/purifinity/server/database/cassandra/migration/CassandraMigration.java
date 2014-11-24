package com.puresoltechnologies.purifinity.server.database.cassandra.migration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.types.Version;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraUtils;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.ReplicationStrategy;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationMetadata;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationStep;
import com.puresoltechnologies.purifinity.server.database.migration.spi.UniversalMigratorConnector;

public class CassandraMigration {

	private static final Logger logger = LoggerFactory
			.getLogger(CassandraMigration.class);

	public static MigrationStep createKeyspace(
			UniversalMigratorConnector connector, final String keyspace,
			final Version version, final String developer,
			final String comment,
			final ReplicationStrategy replicationStrategy,
			final int replicationFactor) throws MigrationException {
		return new AbstractCassandraMigrationStep(connector, null) {

			@Override
			public MigrationMetadata getMetadata() {
				return new MigrationMetadata(version, developer, keyspace,
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
		migrate(cluster, null, version, developer, comment, "DROP KEYSPACE "
				+ keyspace + ";");
	}

	public static MigrationStep createTable(
			UniversalMigratorConnector connector, final String keyspace,
			final Version version, final String developer,
			final String comment, final String creationStatement)
			throws MigrationException {
		return new AbstractCassandraMigrationStep(connector, keyspace) {
			@Override
			public MigrationMetadata getMetadata() {

				return new MigrationMetadata(version, developer, keyspace,
						creationStatement, comment);
			}
		};

	}

	public static MigrationStep createIndex(
			UniversalMigratorConnector connector, final String keyspace,
			final Version version, final String developer,
			final String comment, final String table, final String column)
			throws MigrationException {
		return new AbstractCassandraMigrationStep(connector, keyspace) {
			@Override
			public MigrationMetadata getMetadata() {
				return new MigrationMetadata(version, developer, keyspace,
						"CREATE INDEX idx_" + table + "_" + column + " ON "
								+ table + " (" + column + ");", comment);
			}
		};
	}

	public static void migrate(Cluster cluster, String keyspace,
			MigrationMetadata metadata) throws MigrationException {
		migrate(cluster, keyspace, metadata.getVersion(),
				metadata.getDeveloper(), metadata.getComment(),
				metadata.getCommand());
	}

	public static void migrate(Cluster cluster, String keyspace,
			Version version, String developer, String comment, String command)
			throws MigrationException {
		Session keyspaceSession = CassandraUtils.connectToCluster(cluster,
				keyspace);
		try {
			logger.info("Cassandra migration is needed:\n\"" + command + "\"");
			keyspaceSession.execute(command);
			logger.info("Cassandra migration performed successfully.");
		} finally {
			keyspaceSession.close();
		}
	}

}
