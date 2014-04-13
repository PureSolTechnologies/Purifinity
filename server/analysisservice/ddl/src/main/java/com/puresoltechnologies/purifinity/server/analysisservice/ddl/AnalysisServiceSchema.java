package com.puresoltechnologies.purifinity.server.analysisservice.ddl;

import static com.puresoltechnologies.purifinity.framework.database.cassandra.utils.CassandraMigration.createKeyspace;
import static com.puresoltechnologies.purifinity.framework.database.cassandra.utils.CassandraMigration.createTable;

import com.puresoltechnologies.purifinity.framework.commons.utils.Version;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.ReplicationStrategy;
import com.puresoltechnologies.purifinity.framework.database.migration.DatabaseMigrator;
import com.puresoltechnologies.purifinity.framework.database.migration.MigrationException;

public class AnalysisServiceSchema {

	private static final String ANALYSIS_KEYSPACE = "analysis_store";

	private static final String ANALYSIS_FILES_TABLE = "files";
	private static final String ANALYSIS_PROJECT_SETTINGS_TABLE = "project_settings";

	private static final String RUN_SETTINGS_TABLE = "run_settings";

	private static final String ANALYSIS_FILE_TREE_CACHE = "file_tree_cache";

	private static final Version v100 = new Version(1, 0, 0);

	public static void createSequence(DatabaseMigrator migrator)
			throws MigrationException {
		checkAndCreateKeyspaces(migrator);
		checkAndCreateAnalysisTables(migrator);
	}

	private static void checkAndCreateKeyspaces(DatabaseMigrator migrator)
			throws MigrationException {
		migrator.registerMigrationStep(createKeyspace(ANALYSIS_KEYSPACE, v100,
				"Rick-Rainer Ludwig", "Keyspace for analysis information",
				ReplicationStrategy.SIMPLE_STRATEGY, 1));
	}

	private static void checkAndCreateAnalysisTables(DatabaseMigrator migrator)
			throws MigrationException {
		migrator.registerMigrationStep(createTable(ANALYSIS_KEYSPACE, v100,
				"Rick-Rainer Ludwig",
				"Keeps analysis information for analyzed and unanalyzed "
						+ "files and their raw data.", "CREATE TABLE "
						+ ANALYSIS_FILES_TABLE
						+ " (hashid varchar, raw blob, size int, "
						+ "analysis blob, PRIMARY KEY(hashid));"));

		migrator.registerMigrationStep(createTable(ANALYSIS_KEYSPACE, v100,
				"Rick-Rainer Ludwig", "Keeps settings of analysis projects.",
				"CREATE TABLE " + ANALYSIS_PROJECT_SETTINGS_TABLE
						+ " (project_uuid uuid, " + "name varchar, "
						+ "description varchar, "
						+ "file_includes list<text>, "
						+ "file_excludes list<text>, "
						+ "location_includes list<text>, "
						+ "location_excludes list<text>, "
						+ "ignore_hidden boolean, "
						+ "repository_location map<text,text>, "
						+ "PRIMARY KEY(project_uuid));"));

		migrator.registerMigrationStep(createTable(ANALYSIS_KEYSPACE, v100,
				"Rick-Rainer Ludwig", "Keeps settings of analysis runs.",
				"CREATE TABLE " + RUN_SETTINGS_TABLE + " (run_uuid uuid, "
						+ "file_includes list<text>, "
						+ "file_excludes list<text>, "
						+ "location_includes list<text>, "
						+ "location_excludes list<text>, "
						+ "ignore_hidden boolean, " + "PRIMARY KEY(run_uuid));"));

		migrator.registerMigrationStep(createTable(
				ANALYSIS_KEYSPACE,
				v100,
				"Rick-Rainer Ludwig",
				"Keeps a cache for analysis file trees for performance optimization.",
				"CREATE TABLE " + ANALYSIS_FILE_TREE_CACHE
						+ " (run_uuid uuid, " + "persisted_tree blob, "
						+ "PRIMARY KEY(run_uuid));"));

	}

}