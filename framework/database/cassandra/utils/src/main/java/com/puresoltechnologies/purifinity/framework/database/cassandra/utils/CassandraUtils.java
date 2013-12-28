package com.puresoltechnologies.purifinity.framework.database.cassandra.utils;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.TableMetadata;

public final class CassandraUtils {

	public static void checkAndCreateKeyspace(Cluster cluster,
			String keyspaceName, ReplicationStrategy replicationStrategy,
			int replicationFactor) {
		if (cluster.getMetadata().getKeyspace(keyspaceName) == null) {
			createKeyspace(cluster, keyspaceName, replicationStrategy,
					replicationFactor);
		}
	}

	public static void createKeyspace(Cluster cluster, String keyspaceName,
			ReplicationStrategy replicationStrategy, int replicationFactor) {
		Session session = cluster.connect();
		try {
			createKeyspace(session, keyspaceName, replicationStrategy,
					replicationFactor);
		} finally {
			session.shutdown();
		}
	}

	public static void createKeyspace(Session session, String keyspaceName,
			ReplicationStrategy replicationStrategy, int replicationFactor) {
		session.execute("CREATE KEYSPACE " + keyspaceName
				+ " WITH replication " + "= {'class':'"
				+ replicationStrategy.getStrategyName()
				+ "', 'replication_factor':" + replicationFactor + "};");
	}

	public static void dropKeyspace(Session session, String keyspaceName) {
		session.execute("DROP KEYSPACE " + keyspaceName + ";");
	}

	public static void checkAndCreateTable(Session session,
			KeyspaceMetadata keyspace, String tableName,
			String creationStatement, String... additionalStatements) {
		TableMetadata runSettingsTable = keyspace.getTable(tableName);
		if (runSettingsTable == null) {
			session.execute(creationStatement);
			for (String statement : additionalStatements) {
				session.execute(statement);
			}
		}
	}

	/**
	 * Private constructor to avoid instantiation.
	 */
	private CassandraUtils() {
	}

}
