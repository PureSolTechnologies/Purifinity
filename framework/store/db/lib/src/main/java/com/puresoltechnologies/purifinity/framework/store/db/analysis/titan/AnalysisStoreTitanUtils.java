package com.puresoltechnologies.purifinity.framework.store.db.analysis.titan;

import java.util.Iterator;
import java.util.UUID;

import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.framework.store.db.TitanElementNames;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Vertex;

/**
 * This class contains methods and functionality which is used several times by
 * the Analysis Store.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AnalysisStoreTitanUtils {

	/**
	 * This method looks a specific Analysis Project vertex up in the graph db.
	 * 
	 * @param graph
	 * @param uuid
	 * @return
	 * @throws AnalysisStoreException
	 */
	public static Vertex findAnalysisProjectVertex(TitanGraph graph, UUID uuid)
			throws AnalysisStoreException {
		Iterable<Vertex> vertices = graph
				.query()
				.has(TitanElementNames.ANALYSIS_PROJECT_UUID_PROPERTY,
						uuid.toString()).vertices();
		Iterator<Vertex> vertexIterator = vertices.iterator();
		if (!vertexIterator.hasNext()) {
			throw new AnalysisStoreException(
					"Could not find a project with UUID '" + uuid + "'.");
		}
		Vertex projectVertext = vertexIterator.next();
		if (vertexIterator.hasNext()) {
			throw new AnalysisStoreException("Multiple projects with UUID '"
					+ uuid + "' were found. Database is inconsistent.");
		}
		return projectVertext;
	}

	/**
	 * This method looks a specific Analysis Run vertex up in the graph db.
	 * 
	 * @param graph
	 * @param projectUUID
	 * @param runUUID
	 * @return
	 * @throws AnalysisStoreException
	 */
	public static Vertex findAnalysisRunVertex(TitanGraph graph,
			UUID projectUUID, UUID runUUID) throws AnalysisStoreException {
		Vertex projectVertex = findAnalysisProjectVertex(graph, projectUUID);
		return findAnalysisRunVertex(graph, projectVertex, runUUID);
	}

	/**
	 * This method looks a specific Analysis Run vertex up in the graph db.
	 * 
	 * @param graph
	 * @param projectUUID
	 * @param runUUID
	 * @return
	 * @throws AnalysisStoreException
	 */
	public static Vertex findAnalysisRunVertex(TitanGraph graph,
			Vertex projectVertex, UUID runUUID) throws AnalysisStoreException {
		Iterable<Vertex> analysisRuns = projectVertex
				.query()
				.direction(Direction.OUT)
				.labels(TitanElementNames.HAS_ANALYSIS_RUN_LABEL)
				.has(TitanElementNames.ANALYSIS_RUN_UUID_PROPERTY,
						runUUID.toString()).vertices();
		Iterator<Vertex> runIterator = analysisRuns.iterator();
		if (!runIterator.hasNext()) {
			return null;
		}
		Vertex runVertex = runIterator.next();
		if (runIterator.hasNext()) {
			throw new AnalysisStoreException(
					"Multiple analysis run vertices for '" + runUUID
							+ "' were found. Database is inconsistent.");
		}
		return runVertex;
	}

	/**
	 * This method looks up a specific File Tree vertex of a specific Analysis
	 * Run.
	 * 
	 * @param projectUUID
	 * @param runUUID
	 * @param runVertex
	 * @return
	 * @throws AnalysisStoreException
	 */
	public static Vertex findFileTreeVertex(UUID projectUUID, UUID runUUID,
			Vertex runVertex) throws AnalysisStoreException {
		Iterable<Vertex> fileTreeVertices = runVertex.query()
				.labels(TitanElementNames.ANALYZED_FILE_TREE_LABEL).vertices();
		Iterator<Vertex> analysisVertexIterator = fileTreeVertices.iterator();
		if (!analysisVertexIterator.hasNext()) {
			return null;
		}
		Vertex fileTreeVertex = analysisVertexIterator.next();
		if (analysisVertexIterator.hasNext()) {
			throw new AnalysisStoreException(
					"Multiple file trees for project='" + projectUUID
							+ "' and run='" + runUUID
							+ "' were found. Database is inconsistent.");
		}
		return fileTreeVertex;
	}

}
