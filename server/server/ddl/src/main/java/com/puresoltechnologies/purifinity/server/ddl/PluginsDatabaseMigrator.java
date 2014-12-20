package com.puresoltechnologies.purifinity.server.ddl;

import static com.puresoltechnologies.purifinity.server.database.cassandra.migration.CassandraMigration.createKeyspace;
import static com.puresoltechnologies.purifinity.server.database.cassandra.migration.CassandraMigration.createTable;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.versioning.Version;
import com.puresoltechnologies.purifinity.server.database.cassandra.PluginsKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.migration.CassandraMigratorConnector;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.ReplicationStrategy;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationMetadata;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationSequence;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationStep;

public class PluginsDatabaseMigrator {

	private static final String PLUGINS_TABLE = "plugins";
	private static final String ANALYZERS_TABLE = "analyzsers";
	private static final String EVALUATORS_TABLE = "evaluators";
	private static final String EVALUATOR_PARAMETERS_TABLE = "evaluator_parameters";
	private static final String REPOSITORY_TYPES_TABLE = "repository_types";
	private static final String REPOSITORY_TYPE_PARAMETERS_TABLE = "repository_type_parameters";

	private static final Version v100 = new Version(1, 0, 0);

	private final CassandraMigratorConnector connector;

	public PluginsDatabaseMigrator(CassandraMigratorConnector connector)
			throws MigrationException {
		this.connector = connector;
	}

	public void drop() {
		Cluster cluster = connector.getCluster();
		Session session = cluster.connect();
		try {
			session.execute("DROP KEYSPACE " + PluginsKeyspace.NAME);
		} finally {
			session.close();
		}
	}

	public MigrationSequence getSequence() throws MigrationException {
		MigrationSequence sequence = new MigrationSequence(
				new MigrationMetadata(v100, "Rick-Rainer Ludwig",
						"Plugins Service", "", "Version " + v100 + " sequence."));
		sequence.registerMigrationSteps(checkAndCreateKeyspaces());
		sequence.registerMigrationSteps(checkAndCreateAnalysisTables());
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
