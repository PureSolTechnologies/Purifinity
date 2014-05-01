package com.puresoltechnologies.purifinity.framework.store;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.CassandraUtils;

/**
 * Manages the actual connection to Cassandra. The methods connect and
 * disconnect need to be called by a lifecycle component like the OSGi container
 * of JavaEE container to establish the actual connection.
 * 
 * @author Rick-Rainer Ludwig
 */
public class CassandraConnection {

	private static final Logger logger = LoggerFactory
			.getLogger(CassandraConnection.class);

	public static final String CASSANDRA_HOST = "localhost";
	public static final int CASSANDRA_CQL_PORT = 9042;

	private static final Builder clusterBuilder = Cluster.builder();

	private static Cluster cluster = null;
	private static Session analysisSession = null;
	private static Session evaluationSession = null;

	private static Map<Session, Map<String, PreparedStatement>> preparedStatements = new HashMap<>();

	public static String getHost() {
		return CASSANDRA_HOST;
	}

	public static int getPort() {
		return CASSANDRA_CQL_PORT;
	}

	public static void connect() throws CassandraConnectionException {
		if (cluster == null) {
			logger.info("Connect to Cassandra database...");
			cluster = clusterBuilder.addContactPoints(CASSANDRA_HOST)
					.withPort(CASSANDRA_CQL_PORT).build();
			analysisSession = CassandraUtils.connectToCluster(cluster,
					CassandraElementNames.ANALYSIS_KEYSPACE);
			evaluationSession = CassandraUtils.connectToCluster(cluster,
					CassandraElementNames.EVALUATION_KEYSPACE);
			logger.info("Cassandra database connected.");
		}
	}

	public static void disconnect() {
		preparedStatements.clear();
		if (evaluationSession != null) {
			evaluationSession.close();
			evaluationSession = null;
		}
		if (analysisSession != null) {
			analysisSession.close();
			analysisSession = null;
		}
		if (cluster != null) {
			cluster.close();
			cluster = null;
		}
	}

	public static boolean isConnected() {
		return (cluster != null) && (analysisSession != null)
				&& (evaluationSession != null);
	}

	public static Cluster getCluster() {
		return cluster;
	}

	public static PreparedStatement getPreparedStatement(Session session,
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

	private static synchronized PreparedStatement prepareStatement(
			Session session, String statement) {
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

	public static Session getAnalysisSession() {
		return analysisSession;
	}

	public static Session getEvaluationSession() {
		return evaluationSession;
	}

}
