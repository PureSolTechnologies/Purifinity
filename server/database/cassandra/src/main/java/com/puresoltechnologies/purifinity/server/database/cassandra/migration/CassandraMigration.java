package com.puresoltechnologies.purifinity.server.database.cassandra.migration;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.misc.Version;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraUtils;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.ReplicationStrategy;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationStep;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationStepMetadata;
import com.puresoltechnologies.purifinity.server.database.migration.spi.UniversalMigratorTracker;

public class CassandraMigration {

	private static final Logger logger = LoggerFactory
			.getLogger(CassandraMigration.class);

	public static MigrationStep createKeyspace(final String keyspace,
			final Version version, final String developer,
			final String comment,
			final ReplicationStrategy replicationStrategy,
			final int replicationFactor) throws MigrationException {
		return new AbstractCassandraMigrationStep(keyspace) {

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

	public static void dropKeyspace(UniversalMigratorTracker tracker,
			Cluster cluster, String keyspace, Version version,
			String developer, String comment) throws MigrationException {
		migrate(tracker, cluster, null, version, developer, comment,
				"DROP KEYSPACE " + keyspace + ";");
	}

	public static MigrationStep createTable(final String keyspace,
			final Version version, final String developer,
			final String comment, final String creationStatement)
			throws MigrationException {
		return new AbstractCassandraMigrationStep(keyspace) {
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
		return new AbstractCassandraMigrationStep(keyspace) {
			@Override
			public MigrationStepMetadata getMetadata() {
				return new MigrationStepMetadata(version, developer, keyspace,
						"CREATE INDEX idx_" + table + "_" + column + " ON "
								+ table + " (" + column + ");", comment);
			}
		};
	}

	public static void migrate(UniversalMigratorTracker tracker,
			Cluster cluster, String keyspace, MigrationStepMetadata metadata)
			throws MigrationException {
		migrate(tracker, cluster, keyspace, metadata.getVersion(),
				metadata.getDeveloper(), metadata.getComment(),
				metadata.getCommand());
	}

	public static void migrate(UniversalMigratorTracker tracker,
			Cluster cluster, String keyspace, Version version,
			String developer, String comment, String command)
			throws MigrationException {
		Session keyspaceSession = CassandraUtils.connectToCluster(cluster,
				keyspace);
		try {
			if (!tracker.wasMigrated(version, keyspace, command)) {
				logger.info("Cassandra migration is needed:\n\"" + command
						+ "\"");
				keyspaceSession.execute(command);
				tracker.trackMigration(version, developer, keyspace, command,
						comment);
				logger.info("Cassandra migration performed successfully.");
			}
		} finally {
			keyspaceSession.close();
		}
	}

	public static void migrate(UniversalMigratorTracker tracker,
			Cluster cluster, String keyspace, Version version,
			String developer, MigrationStep migrationStep, String comment)
			throws MigrationException, IOException {
		String command = migrationStep.getClass().getCanonicalName();
		if (!tracker.wasMigrated(version, keyspace, command)) {
			logger.info("Cassandra migration is needed:\n\"" + command + "\"");
			CassandraMigratorConnector connector = new CassandraMigratorConnector(
					cluster);
			try {
				migrationStep.migrate(tracker, connector);
				tracker.trackMigration(version, developer, keyspace, command,
						comment);
			} finally {
				connector.close();
			}
			logger.info("Cassandra migration performed successfully.");
		}
	}

}
