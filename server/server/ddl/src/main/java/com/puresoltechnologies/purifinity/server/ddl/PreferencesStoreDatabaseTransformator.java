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
import com.puresoltechnologies.purifinity.server.database.cassandra.PreferencesStoreKeyspace;
import com.puresoltechnologies.versioning.Version;
import com.puresoltechnologies.versioning.VersionRange;

public class PreferencesStoreDatabaseTransformator implements
		ComponentTransformator {

	private static final String PREFERENCES_TABLE = "preferences";
	private static final String USER_PREFERENCES_TABLE = "user_preferences";
	private static final String PROJECT_PREFERENCES_TABLE = "project_preferences";
	private static final String SERVICE_ACTIVATION_TABLE = "service_activation";
	private static final String SERVICE_PROJECT_ACTIVATION_TABLE = "service_project_activation";

	public void drop() {
		Cluster cluster = connector.getCluster();
		Session session = cluster.connect();
		try {
			session.execute("DROP KEYSPACE " + PreferencesStoreKeyspace.NAME);
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
		steps.add(createKeyspace(connector, PreferencesStoreKeyspace.NAME,
				v100, "Rick-Rainer Ludwig", "Keyspace for preferences",
				ReplicationStrategy.SIMPLE_STRATEGY, 1));
		return steps;
	}

	private List<MigrationStep> checkAndCreateAnalysisTables()
			throws MigrationException {
		List<MigrationStep> steps = new ArrayList<>();
		steps.add(createTable(
				connector,
				PreferencesStoreKeyspace.NAME,
				v100,
				"Rick-Rainer Ludwig",
				"Keeps preferences for the system wide settings.",
				"CREATE TABLE "
						+ PREFERENCES_TABLE
						+ " (changed timestamp, changed_by text, group text, name text, value text, "
						+ "PRIMARY KEY(group, name));"));
		steps.add(createTable(
				connector,
				PreferencesStoreKeyspace.NAME,
				v100,
				"Rick-Rainer Ludwig",
				"Keeps preferences for the user specific settings.",
				"CREATE TABLE "
						+ USER_PREFERENCES_TABLE
						+ " (changed timestamp, changed_by text, user text, group text, name text, value text, "
						+ "PRIMARY KEY(user, group, name));"));
		steps.add(createTable(
				connector,
				PreferencesStoreKeyspace.NAME,
				v100,
				"Rick-Rainer Ludwig",
				"Keeps preferences for the project specific settings.",
				"CREATE TABLE "
						+ PROJECT_PREFERENCES_TABLE
						+ " (changed timestamp, changed_by text, project_uuid uuid, group text, name text, value text, "
						+ "PRIMARY KEY(project_uuid, group, name));"));
		steps.add(createTable(
				connector,
				PreferencesStoreKeyspace.NAME,
				v100,
				"Rick-Rainer Ludwig",
				"Keeps the activation states of services.",
				"CREATE TABLE "
						+ SERVICE_ACTIVATION_TABLE
						+ " (changed timestamp, changed_by text, service_id text, active boolean, "
						+ "PRIMARY KEY(service_id));"));
		steps.add(createTable(
				connector,
				PreferencesStoreKeyspace.NAME,
				v100,
				"Rick-Rainer Ludwig",
				"Keeps the activation states of services.",
				"CREATE TABLE "
						+ SERVICE_PROJECT_ACTIVATION_TABLE
						+ " (changed timestamp, changed_by text, project_uuid uuid, service_id text, active boolean, "
						+ "PRIMARY KEY(project_uuid, service_id));"));
		return steps;
	}

}
