package com.puresoltechnologies.purifinity.server.core.impl.analysis.store;

import java.io.IOException;

import javax.inject.Inject;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.commons.misc.io.FileSearchConfiguration;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.database.cassandra.AnalysisStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraElementNames;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraPreparedStatements;
import com.puresoltechnologies.purifinity.server.database.hadoop.utils.bloob.BloobService;

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

    @Inject
    private BloobService bloob;

    /**
     * This method write the project analysis settings into database.
     * 
     * @param runId
     * @param fileSearchConfiguration
     */
    public void writeAnalysisRunSettings(String projectId, long runId,
	    FileSearchConfiguration fileSearchConfiguration) {
	PreparedStatement preparedStatement = cassandraPreparedStatements.getPreparedStatement(session,
		"INSERT INTO " + CassandraElementNames.ANALYSIS_RUN_SETTINGS_TABLE + " (project_id, " + " run_id, "
			+ "file_includes, file_excludes, " + "location_includes, location_excludes, "
			+ "ignore_hidden) " + "VALUES (?, ?, ?, ?, ?, ?, ?)");
	BoundStatement bound = preparedStatement.bind(projectId, runId, fileSearchConfiguration.getFileIncludes(),
		fileSearchConfiguration.getFileExcludes(), fileSearchConfiguration.getLocationIncludes(),
		fileSearchConfiguration.getLocationExcludes(), fileSearchConfiguration.isIgnoreHidden());
	session.execute(bound);
    }

    /**
     * This method removes Analysis Run Settings from Cassandra.
     * 
     * @param projectId
     * @param runId
     */
    public void removeAnalysisRunSettings(String projectId, long runId) {
	PreparedStatement preparedStatement = cassandraPreparedStatements.getPreparedStatement(session, "DELETE FROM "
		+ CassandraElementNames.ANALYSIS_RUN_SETTINGS_TABLE + " WHERE project_id=? AND run_id=?");
	BoundStatement bound = preparedStatement.bind(projectId, runId);
	session.execute(bound);
    }

    public void removeAnalysisFile(HashId hashId) throws AnalysisStoreException {
	try {
	    bloob.removeRawFile(hashId);
	} catch (IOException e) {
	    throw new AnalysisStoreException("Could not delete file for hash id '" + hashId + "'.", e);
	}
	PreparedStatement preparedStatement = cassandraPreparedStatements.getPreparedStatement(session,
		"DELETE FROM " + CassandraElementNames.ANALYSIS_ANALYSES_TABLE + " WHERE hashid=?;");
	BoundStatement boundStatement = preparedStatement.bind(hashId.toString());
	session.execute(boundStatement);
    }

    public void removeProjectSettings(String projectId) {
	PreparedStatement preparedStatement = cassandraPreparedStatements.getPreparedStatement(session,
		"DELETE FROM " + CassandraElementNames.ANALYSIS_PROJECT_SETTINGS_TABLE + " WHERE project_id=?;");
	BoundStatement bound = preparedStatement.bind(projectId);
	session.execute(bound);
    }

}
