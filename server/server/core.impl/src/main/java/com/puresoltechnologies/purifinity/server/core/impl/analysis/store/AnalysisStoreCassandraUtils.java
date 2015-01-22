package com.puresoltechnologies.purifinity.server.core.impl.analysis.store;

import java.util.UUID;

import javax.inject.Inject;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.commons.misc.io.FileSearchConfiguration;
import com.puresoltechnologies.purifinity.server.database.cassandra.AnalysisStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraElementNames;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraPreparedStatements;

/**
 * This class contains methods to handle analysis projects and run in Cassandra.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisStoreCassandraUtils {

	@Inject
	@AnalysisStoreKeyspace
	private Session session;

	@Inject
	private CassandraPreparedStatements cassandraPreparedStatements;

	/**
	 * This method write the project analysis settings into database.
	 * 
	 * @param runUUID
	 * @param fileSearchConfiguration
	 */
	public void writeAnalysisRunSettings(UUID runUUID,
			FileSearchConfiguration fileSearchConfiguration) {
		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(session, "INSERT INTO "
						+ CassandraElementNames.ANALYSIS_RUN_SETTINGS_TABLE
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
	public void removeAnalysisRunSettings(UUID projectUUID, UUID runUUID) {
		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(session, "DELETE FROM "
						+ CassandraElementNames.ANALYSIS_RUN_SETTINGS_TABLE
						+ " WHERE run_uuid= ?");
		BoundStatement bound = preparedStatement.bind(runUUID);
		session.execute(bound);
	}

	public void removeAnalysisFile(HashId hashId) {
		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(session, "DELETE FROM "
						+ CassandraElementNames.ANALYSIS_FILES_TABLE
						+ " WHERE hashid=?;");
		BoundStatement boundStatement = preparedStatement.bind(hashId
				.toString());
		session.execute(boundStatement);
		preparedStatement = cassandraPreparedStatements.getPreparedStatement(
				session, "DELETE FROM "
						+ CassandraElementNames.ANALYSIS_ANALYZES_TABLE
						+ " WHERE hashid=?;");
		boundStatement = preparedStatement.bind(hashId.toString());
		session.execute(boundStatement);
	}

	public void removeProjectSettings(UUID projectUUID) {
		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(session, "DELETE FROM "
						+ CassandraElementNames.ANALYSIS_PROJECT_SETTINGS_TABLE
						+ " WHERE project_uuid=?;");
		BoundStatement bound = preparedStatement.bind(projectUUID);
		session.execute(bound);
	}

}
