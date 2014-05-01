package com.puresoltechnologies.purifinity.framework.analysis.impl.store.titan;

import java.util.Iterator;

import org.apache.commons.lang.StringUtils;

import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.misc.ProgressObserver;
import com.puresoltechnologies.commons.trees.api.TreeUtils;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.database.titan.utils.TitanElementNames;
import com.puresoltechnologies.purifinity.database.titan.utils.TitanUtils;
import com.puresoltechnologies.purifinity.database.titan.utils.VertexType;
import com.puresoltechnologies.purifinity.framework.analysis.impl.store.cassandra.AnalysisStoreCassandraUtils;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
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
	public static Vertex addContentTree(AnalysisStore analysisStore,
			ProgressObserver<AnalysisStore> progressObserver, TitanGraph graph,
			AnalysisFileTree fileTree, Vertex parentVertex)
			throws AnalysisStoreException {
		return addContentTreeVertex(analysisStore, progressObserver, graph,
				fileTree, parentVertex,
				TitanElementNames.ANALYZED_CONTENT_TREE_LABEL);
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
	public static Vertex addContentTreeVertex(AnalysisStore analysisStore,
			ProgressObserver<AnalysisStore> progressObserver, TitanGraph graph,
			AnalysisFileTree fileTree, Vertex parentVertex, String edgeLabel)
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
			if (progressObserver != null) {
				progressObserver.updateWork(analysisStore,
						"Content found for '" + fileTree.getName() + "'.",
						TreeUtils.countNodes(fileTree));
			}
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
			for (AnalysisFileTree child : fileTree.getChildren()) {
				if (child.isFile()) {
					addContentTreeVertex(analysisStore, progressObserver,
							graph, child, vertex,
							TitanElementNames.CONTAINS_FILE_LABEL);
				} else {
					addContentTreeVertex(analysisStore, progressObserver,
							graph, child, vertex,
							TitanElementNames.CONTAINS_DIRECTORY_LABEL);
				}
			}
			AnalysisStoreFileTreeUtils.addMetadata(vertex, fileTree);
			if (fileTree.isFile()) {
				for (AnalysisInformation analyzedCode : fileTree.getAnalyses()) {
					storeAnalysisInformation(graph, vertex, analyzedCode);
				}
			}
			if (progressObserver != null) {
				progressObserver.updateWork(analysisStore,
						"Created content for '" + fileTree.getName() + "'.", 1);
			}
			return vertex;
		}
	}

	/**
	 * This method adds a analysis information to a content node.
	 * 
	 * @param graph
	 * @param treeNode
	 * @param analysis
	 * @throws AnalysisStoreException
	 */
	public static void storeAnalysisInformation(TitanGraph graph,
			Vertex treeNode, AnalysisInformation analysis)
			throws AnalysisStoreException {
		Vertex analysisVertex = graph.addVertex(null);

		Edge analysisEdge = treeNode.addEdge(
				TitanElementNames.HAS_ANALYSIS_LABEL, analysisVertex);
		analysisEdge.setProperty(TitanElementNames.ANALYSIS_NAME_PROPERTY,
				"n/a");
		analysisEdge.setProperty(TitanElementNames.ANALYSIS_VERSION_PROPERTY,
				"n/a");
		analysisEdge.setProperty(
				TitanElementNames.ANALYSIS_START_TIME_PROPERTY,
				analysis.getStartTime());

		analysisVertex.setProperty(TitanElementNames.VERTEX_TYPE,
				VertexType.ANALYSIS.name());

		analysisVertex.setProperty(TitanElementNames.ANALYSIS_NAME_PROPERTY,
				"n/a");
		analysisVertex.setProperty(TitanElementNames.ANALYSIS_VERSION_PROPERTY,
				"n/a");
		analysisVertex.setProperty(
				TitanElementNames.ANALYSIS_START_TIME_PROPERTY,
				analysis.getStartTime());
		analysisVertex.setProperty(
				TitanElementNames.ANALYSIS_DURATION_PROPERTY,
				analysis.getDuration());
		analysisVertex.setProperty(
				TitanElementNames.ANALYSIS_LANGUAGE_NAME_PROPERTY,
				analysis.getLanguageName());
		analysisVertex.setProperty(
				TitanElementNames.ANALYSIS_LANGUAGE_VERSION_PROPERTY,
				analysis.getLanguageVersion());
		analysisVertex.setProperty(
				TitanElementNames.ANALYSIS_SUCCESSFUL_PROPERTY,
				analysis.isSuccessful());
		String message = analysis.getMessage();
		if (!StringUtils.isEmpty(message)) {
			analysisVertex.setProperty(
					TitanElementNames.ANALYSIS_MESSAGE_PROPERTY, message);
		}
		graph.commit();
	}

	public static void checkAndRemoveAnalysisRunContent(Vertex runVertex)
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

	private static void checkAndRemoveContentNode(Vertex contentVertex)
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
			} else if (vertexType == VertexType.ANALYSIS) {
				edge.remove();
				removeAnalysis(childVertex);
			} else {
				throw new AnalysisStoreException("Unsupported vertex found.");
			}
		}
		HashId hashId = HashId.valueOf((String) contentVertex
				.getProperty(TitanElementNames.TREE_ELEMENT_HASH));
		contentVertex.remove();
		AnalysisStoreCassandraUtils.removeAnalysisFile(hashId);
		// FIXME: The evaluation values need to be removed!
		// boolean isFile = (Boolean) contentVertex
		// .getProperty(TitanElementNames.TREE_ELEMENT_IS_FILE);
		// if (isFile) {
		// EvaluatorStoreCassandraUtils.deleteFileEvaluation(hashId);
		// } else {
		// EvaluatorStoreCassandraUtils.deleteDirectoryEvaluation(hashId);
		// }
	}

	public static void removeAnalysis(Vertex analysisVertex) {
		if (!VertexType.ANALYSIS.name().equals(
				analysisVertex.getProperty(TitanElementNames.VERTEX_TYPE))) {
			throw new IllegalArgumentException(
					"The vertex is not analysis vertex.");
		}
		Iterable<Edge> edges = analysisVertex.query().edges();
		Iterator<Edge> edgeIterator = edges.iterator();
		if (edgeIterator.hasNext()) {
			while (edgeIterator.hasNext()) {
				TitanUtils
						.printEdgeInformation(System.err, edgeIterator.next());
			}
			throw new IllegalStateException(
					"Vertex has still edges and cannot be deleted.");
		}
		analysisVertex.remove();
	}
}
