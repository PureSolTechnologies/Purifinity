package com.puresoltechnologies.purifinity.server.database.cassandra;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;

import com.datastax.driver.core.Cluster;

@Singleton
public class ClusterProducer {

	@Inject
	private Logger logger;

	@Produces
	@Singleton
	public Cluster getCluster() {
		logger.info("Creating Cassandra Cluster...");
		Cluster cluster = CassandraClusterHelper.connect();
		logger.info("Cassandra Cluster created.");
		return cluster;
	}

	public void closeCluster(@Disposes Cluster cluster) {
		logger.info("Closing Cassandra Cluster...");
		cluster.close();
		logger.info("Cassandra Cluster closed.");
	}

}
