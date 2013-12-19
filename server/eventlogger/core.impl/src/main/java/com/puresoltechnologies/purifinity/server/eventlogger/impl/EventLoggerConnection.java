package com.puresoltechnologies.purifinity.server.eventlogger.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.database.cassandra.utils.CassandraUtils;
import com.puresoltechnologies.database.cassandra.utils.ReplicationStrategy;

@Singleton
public class EventLoggerConnection {

	private static final String CASSANDRA_HOST = "localhost";
	private static final int CASSANDRA_CQL_PORT = 9042;
	private static final String EVENT_LOGGER_KEYSPACE_NAME = "EventLogger";

	@Inject
	private Logger logger;

	private Cluster cluster = null;

	private Session session = null;

	@PostConstruct
	private void connect() {
		logger.info("Connect EventLogger to Cassandra...");
		cluster = Cluster.builder().addContactPoints(CASSANDRA_HOST)
				.withPort(CASSANDRA_CQL_PORT).build();

		KeyspaceMetadata keyspace = cluster.getMetadata().getKeyspace(
				EVENT_LOGGER_KEYSPACE_NAME);
		if (keyspace == null) {
			logger.info("Keyspace for EventLogger '"
					+ EVENT_LOGGER_KEYSPACE_NAME
					+ "' was not found. Need to be created now...");
			Session noKeyspaceSession = cluster.connect();
			CassandraUtils.createKeyspace(noKeyspaceSession,
					EVENT_LOGGER_KEYSPACE_NAME,
					ReplicationStrategy.SIMPLE_STRATEGY, 3);
			logger.info("Keyspace  '" + EVENT_LOGGER_KEYSPACE_NAME
					+ "' created.");
		}
		session = cluster.connect(EVENT_LOGGER_KEYSPACE_NAME);
		logger.info("EventLogger connected.");
	}

	@PreDestroy
	private void disconnect() {
		logger.info("Disconnect EventLogger from Cassandra...");
		session.shutdown();
		cluster.shutdown();
		logger.info("EventLogger disconnected.");
	}

	public Cluster getCluster() {
		return cluster;
	}

	public Session getSession() {
		return session;
	}
}
