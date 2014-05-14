package com.puresoltechnologies.purifinity.server.database.cassandra.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;

/**
 * This is a test helper for tests against the local Cassandra test database.
 * 
 * @author Rick-Rainer Ludwig
 */
public final class CassandraTestHelper {

	private static final Logger logger = LoggerFactory
			.getLogger(CassandraTestHelper.class);

	private static final String CASSANDRA_HOST = "127.0.0.1";

	private static Cluster cluster = null;
	private static Session session = null;

	/**
	 * Connects to the local test Cassandra cluster.
	 */
	public static void connect() {
		assertNull(
				"The connection to Cassandra cluster was already established.",
				cluster);
		assertNull("The session to Cassandra cluster was already connected.",
				session);
		cluster = Cluster.builder().addContactPoints(CASSANDRA_HOST)
				.withPort(9042).build();
		logger.info(String.format("Connected to cluster: %s\n",
				cluster.getClusterName()));
		logMetadata();
		session = cluster.connect();
	}

	/**
	 * Disconnects from the local test Cassandra cluster.
	 */
	private static void logMetadata() {
		Metadata metadata = cluster.getMetadata();
		for (Host host : metadata.getAllHosts()) {
			logger.info(String.format("Datacenter: %s; Host: %s; Rack: %s\n",
					host.getDatacenter(), host.getAddress(), host.getRack()));
		}
	}

	public static void disconnect() {
		assertNotNull(
				"The session to Cassandra cluster was not connected, yet.",
				session);
		session.close();
		session = null;
		assertNotNull(
				"The connection to Cassandra cluster was not established, yet.",
				cluster);
		cluster.close();
		cluster = null;
	}

	public static Cluster getCluster() {
		return cluster;
	}

	public static Session getSession() {
		return session;
	}

	// Private constructor to avoid instantiation.
	private CassandraTestHelper() {
	}

}
