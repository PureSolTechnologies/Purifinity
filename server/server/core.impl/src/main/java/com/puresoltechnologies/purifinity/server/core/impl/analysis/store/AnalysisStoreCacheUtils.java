package com.puresoltechnologies.purifinity.server.core.impl.analysis.store;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.inject.Inject;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.server.database.hadoop.utils.HadoopClientHelper;

/**
 * Some database operations are quite complex and time consuming. For that
 * purpose caches are provided which provide different objects Java serialized
 * from database. Only in case the Java de-serialization fails, a new object is
 * created and re-cached. This speeds up operations remarkably.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AnalysisStoreCacheUtils {

    private static final Logger logger = LoggerFactory.getLogger(AnalysisStoreCacheUtils.class);

    private static final String CACHE_DIRECTORY = HadoopClientHelper.PURIFINITY_DIRECTORY + "/cache";

    @Inject
    private FileSystem fileSystem;

    private static Path createPath(String projectId, long runId) {
	return new Path(new Path(new Path(CACHE_DIRECTORY, projectId), String.valueOf(runId)), "file_tree.cache");
    }

    /**
     * This method reads the cached {@link AnalysisFileTree} object stored with
     * {@link #cacheAnalysisFileTree(String, long, AnalysisFileTree)}.
     * 
     * @param projectId
     *            is the id of the project.
     * @param runId
     *            is the id of the run.
     * @return a {@link AnalysisFileTree} is returned.
     */
    public AnalysisFileTree readCachedAnalysisFileTree(String projectId, long runId) {
	try {
	    Path path = createPath(projectId, runId);
	    if (!fileSystem.exists(path)) {
		return null;
	    }
	    try (InputStream inputStream = fileSystem.open(path);
		    ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
		return (AnalysisFileTree) objectInputStream.readObject();
	    }
	} catch (IOException | ClassNotFoundException | ClassCastException e) {
	    logger.warn("Could not read already cached file tree with run_uuid '" + runId + "'.", e);
	    return null;
	}
    }

    /**
     * This method caches a {@link AnalysisFileTree} object Java serialized into
     * Hadoop, because it is quite an effort to read it everytime from
     * DuctileDB.
     * 
     * @param projectId
     *            is the id of the project.
     * @param runId
     *            is the id of the run.
     * @param analysisFileTree
     *            is the file tree to be stored.
     */
    public void cacheAnalysisFileTree(String projectId, long runId, AnalysisFileTree analysisFileTree) {
	try {
	    Path path = createPath(projectId, runId);
	    if (fileSystem.exists(path)) {
		fileSystem.delete(path, true);
	    }
	    fileSystem.createNewFile(path);
	    try (FSDataOutputStream fsDataOutputStream = fileSystem.create(path)) {
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fsDataOutputStream)) {
		    objectOutputStream.writeObject(analysisFileTree);
		}
	    }
	} catch (IOException e) {
	    logger.warn("Could not cache analysis file tree with run_uuid '" + runId + "'.", e);
	}
    }

    /**
     * This method removes Analysis Run related caches.
     * 
     * @param projectId
     *            is the id of the project.
     * @param runId
     *            is the id of the run.
     */
    public void clearAnalysisRunCaches(String projectId, long runId) {
	try {
	    Path path = createPath(projectId, runId);
	    if (fileSystem.exists(path)) {
		fileSystem.delete(path, true);
	    }
	} catch (IOException e) {
	    logger.warn("Could not clear cache analysis file tree with run id'" + runId + "'.", e);
	}
    }

}
