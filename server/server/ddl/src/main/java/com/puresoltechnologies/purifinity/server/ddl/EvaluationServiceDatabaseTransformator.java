package com.puresoltechnologies.purifinity.server.ddl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.genesis.commons.SequenceMetadata;
import com.puresoltechnologies.genesis.commons.cassandra.ReplicationStrategy;
import com.puresoltechnologies.genesis.transformation.cassandra.CassandraStandardMigrations;
import com.puresoltechnologies.genesis.transformation.cassandra.CassandraTransformationSequence;
import com.puresoltechnologies.genesis.transformation.spi.ComponentTransformator;
import com.puresoltechnologies.genesis.transformation.spi.TransformationSequence;
import com.puresoltechnologies.purifinity.server.database.cassandra.AnalysisStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.EvaluationStoreKeyspace;
import com.puresoltechnologies.versioning.Version;
import com.puresoltechnologies.versioning.VersionRange;

public class EvaluationServiceDatabaseTransformator implements
		ComponentTransformator {

	private static final String EVALUATION_KEYSPACE = EvaluationStoreKeyspace.NAME;

	private static final String EVALUATION_METRICS_TABLE = "metrics";

	private static final String EVALUATION_PARAMETERS_TABLE = "parameters";
	private static final String EVALUATION_FILE_METRICS_TABLE = "file_metrics";
	private static final String EVALUATION_DIRECTORY_METRICS_TABLE = "directory_metrics";
	private static final String EVALUATION_PROJECT_METRICS_TABLE = "project_metrics";

	private static final Version v100 = new Version(1, 0, 0);

	public void drop() {
		Cluster cluster = connector.getCluster();
		Session session = cluster.connect();
		try {
			session.execute("DROP KEYSPACE " + EvaluationStoreKeyspace.NAME);
		} finally {
			session.close();
		}
	}

	public static final String CASSANDRA_HOST = "localhost";
	public static final int CASSANDRA_CQL_PORT = 9042;

	@Override
	public String getComponentName() {
		return "AccountManager";
	}

	@Override
	public boolean isHostBased() {
		return false;
	}

	@Override
	public Set<TransformationSequence> getSequences() {
		Set<TransformationSequence> sequences = new HashSet<>();
		sequences.add(migrateVersion0_3_0_pre());
		sequences.add(migrateVersion0_3_0());
		return sequences;
	}

	/**
	 * This pre version is used to create the keyspace.
	 * 
	 * @return
	 */
	private TransformationSequence migrateVersion0_3_0_pre() {
		Version startVersion = new Version(0, 0, 0);
		Version targetVersion = new Version(0, 3, 0, "pre");
		VersionRange versionRange = new VersionRange(targetVersion, true, null,
				false);
		SequenceMetadata metadata = new SequenceMetadata(getComponentName(),
				startVersion, versionRange);
		CassandraTransformationSequence sequence = new CassandraTransformationSequence(
				CASSANDRA_HOST, CASSANDRA_CQL_PORT, metadata);

		sequence.appendTransformation(CassandraStandardMigrations
				.createKeyspace(sequence.getSession(), metadata,
						AnalysisStoreKeyspace.NAME, "Rick-Rainer Ludwig",
						"Keyspace for analysis information",
						ReplicationStrategy.SIMPLE_STRATEGY, 3));

		return sequence;
	}

	private TransformationSequence migrateVersion0_3_0() {
		Version startVersion = new Version(0, 3, 0, "pre");
		Version targetVersion = new Version(0, 3, 0);
		VersionRange versionRange = new VersionRange(targetVersion, true, null,
				false);
		SequenceMetadata metadata = new SequenceMetadata(getComponentName(),
				startVersion, versionRange);
		CassandraTransformationSequence sequence = new CassandraTransformationSequence(
				CASSANDRA_HOST, CASSANDRA_CQL_PORT, AnalysisStoreKeyspace.NAME,
				metadata);

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
