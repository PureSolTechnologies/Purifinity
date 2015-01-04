package com.puresoltechnologies.purifinity.server.ddl;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.versioning.Version;
import com.puresoltechnologies.genesis.commons.cassandra.ReplicationStrategy;
import com.puresoltechnologies.genesis.transformation.spi.Transformator;
import com.puresoltechnologies.purifinity.server.database.cassandra.ProcessStatesKeyspace;

public class ProcessStatesDatabaseTransformator implements Transformator {

	private static final String PROCESSES_KEYSPACE = ProcessStatesKeyspace.NAME;

	private static final String ANALYSIS_PROCESS_TABLE = "analysis_process";

	private static final Version v100 = new Version(1, 0, 0);

	private final CassandraMigratorConnector connector;

	public ProcessStatesDatabaseTransformator(
			CassandraMigratorConnector connector) throws MigrationException {
		this.connector = connector;
	}

	public void drop() {
		Cluster cluster = connector.getCluster();
		Session session = cluster.connect();
		try {
			session.execute("DROP KEYSPACE " + ProcessStatesKeyspace.NAME);
		} finally {
			session.close();
		}
	}

	public MigrationSequence getSequence() throws MigrationException {
		MigrationSequence sequence = new MigrationSequence(
				new MigrationMetadata(v100, "Rick-Rainer Ludwig",
						"Process Monitor", "", "Version " + v100 + " sequence."));
		sequence.registerMigrationSteps(checkAndCreateKeyspaces());
		sequence.registerMigrationSteps(checkAndCreateAnalysisTables());
		return sequence;
	}

	private List<MigrationStep> checkAndCreateKeyspaces()
			throws MigrationException {
		List<MigrationStep> steps = new ArrayList<>();
		steps.add(createKeyspace(connector, PROCESSES_KEYSPACE, v100,
				"Rick-Rainer Ludwig", "Keyspace for process states",
				ReplicationStrategy.SIMPLE_STRATEGY, 1));
		return steps;
	}

	private List<MigrationStep> checkAndCreateAnalysisTables()
			throws MigrationException {
		List<MigrationStep> steps = new ArrayList<>();
		steps.add(createTable(
				connector,
				PROCESSES_KEYSPACE,
				v100,
				"Rick-Rainer Ludwig",
				"Keeps states about the running analysis processes.",
				"CREATE TABLE "
						+ ANALYSIS_PROCESS_TABLE
						+ " (started timestamp, project_uuid uuid, run_uuid uuid, state text, last_progress timestamp,"
						+ "PRIMARY KEY(project_uuid));"));
		return steps;
	}
}
