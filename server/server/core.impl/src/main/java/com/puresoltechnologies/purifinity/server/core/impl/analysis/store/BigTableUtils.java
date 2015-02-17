package com.puresoltechnologies.purifinity.server.core.impl.analysis.store;

import javax.inject.Inject;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.database.cassandra.EvaluationStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraElementNames;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraPreparedStatements;

/**
 * This class contains method to handle the evaluation big tables.
 * 
 * @author Rick-Rainer Ludwig
 */
public class BigTableUtils {

    @Inject
    @EvaluationStoreKeyspace
    private Session session;

    @Inject
    private CassandraPreparedStatements cassandraPreparedStatements;

    public void removeAnalysisRunResults(String projectId, long runId) {
	PreparedStatement preparedStatement = cassandraPreparedStatements
		.getPreparedStatement(session, "DELETE FROM "
			+ CassandraElementNames.EVALUATION_METRICS_TABLE
			+ " WHERE project_id=? AND run_id=?;");
	BoundStatement boundStatement = preparedStatement
		.bind(projectId, runId);
	session.execute(boundStatement);
    }
}
