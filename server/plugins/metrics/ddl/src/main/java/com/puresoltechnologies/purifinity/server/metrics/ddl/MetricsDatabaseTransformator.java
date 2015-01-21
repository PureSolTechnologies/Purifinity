package com.puresoltechnologies.purifinity.server.metrics.ddl;

import java.util.Set;

import com.puresoltechnologies.genesis.commons.TransformationMetadata;
import com.puresoltechnologies.genesis.commons.cassandra.ReplicationStrategy;
import com.puresoltechnologies.genesis.transformation.cassandra.CassandraStandardMigrations;
import com.puresoltechnologies.genesis.transformation.spi.TransformationSequence;
import com.puresoltechnologies.versioning.Version;

public class MetricsDatabaseTransformator implements Transformator {

	public static final String HALSTEAD_EVALUATOR_KEYSPACE_NAME = "halstead_evaluator";
	public static final String CASSANDRA_HOST = "localhost";
	public static final int CASSANDRA_CQL_PORT = 9042;
	public static final String RESULTS_TABLE_NAME = "results";

	private static final Version V_1_0_0 = new Version(1, 0, 0);

	private static final String FILE_RESULTS_TABLE = "file_results";
	private static final String DIRECTORY_RESULTS_TABLE = "directory_results";
	private static final String PROJECT_RESULTS_TABLE = "project_results";

	private final UniversalMigratorConnector connector;

	protected MetricsDatabaseTransformator(UniversalMigratorConnector connector) {
		this.connector = connector;
	}

	@Override
	public Set<TransformationSequence> getSequences() {
		// TODO Auto-generated method stub
		return null;
	}

	private TransformationSequence migrateVersion100()
			throws MigrationException {
		TransformationSequence sequence = new TransformationSequence(
				new TransformationMetadata(V_1_0_0, "Rick-Rainer Ludwig",
						"Metrics Plugin", "", "Version " + V_1_0_0
								+ " sequence."));
		sequence.registerMigrationStep(CassandraStandardMigrations.createKeyspace(
				connector, HALSTEAD_EVALUATOR_KEYSPACE_NAME, V_1_0_0,
				"Rick-Rainer Ludwig", "Keeps the user passwords.",
				ReplicationStrategy.SIMPLE_STRATEGY, 3));
		sequence.registerMigrationStep(createTable(
				connector,
				HALSTEAD_EVALUATOR_KEYSPACE_NAME,
				V_1_0_0,
				"Rick-Rainer Ludwig",
				"Keeps directory results for Halstead evaluator.",
				"CREATE TABLE "
						+ FILE_RESULTS_TABLE
						+ " (hashid varchar, "
						+ "code_range_type varchar, "
						+ "code_range_name varchar, "
						+ "operators map<text,int>, "
						+ "operands map<text,int>, "
						+ "PRIMARY KEY(hashid, code_range_type, code_range_name));"));
		sequence.registerMigrationStep(createTable(connector,
				HALSTEAD_EVALUATOR_KEYSPACE_NAME, V_1_0_0,
				"Rick-Rainer Ludwig",
				"Keeps directory results for Halstead evaluator.",
				"CREATE TABLE " + DIRECTORY_RESULTS_TABLE
						+ " (hashid varchar, " + "operators map<text,int>, "
						+ "operands map<text,int>, " + "PRIMARY KEY(hashid));"));
		sequence.registerMigrationStep(createTable(connector,
				HALSTEAD_EVALUATOR_KEYSPACE_NAME, V_1_0_0,
				"Rick-Rainer Ludwig",
				"Keeps project results for Halstead evaluator.",
				"CREATE TABLE " + PROJECT_RESULTS_TABLE
						+ " (project_uuid uuid, " + "operators map<text,int>, "
						+ "operands map<text,int>, "
						+ "PRIMARY KEY(project_uuid));"));
		return sequence;
	}

	public static void main(String[] args) throws Exception {
		try (CassandraMigratorConnector connector = new CassandraMigratorConnector(
				CASSANDRA_HOST, CASSANDRA_CQL_PORT)) {
			MetricsDatabaseTransformator metricsDatabaseSchema = new MetricsDatabaseTransformator(
					connector);
			try (Migrator migrator = new Migrator()) {
				migrator.runMigration(metricsDatabaseSchema.migrateVersion100());
			}
		}
	}
}
