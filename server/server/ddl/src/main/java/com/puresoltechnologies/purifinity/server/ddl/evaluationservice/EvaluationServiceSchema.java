package com.puresoltechnologies.purifinity.server.ddl.evaluationservice;

import static com.puresoltechnologies.purifinity.framework.database.cassandra.utils.CassandraMigration.createKeyspace;
import static com.puresoltechnologies.purifinity.framework.database.cassandra.utils.CassandraMigration.createTable;

import com.puresoltechnologies.purifinity.framework.commons.utils.Version;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.ReplicationStrategy;
import com.puresoltechnologies.purifinity.framework.database.migration.DatabaseMigrator;
import com.puresoltechnologies.purifinity.framework.database.migration.MigrationException;

public class EvaluationServiceSchema {

	private static final String EVALUATION_KEYSPACE = "evaluation_store";

	private static final String EVALUATION_FILES_TABLE = "files";
	private static final String EVALUATION_DIRECTORIES_TABLE = "directories";
	private static final String EVALUATION_PROJECTS_TABLE = "projects";
	private static final String EVALUATION_METRICS_TABLE = "metrics";

	private static final Version v100 = new Version(1, 0, 0);

	public static void createSequence(DatabaseMigrator migrator)
			throws MigrationException {
		checkAndCreateKeyspaces(migrator);
		checkAndCreateEvaluationTables(migrator);
	}

	private static void checkAndCreateKeyspaces(DatabaseMigrator migrator)
			throws MigrationException {
		migrator.registerMigrationStep(createKeyspace(EVALUATION_KEYSPACE,
				v100, "Rick-Rainer Ludwig",
				"Keyspace for evaluation information",
				ReplicationStrategy.SIMPLE_STRATEGY, 1));
	}

	private static void checkAndCreateEvaluationTables(DatabaseMigrator migrator)
			throws MigrationException {
		migrator.registerMigrationStep(createTable(EVALUATION_KEYSPACE, v100,
				"Rick-Rainer Ludwig", "Keeps settings of analysis projects.",
				"CREATE TABLE " + EVALUATION_FILES_TABLE + " (hashid varchar, "
						+ "resultsClass varchar, " + "results blob, "
						+ "PRIMARY KEY(hashid, resultsClass));"));
		migrator.registerMigrationStep(createTable(EVALUATION_KEYSPACE, v100,
				"Rick-Rainer Ludwig", "Keeps settings of analysis projects.",
				"CREATE TABLE " + EVALUATION_DIRECTORIES_TABLE
						+ " (hashid varchar, " + "resultsClass varchar, "
						+ "results blob, "
						+ "PRIMARY KEY(hashid, resultsClass));"));
		migrator.registerMigrationStep(createTable(EVALUATION_KEYSPACE, v100,
				"Rick-Rainer Ludwig", "Keeps settings of analysis projects.",
				"CREATE TABLE " + EVALUATION_PROJECTS_TABLE
						+ " (project_uuid uuid, " + "resultsClass varchar, "
						+ "results blob, "
						+ "PRIMARY KEY(project_uuid, resultsClass));"));
		migrator.registerMigrationStep(createTable(
				EVALUATION_KEYSPACE,
				v100,
				"Rick-Rainer Ludwig",
				"Keeps the metrics in a big table for efficient retrieval.",
				"CREATE TABLE "
						+ EVALUATION_METRICS_TABLE
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
