package com.puresoltechnologies.purifinity.server.database.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;

public class CassandraClusterHelper {

	private static final String CASSANDRA_HOST = "localhost";
	private static final int CASSANDRA_CQL_PORT = 9042;

	public static Cluster connect() {
		Builder clusterBuilder = Cluster.builder();
		Cluster cluster = clusterBuilder.addContactPoint(CASSANDRA_HOST)
				.withPort(CASSANDRA_CQL_PORT).build();
		return cluster;
	}

}
