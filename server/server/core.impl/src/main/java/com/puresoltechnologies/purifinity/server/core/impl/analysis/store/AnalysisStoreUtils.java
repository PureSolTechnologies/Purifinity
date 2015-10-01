package com.puresoltechnologies.purifinity.server.core.impl.analysis.store;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.commons.misc.io.FileSearchConfiguration;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.AnalysisServiceConnection;
import com.puresoltechnologies.purifinity.server.database.hadoop.utils.bloob.BloobService;
import com.puresoltechnologies.purifinity.server.database.hbase.HBaseElementNames;

/**
 * This class contains methods to handle analysis projects and run in Cassandra.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisStoreUtils {

    @Inject
    private Logger logger;

    @Inject
    @AnalysisServiceConnection
    private Connection connection;

    @Inject
    private BloobService bloob;

    /**
     * This method write the project analysis settings into database.
     * 
     * @param runId
     * @param fileSearchConfiguration
     * @throws AnalysisStoreException
     */
    public void writeAnalysisRunSettings(String projectId, long runId, FileSearchConfiguration fileSearchConfiguration)
	    throws AnalysisStoreException {
	try {
	    PreparedStatement preparedStatement = connection
		    .prepareStatement("UPSERT INTO " + HBaseElementNames.ANALYSIS_RUN_SETTINGS_TABLE + " (project_id, "
			    + " run_id, " + "file_includes, file_excludes, " + "location_includes, location_excludes, "
			    + "ignore_hidden) " + "VALUES (?, ?, ?, ?, ?, ?, ?)");
	    preparedStatement.setString(1, projectId);
	    preparedStatement.setLong(2, runId);
	    preparedStatement.setArray(3,
		    connection.createArrayOf("VARCHAR", fileSearchConfiguration.getFileIncludes().toArray()));
	    preparedStatement.setArray(4,
		    connection.createArrayOf("VARCHAR", fileSearchConfiguration.getFileExcludes().toArray()));
	    preparedStatement.setArray(5,
		    connection.createArrayOf("VARCHAR", fileSearchConfiguration.getLocationIncludes().toArray()));
	    preparedStatement.setArray(6,
		    connection.createArrayOf("VARCHAR", fileSearchConfiguration.getLocationExcludes().toArray()));
	    preparedStatement.setBoolean(7, fileSearchConfiguration.isIgnoreHidden());
	    preparedStatement.execute();
	    connection.commit();
	} catch (SQLException e) {
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		logger.warn("Could not rollback run settings storage.", e1);
	    }
	    throw new AnalysisStoreException("Could not store run settings.", e);
	}
    }

    /**
     * This method removes Analysis Run Settings from Cassandra.
     * 
     * @param projectId
     * @param runId
     * @throws AnalysisStoreException
     */
    public void removeAnalysisRunSettings(String projectId, long runId) throws AnalysisStoreException {
	try {
	    PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM "
		    + HBaseElementNames.ANALYSIS_RUN_SETTINGS_TABLE + " WHERE project_id=? AND run_id=?");
	    preparedStatement.setString(1, projectId);
	    preparedStatement.setLong(2, runId);
	    preparedStatement.execute();
	    connection.commit();
	} catch (SQLException e) {
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		logger.warn("Could not rollback run settings removal.", e1);
	    }
	    throw new AnalysisStoreException("Could not remove run settings.", e);
	}
    }

    public void removeAnalysisFile(HashId hashId) throws AnalysisStoreException {
	try {
	    bloob.removeRawFile(hashId);
	} catch (IOException e) {
	    throw new AnalysisStoreException("Could not delete file for hash id '" + hashId + "'.", e);
	}
	try {
	    PreparedStatement preparedStatement = connection
		    .prepareStatement("DELETE FROM " + HBaseElementNames.ANALYSIS_ANALYSES_TABLE + " WHERE hashid=?");
	    preparedStatement.setString(1, hashId.toString());
	    preparedStatement.execute();
	    connection.commit();
	} catch (SQLException e) {
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		logger.warn("Could not rollback analysis file removal.", e1);
	    }
	    throw new AnalysisStoreException("Could not remove analysis file.", e);
	}
    }

    public void removeProjectSettings(String projectId) throws AnalysisStoreException {
	try {
	    PreparedStatement preparedStatement = connection.prepareStatement(
		    "DELETE FROM " + HBaseElementNames.ANALYSIS_PROJECT_SETTINGS_TABLE + " WHERE project_id=?");
	    preparedStatement.setString(1, projectId);
	    preparedStatement.execute();
	    connection.commit();
	} catch (SQLException e) {
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		logger.warn("Could not rollback project settings removal.", e1);
	    }
	    throw new AnalysisStoreException("Could not remove project settings.", e);
	}
    }

}
