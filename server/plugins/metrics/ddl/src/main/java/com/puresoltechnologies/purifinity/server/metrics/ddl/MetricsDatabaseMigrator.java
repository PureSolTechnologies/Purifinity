package com.puresoltechnologies.purifinity.server.metrics.ddl;

import static com.puresoltechnologies.purifinity.server.database.cassandra.migration.CassandraMigration.createTable;

import com.puresoltechnologies.commons.misc.Version;
import com.puresoltechnologies.purifinity.server.database.cassandra.migration.CassandraMigration;
import com.puresoltechnologies.purifinity.server.database.cassandra.migration.CassandraMigratorConnector;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.ReplicationStrategy;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationMetadata;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationSequence;
import com.puresoltechnologies.purifinity.server.database.migration.Migrator;
import com.puresoltechnologies.purifinity.server.database.migration.spi.UniversalMigratorConnector;

public class MetricsDatabaseMigrator {

    public static final String HALSTEAD_EVALUATOR_KEYSPACE_NAME = "halstead_evaluator";
    public static final String CASSANDRA_HOST = "localhost";
    public static final int CASSANDRA_CQL_PORT = 9042;
    public static final String RESULTS_TABLE_NAME = "results";

    private static final Version V_1_0_0 = new Version(1, 0, 0);

    private static final String FILE_RESULTS_TABLE = "file_results";
    private static final String DIRECTORY_RESULTS_TABLE = "directory_results";
    private static final String PROJECT_RESULTS_TABLE = "project_results";

    private final UniversalMigratorConnector connector;

    protected MetricsDatabaseMigrator(UniversalMigratorConnector connector) {
	this.connector = connector;
    }

    private MigrationSequence migrateVersion100() throws MigrationException {
	MigrationSequence sequence = new MigrationSequence(
		new MigrationMetadata(V_1_0_0, "Rick-Rainer Ludwig",
			"Metrics Plugin", "", "Version " + V_1_0_0
				+ " sequence."));
	sequence.registerMigrationStep(CassandraMigration.createKeyspace(
		connector, HALSTEAD_EVALUATOR_KEYSPACE_NAME, V_1_0_0,
		"Rick-Rainer Ludwig", "Keeps the user passwords.",
		ReplicationStrategy.SIMPLE_STRATEGY, 3));
	sequence.registerMigrationStep(createTable(
		connector,
		HALSTEAD_EVALUATOR_KEYSPACE_NAME,
		V_1_0_0,
		"Rick-Rainer Ludwig",
		"Keeps directory results for Halstead evaluator.",
		"CREATE TABLE "
			+ FILE_RESULTS_TABLE
			+ " (hashid varchar, "
			+ "code_range_type varchar, "
			+ "code_range_name varchar, "
			+ "operators map<text,int>, "
			+ "operands map<text,int>, "
			+ "PRIMARY KEY(hashid, code_range_type, code_range_name));"));
	sequence.registerMigrationStep(createTable(connector,
		HALSTEAD_EVALUATOR_KEYSPACE_NAME, V_1_0_0,
		"Rick-Rainer Ludwig",
		"Keeps directory results for Halstead evaluator.",
		"CREATE TABLE " + DIRECTORY_RESULTS_TABLE
			+ " (hashid varchar, " + "operators map<text,int>, "
			+ "operands map<text,int>, " + "PRIMARY KEY(hashid));"));
	sequence.registerMigrationStep(createTable(connector,
		HALSTEAD_EVALUATOR_KEYSPACE_NAME, V_1_0_0,
		"Rick-Rainer Ludwig",
		"Keeps project results for Halstead evaluator.",
		"CREATE TABLE " + PROJECT_RESULTS_TABLE
			+ " (project_uuid uuid, " + "operators map<text,int>, "
			+ "operands map<text,int>, "
			+ "PRIMARY KEY(project_uuid));"));
	return sequence;
    }

    public static void main(String[] args) throws Exception {
	try (CassandraMigratorConnector connector = new CassandraMigratorConnector(
		CASSANDRA_HOST, CASSANDRA_CQL_PORT)) {
	    MetricsDatabaseMigrator metricsDatabaseSchema = new MetricsDatabaseMigrator(
		    connector);
	    try (Migrator migrator = new Migrator()) {
		migrator.runMigration(metricsDatabaseSchema.migrateVersion100());
	    }
	}
    }
}
