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
import com.puresoltechnologies.purifinity.server.database.cassandra.PluginsKeyspace;
import com.puresoltechnologies.versioning.Version;
import com.puresoltechnologies.versioning.VersionRange;

public class PluginsDatabaseTransformator implements ComponentTransformator {

	private static final String PLUGINS_TABLE = "plugins";
	private static final String ANALYZERS_TABLE = "analyzsers";
	private static final String EVALUATORS_TABLE = "evaluators";
	private static final String EVALUATOR_PARAMETERS_TABLE = "evaluator_parameters";
	private static final String REPOSITORY_TYPES_TABLE = "repository_types";
	private static final String REPOSITORY_TYPE_PARAMETERS_TABLE = "repository_type_parameters";

	public void drop() {
		Cluster cluster = connector.getCluster();
		Session session = cluster.connect();
		try {
			session.execute("DROP KEYSPACE " + PluginsKeyspace.NAME);
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
		steps.add(createKeyspace(connector, PluginsKeyspace.NAME, v100,
				"Rick-Rainer Ludwig", "Keyspace for plugin system.",
				ReplicationStrategy.SIMPLE_STRATEGY, 1));
		return steps;
	}

	private List<MigrationStep> checkAndCreateAnalysisTables()
			throws MigrationException {
		List<MigrationStep> steps = new ArrayList<>();
		steps.add(createTable(
				connector,
				PluginsKeyspace.NAME,
				v100,
				"Rick-Rainer Ludwig",
				"Keeps information about installed plugins.",
				"CREATE TABLE "
						+ PLUGINS_TABLE
						+ " (changed timestamp, changed_by text, id text, name text, version text, description text, vendor text, vendor_url text, path_to_ui text, "
						+ "PRIMARY KEY(id, version));"));

		steps.add(createTable(connector, PluginsKeyspace.NAME, v100,
				"Rick-Rainer Ludwig",
				"Keeps information about installed analyzers.", "CREATE TABLE "
						+ ANALYZERS_TABLE + " (changed timestamp, "
						+ "changed_by text, " + "id text, " + "name text, "
						+ "version text, " + "plugin_id text, "
						+ "plugin_version text, " + "jndi_name text, "
						+ "description text, " + "service_url text, "
						+ "configuration_url text, " + "project_url text, "
						+ "run_url text," + "PRIMARY KEY(id, version));"));

		steps.add(createTable(connector, PluginsKeyspace.NAME, v100,
				"Rick-Rainer Ludwig",
				"Keeps information about the installed evaluators.",
				"CREATE TABLE " + EVALUATORS_TABLE + " (changed timestamp, "
						+ "changed_by text, " + "id text, " + "name text, "
						+ "type text, " + "plugin_id text, "
						+ "plugin_version text, " + "jndi_name text, "
						+ "description text, " + "service_url text, "
						+ "configuration_url text, " + "project_url text, "
						+ "run_url text, "
						+ "quality_characteristics set<text>, "
						+ " dependencies set<text>, " + "PRIMARY KEY(id));"));

		steps.add(createTable(
				connector,
				PluginsKeyspace.NAME,
				v100,
				"Rick-Rainer Ludwig",
				"Keeps information about the provided parameters of evaluators.",
				"CREATE TABLE " + EVALUATOR_PARAMETERS_TABLE
						+ " (changed timestamp, " + "changed_by text, "
						+ "evaluator_id text, " + "name text, " + "unit text, "
						+ "description text, " + "level_of_measurement text, "
						+ "type text, " + "numeric boolean, "
						+ "PRIMARY KEY(evaluator_id, name));"));

		steps.add(createTable(connector, PluginsKeyspace.NAME, v100,
				"Rick-Rainer Ludwig",
				"Keeps information about the installed evaluators.",
				"CREATE TABLE " + REPOSITORY_TYPES_TABLE
						+ " (changed timestamp, " + "changed_by text, "
						+ "class_name text, " + "name text, "
						+ "plugin_id text, " + "plugin_version text, "
						+ "description text, " + "PRIMARY KEY(class_name));"));

		steps.add(createTable(connector, PluginsKeyspace.NAME, v100,
				"Rick-Rainer Ludwig",
				"Keeps information about the installed evaluators.",
				"CREATE TABLE " + REPOSITORY_TYPE_PARAMETERS_TABLE
						+ " (changed timestamp, " + "changed_by text, "
						+ "class_name text, " + "name text, " + "unit text, "
						+ "description text, " + "level_of_measurement text, "
						+ "type text, " + "numeric boolean, "
						+ "PRIMARY KEY(class_name, name));"));
		return steps;
	}

}
