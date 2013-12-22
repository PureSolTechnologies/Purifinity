package com.puresoltechnologies.purifinity.framework.database.cassandra.utils;

import com.datastax.driver.core.Session;

public final class CassandraUtils {

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

	/**
	 * Private constructor to avoid instantiation.
	 */
	private CassandraUtils() {
	}

}
