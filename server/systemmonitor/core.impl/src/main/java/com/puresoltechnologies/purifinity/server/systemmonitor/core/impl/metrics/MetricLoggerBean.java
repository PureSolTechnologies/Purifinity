package com.puresoltechnologies.purifinity.server.systemmonitor.core.impl.metrics;

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
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.purifinity.server.systemmonitor.core.impl.SystemMonitorConstants;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventLogger;
import com.puresoltechnologies.server.systemmonitor.core.api.metrics.MetricLogger;
import com.puresoltechnologies.server.systemmonitor.core.api.metrics.MetricLoggerRemote;

/**
 * This is the central metric logger implementation.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
@Singleton
public class MetricLoggerBean implements MetricLogger, MetricLoggerRemote {

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
    private PreparedStatement preparedStatement;

    @PostConstruct
    public void createStatements() {
	logger.debug("Connect MetricLogger to Cassandra...");
	cluster = Cluster.builder()
		.addContactPoints(SystemMonitorConstants.CASSANDRA_HOST)
		.withPort(SystemMonitorConstants.CASSANDRA_CQL_PORT).build();
	session = cluster
		.connect(SystemMonitorConstants.SYSTEM_MONITOR_KEYSPACE_NAME);
	logger.info("MetricLogger connected to Cassandra.");
	createPreparedStatements();
	eventLogger.logEvent(MetricLoggerEvents.createStartEvent());
    }

    private void createPreparedStatements() {
	preparedStatement = session
		.prepare("INSERT INTO "
			+ METRICS_TABLE_NAME
			+ " (time, server, name, unit, type, description, decimal_value, integer_value, level_of_measurement)"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
    }

    @PreDestroy
    public void disconnect() {
	eventLogger.logEvent(MetricLoggerEvents.createStopEvent());
	session.close();
	cluster.close();
	logger.info("MetricsLogger disconnected.");
    }

    @Override
    public void logEvent(Value<?> value) {
	logEvent(new Date(), value);
    }

    @Override
    public void logEvent(Date time, Value<?> value) {
	writeToCassandra(time, value);
	writeToLogger(value);
    }

    private void writeToCassandra(Date time, Value<?> value) {
	Parameter<?> parameter = value.getParameter();
	if (!parameter.isNumeric()) {
	    throw new IllegalArgumentException("The value '" + value.toString()
		    + "' is not numeric!");
	}
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
    }

    private void writeToLogger(Value<?> value) {
	Parameter<?> parameter = value.getParameter();
	logger.info("-----| parameter: " + parameter + " = " + value.getValue()
		+ " [" + parameter.getUnit() + "] |-----");
    }
}
