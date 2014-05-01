package com.puresoltechnologies.purifinity.framework.store.evaluation.cassandra;

import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.framework.store.CassandraConnection;
import com.puresoltechnologies.purifinity.framework.store.CassandraElementNames;

/**
 * This class contains method to handle the evaluation big tables.
 * 
 * @author Rick-Rainer Ludwig
 */
public class BigTableUtils {

	public static void removeAnalysisRunResults(UUID projectUUID, UUID runUUID) {
		Session session = CassandraConnection.getEvaluationSession();
		PreparedStatement preparedStatement = CassandraConnection
				.getPreparedStatement(session, "DELETE FROM "
						+ CassandraElementNames.EVALUATION_METRICS_TABLE
						+ " WHERE project_uuid=? AND run_uuid=?;");

		BoundStatement boundStatement = preparedStatement.bind(projectUUID,
				runUUID);
		session.execute(boundStatement);
	}

}
