package com.puresoltechnologies.purifinity.server.metrics.ddl;

import java.util.HashSet;
import java.util.Set;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.genesis.commons.ProvidedVersionRange;
import com.puresoltechnologies.genesis.commons.SequenceMetadata;
import com.puresoltechnologies.genesis.commons.cassandra.CassandraUtils;
import com.puresoltechnologies.genesis.commons.cassandra.ReplicationStrategy;
import com.puresoltechnologies.genesis.transformation.cassandra.CassandraCQLTransformationStep;
import com.puresoltechnologies.genesis.transformation.cassandra.CassandraStandardMigrations;
import com.puresoltechnologies.genesis.transformation.cassandra.CassandraTransformationSequence;
import com.puresoltechnologies.genesis.transformation.spi.ComponentTransformator;
import com.puresoltechnologies.genesis.transformation.spi.TransformationSequence;
import com.puresoltechnologies.versioning.Version;

public class McCabeMetricEvaluatorDatabaseTransformator implements
	ComponentTransformator {

    public static final String MCCABE_METRICS_KEYSPACE_NAME = "mccabe_metrics";
    public static final String CASSANDRA_HOST = "localhost";
    public static final int CASSANDRA_CQL_PORT = 9042;

    private static final String FILE_RESULTS_TABLE = "file_results";
    private static final String DIRECTORY_RESULTS_TABLE = "directory_results";
    private static final String PROJECT_RESULTS_TABLE = "project_results";

    @Override
    public String getComponentName() {
	return "McCabeMetricEvaluator";
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
	ProvidedVersionRange versionRange = new ProvidedVersionRange(
		targetVersion, null);
	SequenceMetadata metadata = new SequenceMetadata(getComponentName(),
		startVersion, versionRange);
	CassandraTransformationSequence sequence = new CassandraTransformationSequence(
		CASSANDRA_HOST, CASSANDRA_CQL_PORT, metadata);
	sequence.appendTransformation(CassandraStandardMigrations
		.createKeyspace(
			sequence,
			MCCABE_METRICS_KEYSPACE_NAME,
			"Rick-Rainer Ludwig",
			"This keyspace keeps the detailed results of McCabe Metric evaluations.",
			ReplicationStrategy.SIMPLE_STRATEGY, 1));
	return sequence;
    }

    private TransformationSequence migrateVersion0_3_0() {
	Version startVersion = new Version(0, 3, 0, "pre");
	Version targetVersion = new Version(0, 3, 0);
	ProvidedVersionRange versionRange = new ProvidedVersionRange(
		targetVersion, null);
	SequenceMetadata metadata = new SequenceMetadata(getComponentName(),
		startVersion, versionRange);
	CassandraTransformationSequence sequence = new CassandraTransformationSequence(
		CASSANDRA_HOST, CASSANDRA_CQL_PORT,
		MCCABE_METRICS_KEYSPACE_NAME, metadata);

	sequence.appendTransformation(new CassandraCQLTransformationStep(
		sequence,
		"Rick-Rainer Ludwig",
		"CREATE TABLE "
			+ FILE_RESULTS_TABLE
			+ " (hashid varchar, "
			+ "evaluator_id varchar, "
			+ "source_code_location varchar, "
			+ "code_range_type varchar, "
			+ "code_range_name varchar, "
			+ "vg int, "
			+ "PRIMARY KEY(hashid, evaluator_id, code_range_type, code_range_name));",
		"Keeps directory results for McCabe Metric evaluator."));
	sequence.appendTransformation(new CassandraCQLTransformationStep(
		sequence, "Rick-Rainer Ludwig", "CREATE TABLE "
			+ DIRECTORY_RESULTS_TABLE + " (hashid varchar, "
			+ "evaluator_id varchar, " + "vg int, "
			+ "PRIMARY KEY(hashid, evaluator_id));",
		"Keeps directory results for McCabe Metric evaluator."));
	sequence.appendTransformation(new CassandraCQLTransformationStep(
		sequence, "Rick-Rainer Ludwig", "CREATE TABLE "
			+ PROJECT_RESULTS_TABLE + " (project_id ascii, "
			+ "evaluator_id varchar, " + "vg int, "
			+ "PRIMARY KEY(project_id, evaluator_id));",
		"Keeps project results for McCabe Metric evaluator."));

	return sequence;
    }

    @Override
    public void dropAll() {
	try (Cluster cluster = CassandraUtils.connectCluster()) {
	    try (Session session = cluster.connect()) {
		session.execute("DROP KEYSPACE IF EXISTS "
			+ MCCABE_METRICS_KEYSPACE_NAME);
	    }
	}

    }
}
