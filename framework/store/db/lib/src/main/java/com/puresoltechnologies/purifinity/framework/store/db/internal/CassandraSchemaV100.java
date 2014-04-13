package com.puresoltechnologies.purifinity.framework.store.db.internal;

import static com.puresoltechnologies.purifinity.framework.database.cassandra.utils.CassandraMigration.createKeyspace;
import static com.puresoltechnologies.purifinity.framework.database.cassandra.utils.CassandraMigration.createTable;

import com.puresoltechnologies.purifinity.framework.commons.utils.Version;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.ReplicationStrategy;
import com.puresoltechnologies.purifinity.framework.database.migration.DatabaseMigrator;
import com.puresoltechnologies.purifinity.framework.database.migration.MigrationException;
import com.puresoltechnologies.purifinity.framework.store.db.CassandraElementNames;

public class CassandraSchemaV100 {

	private static final Version v100 = new Version(1, 0, 0);

	public static void createSequence(DatabaseMigrator migrator)
			throws MigrationException {
		checkAndCreateKeyspaces(migrator);
		checkAndCreateAnalysisTables(migrator);
		checkAndCreateEvaluationTables(migrator);
	}

	private static void checkAndCreateKeyspaces(DatabaseMigrator migrator)
			throws MigrationException {
		migrator.registerMigrationStep(createKeyspace(
				CassandraElementNames.ANALYSIS_KEYSPACE, v100,
				"Rick-Rainer Ludwig", "Keyspace for analysis information",
				ReplicationStrategy.SIMPLE_STRATEGY, 1));
		migrator.registerMigrationStep(createKeyspace(
				CassandraElementNames.EVALUATION_KEYSPACE, v100,
				"Rick-Rainer Ludwig", "Keyspace for evaluation information",
				ReplicationStrategy.SIMPLE_STRATEGY, 1));
	}

	private static void checkAndCreateAnalysisTables(DatabaseMigrator migrator)
			throws MigrationException {
		migrator.registerMigrationStep(createTable(
				CassandraElementNames.ANALYSIS_KEYSPACE, v100,
				"Rick-Rainer Ludwig",
				"Keeps analysis information for analyzed and unanalyzed "
						+ "files and their raw data.", "CREATE TABLE "
						+ CassandraElementNames.ANALYSIS_FILES_TABLE
						+ " (hashid varchar, raw blob, size int, "
						+ "analysis blob, PRIMARY KEY(hashid));"));

		migrator.registerMigrationStep(createTable(
				CassandraElementNames.ANALYSIS_KEYSPACE, v100,
				"Rick-Rainer Ludwig", "Keeps settings of analysis projects.",
				"CREATE TABLE "
						+ CassandraElementNames.ANALYSIS_PROJECT_SETTINGS_TABLE
						+ " (project_uuid uuid, " + "name varchar, "
						+ "description varchar, "
						+ "file_includes list<text>, "
						+ "file_excludes list<text>, "
						+ "location_includes list<text>, "
						+ "location_excludes list<text>, "
						+ "ignore_hidden boolean, "
						+ "repository_location map<text,text>, "
						+ "PRIMARY KEY(project_uuid));"));

		migrator.registerMigrationStep(createTable(
				CassandraElementNames.ANALYSIS_KEYSPACE, v100,
				"Rick-Rainer Ludwig", "Keeps settings of analysis runs.",
				"CREATE TABLE " + CassandraElementNames.RUN_SETTINGS_TABLE
						+ " (run_uuid uuid, " + "file_includes list<text>, "
						+ "file_excludes list<text>, "
						+ "location_includes list<text>, "
						+ "location_excludes list<text>, "
						+ "ignore_hidden boolean, " + "PRIMARY KEY(run_uuid));"));

		migrator.registerMigrationStep(createTable(
				CassandraElementNames.ANALYSIS_KEYSPACE,
				v100,
				"Rick-Rainer Ludwig",
				"Keeps a cache for analysis file trees for performance optimization.",
				"CREATE TABLE "
						+ CassandraElementNames.ANALYSIS_FILE_TREE_CACHE
						+ " (run_uuid uuid, " + "persisted_tree blob, "
						+ "PRIMARY KEY(run_uuid));"));

	}

	private static void checkAndCreateEvaluationTables(DatabaseMigrator migrator)
			throws MigrationException {
		migrator.registerMigrationStep(createTable(
				CassandraElementNames.EVALUATION_KEYSPACE, v100,
				"Rick-Rainer Ludwig", "Keeps settings of analysis projects.",
				"CREATE TABLE " + CassandraElementNames.EVALUATION_FILES_TABLE
						+ " (hashid varchar, " + "resultsClass varchar, "
						+ "results blob, "
						+ "PRIMARY KEY(hashid, resultsClass));"));
		migrator.registerMigrationStep(createTable(
				CassandraElementNames.EVALUATION_KEYSPACE, v100,
				"Rick-Rainer Ludwig", "Keeps settings of analysis projects.",
				"CREATE TABLE "
						+ CassandraElementNames.EVALUATION_DIRECTORIES_TABLE
						+ " (hashid varchar, " + "resultsClass varchar, "
						+ "results blob, "
						+ "PRIMARY KEY(hashid, resultsClass));"));
		migrator.registerMigrationStep(createTable(
				CassandraElementNames.EVALUATION_KEYSPACE, v100,
				"Rick-Rainer Ludwig", "Keeps settings of analysis projects.",
				"CREATE TABLE "
						+ CassandraElementNames.EVALUATION_PROJECTS_TABLE
						+ " (project_uuid uuid, " + "resultsClass varchar, "
						+ "results blob, "
						+ "PRIMARY KEY(project_uuid, resultsClass));"));
		migrator.registerMigrationStep(createTable(
				CassandraElementNames.EVALUATION_KEYSPACE,
				v100,
				"Rick-Rainer Ludwig",
				"Keeps the metrics in a big table for efficient retrieval.",
				"CREATE TABLE "
						+ CassandraElementNames.EVALUATION_METRICS_TABLE
						+ " (time timestamp, "
						+ "duration bigint, "
						+ "project_uuid uuid, "
						+ "run_uuid uuid, "
						+ "hashid varchar, "
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
						+ "parameter_name varchar, "
						+ "parameter_unit varchar, "
						+ "numeric boolean, "
						+ "parameter_type varchar, "
						+ "numeric_value double, "
						+ "string_value varchar, "
						+ "level_of_measurement varchar, "
						+ "parameter_description varchar, "
						+ "PRIMARY KEY(project_uuid, run_uuid, evaluator_name, parameter_name, code_range_type, hashid, code_range_name));"));
	}
}
