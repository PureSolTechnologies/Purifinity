package com.puresoltechnologies.purifinity.server.ddl;

import java.io.IOException;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.database.cassandra.migration.CassandraMigratorConnector;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;
import com.puresoltechnologies.purifinity.server.ddl.analysisservice.AnalysisServiceDatabaseMigrator;
import com.puresoltechnologies.purifinity.server.ddl.evaluationservice.EvaluationServiceDatabaseMigrator;
import com.puresoltechnologies.purifinity.server.ddl.plugins.PluginsDatabaseMigrator;
import com.puresoltechnologies.purifinity.server.ddl.preferences.PreferencesStoreDatabaseMigrator;
import com.puresoltechnologies.purifinity.server.ddl.processes.ProcessStatesDatabaseMigrator;

/**
 * This migrator starts all services of all services included in Purifinity
 * server.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DatabaseMigrator {

	public static final String CASSANDRA_HOST = "localhost";
	public static final int CASSANDRA_CQL_PORT = 9042;

	private static boolean migrate = false;
	private static boolean drop = false;

	private static void showUsage() {
		System.out.println("Usage: " + DatabaseMigrator.class.getSimpleName()
				+ " (--drop | --migrate)");
	}

	private static void dropTitanKeyspace(CassandraMigratorConnector connector) {
		Cluster cluster = connector.getCluster();
		Session session = cluster.connect();
		try {
			session.execute("DROP KEYSPACE titan;");
		} finally {
			session.close();
		}
	}

	public static void main(String[] args) throws IOException,
			MigrationException {
		if (args.length == 0) {
			showUsage();
			return;
		}
		for (String arg : args) {
			if ("--drop".equals(arg)) {
				drop = true;
			} else if ("--migrate".equals(arg)) {
				migrate = true;
			}
		}
		AnalysisServiceDatabaseMigrator analysisServiceDatabaseMigrator = new AnalysisServiceDatabaseMigrator(
				CASSANDRA_HOST, CASSANDRA_CQL_PORT);
		EvaluationServiceDatabaseMigrator evaluationServiceDatabaseMigrator = new EvaluationServiceDatabaseMigrator(
				CASSANDRA_HOST, CASSANDRA_CQL_PORT);
		ProcessStatesDatabaseMigrator processStatesDatabaseMigrator = new ProcessStatesDatabaseMigrator(
				CASSANDRA_HOST, CASSANDRA_CQL_PORT);
		PreferencesStoreDatabaseMigrator preferencesStoreDatabaseMigrator = new PreferencesStoreDatabaseMigrator(
				CASSANDRA_HOST, CASSANDRA_CQL_PORT);
		PluginsDatabaseMigrator pluginsDatabaseMigrator = new PluginsDatabaseMigrator(
				CASSANDRA_HOST, CASSANDRA_CQL_PORT);
		if (drop) {
			analysisServiceDatabaseMigrator.drop();
			evaluationServiceDatabaseMigrator.drop();
			processStatesDatabaseMigrator.drop();
			preferencesStoreDatabaseMigrator.drop();
			pluginsDatabaseMigrator.drop();
			dropTitanKeyspace((CassandraMigratorConnector) analysisServiceDatabaseMigrator
					.getConnector());
		}
		if (migrate) {
			analysisServiceDatabaseMigrator.migrate();
			evaluationServiceDatabaseMigrator.migrate();
			processStatesDatabaseMigrator.migrate();
			preferencesStoreDatabaseMigrator.migrate();
			pluginsDatabaseMigrator.migrate();
		}
	}

}
