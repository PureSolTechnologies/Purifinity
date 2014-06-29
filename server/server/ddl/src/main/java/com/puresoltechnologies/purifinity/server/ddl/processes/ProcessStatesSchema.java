package com.puresoltechnologies.purifinity.server.ddl.processes;

import static com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraMigration.createKeyspace;
import static com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraMigration.createTable;

import com.puresoltechnologies.commons.misc.Version;
import com.puresoltechnologies.purifinity.server.database.cassandra.ProcessStatesKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.ReplicationStrategy;
import com.puresoltechnologies.purifinity.server.database.migration.DatabaseMigrator;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;

public class ProcessStatesSchema {

    private static final String PROCESSES_KEYSPACE = ProcessStatesKeyspace.NAME;

    private static final String ANALYSIS_PROCESS_TABLE = "analysis_process";

    private static final Version v100 = new Version(1, 0, 0);

    public static void createSequence(DatabaseMigrator migrator)
	    throws MigrationException {
	checkAndCreateKeyspaces(migrator);
	checkAndCreateAnalysisTables(migrator);
    }

    private static void checkAndCreateKeyspaces(DatabaseMigrator migrator)
	    throws MigrationException {
	migrator.registerMigrationStep(createKeyspace(PROCESSES_KEYSPACE, v100,
		"Rick-Rainer Ludwig", "Keyspace for process states",
		ReplicationStrategy.SIMPLE_STRATEGY, 1));
    }

    private static void checkAndCreateAnalysisTables(DatabaseMigrator migrator)
	    throws MigrationException {
	migrator.registerMigrationStep(createTable(
		PROCESSES_KEYSPACE,
		v100,
		"Rick-Rainer Ludwig",
		"Keeps states about the running analysis processes.",
		"CREATE TABLE "
			+ ANALYSIS_PROCESS_TABLE
			+ " (started timestamp, project_uuid uuid, run_uuid uuid, state text, last_progress timestamp,"
			+ "PRIMARY KEY(project_uuid));"));
    }

}
