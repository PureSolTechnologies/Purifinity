package com.puresoltechnologies.purifinity.server.ddl;

import static com.puresoltechnologies.purifinity.server.database.cassandra.migration.CassandraMigration.createKeyspace;
import static com.puresoltechnologies.purifinity.server.database.cassandra.migration.CassandraMigration.createTable;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.types.Version;
import com.puresoltechnologies.purifinity.server.database.cassandra.AnalysisStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.migration.CassandraMigratorConnector;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraElementNames;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.ReplicationStrategy;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationMetadata;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationSequence;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationStep;
import com.puresoltechnologies.purifinity.server.database.migration.spi.UniversalMigratorConnector;

public class AnalysisServiceDatabaseMigrator {

	private static final Version V_1_0_0 = new Version(1, 0, 0);

	private final CassandraMigratorConnector connector;

	public AnalysisServiceDatabaseMigrator(CassandraMigratorConnector connector)
			throws MigrationException {
		this.connector = connector;
	}

	public UniversalMigratorConnector getConnector() {
		return connector;
	}

	public void drop() {
		Cluster cluster = connector.getCluster();
		Session session = cluster.connect();
		try {
			session.execute("DROP KEYSPACE " + AnalysisStoreKeyspace.NAME);
		} finally {
			session.close();
		}
	}

	public MigrationSequence getSequence() throws MigrationException {
		MigrationSequence sequence = new MigrationSequence(
				new MigrationMetadata(V_1_0_0, "Rick-Rainer Ludwig",
						"Analysis Service", "", "Version " + V_1_0_0
								+ " sequence."));
		sequence.registerMigrationSteps(checkAndCreateKeyspaces());
		sequence.registerMigrationSteps(checkAndCreateAnalysisTables());
		return sequence;
	}

	private List<MigrationStep> checkAndCreateKeyspaces()
			throws MigrationException {
		List<MigrationStep> steps = new ArrayList<>();
		steps.add(createKeyspace(connector, AnalysisStoreKeyspace.NAME,
				V_1_0_0, "Rick-Rainer Ludwig",
				"Keyspace for analysis information",
				ReplicationStrategy.SIMPLE_STRATEGY, 1));
		return steps;
	}

	private List<MigrationStep> checkAndCreateAnalysisTables()
			throws MigrationException {
		List<MigrationStep> steps = new ArrayList<>();
		steps.add(createTable(connector, AnalysisStoreKeyspace.NAME, V_1_0_0,
				"Rick-Rainer Ludwig", "Keeps settings of analysis projects.",
				"CREATE TABLE "
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

		steps.add(createTable(connector, AnalysisStoreKeyspace.NAME, V_1_0_0,
				"Rick-Rainer Ludwig", "Keeps settings of analysis runs.",
				"CREATE TABLE "
						+ CassandraElementNames.ANALYSIS_RUN_SETTINGS_TABLE
						+ " (run_uuid uuid, " + "file_includes list<text>, "
						+ "file_excludes list<text>, "
						+ "location_includes list<text>, "
						+ "location_excludes list<text>, "
						+ "ignore_hidden boolean, " + "PRIMARY KEY(run_uuid));"));

		steps.add(createTable(
				connector,
				AnalysisStoreKeyspace.NAME,
				V_1_0_0,
				"Rick-Rainer Ludwig",
				"Keeps analysis information for analyzed and unanalyzed "
						+ "files and their raw data.",
				"CREATE TABLE "
						+ CassandraElementNames.ANALYSIS_FILES_TABLE
						+ " (time timestamp, hashid varchar, raw blob, size int, "
						+ "PRIMARY KEY(hashid));"));

		steps.add(createTable(
				connector,
				AnalysisStoreKeyspace.NAME,
				V_1_0_0,
				"Rick-Rainer Ludwig",
				"Keeps analysis information for analyzed and unanalyzed "
						+ "files and their raw data.",
				"CREATE TABLE "
						+ CassandraElementNames.ANALYSIS_ANALYZES_TABLE
						+ " (time timestamp, hashid varchar, language varchar, language_version varchar, plugin_version varchar, duration bigint, successful boolean, analyzer_message text,"
						+ "analysis blob, PRIMARY KEY(hashid, language, language_version));"));

		steps.add(createTable(
				connector,
				AnalysisStoreKeyspace.NAME,
				V_1_0_0,
				"Rick-Rainer Ludwig",
				"Keeps a cache for analysis file trees for performance optimization.",
				"CREATE TABLE "
						+ CassandraElementNames.ANALYSIS_FILE_TREE_CACHE
						+ " (run_uuid uuid, " + "persisted_tree blob, "
						+ "PRIMARY KEY(run_uuid));"));
		return steps;
	}

}
