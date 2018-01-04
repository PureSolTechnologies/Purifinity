package com.puresoltechnologies.purifinity.server.database.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.SocketOptions;

public class CassandraClusterHelper {

    public static final String CASSANDRA_HOST = "localhost";
    public static final int CASSANDRA_CQL_PORT = 9042;

    public static Cluster connect() {
	Builder clusterBuilder = Cluster.builder();
	// Socket Options
	SocketOptions socketOptions = new SocketOptions();
	socketOptions.setConnectTimeoutMillis(60000);
	socketOptions.setKeepAlive(true);
	socketOptions.setReadTimeoutMillis(30000);
	Cluster cluster = clusterBuilder.addContactPoint(CASSANDRA_HOST)
		.withPort(CASSANDRA_CQL_PORT).withSocketOptions(socketOptions)
		.build();
	return cluster;
    }

}
