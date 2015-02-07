package com.puresoltechnologies.purifinity.server.systemmonitor.core.impl.events;

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
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.systemmonitor.core.impl.SystemMonitorKeyspace;
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

    public static final String EVENTS_TABLE_NAME = "events";
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

    @Inject
    @SystemMonitorKeyspace
    private Session session = null;

    private PreparedStatement preparedLogEventStatement = null;

    @PostConstruct
    public void prepareStatements() {
	preparedLogEventStatement = session.prepare(LOG_EVENT_STATEMENT);
	logEvent(EventLoggerEvents.createStartEvent());
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
	    logger.warn(
		    "Could not read stacktrace into PrintStream. This should never happen.",
		    e);
	    return "<stacktrace conversion errored>";
	}
    }

    @Override
    public void logEvent(Event event) {
	writeToLogger(event);
	writeToCassandra(event);
    }

    protected void writeToCassandra(Event event) {
	Throwable throwable = event.getThrowable();
	String exceptionMessage = null;
	String exceptionStacktrace = null;
	if (throwable != null) {
	    exceptionMessage = throwable.getMessage();
	    exceptionStacktrace = getStackTrace(throwable);
	}
	if (!session.isClosed()) {
	    String email = event.getUserEmail() != null ? event.getUserEmail()
		    .getAddress() : null;
	    BoundStatement boundStatement = preparedLogEventStatement.bind(
		    event.getTime(), event.getComponent(), event.getEventId(),
		    server, event.getType().name(), event.getSeverity().name(),
		    event.getMessage(), email, event.getUserId(),
		    event.getClientHostname(), exceptionMessage,
		    exceptionStacktrace);
	    session.execute(boundStatement);
	} else {
	    throw new IllegalStateException(
		    "Connection Cassandra was closed already!");
	}
    }

    protected void writeToLogger(Event event) {
	String message = "=====| " + event.getSeverity() + " event: "
		+ event.getMessage() + " (time=" + event.getTime() + ";type="
		+ event.getType() + ") |=====";
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
