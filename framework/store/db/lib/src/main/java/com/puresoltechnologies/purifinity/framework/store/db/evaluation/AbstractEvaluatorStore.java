package com.puresoltechnologies.purifinity.framework.store.db.evaluation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricFileResults;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.CassandraUtils;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.ReplicationStrategy;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.framework.store.db.CassandraConnection;

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

		Cluster cluster = CassandraConnection.getCluster();
		CassandraUtils.checkAndCreateKeyspace(cluster, getStoreName(),
				ReplicationStrategy.SIMPLE_STRATEGY, 1);
		session = cluster.connect(getStoreName());
		KeyspaceMetadata keyspace = cluster.getMetadata().getKeyspace(
				getStoreName());
		CassandraUtils
				.checkAndCreateTable(
						session,
						keyspace,
						CassandraConnection.EVALUATION_PROJECTS_TABLE,
						"CREATE TABLE "
								+ CassandraConnection.EVALUATION_PROJECTS_TABLE
								+ " (run_uuid uuid, results blob, PRIMARY KEY(run_uuid));");
		CassandraUtils
				.checkAndCreateTable(
						session,
						keyspace,
						CassandraConnection.EVALUATION_DIRECTORIES_TABLE,
						"CREATE TABLE "
								+ CassandraConnection.EVALUATION_DIRECTORIES_TABLE
								+ " (hashid varchar, results blob, PRIMARY KEY(hashid));");
		CassandraUtils
				.checkAndCreateTable(
						session,
						keyspace,
						CassandraConnection.EVALUATION_FILES_TABLE,
						"CREATE TABLE "
								+ CassandraConnection.EVALUATION_FILES_TABLE
								+ " (hashid varchar, results blob, PRIMARY KEY(hashid));");

	}

	public final void shutdown() {
		session.shutdown();
	}

	@Override
	public final boolean hasFileResults(HashId hashId)
			throws EvaluationStoreException {
		ResultSet resultSet = session.execute("SELECT hashid FROM "
				+ CassandraConnection.EVALUATION_FILES_TABLE
				+ " WHERE hashid='" + hashId.toString() + "'");
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
				+ CassandraConnection.EVALUATION_DIRECTORIES_TABLE
				+ " WHERE hashid='" + hashId.toString() + "'");
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
		ResultSet resultSet = session.execute("SELECT run_uuid FROM "
				+ CassandraConnection.EVALUATION_DIRECTORIES_TABLE
				+ " WHERE run_uuid='" + analysisRunUUID + "'");
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
						+ CassandraConnection.EVALUATION_FILES_TABLE
						+ "(hashId, results) VALUES (?,?)");
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
			try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					byteArrayOutputStream)) {
				objectOutputStream.writeObject(results);
				BoundStatement boundStatement = preparedStatement.bind(hashId
						.toString());
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
	public final void storeDirectoryResults(HashId hashId,
			MetricDirectoryResults results) throws EvaluationStoreException {
		PreparedStatement preparedStatement = CassandraConnection
				.getPreparedStatement(session, "storeFileResults:"
						+ getStoreName(), "INSERT INTO "
						+ CassandraConnection.EVALUATION_DIRECTORIES_TABLE
						+ "(hashId, results) VALUES (?,?)");
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
			try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					byteArrayOutputStream)) {
				objectOutputStream.writeObject(results);
				BoundStatement boundStatement = preparedStatement.bind(hashId
						.toString());
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
				.getPreparedStatement(session, "storeFileResults:"
						+ getStoreName(), "INSERT INTO "
						+ CassandraConnection.EVALUATION_PROJECTS_TABLE
						+ "(run_uuid, results) VALUES (?,?)");
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
			try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					byteArrayOutputStream)) {
				objectOutputStream.writeObject(results);
				BoundStatement boundStatement = preparedStatement
						.bind(analysisRunUUID);
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
		ResultSet resultSet = session.execute("SELECT results FROM "
				+ CassandraConnection.EVALUATION_FILES_TABLE
				+ " WHERE hashid='" + hashId.toString() + "'");
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
				+ CassandraConnection.EVALUATION_DIRECTORIES_TABLE
				+ " WHERE hashid='" + hashId.toString() + "'");
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
				+ CassandraConnection.EVALUATION_PROJECTS_TABLE
				+ " WHERE run_uuid='" + analysisRunUUID + "'");
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
