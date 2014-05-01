package com.puresoltechnologies.purifinity.framework.analysis.impl.store.cassandra;

import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.CassandraConnection;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.CassandraElementNames;

/**
 * This class contains methods to handle analysis projects and run in Cassandra.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisStoreCassandraUtils {

	/**
	 * This method write the project analysis settings into database.
	 * 
	 * @param runUUID
	 * @param fileSearchConfiguration
	 */
	public static void writeAnalysisRunSettings(UUID runUUID,
			FileSearchConfiguration fileSearchConfiguration) {
		Session session = CassandraConnection.getAnalysisSession();
		PreparedStatement preparedStatement = CassandraConnection
				.getPreparedStatement(session, "INSERT INTO "
						+ CassandraElementNames.RUN_SETTINGS_TABLE
						+ " (run_uuid, " + "file_includes, file_excludes, "
						+ "location_includes, location_excludes, "
						+ "ignore_hidden) " + "VALUES (?, ?, ?, ?, ?, ?)");
		BoundStatement bound = preparedStatement.bind(runUUID,
				fileSearchConfiguration.getFileIncludes(),
				fileSearchConfiguration.getFileExcludes(),
				fileSearchConfiguration.getLocationIncludes(),
				fileSearchConfiguration.getLocationExcludes(),
				fileSearchConfiguration.isIgnoreHidden());
		session.execute(bound);
	}

	/**
	 * This method removes Analysis Run Settings from Cassandra.
	 * 
	 * @param projectUUID
	 * @param runUUID
	 */
	public static void removeAnalysisRunSettings(UUID projectUUID, UUID runUUID) {
		Session session = CassandraConnection.getAnalysisSession();
		PreparedStatement preparedStatement = CassandraConnection
				.getPreparedStatement(session, "DELETE FROM "
						+ CassandraElementNames.RUN_SETTINGS_TABLE
						+ " WHERE run_uuid= ?");
		BoundStatement bound = preparedStatement.bind(runUUID);
		session.execute(bound);
	}

	public static void removeAnalysisFile(HashId hashId) {
		Session session = CassandraConnection.getAnalysisSession();
		PreparedStatement preparedStatement = CassandraConnection
				.getPreparedStatement(session, "DELETE FROM "
						+ CassandraElementNames.ANALYSIS_FILES_TABLE
						+ " WHERE hashid=?;");
		BoundStatement boundStatement = preparedStatement.bind(hashId
				.toString());
		session.execute(boundStatement);
	}

	public static void removeProjectSettings(UUID projectUUID) {
		Session session = CassandraConnection.getAnalysisSession();
		PreparedStatement preparedStatement = CassandraConnection
				.getPreparedStatement(session, "DELETE FROM "
						+ CassandraElementNames.ANALYSIS_PROJECT_SETTINGS_TABLE
						+ " WHERE project_uuid=?;");
		BoundStatement bound = preparedStatement.bind(projectUUID);
		session.execute(bound);
	}

}
