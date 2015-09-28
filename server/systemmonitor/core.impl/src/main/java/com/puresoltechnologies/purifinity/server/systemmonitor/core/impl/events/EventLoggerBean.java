package com.puresoltechnologies.purifinity.server.systemmonitor.core.impl.events;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.puresoltechnologies.purifinity.server.systemmonitor.core.impl.SystemMonitorConnection;
import com.puresoltechnologies.server.systemmonitor.core.api.events.Event;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventLogger;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventLoggerRemote;

/**
 * This is the central event logger implementation.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
@Singleton
public class EventLoggerBean implements EventLogger, EventLoggerRemote {

    private static final long serialVersionUID = -4162895953533068913L;

    public static final String EVENTS_TABLE_NAME = "system_monitor_events";
    private static final String LOG_EVENT_STATEMENT = "UPSERT INTO " + EVENTS_TABLE_NAME
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

    @Inject
    @SystemMonitorConnection
    private Connection connection;

    private PreparedStatement preparedLogEventStatement = null;

    @PostConstruct
    public void prepareStatements() {
	try {
	    preparedLogEventStatement = connection.prepareStatement(LOG_EVENT_STATEMENT);
	    logEvent(EventLoggerEvents.createStartEvent());
	} catch (SQLException e) {
	    throw new RuntimeException("Could not prepare statement for event logger.", e);
	}
    }

    @PreDestroy
    public void disconnect() {
	logEvent(EventLoggerEvents.createStopEvent());
    }

    private String getStackTrace(Throwable throwable) {
	try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(byteArrayOutputStream)) {
	    throwable.printStackTrace(stream);
	    return byteArrayOutputStream.toString();
	} catch (IOException e) {
	    logger.warn("Could not read stacktrace into PrintStream. This should never happen.", e);
	    return "<stacktrace conversion errored>";
	}
    }

    @Override
    public void logEvent(Event event) {
	writeToLogger(event);
	writeToHBase(event);
    }

    protected void writeToHBase(Event event) {
	try {
	    Throwable throwable = event.getThrowable();
	    String exceptionMessage = null;
	    String exceptionStacktrace = null;
	    if (throwable != null) {
		exceptionMessage = throwable.getMessage();
		exceptionStacktrace = getStackTrace(throwable);
	    }
	    if (!connection.isClosed()) {
		String email = event.getUserEmail() != null ? event.getUserEmail().getAddress() : null;
		preparedLogEventStatement.setTime(1, new Time(event.getTime().getTime()));
		preparedLogEventStatement.setString(2, event.getComponent());
		preparedLogEventStatement.setLong(3, event.getEventId());
		preparedLogEventStatement.setString(4, server);
		preparedLogEventStatement.setString(5, event.getType().name());
		preparedLogEventStatement.setString(6, event.getSeverity().name());
		preparedLogEventStatement.setString(7, event.getMessage());
		preparedLogEventStatement.setString(8, email);
		preparedLogEventStatement.setLong(9, event.getUserId());
		preparedLogEventStatement.setString(10, event.getClientHostname());
		preparedLogEventStatement.setString(11, exceptionMessage);
		preparedLogEventStatement.setString(12, exceptionStacktrace);
		preparedLogEventStatement.execute();
		connection.commit();
	    } else {
		throw new IllegalStateException("Connection to HBase was closed already!");
	    }
	} catch (SQLException e) {
	    throw new RuntimeException("Could not insert event into event log.", e);
	}
    }

    protected void writeToLogger(Event event) {
	String message = "=====| " + event.getSeverity() + " event: " + event.getMessage() + " (time=" + event.getTime()
		+ ";type=" + event.getType() + ") |=====";
	Throwable throwable = event.getThrowable();
	switch (event.getSeverity()) {
	case INFO:
	    logger.info(message);
	    break;
	case WARNING:
	    if (throwable == null) {
		logger.warn(message);
	    } else {
		logger.warn(message, throwable);
	    }
	    break;
	case ERROR:
	case FATAL:
	    logger.error(message, throwable);
	    break;
	}
    }
}
