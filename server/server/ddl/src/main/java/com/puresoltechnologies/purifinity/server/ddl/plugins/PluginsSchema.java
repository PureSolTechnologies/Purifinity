package com.puresoltechnologies.purifinity.server.ddl.plugins;

import static com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraMigration.createKeyspace;
import static com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraMigration.createTable;

import com.puresoltechnologies.commons.misc.Version;
import com.puresoltechnologies.purifinity.server.database.cassandra.PluginsKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.ReplicationStrategy;
import com.puresoltechnologies.purifinity.server.database.migration.DatabaseMigrator;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;

public class PluginsSchema {

	private static final String PLUGINS_TABLE = "plugins";
	private static final String ANALYZERS_TABLE = "analyzsers";
	private static final String EVALUATORS_TABLE = "evaluators";
	private static final String EVALUATOR_PARAMETERS_TABLE = "evaluator_parameters";
	private static final String REPOSITORY_TYPES_TABLE = "repository_types";
	private static final String REPOSITORY_TYPE_PARAMETERS_TABLE = "repository_type_parameters";

	private static final Version v100 = new Version(1, 0, 0);

	public static void createSequence(DatabaseMigrator migrator)
			throws MigrationException {
		checkAndCreateKeyspaces(migrator);
		checkAndCreateAnalysisTables(migrator);
	}

	private static void checkAndCreateKeyspaces(DatabaseMigrator migrator)
			throws MigrationException {
		migrator.registerMigrationStep(createKeyspace(PluginsKeyspace.NAME,
				v100, "Rick-Rainer Ludwig", "Keyspace for plugin system.",
				ReplicationStrategy.SIMPLE_STRATEGY, 1));
	}

	private static void checkAndCreateAnalysisTables(DatabaseMigrator migrator)
			throws MigrationException {

		migrator.registerMigrationStep(createTable(
				PluginsKeyspace.NAME,
				v100,
				"Rick-Rainer Ludwig",
				"Keeps information about installed plugins.",
				"CREATE TABLE "
						+ PLUGINS_TABLE
						+ " (changed timestamp, changed_by text, id text, name text, version text, description text, vendor text, vendor_url text, path_to_ui text, "
						+ "PRIMARY KEY(id, version));"));

		migrator.registerMigrationStep(createTable(PluginsKeyspace.NAME, v100,
				"Rick-Rainer Ludwig",
				"Keeps information about installed analyzers.", "CREATE TABLE "
						+ ANALYZERS_TABLE + " (changed timestamp, "
						+ "changed_by text, " + "id text, " + "name text, "
						+ "version text, " + "plugin_id text, "
						+ "plugin_version text, " + "jndi_name text, "
						+ "description text, " + "service_url text, "
						+ "configuration_url text, " + "project_url text, "
						+ "run_url text," + "PRIMARY KEY(id, version));"));

		migrator.registerMigrationStep(createTable(PluginsKeyspace.NAME, v100,
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

		migrator.registerMigrationStep(createTable(
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

		migrator.registerMigrationStep(createTable(PluginsKeyspace.NAME, v100,
				"Rick-Rainer Ludwig",
				"Keeps information about the installed evaluators.",
				"CREATE TABLE " + REPOSITORY_TYPES_TABLE
						+ " (changed timestamp, " + "changed_by text, "
						+ "class_name text, " + "name text, "
						+ "plugin_id text, " + "plugin_version text, "
						+ "description text, " + "PRIMARY KEY(class_name));"));

		migrator.registerMigrationStep(createTable(PluginsKeyspace.NAME, v100,
				"Rick-Rainer Ludwig",
				"Keeps information about the installed evaluators.",
				"CREATE TABLE " + REPOSITORY_TYPE_PARAMETERS_TABLE
						+ " (changed timestamp, " + "changed_by text, "
						+ "class_name text, " + "name text, " + "unit text, "
						+ "description text, " + "level_of_measurement text, "
						+ "type text, " + "numeric boolean, "
						+ "PRIMARY KEY(class_name, name));"));

	}
}
