package com.puresoltechnologies.purifinity.server.databaseconnector.cassandra;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;

@Singleton
public class ClusterProducer {

	private static final String CASSANDRA_HOST = "localhost";
	private static final int CASSANDRA_CQL_PORT = 9042;
	@Inject
	private Logger logger;

	@Produces
	@Singleton
	public Cluster getCluster() {
		logger.info("Creating Cassandra Cluster...");
		Builder clusterBuilder = Cluster.builder();
		Cluster cluster = clusterBuilder.addContactPoint(CASSANDRA_HOST)
				.withPort(CASSANDRA_CQL_PORT).build();
		logger.info("Cassandra Cluster created.");
		return cluster;
	}

	public void closeCluster(@Disposes Cluster cluster) {
		logger.info("Closing Cassandra Cluster...");
		cluster.close();
		logger.info("Cassandra Cluster closed.");
	}

}
