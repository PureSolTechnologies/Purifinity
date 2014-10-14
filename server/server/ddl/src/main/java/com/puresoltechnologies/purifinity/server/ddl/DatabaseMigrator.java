package com.puresoltechnologies.purifinity.server.ddl;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.database.cassandra.migration.CassandraMigratorConnector;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;
import com.puresoltechnologies.purifinity.server.database.migration.Migrator;

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

    public static void main(String[] args) throws Exception, MigrationException {
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
	try (CassandraMigratorConnector connector = new CassandraMigratorConnector(
		CASSANDRA_HOST, CASSANDRA_CQL_PORT)) {
	    try (Migrator migrator = new Migrator()) {
		AnalysisServiceDatabaseMigrator analysisServiceDatabaseMigrator = new AnalysisServiceDatabaseMigrator(
			connector);
		EvaluationServiceDatabaseMigrator evaluationServiceDatabaseMigrator = new EvaluationServiceDatabaseMigrator(
			connector);
		ProcessStatesDatabaseMigrator processStatesDatabaseMigrator = new ProcessStatesDatabaseMigrator(
			connector);
		PreferencesStoreDatabaseMigrator preferencesStoreDatabaseMigrator = new PreferencesStoreDatabaseMigrator(
			connector);
		PluginsDatabaseMigrator pluginsDatabaseMigrator = new PluginsDatabaseMigrator(
			connector);
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
		    migrator.runMigration(analysisServiceDatabaseMigrator
			    .getSequence());
		    migrator.runMigration(evaluationServiceDatabaseMigrator
			    .getSequence());
		    migrator.runMigration(processStatesDatabaseMigrator
			    .getSequence());
		    migrator.runMigration(preferencesStoreDatabaseMigrator
			    .getSequence());
		    migrator.runMigration(pluginsDatabaseMigrator.getSequence());
		}
	    }
	}
    }
}
