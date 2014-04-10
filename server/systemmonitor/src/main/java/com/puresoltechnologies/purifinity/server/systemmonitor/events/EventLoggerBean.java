package com.puresoltechnologies.purifinity.server.systemmonitor.events;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.CassandraMigration;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.MigrationException;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.ReplicationStrategy;
import com.puresoltechnologies.purifinity.server.systemmonitor.SystemMonitorConstants;

/**
 * This is the central event logger implementation.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
@Singleton
public class EventLoggerBean implements EventLogger {

	private static final long serialVersionUID = -4162895953533068913L;

	private static final String EVENTS_TABLE_NAME = "events";

	@Inject
	private Logger logger;

	private final String server;
	{
		try {
			server = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
	}

	private Cluster cluster = null;
	private Session session = null;
	private PreparedStatement preparedLogEventStatement = null;

	@PostConstruct
	public void connectAndInitialize() {
		try {
			connectToCassandra();
			createKeyspaceAndConnectToIt();
			createPreparedStatements();
			logEvent(EventLoggerEvents.createStartEvent());
		} catch (MigrationException e) {
			throw new RuntimeException("Cassandra could not be migrated.", e);
		}
	}

	private void connectToCassandra() {
		logger.debug("Connect EventLogger to Cassandra...");
		cluster = Cluster.builder()
				.addContactPoints(SystemMonitorConstants.CASSANDRA_HOST)
				.withPort(SystemMonitorConstants.CASSANDRA_CQL_PORT).build();
		logger.info("EventLogger connected to Cassandra.");
	}

	private void createKeyspaceAndConnectToIt() throws MigrationException {
		logger.debug("Initialize migration and check schema...");
		CassandraMigration.initialize(cluster);
		checkAndCreateKeyspace();
		session = cluster
				.connect(SystemMonitorConstants.SYSTEM_MONITOR_KEYSPACE_NAME);
		checkAndCreateTables();
		logger.info("EventLogger schema is ok.");
	}

	private void checkAndCreateKeyspace() throws MigrationException {
		CassandraMigration.createKeyspace(cluster,
				SystemMonitorConstants.SYSTEM_MONITOR_KEYSPACE_NAME, "1.0.0",
				"Rick-Rainer Ludwig", "Keeps the event log.",
				ReplicationStrategy.SIMPLE_STRATEGY, 3);
	}

	private void checkAndCreateTables() throws MigrationException {
		String description = "This is the table for the event log.";
		CassandraMigration
				.createTable(
						cluster,
						SystemMonitorConstants.SYSTEM_MONITOR_KEYSPACE_NAME,
						"1.0.0",
						"Rick-Rainer Ludwig",
						description,
						"CREATE TABLE "
								+ EVENTS_TABLE_NAME //
								+ " (time timestamp, " //
								+ "component ascii," //
								+ "event_id bigint," //
								+ "server ascii," //
								+ "type ascii, " //
								+ "severity ascii, "
								+ "message text, "//
								+ "user varchar, "
								+ "user_id bigint," //
								+ "client ascii, " //
								+ "exception_message ascii, "
								+ "exception_stacktrace ascii, "//
								+ "PRIMARY KEY (server, time, severity, type, component, event_id, message))"
								+ "WITH comment='" + description + "';");
	}

	private void createPreparedStatements() {
		preparedLogEventStatement = session
				.prepare("INSERT INTO "
						+ EVENTS_TABLE_NAME
						+ " (time, component, event_id, server, type, severity, message, user, user_id, client, exception_message, exception_stacktrace)"
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	}

	@PreDestroy
	public void disconnect() {
		logEvent(EventLoggerEvents.createStopEvent());
		session.close();
		cluster.close();
		logger.info("EventLogger disconnected.");
	}

	private String getStackTrace(Throwable throwable) {
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				PrintStream stream = new PrintStream(byteArrayOutputStream)) {
			throwable.printStackTrace(stream);
			String stackTrace = byteArrayOutputStream.toString();
			return stackTrace;
		} catch (IOException e) {
			logger.warn(
					"Could not read stacktrace into PrintStream. This should never happen.",
					e);
			return "<stacktrace conversion errored>";
		}
	}

	@Override
	public void logEvent(Event event) {
		writeToCassandra(event);
		writeToLogger(event);
	}

	private void writeToCassandra(Event event) {
		Throwable throwable = event.getThrowable();
		String exceptionMessage = null;
		String exceptionStacktrace = null;
		if (throwable != null) {
			exceptionMessage = throwable.getMessage();
			exceptionStacktrace = getStackTrace(throwable);
		}
		BoundStatement boundStatement = preparedLogEventStatement.bind(
				event.getTime(), event.getComponent(), event.getEventId(),
				server, event.getType().name(), event.getSeverity().name(),
				event.getMessage(), event.getUserEmail(), event.getUserId(),
				event.getClientHostname(), exceptionMessage,
				exceptionStacktrace);
		session.execute(boundStatement);
	}

	private void writeToLogger(Event event) {
		String message = "=====| " + event.getSeverity() + " event: "
				+ event.getMessage() + " (time=" + event.getTime() + ";type="
				+ event.getType() + ") |=====";
		switch (event.getSeverity()) {
		case INFO:
			logger.info(message);
			break;
		case WARNING:
			logger.warn(message);
			break;
		case ERROR:
		case FATAL:
			logger.error(message);
			break;
		}
	}
}
