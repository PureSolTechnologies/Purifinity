package com.puresoltechnologies.purifinity.framework.store.evaluation.cassandra;

import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.framework.store.CassandraConnection;
import com.puresoltechnologies.purifinity.framework.store.CassandraElementNames;

public class EvaluatorStoreCassandraUtils {

	public static void deleteFileEvaluation(HashId hashId) {
		Session session = CassandraConnection.getEvaluationSession();
		PreparedStatement preparedStatement = CassandraConnection
				.getPreparedStatement(session, "DELETE FROM "
						+ CassandraElementNames.EVALUATION_FILES_TABLE
						+ " WHERE hashid=?;");
		BoundStatement boundStatement = preparedStatement.bind(hashId
				.toString());
		session.execute(boundStatement);
	}

	public static void deleteDirectoryEvaluation(HashId hashId) {
		Session session = CassandraConnection.getEvaluationSession();
		PreparedStatement preparedStatement = CassandraConnection
				.getPreparedStatement(session, "DELETE FROM "
						+ CassandraElementNames.EVALUATION_DIRECTORIES_TABLE
						+ " WHERE hashid=?;");
		BoundStatement boundStatement = preparedStatement.bind(hashId
				.toString());
		session.execute(boundStatement);
	}

	public static void deleteDirectoryEvaluation(UUID runUUID) {
		Session session = CassandraConnection.getEvaluationSession();
		PreparedStatement preparedStatement = CassandraConnection
				.getPreparedStatement(session, "DELETE FROM "
						+ CassandraElementNames.EVALUATION_PROJECTS_TABLE
						+ " WHERE run_uuid=?;");
		BoundStatement boundStatement = preparedStatement.bind(runUUID);
		session.execute(boundStatement);
	}

}
