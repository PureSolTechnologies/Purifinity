package com.puresoltechnologies.purifinity.server.core.impl.analysis.store;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.UUID;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.server.database.cassandra.AnalysisStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraElementNames;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraPreparedStatements;

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

    @Inject
    @AnalysisStoreKeyspace
    private Session session;

    @Inject
    private CassandraPreparedStatements cassandraPreparedStatements;

    /**
     * This method reads the cached {@link AnalysisFileTree} object stored with
     * {@link #cacheAnalysisFileTree(UUID, UUID, AnalysisFileTree)}.
     * 
     * @param projectId
     * @param runId
     * @return
     */
    public AnalysisFileTree readCachedAnalysisFileTree(String projectId,
	    long runId) {
	PreparedStatement preparedStatement = cassandraPreparedStatements
		.getPreparedStatement(session, "SELECT persisted_tree FROM "
			+ CassandraElementNames.ANALYSIS_FILE_TREE_CACHE_TABLE
			+ " WHERE project_id=? AND  run_id=?");
	BoundStatement boundStatement = preparedStatement
		.bind(projectId, runId);
	ResultSet resultSet = session.execute(boundStatement);
	Row result = resultSet.one();
	if (result == null) {
	    return null;
	}
	ByteBuffer byteBuffer = result.getBytes("persisted_tree");
	try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
		byteBuffer.array(), byteBuffer.position(), byteBuffer.limit());
		ObjectInputStream objectInputStream = new ObjectInputStream(
			byteArrayInputStream)) {
	    return (AnalysisFileTree) objectInputStream.readObject();
	} catch (IOException | ClassNotFoundException | ClassCastException e) {
	    logger.warn(
		    "Could not read already cached file tree with run_uuid '"
			    + runId + "'.", e);
	    return null;
	}
    }

    /**
     * This method caches a {@link AnalysisFileTree} object Java serialized into
     * Cassandra, because it is quite an effort to read it from Titan.
     * 
     * @param projectId
     * @param runId
     * @param analysisFileTree
     */
    public void cacheAnalysisFileTree(String projectId, long runId,
	    AnalysisFileTree analysisFileTree) {
	PreparedStatement preparedStatement = cassandraPreparedStatements
		.getPreparedStatement(session, "INSERT INTO "
			+ CassandraElementNames.ANALYSIS_FILE_TREE_CACHE_TABLE
			+ " (project_id, run_id, persisted_tree) VALUES (?, ?)");
	try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
	    try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
		    byteArrayOutputStream)) {
		objectOutputStream.writeObject(analysisFileTree);
		BoundStatement boundStatement = preparedStatement.bind(runId);
		boundStatement.setBytes("persisted_tree",
			ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
		session.execute(boundStatement);
	    }
	} catch (IOException e) {
	    logger.warn("Could not cache analysis file tree with run_uuid '"
		    + runId + "'.", e);
	}
    }

    /**
     * This method removes Analysis Run related caches.
     * 
     * @param projectId
     * @param runId
     */
    public void clearAnalysisRunCaches(String projectId, long runId) {
	PreparedStatement preparedStatement = cassandraPreparedStatements
		.getPreparedStatement(session, "DELETE FROM "
			+ CassandraElementNames.ANALYSIS_FILE_TREE_CACHE_TABLE
			+ " WHERE project_id=? AND run_id=?");
	BoundStatement boundStatement = preparedStatement
		.bind(projectId, runId);
	session.execute(boundStatement);
    }

}
