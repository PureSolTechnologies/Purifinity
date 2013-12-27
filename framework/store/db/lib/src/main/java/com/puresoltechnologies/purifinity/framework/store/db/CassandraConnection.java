package com.puresoltechnologies.purifinity.framework.store.db;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.PreparedStatement;
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

	public static final String ANALYSIS_KEYSPACE = "analysis_store";
	public static final String EVALUATION_KEYSPACE = "evaluation_store";

	private static final String CHANGELOG_TABLE = "changelog";

	public static final String ANALYSIS_FILES_TABLE = "files";
	public static final String ANALYSIS_DIRECTORIES_TABLE = "directories";
	public static final String ANALYSIS_PROJECT_SETTINGS_TABLE = "project_settings";
	public static final String RUN_SETTINGS_TABLE = "run_settings";

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
		preparedStatements.clear();
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

	public static Cluster getCluster() {
		return cluster;
	}

	public static PreparedStatement getPreparedStatement(Session session,
			String id, String statement) {
		PreparedStatement preparedStatement = preparedStatements.get(id);
		if (preparedStatement == null) {
			synchronized (preparedStatements) {
				preparedStatement = preparedStatements.get(id);
				if (preparedStatement == null) {
					preparedStatement = session.prepare(statement);
					preparedStatements.put(id, preparedStatement);
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
							+ " (hashid varchar, raw blob, analysis blob, PRIMARY KEY(hashid));");
		}
		TableMetadata directoriesTable = analysisKeyspace
				.getTable(ANALYSIS_DIRECTORIES_TABLE);
		if (directoriesTable == null) {
			analysisSession.execute("CREATE TABLE "
					+ ANALYSIS_DIRECTORIES_TABLE
					+ " (hashid varchar, PRIMARY KEY(hashid));");
		}
		TableMetadata projectSettingsTable = analysisKeyspace
				.getTable(ANALYSIS_PROJECT_SETTINGS_TABLE);
		if (projectSettingsTable == null) {
			analysisSession
					.execute("CREATE TABLE "
							+ ANALYSIS_PROJECT_SETTINGS_TABLE
							+ " (uuid uuid, name varchar, description varchar, file_includes list<text>, file_excludes list<text>, location_includes list<text>, location_excludes list<text>, ignore_hidden boolean, repository_location map<text,text>, PRIMARY KEY(uuid));");
		}
		TableMetadata runSettingsTable = analysisKeyspace
				.getTable(RUN_SETTINGS_TABLE);
		if (runSettingsTable == null) {
			analysisSession
					.execute("CREATE TABLE "
							+ RUN_SETTINGS_TABLE
							+ " (uuid uuid, file_includes list<text>, file_excludes list<text>, location_includes list<text>, location_excludes list<text>, ignore_hidden boolean, PRIMARY KEY(uuid));");
		}
	}

	private static void checkAndCreateEvaluationTables() {

	}

}
