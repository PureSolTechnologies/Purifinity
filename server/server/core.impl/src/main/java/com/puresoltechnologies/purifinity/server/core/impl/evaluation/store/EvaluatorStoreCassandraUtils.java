package com.puresoltechnologies.purifinity.server.core.impl.evaluation.store;

import java.util.UUID;

import javax.inject.Inject;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.server.database.cassandra.EvaluationStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraConnection;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraElementNames;

public class EvaluatorStoreCassandraUtils {

	@Inject
	@EvaluationStoreKeyspace
	private Session session;

	public void deleteFileEvaluation(HashId hashId) {
		PreparedStatement preparedStatement = CassandraConnection
				.getPreparedStatement(session, "DELETE FROM "
						+ CassandraElementNames.EVALUATION_FILES_TABLE
						+ " WHERE hashid=?;");
		BoundStatement boundStatement = preparedStatement.bind(hashId
				.toString());
		session.execute(boundStatement);
	}

	public void deleteDirectoryEvaluation(HashId hashId) {
		PreparedStatement preparedStatement = CassandraConnection
				.getPreparedStatement(session, "DELETE FROM "
						+ CassandraElementNames.EVALUATION_DIRECTORIES_TABLE
						+ " WHERE hashid=?;");
		BoundStatement boundStatement = preparedStatement.bind(hashId
				.toString());
		session.execute(boundStatement);
	}

	public void deleteDirectoryEvaluation(UUID runUUID) {
		PreparedStatement preparedStatement = CassandraConnection
				.getPreparedStatement(session, "DELETE FROM "
						+ CassandraElementNames.EVALUATION_PROJECTS_TABLE
						+ " WHERE run_uuid=?;");
		BoundStatement boundStatement = preparedStatement.bind(runUUID);
		session.execute(boundStatement);
	}

}
