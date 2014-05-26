package com.puresoltechnologies.purifinity.server.ddl;

import com.puresoltechnologies.purifinity.server.ddl.analysisservice.AnalysisServiceDatabaseMigrator;
import com.puresoltechnologies.purifinity.server.ddl.evaluationservice.EvaluationServiceDatabaseMigrator;
import com.puresoltechnologies.purifinity.server.ddl.processes.ProcessStatesDatabaseMigrator;

/**
 * This migrator starts all services of all services included in Purifinity
 * server.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DatabaseMigrator {

	public static void main(String[] args) {
		AnalysisServiceDatabaseMigrator.main(args);
		EvaluationServiceDatabaseMigrator.main(args);
		ProcessStatesDatabaseMigrator.main(args);
	}
}
