package com.puresoltechnologies.purifinity.server.core.impl.analysis.store;

import java.util.Date;
import java.util.Iterator;

import com.buschmais.xo.api.ResultIterable;
import com.buschmais.xo.api.ResultIterator;
import com.buschmais.xo.api.XOManager;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.AnalysisProjectVertex;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.AnalysisRunVertex;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.ProjectToRunEdge;

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
     * @param xoManager
     *            is the {@link XOManager} to use for graph access.
     * @param projectId
     *            is the id of the project.
     * @return An {@link AnalysisProjectVertex} is returned.
     * @throws AnalysisStoreException
     *             is thrown in case of analysis store issues.
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
     * @param xoManager
     *            is the {@link XOManager} to use for graph access.
     * @param analysisProjectVertex
     *            is the vertex of the analysis project.
     * @param runId
     *            is the id of the run.
     * @param creationTime
     *            is the creation time.
     * @param startTime
     *            is the start time.
     * @param duration
     *            is the duration of the run.
     * @param description
     *            is a description string.
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
     * @param xoManager
     *            is the {@link XOManager} to use for graph access.
     * @param runId
     *            is the of the run.
     * @return An {@link AnalysisRunVertex} object is returned.
     * @throws AnalysisStoreException
     *             is thrown in case of analysis store issues.
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
}
