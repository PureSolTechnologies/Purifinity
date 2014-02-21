package com.puresoltechnologies.purifinity.framework.store.db.analysis.cassandra;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.framework.store.db.CassandraConnection;
import com.puresoltechnologies.purifinity.framework.store.db.CassandraElementNames;

/**
 * Some database operations are quite complex and time consuming. For that
 * purpose caches are provided which provide different objects Java serialized
 * from database. Only in case the Java de-serialization fails, a new object is
 * created and re-cached. This speeds up operations remarkably.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisStoreCacheUtils {

	private static final Logger logger = LoggerFactory
			.getLogger(AnalysisStoreCacheUtils.class);

	/**
	 * This method reads the cached {@link AnalysisFileTree} object stored with
	 * {@link #cacheAnalysisFileTree(UUID, UUID, AnalysisFileTree)}.
	 * 
	 * @param projectUUID
	 * @param runUUID
	 * @return
	 */
	public static AnalysisFileTree readCachedAnalysisFileTree(UUID projectUUID,
			UUID runUUID) {
		Session session = CassandraConnection.getAnalysisSession();
		PreparedStatement preparedStatement = CassandraConnection
				.getPreparedStatement(session, "SELECT persisted_tree FROM "
						+ CassandraElementNames.ANALYSIS_FILE_TREE_CACHE
						+ " WHERE uuid=?");
		BoundStatement boundStatement = preparedStatement.bind(runUUID);
		ResultSet resultSet = session.execute(boundStatement);
		Row result = resultSet.one();
		if (result == null) {
			return null;
		}
		ByteBuffer byteBuffer = result.getBytes("persisted_tree");
		try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				byteBuffer.array(), byteBuffer.position(), byteBuffer.limit())) {
			try (ObjectInputStream objectInputStream = new ObjectInputStream(
					byteArrayInputStream)) {
				AnalysisFileTree object = (AnalysisFileTree) objectInputStream
						.readObject();
				return object;
			}
		} catch (IOException | ClassNotFoundException e) {
			logger.warn("Could not read already cached file tree with uuid '"
					+ runUUID + "'.", e);
			return null;
		}
	}

	/**
	 * This method caches a {@link AnalysisFileTree} object Java serialized into
	 * Cassandra, because it is quite an effort to read it from Titan.
	 * 
	 * @param projectUUID
	 * @param runUUID
	 * @param analysisFileTree
	 */
	public static void cacheAnalysisFileTree(UUID projectUUID, UUID runUUID,
			AnalysisFileTree analysisFileTree) {
		Session session = CassandraConnection.getAnalysisSession();
		PreparedStatement preparedStatement = CassandraConnection
				.getPreparedStatement(session, "INSERT INTO "
						+ CassandraElementNames.ANALYSIS_FILE_TREE_CACHE
						+ " (uuid, persisted_tree) VALUES (?, ?)");
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
			try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					byteArrayOutputStream)) {
				objectOutputStream.writeObject(analysisFileTree);
				BoundStatement boundStatement = preparedStatement.bind(runUUID);
				boundStatement.setBytes("persisted_tree",
						ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
				session.execute(boundStatement);
			}
		} catch (IOException e) {
			logger.warn("Could not cache analysis file tree with uuid '"
					+ runUUID + "'.", e);
		}
	}

}
