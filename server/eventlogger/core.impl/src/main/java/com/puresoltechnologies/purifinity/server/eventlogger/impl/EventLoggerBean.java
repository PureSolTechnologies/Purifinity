package com.puresoltechnologies.purifinity.server.eventlogger.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
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

	@Inject
	private Logger logger;

	@Inject
	private EventLoggerConnection eventLoggerConnection;

	private PreparedStatement logUserActionStatement;
	private PreparedStatement logUserExceptionStatement;
	private PreparedStatement logUserExceptionWithStackTraceStatement;
	private PreparedStatement logSystemEventStatement;
	private PreparedStatement logSystemExceptionStatement;
	private PreparedStatement logSystemExceptionWithStackTraceStatement;

	@PostConstruct
	public void createStatements() {
		Session session = eventLoggerConnection.getSession();
	}

	private String getStackTrace(Exception exception) {
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
			try (PrintStream stream = new PrintStream(byteArrayOutputStream)) {
				exception.printStackTrace(stream);
				String stackTrace = byteArrayOutputStream.toString();
				return stackTrace;
			}
		} catch (IOException e) {
			/*
			 * This should not happen...
			 */
			return "";
		}
	}

	public void writeLog(String key, Properties content) {
		Session session = eventLoggerConnection.getSession();
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
