package com.puresoltechnologies.purifinity.server.core.impl.analysis.store;

import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

import com.buschmais.xo.api.ResultIterable;
import com.buschmais.xo.api.ResultIterator;
import com.buschmais.xo.api.XOManager;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.AnalysisProjectVertex;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.AnalysisRunVertex;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.ProjectToRunEdge;
import com.puresoltechnologies.purifinity.server.database.titan.TitanElementNames;
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
	public static AnalysisProjectVertex findAnalysisProjectVertex(
			XOManager xoManager, UUID uuid) throws AnalysisStoreException {
		ResultIterable<AnalysisProjectVertex> result = xoManager.find(
				AnalysisProjectVertex.class, uuid.toString());
		ResultIterator<AnalysisProjectVertex> iterator = result.iterator();
		if (!iterator.hasNext()) {
			throw new AnalysisStoreException(
					"Could not find a project with UUID '" + uuid + "'.");
		}
		AnalysisProjectVertex analysisProject = iterator.next();
		if (iterator.hasNext()) {
			throw new AnalysisStoreException("Multiple projects with UUID '"
					+ uuid + "' were found. Database is inconsistent.");
		}
		return analysisProject;
	}

	/**
	 * Creates a new Analysis Run vertex.
	 * 
	 * @param graph
	 * @param analysisProjectVertex
	 * @param uuid
	 * @param creationTime
	 * @param startTime
	 * @param duration
	 * @param description
	 */
	public static void createAnalysisRunVertex(XOManager xoManager,
			AnalysisProjectVertex analysisProjectVertex, UUID uuid,
			Date creationTime, Date startTime, long duration, String description) {
		AnalysisRunVertex analysisRunVertex = xoManager
				.create(AnalysisRunVertex.class);

		analysisRunVertex.setRunUUID(uuid.toString());
		analysisRunVertex.setCreationTime(creationTime);
		analysisRunVertex.setStartTime(startTime);
		analysisRunVertex.setDuration(duration);
		analysisRunVertex.setDescription(description);

		ProjectToRunEdge edge = xoManager.create(analysisProjectVertex,
				ProjectToRunEdge.class, analysisRunVertex);
		edge.setRunUUID(uuid.toString());
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
	public static AnalysisRunVertex findAnalysisRunVertex(XOManager xoManager,
			UUID runUUID) throws AnalysisStoreException {
		ResultIterable<AnalysisRunVertex> analysisRunVertex = xoManager.find(
				AnalysisRunVertex.class, runUUID);
		Iterator<AnalysisRunVertex> runIterator = analysisRunVertex.iterator();
		if (!runIterator.hasNext()) {
			return null;
		}
		AnalysisRunVertex runVertex = runIterator.next();
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
