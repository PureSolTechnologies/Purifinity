package com.puresoltechnologies.purifinity.framework.store.db;

import java.util.Date;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.TableMetadata;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.CassandraUtils;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.ReplicationStrategy;

/**
 * Manages the actual connection to Cassandra. The methods connect and
 * disconnect need to be called by a lifecycle component like the OSGi container
 * of JavaEE container to establish the actual connection.
 * 
 * @author Rick-Rainer Ludwig
 */
public class CassandraConnection {

	private static final String CASSANDRA_HOST = "localhost";
	private static final int CASSANDRA_CQL_PORT = 9042;

	private static final String ANALYSIS_KEYSPACE = "analysis_store";
	private static final String EVALUATION_KEYSPACE = "evaluation_store";

	private static final String CHANGELOG_TABLE = "changelog";
	private static final String ANALYSIS_FILES_TABLE = "files";
	private static final String ANALYSIS_DIRECTORIES_TABLE = "directories";

	private static Cluster cluster = null;
	private static Session analysisSession = null;
	private static Session evaluationSession = null;

	public static String getHost() {
		return CASSANDRA_HOST;
	}

	public static int getPort() {
		return CASSANDRA_CQL_PORT;
	}

	public static void connect() throws CassandraConnectionException {
		if (cluster != null) {
			throw new CassandraConnectionException(
					"Cassandra database was already connected.");
		}
		cluster = Cluster.builder().addContactPoints(CASSANDRA_HOST)
				.withPort(CASSANDRA_CQL_PORT).build();
		checkAndCreateKeyspaces();
		analysisSession = cluster.connect(ANALYSIS_KEYSPACE);
		checkAndCreateAnalysisTables();
		evaluationSession = cluster.connect(EVALUATION_KEYSPACE);
		checkAndCreateEvaluationTables();
	}

	public static void disconnect() throws CassandraConnectionException {
		if (cluster == null) {
			throw new CassandraConnectionException(
					"Cassandra database has not been connected, yet.");
		}
		evaluationSession.shutdown();
		evaluationSession = null;
		analysisSession.shutdown();
		analysisSession = null;
		cluster.shutdown();
		cluster = null;
	}

	public static boolean isConnected() {
		return cluster != null;
	}

	public Cluster getCluster() {
		return cluster;
	}

	public Session getAnalysisSession() {
		return analysisSession;
	}

	public Session getEvaluationSession() {
		return evaluationSession;
	}

	private static void checkAndCreateKeyspaces() {
		Session session = cluster.connect();
		try {
			if (cluster.getMetadata().getKeyspace(ANALYSIS_KEYSPACE) == null) {
				CassandraUtils.createKeyspace(session, ANALYSIS_KEYSPACE,
						ReplicationStrategy.SIMPLE_STRATEGY, 1);
			}
			if (cluster.getMetadata().getKeyspace(EVALUATION_KEYSPACE) == null) {
				CassandraUtils.createKeyspace(session, EVALUATION_KEYSPACE,
						ReplicationStrategy.SIMPLE_STRATEGY, 1);
			}
		} finally {
			session.shutdown();
		}
	}

	private static void checkAndCreateAnalysisTables() {
		KeyspaceMetadata analysisKeyspace = cluster.getMetadata().getKeyspace(
				ANALYSIS_KEYSPACE);
		TableMetadata changelogTable = analysisKeyspace
				.getTable(CHANGELOG_TABLE);
		if (changelogTable == null) {
			analysisSession.execute("CREATE TABLE " + CHANGELOG_TABLE
					+ " (version int, utc timestamp, PRIMARY KEY(version));");
			analysisSession.execute("INSERT INTO " + CHANGELOG_TABLE
					+ " (version, utc) VALUES (1, " + new Date().getTime()
					+ ");");
		}
		TableMetadata filesTable = analysisKeyspace
				.getTable(ANALYSIS_FILES_TABLE);
		if (filesTable == null) {
			analysisSession
					.execute("CREATE TABLE "
							+ ANALYSIS_FILES_TABLE
							+ " (hashId varchar, raw varchar, parser_tree blob, PRIMARY KEY(hashId));");
		}
		TableMetadata directoriesTable = analysisKeyspace
				.getTable(ANALYSIS_DIRECTORIES_TABLE);
		if (directoriesTable == null) {
			analysisSession.execute("CREATE TABLE "
					+ ANALYSIS_DIRECTORIES_TABLE
					+ " (hashId varchar, PRIMARY KEY(hashId));");
		}
	}

	private static void checkAndCreateEvaluationTables() {

	}
}
