package com.puresoltechnologies.purifinity.server.core.impl.analysis.store;

import java.util.Iterator;

import javax.inject.Inject;

import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisRunFileTree;
import com.puresoltechnologies.purifinity.server.database.titan.TitanElementNames;
import com.puresoltechnologies.purifinity.server.database.titan.VertexType;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

/**
 * This class contains methods to deal with the content tree in Titan graph
 * database.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisStoreContentTreeUtils {

	@Inject
	private AnalysisStoreCassandraUtils analysisStoreCassandraUtils;

	/**
	 * This method adds a new content tree node or content tree part to the
	 * database.
	 * 
	 * @param progressObserver
	 *            is an optional observer to be informed about progress (may be
	 *            null).
	 * @param graph
	 * @param fileTree
	 * @param parentVertex
	 * @param edgeLabel
	 * @return
	 * @throws AnalysisStoreException
	 */
	public Vertex addContentTree(AnalysisStore analysisStore, TitanGraph graph,
			AnalysisRunFileTree fileTree, Vertex parentVertex)
			throws AnalysisStoreException {
		return addContentTreeVertex(analysisStore, graph, fileTree,
				parentVertex, TitanElementNames.ANALYZED_CONTENT_TREE_LABEL);
	}

	/**
	 * This method adds a new content tree node or content tree part to the
	 * database.
	 * 
	 * @param progressObserver
	 *            is an optional observer to be informed about progress (may be
	 *            null).
	 * @param graph
	 * @param fileTree
	 * @param parentVertex
	 * @param edgeLabel
	 * @return
	 * @throws AnalysisStoreException
	 */
	private Vertex addContentTreeVertex(AnalysisStore analysisStore,
			TitanGraph graph, AnalysisRunFileTree fileTree,
			Vertex parentVertex, String edgeLabel)
			throws AnalysisStoreException {
		Iterable<Vertex> vertices = graph
				.query()
				.has(TitanElementNames.TREE_ELEMENT_HASH,
						fileTree.getHashId().toString()).vertices();
		Iterator<Vertex> iterator = vertices.iterator();
		if (iterator.hasNext()) {
			Vertex exstingTreeElementVertex = iterator.next();
			if (iterator.hasNext()) {
				throw new AnalysisStoreException(
						"Content tree vertex for '"
								+ fileTree.getHashId()
								+ "' was found multiple times. Database is inconsistent.");
			}
			Edge edge = parentVertex.addEdge(edgeLabel,
					exstingTreeElementVertex);
			edge.setProperty(TitanElementNames.TREE_ELEMENT_HASH,
					exstingTreeElementVertex
							.getProperty(TitanElementNames.TREE_ELEMENT_HASH));
			edge.setProperty(
					TitanElementNames.TREE_ELEMENT_IS_FILE,
					exstingTreeElementVertex
							.getProperty(TitanElementNames.TREE_ELEMENT_IS_FILE));
			return exstingTreeElementVertex;
		} else {
			Vertex vertex = graph.addVertex(null);
			vertex.setProperty(TitanElementNames.VERTEX_TYPE,
					VertexType.CONTENT_TREE_ELEMENT.name());
			vertex.setProperty(TitanElementNames.TREE_ELEMENT_HASH, fileTree
					.getHashId().toString());
			vertex.setProperty(TitanElementNames.TREE_ELEMENT_IS_FILE,
					fileTree.isFile());
			Edge edge = parentVertex.addEdge(edgeLabel, vertex);
			edge.setProperty(TitanElementNames.TREE_ELEMENT_HASH, fileTree
					.getHashId().toString());
			edge.setProperty(TitanElementNames.TREE_ELEMENT_IS_FILE,
					fileTree.isFile());
			for (AnalysisRunFileTree child : fileTree.getChildren()) {
				if (child.isFile()) {
					addContentTreeVertex(analysisStore, graph, child, vertex,
							TitanElementNames.CONTAINS_FILE_LABEL);
				} else {
					addContentTreeVertex(analysisStore, graph, child, vertex,
							TitanElementNames.CONTAINS_DIRECTORY_LABEL);
				}
			}
			return vertex;
		}
	}

	public void checkAndRemoveAnalysisRunContent(Vertex runVertex)
			throws AnalysisStoreException {
		Iterable<Edge> edges = runVertex.query().direction(Direction.OUT)
				.labels(TitanElementNames.ANALYZED_CONTENT_TREE_LABEL).edges();
		Iterator<Edge> edgeIterator = edges.iterator();
		if (!edgeIterator.hasNext()) {
			return;
		}
		Edge contentEdge = edgeIterator.next();
		if (edgeIterator.hasNext()) {
			throw new AnalysisStoreException(
					"Analysis run '"
							+ runVertex
									.getProperty(TitanElementNames.ANALYSIS_NAME_PROPERTY)
							+ "'contains multiple content nodes. Database is inconsistent!");
		}
		Vertex contentVertex = contentEdge.getVertex(Direction.IN);
		contentEdge.remove();
		checkAndRemoveContentNode(contentVertex);
	}

	private void checkAndRemoveContentNode(Vertex contentVertex)
			throws AnalysisStoreException {
		if (!VertexType.CONTENT_TREE_ELEMENT.name().equals(
				contentVertex.getProperty(TitanElementNames.VERTEX_TYPE))) {
			throw new IllegalArgumentException(
					"The vertex is not a content tree vertex.");
		}
		Iterable<Edge> incomingEdges = contentVertex.query()
				.direction(Direction.IN).edges();
		Iterator<Edge> incomingEdgesIterator = incomingEdges.iterator();
		if (incomingEdgesIterator.hasNext()) {
			// We have incoming edges, so this is a shared content. We are not
			// allowed to remove it.
			return;
		}
		Iterable<Edge> outgoingEdges = contentVertex.query()
				.direction(Direction.OUT).edges();
		Iterator<Edge> outgoingEdgesIterator = outgoingEdges.iterator();
		while (outgoingEdgesIterator.hasNext()) {
			Edge edge = outgoingEdgesIterator.next();
			Vertex childVertex = edge.getVertex(Direction.IN);
			VertexType vertexType = VertexType.valueOf((String) childVertex
					.getProperty(TitanElementNames.VERTEX_TYPE));
			if (vertexType == VertexType.CONTENT_TREE_ELEMENT) {
				edge.remove();
				checkAndRemoveContentNode(childVertex);
			} else {
				throw new AnalysisStoreException("Unsupported vertex found.");
			}
		}
		HashId hashId = HashId.valueOf((String) contentVertex
				.getProperty(TitanElementNames.TREE_ELEMENT_HASH));
		contentVertex.remove();
		analysisStoreCassandraUtils.removeAnalysisFile(hashId);
		// FIXME: The evaluation values need to be removed!
		// boolean isFile = (Boolean) contentVertex
		// .getProperty(TitanElementNames.TREE_ELEMENT_IS_FILE);
		// if (isFile) {
		// EvaluatorStoreCassandraUtils.deleteFileEvaluation(hashId);
		// } else {
		// EvaluatorStoreCassandraUtils.deleteDirectoryEvaluation(hashId);
		// }
	}
}
