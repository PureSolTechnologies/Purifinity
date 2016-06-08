package com.puresoltechnologies.purifinity.server.systemmonitor.core.impl.metrics;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.puresoltechnologies.commons.domain.Parameter;
import com.puresoltechnologies.commons.domain.Value;
import com.puresoltechnologies.purifinity.server.systemmonitor.core.impl.SystemMonitorConnection;
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

    public static final String METRICS_TABLE_NAME = "system_monitor_metrics";

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

    @Inject
    @SystemMonitorConnection
    private Connection connection;

    private PreparedStatement preparedStatement;

    @PostConstruct
    public void createStatements() {
	try {
	    preparedStatement = connection.prepareStatement("UPSERT INTO " + METRICS_TABLE_NAME
		    + " (time, server, name, unit, type, description, decimal_value, integer_value, level_of_measurement)"
		    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
	    eventLogger.logEvent(MetricLoggerEvents.createStartEvent());
	} catch (SQLException e) {
	    throw new RuntimeException("Could not prepare statement for event logger.", e);
	}
    }

    @PreDestroy
    public void disconnect() {
	try {
	    preparedStatement.close();
	} catch (SQLException e) {
	    logger.warn("Could not close prepared statement.", e);
	}
	eventLogger.logEvent(MetricLoggerEvents.createStopEvent());
    }

    @Override
    public void logEvent(Value<?> value) {
	logEvent(new Date(), value);
    }

    @Override
    public void logEvent(Date time, Value<?> value) {
	writeToDatabase(time, value);
	writeToLogger(value);
    }

    private void writeToDatabase(Date time, Value<?> value) {
	try {
	    Parameter<?> parameter = value.getParameter();
	    if (!parameter.isNumeric()) {
		throw new IllegalArgumentException("The value '" + value.toString() + "' is not numeric!");
	    }
	    BigDecimal decimalValue = null;
	    BigInteger integerValue = null;
	    if (Long.class.isAssignableFrom(parameter.getType())) {
		integerValue = BigInteger.valueOf((Long) value.getValue());
	    } else {
		decimalValue = BigDecimal.valueOf((Double) value.getValue());
	    }
	    preparedStatement.setTime(1, new Time(time.getTime()));
	    preparedStatement.setString(2, server);
	    preparedStatement.setString(3, parameter.getName());
	    preparedStatement.setString(4, parameter.getUnit());
	    preparedStatement.setString(5, parameter.getType().getName());
	    preparedStatement.setString(6, parameter.getDescription());
	    preparedStatement.setBigDecimal(7, decimalValue);
	    preparedStatement.setLong(8, integerValue.longValue());
	    preparedStatement.setString(9, parameter.getLevelOfMeasurement().name());
	    preparedStatement.execute();
	} catch (SQLException e) {
	    throw new RuntimeException("Could not insert event into event log.", e);
	}
    }

    private void writeToLogger(Value<?> value) {
	Parameter<?> parameter = value.getParameter();
	logger.info(
		"-----| parameter: " + parameter + " = " + value.getValue() + " [" + parameter.getUnit() + "] |-----");
    }
}
