package com.puresoltechnologies.purifinity.server.ddl.analysisservice;

import static com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraMigration.createKeyspace;
import static com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraMigration.createTable;

import com.puresoltechnologies.commons.misc.Version;
import com.puresoltechnologies.purifinity.server.database.cassandra.AnalysisStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraElementNames;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.ReplicationStrategy;
import com.puresoltechnologies.purifinity.server.database.migration.DatabaseMigrator;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;

public class AnalysisServiceSchema {

	private static final Version V_1_0_0 = new Version(1, 0, 0);

	public static void createSequence(DatabaseMigrator migrator)
			throws MigrationException {
		checkAndCreateKeyspaces(migrator);
		checkAndCreateAnalysisTables(migrator);
	}

	private static void checkAndCreateKeyspaces(DatabaseMigrator migrator)
			throws MigrationException {
		migrator.registerMigrationStep(createKeyspace(
				AnalysisStoreKeyspace.NAME, V_1_0_0, "Rick-Rainer Ludwig",
				"Keyspace for analysis information",
				ReplicationStrategy.SIMPLE_STRATEGY, 1));
	}

	private static void checkAndCreateAnalysisTables(DatabaseMigrator migrator)
			throws MigrationException {
		migrator.registerMigrationStep(createTable(AnalysisStoreKeyspace.NAME,
				V_1_0_0, "Rick-Rainer Ludwig",
				"Keeps settings of analysis projects.", "CREATE TABLE "
						+ CassandraElementNames.ANALYSIS_PROJECT_SETTINGS_TABLE
						+ " (project_uuid uuid, " + "name varchar, "
						+ "description varchar, "
						+ "file_includes list<text>, "
						+ "file_excludes list<text>, "
						+ "location_includes list<text>, "
						+ "location_excludes list<text>, "
						+ "ignore_hidden boolean, "
						+ "repository_location map<text,text>, "
						+ "PRIMARY KEY(project_uuid));"));

		migrator.registerMigrationStep(createTable(AnalysisStoreKeyspace.NAME,
				V_1_0_0, "Rick-Rainer Ludwig",
				"Keeps settings of analysis runs.", "CREATE TABLE "
						+ CassandraElementNames.ANALYSIS_RUN_SETTINGS_TABLE
						+ " (run_uuid uuid, " + "file_includes list<text>, "
						+ "file_excludes list<text>, "
						+ "location_includes list<text>, "
						+ "location_excludes list<text>, "
						+ "ignore_hidden boolean, " + "PRIMARY KEY(run_uuid));"));

		migrator.registerMigrationStep(createTable(
				AnalysisStoreKeyspace.NAME,
				V_1_0_0,
				"Rick-Rainer Ludwig",
				"Keeps analysis information for analyzed and unanalyzed "
						+ "files and their raw data.",
				"CREATE TABLE "
						+ CassandraElementNames.ANALYSIS_FILES_TABLE
						+ " (time timestamp, hashid varchar, raw blob, size int, "
						+ "PRIMARY KEY(hashid));"));

		migrator.registerMigrationStep(createTable(
				AnalysisStoreKeyspace.NAME,
				V_1_0_0,
				"Rick-Rainer Ludwig",
				"Keeps analysis information for analyzed and unanalyzed "
						+ "files and their raw data.",
				"CREATE TABLE "
						+ CassandraElementNames.ANALYSIS_ANALYZES_TABLE
						+ " (time timestamp, hashid varchar, analyzer varchar, analyzer_version varchar, duration bigint, successful boolean, analyzer_message text,"
						+ "analysis blob, PRIMARY KEY(hashid, analyzer, analyzer_version));"));

		migrator.registerMigrationStep(createTable(
				AnalysisStoreKeyspace.NAME,
				V_1_0_0,
				"Rick-Rainer Ludwig",
				"Keeps a cache for analysis file trees for performance optimization.",
				"CREATE TABLE "
						+ CassandraElementNames.ANALYSIS_FILE_TREE_CACHE
						+ " (run_uuid uuid, " + "persisted_tree blob, "
						+ "PRIMARY KEY(run_uuid));"));

	}

}
