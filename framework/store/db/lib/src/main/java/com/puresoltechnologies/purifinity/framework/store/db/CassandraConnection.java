package com.puresoltechnologies.purifinity.framework.store.db;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.CassandraUtils;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.MigrationException;

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

	private static final String CASSANDRA_HOST = "localhost";
	private static final int CASSANDRA_CQL_PORT = 9042;

	private static final Builder clusterBuilder = Cluster.builder();

	private static Cluster cluster = null;
	private static Session analysisSession = null;
	private static Session evaluationSession = null;

	private static Map<String, PreparedStatement> preparedStatements = new HashMap<>();

	public static String getHost() {
		return CASSANDRA_HOST;
	}

	public static int getPort() {
		return CASSANDRA_CQL_PORT;
	}

	public static void connect() throws CassandraConnectionException {
		if (cluster != null) {
			return;
		}
		logger.info("Connect to Cassandra database...");
		cluster = clusterBuilder.addContactPoints(CASSANDRA_HOST)
				.withPort(CASSANDRA_CQL_PORT).build();
		migrate();
		analysisSession = CassandraUtils.connectToCluster(cluster,
				CassandraElementNames.ANALYSIS_KEYSPACE);
		evaluationSession = CassandraUtils.connectToCluster(cluster,
				CassandraElementNames.EVALUATION_KEYSPACE);
		logger.info("Cassandra database connected.");
	}

	private static void migrate() throws CassandraConnectionException {
		try {
			CassandraSchema.migrate(cluster);
		} catch (MigrationException e) {
			throw new CassandraConnectionException(
					"Could not migrate Cassandra.", e);
		}
	}

	public static void disconnect() throws CassandraConnectionException {
		if (cluster != null) {
			preparedStatements.clear();
			evaluationSession.shutdown();
			evaluationSession = null;
			analysisSession.shutdown();
			analysisSession = null;
			cluster.shutdown();
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
		PreparedStatement preparedStatement = preparedStatements.get(statement);
		if (preparedStatement == null) {
			synchronized (preparedStatements) {
				preparedStatement = preparedStatements.get(statement);
				if (preparedStatement == null) {
					preparedStatement = session.prepare(statement);
					preparedStatements.put(statement, preparedStatement);
				}
			}
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
