package com.puresoltechnologies.purifinity.server.ddl;

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
import com.puresoltechnologies.purifinity.server.database.cassandra.PreferencesStoreKeyspace;
import com.puresoltechnologies.versioning.Version;

public class PreferencesStoreDatabaseTransformator implements
		ComponentTransformator {

	private static final String SYSTEM_PREFERENCES_TABLE = "system_preferences";
	private static final String USER_PREFERENCES_TABLE = "user_preferences";
	private static final String PLUGIN_PREFERENCES_TABLE = "plugin_preferences";
	private static final String SERVICE_ACTIVATION_TABLE = "service_activation";
	private static final String SERVICE_PROJECT_ACTIVATION_TABLE = "service_project_activation";

	public static final String CASSANDRA_HOST = "localhost";
	public static final int CASSANDRA_CQL_PORT = 9042;

	@Override
	public String getComponentName() {
		return "PreferencesStore";
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
				.createKeyspace(sequence, PreferencesStoreKeyspace.NAME,
						"Rick-Rainer Ludwig", "Keyspace for preferences",
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
				PreferencesStoreKeyspace.NAME, metadata);

		sequence.appendTransformation(new CassandraCQLTransformationStep(
				sequence,
				"Rick-Rainer Ludwig",
				"CREATE TABLE "
						+ SYSTEM_PREFERENCES_TABLE
						+ " (changed timestamp, changed_by text, key ascii, value text, "
						+ "PRIMARY KEY(key));",
				"Keeps preferences for the system wide settings."));
		sequence.appendTransformation(new CassandraCQLTransformationStep(
				sequence,
				"Rick-Rainer Ludwig",
				"CREATE TABLE "
						+ USER_PREFERENCES_TABLE
						+ " (changed timestamp, changed_by text, user ascii, key ascii, value text, "
						+ "PRIMARY KEY(user, key));",
				"Keeps preferences for the user specific settings."));
		sequence.appendTransformation(new CassandraCQLTransformationStep(
				sequence,
				"Rick-Rainer Ludwig",
				"CREATE TABLE "
						+ PLUGIN_PREFERENCES_TABLE
						+ " (changed timestamp, changed_by text, project_id ascii, key ascii, value text, "
						+ "PRIMARY KEY(project_id, key));",
				"Keeps preferences for the project specific settings."));
		sequence.appendTransformation(new CassandraCQLTransformationStep(
				sequence,
				"Rick-Rainer Ludwig",
				"CREATE TABLE "
						+ SERVICE_ACTIVATION_TABLE
						+ " (changed timestamp, changed_by text, service_id ascii, active boolean, "
						+ "PRIMARY KEY(service_id));",
				"Keeps the activation states of services."));
		sequence.appendTransformation(new CassandraCQLTransformationStep(
				sequence,
				"Rick-Rainer Ludwig",
				"CREATE TABLE "
						+ SERVICE_PROJECT_ACTIVATION_TABLE
						+ " (changed timestamp, changed_by text, project_id ascii, service_id ascii, active boolean, "
						+ "PRIMARY KEY(project_id, service_id));",
				"Keeps the activation states of services."));

		return sequence;
	}

	@Override
	public void dropAll() {
		try (Cluster cluster = CassandraUtils.connectCluster()) {
			try (Session session = cluster.connect()) {
				session.execute("DROP KEYSPACE IF EXISTS "
						+ PreferencesStoreKeyspace.NAME);
			}
		}
	}

}
