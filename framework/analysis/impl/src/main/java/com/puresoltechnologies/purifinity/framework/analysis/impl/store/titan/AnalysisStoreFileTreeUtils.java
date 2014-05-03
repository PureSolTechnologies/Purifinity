package com.puresoltechnologies.purifinity.framework.analysis.impl.store.titan;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.misc.ProgressObserver;
import com.puresoltechnologies.parsers.api.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.database.titan.utils.TitanConnection;
import com.puresoltechnologies.purifinity.database.titan.utils.TitanElementNames;
import com.puresoltechnologies.purifinity.database.titan.utils.VertexType;
import com.puresoltechnologies.purifinity.framework.analysis.impl.SourceCodeLocationCreator;
import com.puresoltechnologies.purifinity.framework.analysis.impl.store.AnalysisStoreDAO;
import com.puresoltechnologies.purifinity.framework.commons.utils.PropertiesUtils;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

/**
 * This class contains functionality for {@link AnalysisFileTree}s in Titan
 * graph database.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisStoreFileTreeUtils {

	/**
	 * This method adds a new file tree to a Analysis Run vertex.
	 * 
	 * @param analysisStore
	 * @param progressObserver
	 * @param graph
	 * @param fileTree
	 * @param analysisRunVertex
	 * @return
	 * @throws AnalysisStoreException
	 */
	public static Vertex addFileTree(AnalysisStore analysisStore,
			ProgressObserver<AnalysisStore> progressObserver, TitanGraph graph,
			AnalysisFileTree fileTree, Vertex analysisRunVertex)
			throws AnalysisStoreException {
		return addFileTreeVertex(analysisStore, progressObserver, graph,
				fileTree, analysisRunVertex,
				TitanElementNames.ANALYZED_FILE_TREE_LABEL);
	}

	/**
	 * This method adds a new file tree node to a parent vertex.
	 * 
	 * @param analysisStore
	 * @param progressObserver
	 * @param graph
	 * @param fileTree
	 * @param parentVertex
	 * @param edgeLabel
	 * @return
	 * @throws AnalysisStoreException
	 */
	private static Vertex addFileTreeVertex(AnalysisStore analysisStore,
			ProgressObserver<AnalysisStore> progressObserver, TitanGraph graph,
			AnalysisFileTree fileTree, Vertex parentVertex, String edgeLabel)
			throws AnalysisStoreException {
		Vertex vertex = graph.addVertex(null);
		vertex.setProperty(TitanElementNames.VERTEX_TYPE,
				VertexType.FILE_TREE_ELEMENT.name());
		vertex.setProperty(TitanElementNames.TREE_ELEMENT_NAME,
				fileTree.getName());
		vertex.setProperty(TitanElementNames.TREE_ELEMENT_IS_FILE,
				fileTree.isFile());
		SourceCodeLocation sourceCodeLocation = fileTree
				.getSourceCodeLocation();
		if (sourceCodeLocation != null) {
			vertex.setProperty(
					TitanElementNames.TREE_ELEMENT_SOURCE_CODE_LOCATION,
					PropertiesUtils.toString(sourceCodeLocation
							.getSerialization()));
		}
		Edge edge = parentVertex.addEdge(edgeLabel, vertex);
		edge.setProperty(TitanElementNames.TREE_ELEMENT_HASH, fileTree
				.getHashId().toString());
		edge.setProperty(TitanElementNames.TREE_ELEMENT_IS_FILE,
				fileTree.isFile());

		AnalysisStoreFileTreeUtils.addMetadata(vertex, fileTree);
		graph.commit();

		Iterable<Vertex> vertices = graph
				.query()
				.has(TitanElementNames.TREE_ELEMENT_HASH,
						fileTree.getHashId().toString()).vertices();
		Iterator<Vertex> vertexIterator = vertices.iterator();
		if (!vertexIterator.hasNext()) {
			throw new AnalysisStoreException(
					"Could not find content node for file tree node '"
							+ fileTree.toString() + "'.");
		}
		Vertex contentVertex = vertexIterator.next();
		Edge contentEdge = vertex.addEdge(TitanElementNames.HAS_CONTENT_LABEL,
				contentVertex);
		// XXX
		graph.commit();

		if (fileTree.isFile()) {
			for (AnalysisInformation analyzedCode : fileTree.getAnalyses()) {
				// XXX do we really need this information here?
				AnalysisStoreContentTreeUtils.storeAnalysisInformation(graph,
						vertex, analyzedCode);
			}
		}

		if (progressObserver != null) {
			progressObserver.updateWork(analysisStore,
					"Created file tree node for '" + fileTree.getName() + "'.",
					1);
		}

		for (AnalysisFileTree child : fileTree.getChildren()) {
			if (child.isFile()) {
				addFileTreeVertex(analysisStore, progressObserver, graph,
						child, vertex, TitanElementNames.CONTAINS_FILE_LABEL);
			} else {
				addFileTreeVertex(analysisStore, progressObserver, graph,
						child, vertex,
						TitanElementNames.CONTAINS_DIRECTORY_LABEL);
			}
		}
		return vertex;
	}

	/**
	 * This method adds meta data to the file tree node.
	 * 
	 * @param vertex
	 * @param fileTreeNode
	 * @throws AnalysisStoreException
	 */
	public static void addMetadata(Vertex vertex, AnalysisFileTree fileTreeNode)
			throws AnalysisStoreException {
		if (fileTreeNode.isFile()) {
			long size = AnalysisStoreDAO.getFileSize(fileTreeNode.getHashId());
			vertex.setProperty(TitanElementNames.TREE_ELEMENT_SIZE, size);
		} else {
			Iterable<Vertex> childVertexes = vertex
					.query()
					.direction(Direction.OUT)
					.labels(TitanElementNames.CONTAINS_DIRECTORY_LABEL,
							TitanElementNames.CONTAINS_FILE_LABEL).vertices();
			Iterator<Vertex> childVertexIterator = childVertexes.iterator();
			long files = 0;
			long directories = 0;
			long filesRecursive = 0;
			long directoriesRecursive = 0;
			long size = 0;
			long sizeRecursive = 0;
			while (childVertexIterator.hasNext()) {
				Vertex childVertex = childVertexIterator.next();
				boolean isFile = (boolean) childVertex
						.getProperty(TitanElementNames.TREE_ELEMENT_IS_FILE);
				if (isFile) {
					files++;
					filesRecursive++;
					long fileSize = (long) childVertex
							.getProperty(TitanElementNames.TREE_ELEMENT_SIZE);
					size += fileSize;
					sizeRecursive += fileSize;
				} else {
					directories++;
					directoriesRecursive++;
					Long dr = (Long) childVertex
							.getProperty(TitanElementNames.TREE_ELEMENT_CONTAINS_DIRECTORIES_RECURSIVE);
					directoriesRecursive += dr != null ? dr : 0;
					Long fr = (Long) childVertex
							.getProperty(TitanElementNames.TREE_ELEMENT_CONTAINS_FILES_RECURSIVE);
					filesRecursive += fr != null ? fr : 0;
					Long sr = (Long) childVertex
							.getProperty(TitanElementNames.TREE_ELEMENT_SIZE_RECURSIVE);
					sizeRecursive += sr != null ? sr : 0;
				}
			}
			vertex.setProperty(TitanElementNames.TREE_ELEMENT_CONTAINS_FILES,
					files);
			vertex.setProperty(
					TitanElementNames.TREE_ELEMENT_CONTAINS_FILES_RECURSIVE,
					filesRecursive);
			vertex.setProperty(
					TitanElementNames.TREE_ELEMENT_CONTAINS_DIRECTORIES,
					directories);
			vertex.setProperty(
					TitanElementNames.TREE_ELEMENT_CONTAINS_DIRECTORIES_RECURSIVE,
					directoriesRecursive);
			vertex.setProperty(TitanElementNames.TREE_ELEMENT_SIZE, size);
			vertex.setProperty(TitanElementNames.TREE_ELEMENT_SIZE_RECURSIVE,
					sizeRecursive);
		}
	}

	/**
	 * This method reads the analysis file tree from a analysis run.
	 * 
	 * @param projectUUID
	 * @param runUUID
	 * @return
	 * @throws AnalysisStoreException
	 */
	public static AnalysisFileTree createAnalysisFileTree(UUID projectUUID,
			UUID runUUID) throws AnalysisStoreException {
		TitanGraph graph = TitanConnection.getGraph();
		try {
			Iterable<Vertex> runVertices = graph.query()
					.has(TitanElementNames.ANALYSIS_RUN_UUID_PROPERTY, runUUID)
					.vertices();
			Iterator<Vertex> runVertexIterator = runVertices.iterator();
			if (!runVertexIterator.hasNext()) {
				return null;
			}
			Vertex runVertex = runVertexIterator.next();
			if (runVertexIterator.hasNext()) {
				throw new AnalysisStoreException(
						"Multiple analysis runs were found for UUID '"
								+ runUUID + "'. Database is inconsistent!");
			}
			Vertex fileTreeVertex = AnalysisStoreTitanUtils.findFileTreeVertex(
					projectUUID, runUUID, runVertex);
			if (fileTreeVertex == null) {
				return null;
			}
			return convertToAnalysisFileTree(fileTreeVertex, null);
		} finally {
			graph.rollback();
		}
	}

	/**
	 * This method reads from Titan the whole File Tree into a
	 * {@link AnalysisFileTree}. This method only should be used if no cached
	 * file tree is available to create a new tree which is also cached for
	 * later use. This method needs a significant amount of time.
	 * 
	 * @param fileTreeVertex
	 * @param parent
	 * @return
	 * @throws AnalysisStoreException
	 */
	private static AnalysisFileTree convertToAnalysisFileTree(
			Vertex fileTreeVertex, AnalysisFileTree parent)
			throws AnalysisStoreException {
		AnalysisFileTree hashIdFileTree = createAnalysisFileTreeNode(
				fileTreeVertex, parent);
		Iterable<Vertex> vertices = fileTreeVertex
				.query()
				.labels(TitanElementNames.CONTAINS_DIRECTORY_LABEL,
						TitanElementNames.CONTAINS_FILE_LABEL)
				.direction(Direction.OUT).vertices();
		Iterator<Vertex> vertexIterator = vertices.iterator();
		while (vertexIterator.hasNext()) {
			convertToAnalysisFileTree(vertexIterator.next(), hashIdFileTree);
		}
		return hashIdFileTree;
	}

	/**
	 * This method creates a new {@link AnalysisFileTree} object out of a single
	 * file tree node.
	 * 
	 * @param fileTreeVertex
	 * @param parent
	 * @return
	 * @throws AnalysisStoreException
	 */
	private static AnalysisFileTree createAnalysisFileTreeNode(
			Vertex fileTreeVertex, AnalysisFileTree parent)
			throws AnalysisStoreException {
		String name = (String) fileTreeVertex
				.getProperty(TitanElementNames.TREE_ELEMENT_NAME);
		boolean isFile = (boolean) fileTreeVertex
				.getProperty(TitanElementNames.TREE_ELEMENT_IS_FILE);
		Object serializedSourceCodeLocation = fileTreeVertex
				.getProperty(TitanElementNames.TREE_ELEMENT_SOURCE_CODE_LOCATION);
		SourceCodeLocation sourceCodeLocation = serializedSourceCodeLocation != null ? SourceCodeLocationCreator
				.createFromSerialization(PropertiesUtils
						.fromString(serializedSourceCodeLocation.toString()))
				: null;
		Iterable<Vertex> contentVertices = fileTreeVertex.query()
				.labels(TitanElementNames.HAS_CONTENT_LABEL).vertices();
		Iterator<Vertex> contextVertexIterator = contentVertices.iterator();
		if (!contextVertexIterator.hasNext()) {
			throw new AnalysisStoreException(
					"Could not find content node for file tree node '"
							+ parent.toString() + "'.");
		}
		Vertex contentVertex = contextVertexIterator.next();
		String hash = contentVertex
				.getProperty(TitanElementNames.TREE_ELEMENT_HASH);
		final List<AnalysisInformation> analyses;
		if (isFile) {
			analyses = readAnalyses(fileTreeVertex, hash);
		} else {
			analyses = null;
		}
		AnalysisFileTree hashIdFileTree = new AnalysisFileTree(parent, name,
				HashId.valueOf(hash), isFile, sourceCodeLocation, analyses);
		return hashIdFileTree;
	}

	/**
	 * This method reads all analyzes which are attached to a file tree node.
	 * 
	 * @param fileTreeVertex
	 * @param hash
	 * @return
	 */
	private static List<AnalysisInformation> readAnalyses(
			Vertex fileTreeVertex, String hash) {
		List<AnalysisInformation> analyses = new ArrayList<AnalysisInformation>();
		Iterable<Vertex> analysisVertices = fileTreeVertex.query()
				.direction(Direction.OUT)
				.labels(TitanElementNames.HAS_ANALYSIS_LABEL).vertices();
		Iterator<Vertex> analysisVertexIterator = analysisVertices.iterator();
		while (analysisVertexIterator.hasNext()) {
			Vertex analysisVertex = analysisVertexIterator.next();
			AnalysisInformation analysis = readAnalysisInformation(hash,
					analysisVertex);
			analyses.add(analysis);
		}
		return analyses;
	}

	/**
	 * This method reads the analysis information from an Analysis Node attached
	 * as properties.
	 * 
	 * @param hashId
	 * @param analysisVertex
	 * @return
	 */
	private static AnalysisInformation readAnalysisInformation(String hashId,
			Vertex analysisVertex) {
		Date startTime = analysisVertex
				.getProperty(TitanElementNames.ANALYSIS_START_TIME_PROPERTY);
		long duration = analysisVertex
				.getProperty(TitanElementNames.ANALYSIS_DURATION_PROPERTY);
		String languageName = analysisVertex
				.getProperty(TitanElementNames.ANALYSIS_LANGUAGE_NAME_PROPERTY);
		String languageVersion = analysisVertex
				.getProperty(TitanElementNames.ANALYSIS_LANGUAGE_VERSION_PROPERTY);
		boolean successful = analysisVertex
				.getProperty(TitanElementNames.ANALYSIS_SUCCESSFUL_PROPERTY);
		String analyzerMessage = analysisVertex
				.getProperty(TitanElementNames.ANALYSIS_MESSAGE_PROPERTY);

		return new AnalysisInformation(HashId.valueOf(hashId), startTime,
				duration, successful, languageName, languageVersion,
				analyzerMessage);
	}

	/**
	 * This method deletes a file tree from an Analysis Run. This should always
	 * be accompanied with a deletion of the Analsysis Run, because an Analysis
	 * Run without a File Tree is meaningless.
	 * 
	 * @param fileTreeVertex
	 *            is the vertex of the file to be deleted.
	 * @throws AnalysisStoreException
	 */
	public static void deleteFileTree(Vertex fileTreeVertex)
			throws AnalysisStoreException {
		Iterable<Edge> edges = fileTreeVertex.query().direction(Direction.OUT)
				.edges();
		Iterator<Edge> edgeIterator = edges.iterator();
		while (edgeIterator.hasNext()) {
			Edge edge = edgeIterator.next();
			String edgeLabel = edge.getLabel();
			Vertex childVertex = edge.getVertex(Direction.IN);
			edge.remove();
			if (TitanElementNames.CONTAINS_FILE_LABEL.equals(edgeLabel)) {
				deleteFileTree(childVertex);
			} else if (TitanElementNames.CONTAINS_DIRECTORY_LABEL
					.equals(edgeLabel)) {
				deleteFileTree(childVertex);
			} else if (TitanElementNames.HAS_ANALYSIS_LABEL.equals(edgeLabel)) {
				AnalysisStoreContentTreeUtils.removeAnalysis(childVertex);
			} else if (TitanElementNames.HAS_CONTENT_LABEL.equals(edgeLabel)) {
				// intentionally left blank, content is deleted in another step
				// in analysis run deletion
			} else {
				throw new AnalysisStoreException("Unknown edge label '"
						+ edgeLabel
						+ "' in file tree vertex '"
						+ fileTreeVertex.getProperty(
								TitanElementNames.TREE_ELEMENT_NAME).toString()
						+ "'.");
			}
		}
		fileTreeVertex.remove();
	}

}
