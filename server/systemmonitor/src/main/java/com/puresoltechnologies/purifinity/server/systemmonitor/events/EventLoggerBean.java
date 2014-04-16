package com.puresoltechnologies.purifinity.server.systemmonitor.events;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.systemmonitor.SystemMonitorConstants;

/**
 * This is the central event logger implementation.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EventLoggerBean implements EventLogger {

	private static final long serialVersionUID = -4162895953533068913L;

	private static final String EVENTS_TABLE_NAME = "events";
	private static final String LOG_EVENT_STATEMENT = "INSERT INTO "
			+ EVENTS_TABLE_NAME
			+ " (time, component, event_id, server, type, severity, message, user, user_id, client, exception_message, exception_stacktrace)"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
		logger.debug("Connect EventLogger to Cassandra...");
		cluster = Cluster.builder()
				.addContactPoints(SystemMonitorConstants.CASSANDRA_HOST)
				.withPort(SystemMonitorConstants.CASSANDRA_CQL_PORT).build();
		logger.debug("Cluster is connected.");
		session = cluster
				.connect(SystemMonitorConstants.SYSTEM_MONITOR_KEYSPACE_NAME);
		logger.debug("Session is connected.");
		logger.info("EventLogger connected to Cassandra.");
		createPreparedStatements();
	}

	private void createPreparedStatements() {
		preparedLogEventStatement = session.prepare(LOG_EVENT_STATEMENT);
	}

	@PreDestroy
	public void disconnect() {
		logger.debug("EventLogger is going to disconnet from Cassandra...");
		session.close();
		logger.debug("Session was closed.");
		cluster.close();
		logger.debug("Cluster was closed.");
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
