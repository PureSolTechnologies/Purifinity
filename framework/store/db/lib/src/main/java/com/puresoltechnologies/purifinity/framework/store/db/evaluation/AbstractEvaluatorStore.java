package com.puresoltechnologies.purifinity.framework.store.db.evaluation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricFileResults;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.framework.store.db.CassandraConnection;
import com.puresoltechnologies.purifinity.framework.store.db.CassandraElementNames;

/**
 * This is an abstract implementation of an evaluator store.
 * 
 * @author Rick-Rainer Ludwig
 */
public abstract class AbstractEvaluatorStore implements EvaluatorStore {

	protected abstract Class<? extends MetricFileResults> getFileResultClass();

	protected abstract Class<? extends MetricDirectoryResults> getDirectoryResultClass();

	protected abstract Class<? extends MetricDirectoryResults> getProjectResultClass();

	private final Session session;

	public AbstractEvaluatorStore() {
		super();
		session = CassandraConnection.getEvaluationSession();
	}

	@Override
	public final boolean hasFileResults(HashId hashId)
			throws EvaluationStoreException {
		ResultSet resultSet = session
				.execute("SELECT hashid FROM "
						+ CassandraElementNames.EVALUATION_FILES_TABLE
						+ " WHERE hashid='" + hashId.toString()
						+ "' AND resultsClass='"
						+ getFileResultClass().getName() + "'");
		Row result = resultSet.one();
		if (resultSet.one() != null) {
			throw new EvaluationStoreException("Multiple files for hashid '"
					+ hashId + "' found. Database is inkonsistent!");
		}
		return result != null;
	}

	@Override
	public final boolean hasDirectoryResults(HashId hashId)
			throws EvaluationStoreException {
		ResultSet resultSet = session.execute("SELECT hashid FROM "
				+ CassandraElementNames.EVALUATION_DIRECTORIES_TABLE
				+ " WHERE hashid='" + hashId.toString()
				+ "' AND resultsClass='" + getDirectoryResultClass().getName()
				+ "'");
		Row result = resultSet.one();
		if (resultSet.one() != null) {
			throw new EvaluationStoreException("Multiple files for hashid '"
					+ hashId + "' found. Database is inkonsistent!");
		}
		return result != null;
	}

	@Override
	public final boolean hasProjectResults(UUID analysisRunUUID)
			throws EvaluationStoreException {
		ResultSet resultSet = session.execute("SELECT uuid FROM "
				+ CassandraElementNames.EVALUATION_PROJECTS_TABLE
				+ " WHERE uuid=" + analysisRunUUID + " AND resultsClass='"
				+ getProjectResultClass().getName() + "'");
		Row result = resultSet.one();
		if (resultSet.one() != null) {
			throw new EvaluationStoreException("Multiple files for run uuid '"
					+ analysisRunUUID + "' found. Database is inkonsistent!");
		}
		return result != null;
	}

	@Override
	public final void storeFileResults(HashId hashId, MetricFileResults results)
			throws EvaluationStoreException {
		PreparedStatement preparedStatement = CassandraConnection
				.getPreparedStatement(session, "storeFileResults:"
						+ getStoreName(), "INSERT INTO "
						+ CassandraElementNames.EVALUATION_FILES_TABLE
						+ "(hashId, resultsClass, results) VALUES (?, ?, ?)");
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(
						byteArrayOutputStream)) {
			objectOutputStream.writeObject(results);
			BoundStatement boundStatement = preparedStatement.bind(
					hashId.toString(), getFileResultClass().getName());
			boundStatement.setBytes("results",
					ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
			session.execute(boundStatement);
			//
			// for (Map<String, Value<?>> row : results.getValues()) {
			// for (String column : row.keySet()) {
			// Value<?> value = row.get(column);
			// value.getParameter().get
			// }
			// }

		} catch (IOException e) {
			throw new EvaluationStoreException(
					"Could not store results for hashId '" + hashId
							+ "' into '" + getStoreName() + "'.", e);
		}
	}

	@Override
	public final void storeDirectoryResults(HashId hashId,
			MetricDirectoryResults results) throws EvaluationStoreException {
		PreparedStatement preparedStatement = CassandraConnection
				.getPreparedStatement(session, "storeDirectoryResults:"
						+ getStoreName(), "INSERT INTO "
						+ CassandraElementNames.EVALUATION_DIRECTORIES_TABLE
						+ "(hashId, resultsClass, results) VALUES (?, ?, ?)");
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
			try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					byteArrayOutputStream)) {
				objectOutputStream.writeObject(results);
				BoundStatement boundStatement = preparedStatement.bind(
						hashId.toString(), getDirectoryResultClass().getName());
				boundStatement.setBytes("results",
						ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
				session.execute(boundStatement);
			}
		} catch (IOException e) {
			throw new EvaluationStoreException(
					"Could not store results for hashId '" + hashId
							+ "' into '" + getStoreName() + "'.", e);
		}
	}

	@Override
	public final void storeProjectResults(UUID analysisRunUUID,
			MetricDirectoryResults results) throws EvaluationStoreException {
		PreparedStatement preparedStatement = CassandraConnection
				.getPreparedStatement(session, "storeProjectResults:"
						+ getStoreName(), "INSERT INTO "
						+ CassandraElementNames.EVALUATION_PROJECTS_TABLE
						+ "(uuid, resultsClass, results) VALUES (?, ?, ?)");
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
			try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					byteArrayOutputStream)) {
				objectOutputStream.writeObject(results);
				BoundStatement boundStatement = preparedStatement.bind(
						analysisRunUUID, getProjectResultClass().getName());
				boundStatement.setBytes("results",
						ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
				session.execute(boundStatement);
			}
		} catch (IOException e) {
			throw new EvaluationStoreException(
					"Could not store results for analysis run UUID '"
							+ analysisRunUUID + "' into '" + getStoreName()
							+ "'.", e);
		}
	}

	@Override
	public final MetricFileResults readFileResults(HashId hashId)
			throws EvaluationStoreException {
		ResultSet resultSet = session
				.execute("SELECT results FROM "
						+ CassandraElementNames.EVALUATION_FILES_TABLE
						+ " WHERE hashid='" + hashId.toString()
						+ "' AND resultsClass='"
						+ getFileResultClass().getName() + "'");
		Row result = resultSet.one();
		if (result == null) {
			return null;
		}
		if (resultSet.one() != null) {
			throw new EvaluationStoreException(
					"Found multiple elements for hashId '" + hashId
							+ "'. Database is inconsistent!");
		}
		ByteBuffer byteBuffer = result.getBytes("results");
		try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				byteBuffer.array(), byteBuffer.position(), byteBuffer.limit())) {
			try (ObjectInputStream objectInputStream = new ObjectInputStream(
					byteArrayInputStream)) {
				MetricFileResults object = (MetricFileResults) objectInputStream
						.readObject();
				return object;
			}
		} catch (IOException | ClassNotFoundException e) {
			throw new EvaluationStoreException(
					"Could not read object with hashId '" + hashId + "'.", e);
		}
	}

	@Override
	public final MetricDirectoryResults readDirectoryResults(HashId hashId)
			throws EvaluationStoreException {
		ResultSet resultSet = session.execute("SELECT results FROM "
				+ CassandraElementNames.EVALUATION_DIRECTORIES_TABLE
				+ " WHERE hashid='" + hashId.toString()
				+ "' AND resultsClass='" + getDirectoryResultClass().getName()
				+ "'");
		Row result = resultSet.one();
		if (result == null) {
			return null;
		}
		if (resultSet.one() != null) {
			throw new EvaluationStoreException(
					"Found multiple elements for hashId '" + hashId
							+ "'. Database is inconsistent!");
		}
		ByteBuffer byteBuffer = result.getBytes("results");
		try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				byteBuffer.array(), byteBuffer.position(), byteBuffer.limit())) {
			try (ObjectInputStream objectInputStream = new ObjectInputStream(
					byteArrayInputStream)) {
				MetricDirectoryResults object = (MetricDirectoryResults) objectInputStream
						.readObject();
				return object;
			}
		} catch (IOException | ClassNotFoundException e) {
			throw new EvaluationStoreException(
					"Could not read object with hashId '" + hashId + "'.", e);
		}
	}

	@Override
	public final MetricDirectoryResults readProjectResults(UUID analysisRunUUID)
			throws EvaluationStoreException {
		ResultSet resultSet = session.execute("SELECT results FROM "
				+ CassandraElementNames.EVALUATION_PROJECTS_TABLE
				+ " WHERE uuid='" + analysisRunUUID + "' AND resultsClass='"
				+ getProjectResultClass().getName() + "'");
		Row result = resultSet.one();
		if (result == null) {
			return null;
		}
		if (resultSet.one() != null) {
			throw new EvaluationStoreException(
					"Found multiple elements for analysis run UUID '"
							+ analysisRunUUID + "'. Database is inconsistent!");
		}
		ByteBuffer byteBuffer = result.getBytes("results");
		try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				byteBuffer.array(), byteBuffer.position(), byteBuffer.limit())) {
			try (ObjectInputStream objectInputStream = new ObjectInputStream(
					byteArrayInputStream)) {
				MetricDirectoryResults object = (MetricDirectoryResults) objectInputStream
						.readObject();
				return object;
			}
		} catch (IOException | ClassNotFoundException e) {
			throw new EvaluationStoreException(
					"Could not read object with analysis run UUID '"
							+ analysisRunUUID + "'.", e);
		}
	}
}
