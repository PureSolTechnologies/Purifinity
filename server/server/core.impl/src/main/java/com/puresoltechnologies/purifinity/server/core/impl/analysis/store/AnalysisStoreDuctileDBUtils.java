package com.puresoltechnologies.purifinity.server.core.impl.analysis.store;

import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import com.buschmais.xo.api.ResultIterable;
import com.buschmais.xo.api.ResultIterator;
import com.buschmais.xo.api.XOManager;
import com.puresoltechnologies.ductiledb.tinkerpop.DuctileVertex;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.AnalysisProjectVertex;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.AnalysisRunVertex;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.ProjectToRunEdge;
import com.puresoltechnologies.purifinity.server.database.ductiledb.utils.DuctileDBElementNames;

/**
 * This class contains methods and functionality which is used several times by
 * the Analysis Store.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AnalysisStoreDuctileDBUtils {

    /**
     * This method looks a specific Analysis Project vertex up in the graph db.
     * 
     * @param graph
     * @param uuid
     * @return
     * @throws AnalysisStoreException
     */
    public static AnalysisProjectVertex findAnalysisProjectVertex(XOManager xoManager, String projectId)
	    throws AnalysisStoreException {
	ResultIterable<AnalysisProjectVertex> result = xoManager.find(AnalysisProjectVertex.class, projectId);
	ResultIterator<AnalysisProjectVertex> iterator = result.iterator();
	if (!iterator.hasNext()) {
	    throw new AnalysisStoreException("Could not find a project with identifier '" + projectId + "'.");
	}
	AnalysisProjectVertex analysisProject = iterator.next();
	if (iterator.hasNext()) {
	    throw new AnalysisStoreException(
		    "Multiple projects with identifier '" + projectId + "' were found. Database is inconsistent.");
	}
	return analysisProject;
    }

    /**
     * Creates a new Analysis Run vertex.
     * 
     * @param graph
     * @param analysisProjectVertex
     * @param runId
     * @param creationTime
     * @param startTime
     * @param duration
     * @param description
     */
    public static void createAnalysisRunVertex(XOManager xoManager, AnalysisProjectVertex analysisProjectVertex,
	    long runId, Date creationTime, Date startTime, long duration, String description) {
	AnalysisRunVertex analysisRunVertex = xoManager.create(AnalysisRunVertex.class);

	analysisRunVertex.setRunId(runId);
	analysisRunVertex.setCreationTime(creationTime);
	analysisRunVertex.setStartTime(startTime);
	analysisRunVertex.setDuration(duration);
	analysisRunVertex.setDescription(description);

	ProjectToRunEdge edge = xoManager.create(analysisProjectVertex, ProjectToRunEdge.class, analysisRunVertex);
	edge.setRunId(runId);
    }

    /**
     * This method looks a specific Analysis Run vertex up in the graph db.
     * 
     * @param graph
     * @param projectUUID
     * @param runId
     * @return
     * @throws AnalysisStoreException
     */
    public static AnalysisRunVertex findAnalysisRunVertex(XOManager xoManager, long runId)
	    throws AnalysisStoreException {
	ResultIterable<AnalysisRunVertex> analysisRunVertex = xoManager.find(AnalysisRunVertex.class, runId);
	Iterator<AnalysisRunVertex> runIterator = analysisRunVertex.iterator();
	if (!runIterator.hasNext()) {
	    return null;
	}
	AnalysisRunVertex runVertex = runIterator.next();
	if (runIterator.hasNext()) {
	    throw new AnalysisStoreException(
		    "Multiple analysis run vertices for '" + runId + "' were found. Database is inconsistent.");
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
    public static DuctileVertex findFileTreeVertex(UUID projectUUID, UUID runUUID, DuctileVertex runVertex)
	    throws AnalysisStoreException {
	GraphTraversal<Vertex, Vertex> fileTreeVertices = runVertex.graph().traversal().V(runVertex.id())
		.hasLabel(DuctileDBElementNames.ANALYZED_FILE_TREE_LABEL).emit();
	if (!fileTreeVertices.hasNext()) {
	    return null;
	}
	DuctileVertex fileTreeVertex = (DuctileVertex) fileTreeVertices.next();
	if (fileTreeVertices.hasNext()) {
	    throw new AnalysisStoreException("Multiple file trees for project='" + projectUUID + "' and run='" + runUUID
		    + "' were found. Database is inconsistent.");
	}
	return fileTreeVertex;
    }

}
