package com.puresoltechnologies.purifinity.server.ddl;

import com.puresoltechnologies.purifinity.server.ddl.analysisservice.AnalysisServiceDatabaseMigrator;
import com.puresoltechnologies.purifinity.server.ddl.evaluationservice.EvaluationServiceDatabaseMigrator;

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
	}
}
