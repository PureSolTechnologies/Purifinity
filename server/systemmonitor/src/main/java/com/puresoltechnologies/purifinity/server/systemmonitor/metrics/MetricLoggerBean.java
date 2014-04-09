package com.puresoltechnologies.purifinity.server.systemmonitor.metrics;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

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
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.CassandraMigration;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.MigrationException;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.ReplicationStrategy;
import com.puresoltechnologies.purifinity.server.systemmonitor.SystemMonitorConstants;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.Event;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventLogger;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventSeverity;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventType;

/**
 * This is the central metric logger implementation.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
@Singleton
public class MetricLoggerBean implements MetricLogger {

	private static final long serialVersionUID = -4162895953533068913L;

	private static final String METRICS_TABLE_NAME = "metrics";

	@Inject
	private Logger logger;

	@Inject
	private EventLogger eventLogger;

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
			eventLogger.logEvent(new Event(EventType.SYSTEM,
					EventSeverity.INFO, "MetricLogger was started up..."));
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
		String description = "This is the table for metrics and KPIs.";
		CassandraMigration.createTable(cluster,
				SystemMonitorConstants.SYSTEM_MONITOR_KEYSPACE_NAME, "1.0.0",
				"Rick-Rainer Ludwig", description, "CREATE TABLE "
						+ METRICS_TABLE_NAME //
						+ " (time timestamp, " //
						+ "server ascii," //
						+ "name varchar," //
						+ "unit varchar, " //
						+ "type ascii, "
						+ "description text, "//
						+ "decimal_value decimal, "//
						+ "integer_value varint, "//
						+ "level_of_measurement ascii, "
						+ "PRIMARY KEY (server, time, name))"
						+ "WITH comment='" + description + "';");
	}

	@PreDestroy
	public void disconnect() {
		eventLogger.logEvent(new Event(EventType.SYSTEM, EventSeverity.INFO,
				"MetricLogger is shutting down..."));
		logger.info("Disconnect EventLogger from Cassandra...");
		session.close();
		cluster.close();
		logger.info("EventLogger disconnected.");
	}

	@Override
	public void logEvent(Value<?> value) {
		logEvent(new Date(), value);
	}

	@Override
	public void logEvent(Date time, Value<?> value) {
		Parameter<?> parameter = value.getParameter();
		if (!parameter.isNumeric()) {
			throw new IllegalArgumentException("The value '" + value.toString()
					+ "' is not numeric!");
		}
		PreparedStatement preparedStatement = session
				.prepare("INSERT INTO "
						+ METRICS_TABLE_NAME
						+ " (time, server, name, unit, type, description, decimal_value, integer_value, level_of_measurement)"
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		BigDecimal decimalValue = null;
		BigInteger integerValue = null;
		if (Long.class.isAssignableFrom(parameter.getType())) {
			integerValue = BigInteger.valueOf((Long) value.getValue());
		} else {
			decimalValue = BigDecimal.valueOf((Double) value.getValue());
		}
		BoundStatement boundStatement = preparedStatement.bind(time, server,
				parameter.getName(), parameter.getUnit(), parameter.getType()
						.getName(), parameter.getDescription(), decimalValue,
				integerValue, parameter.getLevelOfMeasurement().name());
		session.execute(boundStatement);
		logger.info("-----| parameter: " + parameter + " = " + value.getValue()
				+ " [" + parameter.getUnit() + "] |-----");
	}
}
