package com.puresoltechnologies.purifinity.server.database.cassandra.utils;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;

/**
 * Manages the actual connection to Cassandra. The methods connect and
 * disconnect need to be called by a lifecycle component like the OSGi container
 * of JavaEE container to establish the actual connection.
 * 
 * @author Rick-Rainer Ludwig
 */
@Singleton
public class CassandraPreparedStatements {

	private final Map<Session, Map<String, PreparedStatement>> preparedStatements = new HashMap<>();

	public PreparedStatement getPreparedStatement(Session session,
			String statement) {
		if (session == null) {
			throw new IllegalArgumentException("Session must no be null");
		}
		if (statement == null) {
			throw new IllegalArgumentException("Statement must no be null");
		}
		PreparedStatement preparedStatement = null;
		Map<String, PreparedStatement> sessionStatements = preparedStatements
				.get(session);
		if (sessionStatements != null) {
			preparedStatement = sessionStatements.get(preparedStatement);
		}
		if (preparedStatement == null) {
			preparedStatement = prepareStatement(session, statement);
		}
		return preparedStatement;
	}

	private synchronized PreparedStatement prepareStatement(Session session,
			String statement) {
		Map<String, PreparedStatement> sessionStatements = preparedStatements
				.get(session);
		if (sessionStatements == null) {
			sessionStatements = new HashMap<>();
			preparedStatements.put(session, sessionStatements);
		}
		PreparedStatement preparedStatement = sessionStatements.get(statement);
		if (preparedStatement == null) {
			preparedStatement = session.prepare(statement);
			sessionStatements.put(statement, preparedStatement);
		}
		return preparedStatement;
	}

}
