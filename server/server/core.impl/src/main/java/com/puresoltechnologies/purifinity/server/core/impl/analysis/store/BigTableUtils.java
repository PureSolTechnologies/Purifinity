package com.puresoltechnologies.purifinity.server.core.impl.analysis.store;

import java.util.UUID;

import javax.inject.Inject;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.database.cassandra.EvaluationStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraConnection;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraElementNames;

/**
 * This class contains method to handle the evaluation big tables.
 * 
 * @author Rick-Rainer Ludwig
 */
public class BigTableUtils {

	@Inject
	@EvaluationStoreKeyspace
	private Session session;

	public void removeAnalysisRunResults(UUID projectUUID, UUID runUUID) {
		PreparedStatement preparedStatement = CassandraConnection
				.getPreparedStatement(session, "DELETE FROM "
						+ CassandraElementNames.EVALUATION_METRICS_TABLE
						+ " WHERE project_uuid=? AND run_uuid=?;");

		BoundStatement boundStatement = preparedStatement.bind(projectUUID,
				runUUID);
		session.execute(boundStatement);
	}

}
