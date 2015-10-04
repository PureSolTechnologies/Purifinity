package com.puresoltechnologies.purifinity.server.core.impl.analysis.store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.core.impl.evaluation.EvaluatorStoreConnection;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraElementNames;

/**
 * This class contains method to handle the evaluation big tables.
 * 
 * @author Rick-Rainer Ludwig
 */
public class BigTableUtils {

    @Inject
    private Logger logger;

    @Inject
    @EvaluatorStoreConnection
    private Connection connection;

    public void removeAnalysisRunResults(String projectId, long runId) throws AnalysisStoreException {
	try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM "
		+ CassandraElementNames.EVALUATION_METRICS_TABLE + " WHERE project_id=? AND run_id=?;")) {
	    preparedStatement.setString(1, projectId);
	    preparedStatement.setLong(2, runId);
	    preparedStatement.execute();
	    connection.commit();
	} catch (SQLException e) {
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		logger.warn("Could not rollback analysis removal.", e1);
	    }
	    throw new AnalysisStoreException(
		    "Could not remove analysis '" + runId + "' for project '" + projectId + "'.", e);
	}
    }
}
