package com.puresoltechnologies.purifinity.server.core.impl.analysis.store;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.misc.Version;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.framework.commons.utils.PropertiesUtils;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisRunFileTree;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.common.SourceCodeLocationCreator;
import com.puresoltechnologies.purifinity.server.database.cassandra.AnalysisStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraElementNames;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraPreparedStatements;
import com.puresoltechnologies.purifinity.server.database.titan.TitanElementNames;
import com.puresoltechnologies.purifinity.server.database.titan.VertexType;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

/**
 * This class contains functionality for {@link AnalysisFileTree}s in Titan
 * graph database.
 * 
 * @author Rick-Rainer Ludwig -
 */
public class AnalysisStoreFileTreeUtils {

	@Inject
	private AnalysisStoreDAO analysisStoreDAO;

	@Inject
	@AnalysisStoreKeyspace
	private Session session;

	@Inject
	private CassandraPreparedStatements preparedStatements;

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
	public Vertex addFileTree(AnalysisStore analysisStore, TitanGraph graph,
			AnalysisRunFileTree fileTree, Vertex analysisRunVertex)
			throws AnalysisStoreException {
		return addFileTreeVertex(analysisStore, graph, fileTree,
				analysisRunVertex, TitanElementNames.ANALYZED_FILE_TREE_LABEL);
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
	private Vertex addFileTreeVertex(AnalysisStore analysisStore,
			TitanGraph graph, AnalysisRunFileTree fileTree,
			Vertex parentVertex, String edgeLabel)
			throws AnalysisStoreException {
		Vertex vertex = graph.addVertex(null);
		vertex.setProperty(TitanElementNames.VERTEX_TYPE,
				VertexType.FILE_TREE_ELEMENT.name());
		vertex.setProperty(TitanElementNames.TREE_ELEMENT_NAME,
				fileTree.getName());
		vertex.setProperty(TitanElementNames.TREE_ELEMENT_IS_FILE,
				fileTree.isFile());

		Edge edge = parentVertex.addEdge(edgeLabel, vertex);
		edge.setProperty(TitanElementNames.TREE_ELEMENT_HASH, fileTree
				.getHashId().toString());
		edge.setProperty(TitanElementNames.TREE_ELEMENT_IS_FILE,
				fileTree.isFile());

		addMetadata(vertex, fileTree);
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
		vertex.addEdge(TitanElementNames.HAS_CONTENT_LABEL, contentVertex);
		graph.commit();

		for (AnalysisRunFileTree child : fileTree.getChildren()) {
			if (child.isFile()) {
				addFileTreeVertex(analysisStore, graph, child, vertex,
						TitanElementNames.CONTAINS_FILE_LABEL);
			} else {
				addFileTreeVertex(analysisStore, graph, child, vertex,
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
	public void addMetadata(Vertex vertex, AnalysisRunFileTree fileTreeNode)
			throws AnalysisStoreException {
		if (fileTreeNode.isFile()) {
			long size = analysisStoreDAO.getFileSize(fileTreeNode.getHashId());
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
	public AnalysisFileTree createAnalysisFileTree(TitanGraph graph,
			UUID projectUUID, UUID runUUID) throws AnalysisStoreException {
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
	private AnalysisFileTree convertToAnalysisFileTree(Vertex fileTreeVertex,
			AnalysisFileTree parent) throws AnalysisStoreException {
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
	private AnalysisFileTree createAnalysisFileTreeNode(Vertex fileTreeVertex,
			AnalysisFileTree parent) throws AnalysisStoreException {
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
		HashId hashId = HashId.valueOf(hash);
		final List<AnalysisInformation> analyses;
		if (isFile) {
			analyses = readAnalyses(hashId);
		} else {
			analyses = null;
		}
		AnalysisFileTree hashIdFileTree = new AnalysisFileTree(parent, name,
				hashId, isFile, sourceCodeLocation, analyses);
		return hashIdFileTree;
	}

	/**
	 * This method reads all analyzes which are attached to a file tree node.
	 * 
	 * @param fileTreeVertex
	 * @param hash
	 * @return
	 */
	private List<AnalysisInformation> readAnalyses(HashId hashId) {
		List<AnalysisInformation> analyses = new ArrayList<AnalysisInformation>();

		PreparedStatement preparedStatement = preparedStatements
				.getPreparedStatement(session, "SELECT * FROM "
						+ CassandraElementNames.ANALYSIS_ANALYSES_TABLE
						+ " WHERE hashId=?;");
		BoundStatement boundStatement = preparedStatement.bind(hashId
				.toString());
		ResultSet resultSet = session.execute(boundStatement);
		while (!resultSet.isExhausted()) {
			Row row = resultSet.one();
			Date time = row.getDate("time");
			long duration = row.getLong("duration");
			String analyzer = row.getString("analyzer");
			String analyzerVersion = row.getString("analyzer_version");
			boolean successful = row.getBool("successful");
			String message = row.getString("analyzer_message");
			AnalysisInformation information = new AnalysisInformation(hashId,
					time, duration, successful, analyzer,
					Version.valueOf(analyzerVersion), message);
			analyses.add(information);
		}
		return analyses;
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
	public void deleteFileTree(Vertex fileTreeVertex)
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
