package com.puresoltechnologies.purifinity.server.ddl;

import java.util.HashSet;
import java.util.Set;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.genesis.commons.SequenceMetadata;
import com.puresoltechnologies.genesis.commons.cassandra.CassandraUtils;
import com.puresoltechnologies.genesis.commons.cassandra.ReplicationStrategy;
import com.puresoltechnologies.genesis.transformation.cassandra.CassandraCQLTransformationStep;
import com.puresoltechnologies.genesis.transformation.cassandra.CassandraStandardMigrations;
import com.puresoltechnologies.genesis.transformation.cassandra.CassandraTransformationSequence;
import com.puresoltechnologies.genesis.transformation.spi.ComponentTransformator;
import com.puresoltechnologies.genesis.transformation.spi.TransformationSequence;
import com.puresoltechnologies.purifinity.server.database.cassandra.ProcessStatesKeyspace;
import com.puresoltechnologies.versioning.Version;
import com.puresoltechnologies.versioning.VersionRange;

public class ProcessStatesDatabaseTransformator implements
		ComponentTransformator {

	private static final String PROCESSES_KEYSPACE = ProcessStatesKeyspace.NAME;

	private static final String ANALYSIS_PROCESS_TABLE = "analysis_process";

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
				.createKeyspace(sequence, PROCESSES_KEYSPACE,
						"Rick-Rainer Ludwig", "Keyspace for process states",
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
				CASSANDRA_HOST, CASSANDRA_CQL_PORT, PROCESSES_KEYSPACE,
				metadata);
		sequence.appendTransformation(new CassandraCQLTransformationStep(
				sequence,
				"Rick-Rainer Ludwig",
				"CREATE TABLE "
						+ ANALYSIS_PROCESS_TABLE
						+ " (started timestamp, project_uuid uuid, run_uuid uuid, state text, last_progress timestamp,"
						+ "PRIMARY KEY(project_uuid));",
				"Keeps states about the running analysis processes."));

		return sequence;
	}

	@Override
	public void dropAll() {
		try (Cluster cluster = CassandraUtils.connectCluster()) {
			try (Session session = cluster.connect()) {
				session.execute("DROP KEYSPACE " + ProcessStatesKeyspace.NAME);
			}
		}
	}
}
