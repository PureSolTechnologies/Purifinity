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
import com.datastax.driver.core.Host;
import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.Metadata;
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

	@PostConstruct
	public void createStatements() {
		try {
			logger.info("Connect EventLogger to Cassandra...");
			cluster = Cluster.builder()
					.addContactPoints(SystemMonitorConstants.CASSANDRA_HOST)
					.withPort(SystemMonitorConstants.CASSANDRA_CQL_PORT)
					.build();
			CassandraMigration.initialize(cluster);
			checkAndCreateKeyspace();
			session = cluster
					.connect(SystemMonitorConstants.SYSTEM_MONITOR_KEYSPACE_NAME);
			checkAndCreateTables();
			logger.info("EventLogger connected.");
			logEvent(new Event(EventType.SYSTEM, EventSeverity.INFO,
					"EventLogger was started up..."));
		} catch (MigrationException e) {
			throw new RuntimeException("Cassandra could not be migrated.", e);
		}
	}

	private void checkAndCreateKeyspace() throws MigrationException {
		Metadata metadata = cluster.getMetadata();
		logger.info("Cassandra cluster name: '" + metadata.getClusterName()
				+ "'.");
		int hostId = 0;
		for (Host host : metadata.getAllHosts()) {
			hostId++;
			logger.info("Host " + hostId + ": " + host.getDatacenter() + "/"
					+ host.getRack() + "/" + host.getAddress().toString());
		}
		int keyspaceId = 0;
		for (KeyspaceMetadata keyspaceMetadata : metadata.getKeyspaces()) {
			keyspaceId++;
			logger.info("Keyspace " + keyspaceId + ": "
					+ keyspaceMetadata.getName());
		}
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
								+ "server ascii," //
								+ "type ascii, " //
								+ "severity ascii, "
								+ "message text, "//
								+ "user varchar, "
								+ "ip_address inet, " //
								+ "exception_message ascii, "
								+ "exception_stacktrace ascii, "//
								+ "PRIMARY KEY (server, time, type, severity, message))"
								+ "WITH comment='" + description + "';");
	}

	@PreDestroy
	public void disconnect() {
		logEvent(new Event(EventType.SYSTEM, EventSeverity.INFO,
				"EventLogger is shutting down..."));
		logger.info("Disconnect EventLogger from Cassandra...");
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
			/*
			 * This should not happen...
			 */
			return "";
		}
	}

	@Override
	public void logEvent(Event event) {
		PreparedStatement preparedStatement = session
				.prepare("INSERT INTO "
						+ EVENTS_TABLE_NAME
						+ " (time, server, type, severity, message, user, ip_address, exception_message, exception_stacktrace)"
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		Throwable throwable = event.getThrowable();
		String exceptionMessage = null;
		String exceptionStacktrace = null;
		if (throwable != null) {
			exceptionMessage = throwable.getMessage();
			exceptionStacktrace = getStackTrace(throwable);
		}
		BoundStatement boundStatement = preparedStatement.bind(event.getTime(),
				server, event.getType().name(), event.getSeverity().name(),
				event.getMessage(), event.getUser(), event.getIpAddress(),
				exceptionMessage, exceptionStacktrace);
		session.execute(boundStatement);

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
