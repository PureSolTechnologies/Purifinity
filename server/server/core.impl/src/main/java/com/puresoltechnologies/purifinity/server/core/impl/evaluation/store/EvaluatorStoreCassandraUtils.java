package com.puresoltechnologies.purifinity.server.core.impl.evaluation.store;

import javax.inject.Inject;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.purifinity.server.database.cassandra.EvaluationStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraElementNames;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraPreparedStatements;

public class EvaluatorStoreCassandraUtils {

	@Inject
	@EvaluationStoreKeyspace
	private Session session;

	@Inject
	private CassandraPreparedStatements cassandraPreparedStatements;

	public void deleteFileEvaluation(HashId hashId) {
		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(session, "DELETE FROM "
						+ CassandraElementNames.EVALUATION_FILE_METRICS_TABLE
						+ " WHERE hashid=?;");
		BoundStatement boundStatement = preparedStatement.bind(hashId
				.toString());
		session.execute(boundStatement);
	}

	public void deleteDirectoryEvaluation(HashId hashId) {
		PreparedStatement preparedStatement = cassandraPreparedStatements
				.getPreparedStatement(
						session,
						"DELETE FROM "
								+ CassandraElementNames.EVALUATION_DIRECTORY_METRICS_TABLE
								+ " WHERE hashid=?;");
		BoundStatement boundStatement = preparedStatement.bind(hashId
				.toString());
		session.execute(boundStatement);
	}

}
