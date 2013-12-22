package com.puresoltechnologies.purifinity.server.eventlogger.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.CassandraUtils;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.ReplicationStrategy;
import com.puresoltechnologies.purifinity.server.eventlogger.EventLogger;
import com.puresoltechnologies.purifinity.server.eventlogger.EventLoggerRemote;

/**
 * This is the central event logger implementation.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
@Singleton
@Remote(EventLoggerRemote.class)
@Local(EventLogger.class)
public class EventLoggerBean implements EventLoggerRemote {

	private static final long serialVersionUID = -4162895953533068913L;

	private static final String CASSANDRA_HOST = "localhost";
	private static final int CASSANDRA_CQL_PORT = 9042;
	private static final String EVENT_LOGGER_KEYSPACE_NAME = "event_logger";

	@Inject
	private Logger logger;

	private Cluster cluster = null;

	private Session session = null;

	@PostConstruct
	public void createStatements() {
		logger.info("Connect EventLogger to Cassandra...");
		cluster = Cluster.builder().addContactPoints(CASSANDRA_HOST)
				.withPort(CASSANDRA_CQL_PORT).build();
		checkAndCreateKeyspace();
		session = cluster.connect(EVENT_LOGGER_KEYSPACE_NAME);
		checkAndCreateTables();
		logger.info("EventLogger connected.");
	}

	private void checkAndCreateKeyspace() {
		Session noKeyspaceSession = cluster.connect();
		try {
			Metadata metadata = cluster.getMetadata();
			logger.info("Cassandra cluster name: '" + metadata.getClusterName()
					+ "'.");
			int hostId = 0;
			for (Host host : metadata.getAllHosts()) {
				hostId++;
				logger.info("Host " + hostId + ": " + host.getDatacenter()
						+ "/" + host.getRack() + "/"
						+ host.getAddress().toString());
			}
			int keyspaceId = 0;
			for (KeyspaceMetadata keyspaceMetadata : metadata.getKeyspaces()) {
				keyspaceId++;
				logger.info("Keyspace " + keyspaceId + ": "
						+ keyspaceMetadata.getName());
			}
			KeyspaceMetadata keyspace = metadata
					.getKeyspace(EVENT_LOGGER_KEYSPACE_NAME);
			if (keyspace == null) {
				logger.info("Keyspace for EventLogger '"
						+ EVENT_LOGGER_KEYSPACE_NAME
						+ "' was not found. Need to be created now...");
				CassandraUtils.createKeyspace(noKeyspaceSession,
						EVENT_LOGGER_KEYSPACE_NAME,
						ReplicationStrategy.SIMPLE_STRATEGY, 3);
				logger.info("Keyspace  '" + EVENT_LOGGER_KEYSPACE_NAME
						+ "' created.");
			}
		} finally {
			noKeyspaceSession.shutdown();
		}
	}

	private void checkAndCreateTables() {
		// TODO Auto-generated method stub

	}

	@PreDestroy
	private void disconnect() {
		logger.info("Disconnect EventLogger from Cassandra...");
		session.shutdown();
		cluster.shutdown();
		logger.info("EventLogger disconnected.");
	}

	private String getStackTrace(Exception exception) {
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				PrintStream stream = new PrintStream(byteArrayOutputStream)) {
			exception.printStackTrace(stream);
			String stackTrace = byteArrayOutputStream.toString();
			return stackTrace;
		} catch (IOException e) {
			/*
			 * This should not happen...
			 */
			return "";
		}
	}

	public void writeLog(String key, Properties content) {

	}

	@Override
	public void logUserAction(String user, String message) {
		Properties properties = new Properties();
		properties.put("user", user);
		properties.put("message", message);
		writeLog(String.valueOf(System.nanoTime()), properties);
	}

	@Override
	public void logUserException(String user, String exceptionMessage,
			String message) {
		Properties properties = new Properties();
		properties.put("user", user);
		properties.put("exceptionMessage", exceptionMessage);
		properties.put("message", message);
		writeLog(String.valueOf(System.nanoTime()), properties);
	}

	@Override
	public void logUserException(String user, Exception exception,
			String additional) {
		Properties properties = new Properties();
		properties.put("user", user);
		properties.put("exceptionMessage", exception.getMessage());
		properties.put("stackTrace", getStackTrace(exception));
		properties.put("message", additional);
		writeLog(String.valueOf(System.nanoTime()), properties);
	}

	@Override
	public void logSystemEvent(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void logSystemException(String message, String additional) {
		// TODO Auto-generated method stub

	}

	@Override
	public void logSystemException(Exception excpetion, String additional) {
		// TODO Auto-generated method stub

	}
}
