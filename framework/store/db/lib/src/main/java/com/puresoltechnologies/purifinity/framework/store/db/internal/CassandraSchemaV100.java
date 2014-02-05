package com.puresoltechnologies.purifinity.framework.store.db.internal;

import com.datastax.driver.core.Cluster;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.CassandraMigration;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.MigrationException;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.ReplicationStrategy;
import com.puresoltechnologies.purifinity.framework.store.db.CassandraElementNames;

public class CassandraSchemaV100 {

	public static void migrate(Cluster cluster) throws MigrationException {
		checkAndCreateKeyspaces(cluster);
		checkAndCreateAnalysisTables(cluster);
		checkAndCreateEvaluationTables(cluster);
	}

	private static void checkAndCreateKeyspaces(Cluster cluster)
			throws MigrationException {
		CassandraMigration.createKeyspace(cluster,
				CassandraElementNames.ANALYSIS_KEYSPACE, "1.0.0",
				"Rick-Rainer Ludwig", "Keyspace for analysis information",
				ReplicationStrategy.SIMPLE_STRATEGY, 1);
		CassandraMigration.createKeyspace(cluster,
				CassandraElementNames.EVALUATION_KEYSPACE, "1.0.0",
				"Rick-Rainer Ludwig", "Keyspace for evaluation information",
				ReplicationStrategy.SIMPLE_STRATEGY, 1);
	}

	private static void checkAndCreateAnalysisTables(Cluster cluster)
			throws MigrationException {
		CassandraMigration
				.createTable(
						cluster,
						CassandraElementNames.ANALYSIS_KEYSPACE,
						"1.0.0",
						"Rick-Rainer Ludwig",
						"Keeps analysis information for analyzed and unanalyzed files and their raw data.",
						"CREATE TABLE "
								+ CassandraElementNames.ANALYSIS_FILES_TABLE
								+ " (hashid varchar, raw blob, size int, analysis blob, PRIMARY KEY(hashid));");

		CassandraMigration
				.createTable(
						cluster,
						CassandraElementNames.ANALYSIS_KEYSPACE,
						"1.0.0",
						"Rick-Rainer Ludwig",
						"Keeps settings of analysis projects.",
						"CREATE TABLE "
								+ CassandraElementNames.ANALYSIS_PROJECT_SETTINGS_TABLE
								+ " (uuid uuid, name varchar, description varchar, file_includes list<text>, file_excludes list<text>, location_includes list<text>, location_excludes list<text>, ignore_hidden boolean, repository_location map<text,text>, PRIMARY KEY(uuid));");

		CassandraMigration
				.createTable(
						cluster,
						CassandraElementNames.ANALYSIS_KEYSPACE,
						"1.0.0",
						"Rick-Rainer Ludwig",
						"Keeps settings of analysis runs.",
						"CREATE TABLE "
								+ CassandraElementNames.RUN_SETTINGS_TABLE
								+ " (uuid uuid, file_includes list<text>, file_excludes list<text>, location_includes list<text>, location_excludes list<text>, ignore_hidden boolean, PRIMARY KEY(uuid));");

		CassandraMigration
				.createTable(
						cluster,
						CassandraElementNames.ANALYSIS_KEYSPACE,
						"1.0.0",
						"Rick-Rainer Ludwig",
						"Keeps a cache for analysis file trees for performance optimization.",
						"CREATE TABLE "
								+ CassandraElementNames.ANALYSIS_FILE_TREE_CACHE
								+ " (uuid uuid, persisted_tree blob, PRIMARY KEY(uuid));");

	}

	private static void checkAndCreateEvaluationTables(Cluster cluster)
			throws MigrationException {
		CassandraMigration
				.createTable(
						cluster,
						CassandraElementNames.EVALUATION_KEYSPACE,
						"1.0.0",
						"Rick-Rainer Ludwig",
						"Keeps settings of analysis projects.",
						"CREATE TABLE "
								+ CassandraElementNames.EVALUATION_FILES_TABLE
								+ " (hashid varchar, resultsClass varchar, results blob, PRIMARY KEY(hashid, resultsClass));");
		CassandraMigration
				.createTable(
						cluster,
						CassandraElementNames.EVALUATION_KEYSPACE,
						"1.0.0",
						"Rick-Rainer Ludwig",
						"Keeps settings of analysis projects.",
						"CREATE TABLE "
								+ CassandraElementNames.EVALUATION_DIRECTORIES_TABLE
								+ " (hashid varchar, resultsClass varchar, results blob, PRIMARY KEY(hashid, resultsClass));");
		CassandraMigration
				.createTable(
						cluster,
						CassandraElementNames.EVALUATION_KEYSPACE,
						"1.0.0",
						"Rick-Rainer Ludwig",
						"Keeps settings of analysis projects.",
						"CREATE TABLE "
								+ CassandraElementNames.EVALUATION_PROJECTS_TABLE
								+ " (uuid uuid, resultsClass varchar, results blob, PRIMARY KEY(uuid, resultsClass));");
		CassandraMigration
				.createTable(
						cluster,
						CassandraElementNames.EVALUATION_KEYSPACE,
						"1.0.0",
						"Rick-Rainer Ludwig",
						"Keeps the metrics in a big table for efficient retrieval.",
						"CREATE TABLE "
								+ CassandraElementNames.EVALUATION_METRICS_TABLE
								+ " (time timestamp, "
								+ "duration bigint, "
								+ "project uuid, "
								+ "analysis_run uuid, "
								+ "internal_directory varchar, "
								+ "file_name varchar, "
								+ "source_code_location varchar, "
								+ "language_name varchar, "
								+ "language_version varchar, "
								+ "evaluator_name varchar, "
								+ "code_range_name varchar, "
								+ "code_range_type varchar, "
								+ "quality varchar, "
								+ "quality_level float, "
								+ "name varchar, "
								+ "unit varchar, "
								+ "metric double, "
								+ "PRIMARY KEY(project, analysis_run, internal_directory, file_name, code_range_name, code_range_type, evaluator_name, name));");
	}

}
