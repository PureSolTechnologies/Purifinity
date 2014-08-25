package com.puresoltechnologies.purifinity.server.ddl.evaluationservice;

import static com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraMigration.createKeyspace;
import static com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraMigration.createTable;

import com.puresoltechnologies.commons.misc.Version;
import com.puresoltechnologies.purifinity.server.database.cassandra.EvaluationStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.ReplicationStrategy;
import com.puresoltechnologies.purifinity.server.database.migration.DatabaseMigrator;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;

public class EvaluationServiceSchema {

	private static final String EVALUATION_KEYSPACE = EvaluationStoreKeyspace.NAME;

	private static final String EVALUATION_METRICS_TABLE = "metrics";

	private static final String EVALUATION_PARAMETERS_TABLE = "parameters";
	private static final String EVALUATION_FILE_METRICS_TABLE = "file_metrics";
	private static final String EVALUATION_DIRECTORY_METRICS_TABLE = "directory_metrics";
	private static final String EVALUATION_PROJECT_METRICS_TABLE = "project_metrics";

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
		migrator.registerMigrationStep(createTable(
				EVALUATION_KEYSPACE,
				v100,
				"Rick-Rainer Ludwig",
				"This table contains all available parameters of all evaluators.",
				"CREATE TABLE " + EVALUATION_PARAMETERS_TABLE
						+ " (time timestamp, " + "evaluator_id varchar, "
						+ "parameter_name varchar, "
						+ "parameter_unit varchar, "
						+ "level_of_measurement varchar, "
						+ "parameter_description varchar, " + "value double, "
						+ "PRIMARY KEY(evaluator_id, parameter_name))"));
		migrator.registerMigrationStep(createTable(
				EVALUATION_KEYSPACE,
				v100,
				"Rick-Rainer Ludwig",
				"Keeps metrics for single files and their code ranges.",
				"CREATE TABLE "
						+ EVALUATION_FILE_METRICS_TABLE
						+ " (time timestamp, "
						+ "hashid varchar, "
						+ "source_code_location varchar, "
						+ "code_range_type varchar, "
						+ "code_range_name varchar, "
						+ "evaluator_id varchar, "
						+ "parameter_name varchar, "
						+ "value double, "
						+ "PRIMARY KEY(hashid, evaluator_id, parameter_name, code_range_type, code_range_name))"));
		migrator.registerMigrationStep(createTable(EVALUATION_KEYSPACE, v100,
				"Rick-Rainer Ludwig",
				"Keeps metrics for single files and their code ranges.",
				"CREATE TABLE " + EVALUATION_DIRECTORY_METRICS_TABLE
						+ " (time timestamp, " + "hashid varchar, "
						+ "evaluator_id varchar, " + "parameter_name varchar, "
						+ "value double, "
						+ "PRIMARY KEY(hashid, evaluator_id, parameter_name))"));
		migrator.registerMigrationStep(createTable(
				EVALUATION_KEYSPACE,
				v100,
				"Rick-Rainer Ludwig",
				"Keeps metrics for single files and their code ranges.",
				"CREATE TABLE "
						+ EVALUATION_PROJECT_METRICS_TABLE
						+ " (time timestamp, "
						+ "project_uuid UUID, "
						+ "run_uuid UUID, "
						+ "evaluator_id varchar, "
						+ "parameter_name varchar, "
						+ "value double, "
						+ "PRIMARY KEY(project_uuid, run_uuid, evaluator_id, parameter_name))"));
		migrator.registerMigrationStep(createTable(
				EVALUATION_KEYSPACE,
				v100,
				"Rick-Rainer Ludwig",
				"Keeps the metrics in a big table for efficient retrieval.",
				"CREATE TABLE "
						+ EVALUATION_METRICS_TABLE
						+ " (time timestamp, "
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
