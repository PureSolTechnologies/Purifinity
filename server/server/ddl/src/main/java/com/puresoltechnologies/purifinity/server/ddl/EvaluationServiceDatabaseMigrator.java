package com.puresoltechnologies.purifinity.server.ddl;

import static com.puresoltechnologies.purifinity.server.database.cassandra.migration.CassandraMigration.createKeyspace;
import static com.puresoltechnologies.purifinity.server.database.cassandra.migration.CassandraMigration.createTable;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.types.Version;
import com.puresoltechnologies.purifinity.server.database.cassandra.EvaluationStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.migration.CassandraMigratorConnector;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.ReplicationStrategy;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationMetadata;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationSequence;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationStep;

public class EvaluationServiceDatabaseMigrator {

	private static final String EVALUATION_KEYSPACE = EvaluationStoreKeyspace.NAME;

	private static final String EVALUATION_METRICS_TABLE = "metrics";

	private static final String EVALUATION_PARAMETERS_TABLE = "parameters";
	private static final String EVALUATION_FILE_METRICS_TABLE = "file_metrics";
	private static final String EVALUATION_DIRECTORY_METRICS_TABLE = "directory_metrics";
	private static final String EVALUATION_PROJECT_METRICS_TABLE = "project_metrics";

	private static final Version v100 = new Version(1, 0, 0);

	private final CassandraMigratorConnector connector;

	public EvaluationServiceDatabaseMigrator(
			CassandraMigratorConnector connector) throws MigrationException {
		this.connector = connector;
	}

	public void drop() {
		Cluster cluster = connector.getCluster();
		Session session = cluster.connect();
		try {
			session.execute("DROP KEYSPACE " + EvaluationStoreKeyspace.NAME);
		} finally {
			session.close();
		}
	}

	public MigrationSequence getSequence() throws MigrationException {
		MigrationSequence sequence = new MigrationSequence(
				new MigrationMetadata(v100, "Rick-Rainer Ludwig",
						"Evaluation Service", "", "Version " + v100
								+ " sequence."));
		sequence.registerMigrationSteps(checkAndCreateKeyspaces());
		sequence.registerMigrationSteps(checkAndCreateEvaluationTables());
		return sequence;
	}

	private List<MigrationStep> checkAndCreateKeyspaces()
			throws MigrationException {
		List<MigrationStep> steps = new ArrayList<>();
		steps.add(createKeyspace(connector, EVALUATION_KEYSPACE, v100,
				"Rick-Rainer Ludwig", "Keyspace for evaluation information",
				ReplicationStrategy.SIMPLE_STRATEGY, 1));
		return steps;
	}

	private List<MigrationStep> checkAndCreateEvaluationTables()
			throws MigrationException {
		List<MigrationStep> steps = new ArrayList<>();
		steps.add(createTable(
				connector,
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
		steps.add(createTable(
				connector,
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
		steps.add(createTable(connector, EVALUATION_KEYSPACE, v100,
				"Rick-Rainer Ludwig",
				"Keeps metrics for single files and their code ranges.",
				"CREATE TABLE " + EVALUATION_DIRECTORY_METRICS_TABLE
						+ " (time timestamp, " + "hashid varchar, "
						+ "evaluator_id varchar, " + "parameter_name varchar, "
						+ "value double, "
						+ "PRIMARY KEY(hashid, evaluator_id, parameter_name))"));
		steps.add(createTable(
				connector,
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
		steps.add(createTable(
				connector,
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
		return steps;
	}
}
