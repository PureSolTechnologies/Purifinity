package com.puresoltechnologies.purifinity.framework.store.db.analysis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.parsers.api.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.framework.analysis.impl.SourceCodeLocationCreator;
import com.puresoltechnologies.purifinity.framework.commons.utils.PropertiesUtils;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.framework.store.db.CassandraConnection;
import com.puresoltechnologies.purifinity.framework.store.db.CassandraElementNames;
import com.puresoltechnologies.purifinity.framework.store.db.TitanConnection;
import com.puresoltechnologies.purifinity.framework.store.db.TitanElementNames;
import com.puresoltechnologies.purifinity.framework.store.db.VertexType;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

public class AnalysisStoreImpl implements AnalysisStore {

	private static final Logger logger = LoggerFactory
			.getLogger(AnalysisStoreImpl.class);

	@Override
	public AnalysisProjectInformation createAnalysisProject(
			AnalysisProjectSettings settings) throws AnalysisStoreException {
		UUID uuid = UUID.randomUUID();
		Date creationTime = new Date();
		createAnalysisProjectVertex(uuid, creationTime);
		storeProjectAnalysisSettings(settings, uuid);
		return new AnalysisProjectInformation(uuid, creationTime);
	}

	private void createAnalysisProjectVertex(UUID uuid, Date creationTime) {
		TitanGraph graph = TitanConnection.getGraph();
		Vertex vertex = graph.addVertex(null);
		vertex.setProperty(TitanElementNames.VERTEX_TYPE,
				VertexType.ANALYSIS_PROJECT.name());
		vertex.setProperty(TitanElementNames.ANALYSIS_PROJECT_UUID_PROPERTY,
				uuid.toString());
		vertex.setProperty(TitanElementNames.CREATION_TIME_PROPERTY,
				creationTime);
		graph.commit();
	}

	private void storeProjectAnalysisSettings(AnalysisProjectSettings settings,
			UUID uuid) {
		String name = settings.getName();
		String description = settings.getDescription();
		FileSearchConfiguration fileSearchConfiguration = settings
				.getFileSearchConfiguration();

		Session session = CassandraConnection.getAnalysisSession();
		PreparedStatement preparedStatement = CassandraConnection
				.getPreparedStatement(
						session,
						"storeProjectAnalysisSettings",
						"INSERT INTO "
								+ CassandraElementNames.ANALYSIS_PROJECT_SETTINGS_TABLE
								+ " (uuid, name, description, "
								+ "file_includes, file_excludes, "
								+ "location_includes, location_excludes, "
								+ "ignore_hidden, repository_location) "
								+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		BoundStatement bound = preparedStatement.bind(uuid, name, description,
				fileSearchConfiguration.getFileIncludes(),
				fileSearchConfiguration.getFileExcludes(),
				fileSearchConfiguration.getLocationIncludes(),
				fileSearchConfiguration.getLocationExcludes(),
				fileSearchConfiguration.isIgnoreHidden(),
				settings.getRepositoryLocation());
		session.execute(bound);
	}

	@Override
	public List<AnalysisProjectInformation> readAllAnalysisProjectInformation()
			throws AnalysisStoreException {
		TitanGraph graph = TitanConnection.getGraph();
		List<AnalysisProjectInformation> projects = new ArrayList<>();
		Iterable<Vertex> vertices = graph
				.query()
				.has(TitanElementNames.VERTEX_TYPE,
						VertexType.ANALYSIS_PROJECT.name()).vertices();
		Iterator<Vertex> vertexIterator = vertices.iterator();
		while (vertexIterator.hasNext()) {
			Vertex vertex = vertexIterator.next();
			UUID uuid = UUID
					.fromString((String) vertex
							.getProperty(TitanElementNames.ANALYSIS_PROJECT_UUID_PROPERTY));
			Date creationTime = (Date) vertex
					.getProperty(TitanElementNames.CREATION_TIME_PROPERTY);
			projects.add(new AnalysisProjectInformation(uuid, creationTime));
		}
		return projects;
	}

	@Override
	public AnalysisProjectInformation readAnalysisProjectInformation(UUID uuid)
			throws AnalysisStoreException {
		TitanGraph graph = TitanConnection.getGraph();
		Vertex vertex = findAnalysisProjectVertex(graph, uuid);
		Date creationTime = (Date) vertex
				.getProperty(TitanElementNames.CREATION_TIME_PROPERTY);
		return new AnalysisProjectInformation(uuid, creationTime);
	}

	private Vertex findAnalysisProjectVertex(TitanGraph graph, UUID uuid)
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
		return vertexIterator.next();
	}

	@Override
	public void removeAnalysisProject(UUID uuid) throws AnalysisStoreException {
		TitanGraph graph = TitanConnection.getGraph();
		Vertex vertex = findAnalysisProjectVertex(graph, uuid);
		graph.removeVertex(vertex);
		graph.commit();
	}

	@Override
	public AnalysisProjectSettings readAnalysisProjectSettings(
			UUID analysisProjectUUID) throws AnalysisStoreException {
		Session session = CassandraConnection.getAnalysisSession();
		PreparedStatement preparedStatement = CassandraConnection
				.getPreparedStatement(
						session,
						"readAnalysisProjectSettings",
						"SELECT name, description, file_includes, file_excludes, location_includes, location_excludes, ignore_hidden, repository_location FROM "
								+ CassandraElementNames.ANALYSIS_PROJECT_SETTINGS_TABLE
								+ " WHERE uuid=?");
		BoundStatement boundStatement = preparedStatement
				.bind(analysisProjectUUID);
		ResultSet resultSet = session.execute(boundStatement);
		Row result = resultSet.one();
		if (result == null) {
			return null;
		}
		String name = result.getString("name");
		String description = result.getString("description");
		List<String> fileIncludes = result.getList("file_includes",
				String.class);
		List<String> fileExcludes = result.getList("file_excludes",
				String.class);
		List<String> locationIncludes = result.getList("location_includes",
				String.class);
		List<String> locationExcludes = result.getList("location_excludes",
				String.class);
		boolean ignoreHidden = result.getBool("ignore_hidden");
		FileSearchConfiguration fileSearchConfiguration = new FileSearchConfiguration(
				locationIncludes, locationExcludes, fileIncludes, fileExcludes,
				ignoreHidden);
		Map<String, String> repositoryLocationMap = result.getMap(
				"repository_location", String.class, String.class);
		Properties repositoryLocation = new Properties();
		for (Object key : repositoryLocationMap.keySet()) {
			repositoryLocation.put(key.toString(),
					repositoryLocationMap.get(key).toString());
		}
		return new AnalysisProjectSettings(name, description,
				fileSearchConfiguration, repositoryLocation);
	}

	@Override
	public void updateAnalysisProjectSettings(UUID uuid,
			AnalysisProjectSettings settings) throws AnalysisStoreException {
		storeProjectAnalysisSettings(settings, uuid);
	}

	@Override
	public List<AnalysisRunInformation> readAllRunInformation(UUID projectUUID)
			throws AnalysisStoreException {
		TitanGraph graph = TitanConnection.getGraph();
		Vertex projectVertex = findAnalysisProjectVertex(graph, projectUUID);
		Iterable<Vertex> analysisRuns = projectVertex.query()
				.direction(Direction.OUT)
				.labels(TitanElementNames.HAS_ANALYSIS_RUN_LABEL).vertices();
		Iterator<Vertex> runIterator = analysisRuns.iterator();
		List<AnalysisRunInformation> allRunInformation = new ArrayList<>();
		while (runIterator.hasNext()) {
			Vertex run = runIterator.next();
			UUID uuid = UUID.fromString((String) run
					.getProperty(TitanElementNames.ANALYSIS_RUN_UUID_PROPERTY));
			Date startTime = (Date) run
					.getProperty(TitanElementNames.ANALYSIS_RUN_START_TIME_PROPERTY);
			long duration = (long) run
					.getProperty(TitanElementNames.ANALYSIS_RUN_DURATION_PROPERTY);
			String description = (String) run
					.getProperty(TitanElementNames.ANALYSIS_RUN_DESCRIPTION_PROPERTY);
			FileSearchConfiguration fileSearchConfiguration = readSearchConfiguration(uuid);
			allRunInformation.add(new AnalysisRunInformation(projectUUID, uuid,
					startTime, duration, description, fileSearchConfiguration));
		}
		return allRunInformation;
	}

	@Override
	public AnalysisRunInformation createAnalysisRun(UUID analysisProjectUUID,
			Date startTime, long duration, String description,
			FileSearchConfiguration fileSearchConfiguration)
			throws AnalysisStoreException {
		UUID uuid = UUID.randomUUID();
		Date creationTime = new Date();

		TitanGraph graph = TitanConnection.getGraph();
		Vertex projectVertex = findAnalysisProjectVertex(graph,
				analysisProjectUUID);

		Vertex runVertex = graph.addVertex(null);
		runVertex.setProperty(TitanElementNames.VERTEX_TYPE,
				VertexType.ANALYSIS_RUN.name());
		runVertex.setProperty(TitanElementNames.ANALYSIS_RUN_UUID_PROPERTY,
				uuid.toString());
		runVertex.setProperty(TitanElementNames.CREATION_TIME_PROPERTY,
				creationTime);
		runVertex.setProperty(
				TitanElementNames.ANALYSIS_RUN_START_TIME_PROPERTY, startTime);
		runVertex.setProperty(TitanElementNames.ANALYSIS_RUN_DURATION_PROPERTY,
				duration);
		runVertex.setProperty(
				TitanElementNames.ANALYSIS_RUN_DESCRIPTION_PROPERTY,
				description);

		Edge hasAnalysisRunEdge = projectVertex.addEdge(
				TitanElementNames.HAS_ANALYSIS_RUN_LABEL, runVertex);
		hasAnalysisRunEdge.setProperty(
				TitanElementNames.ANALYSIS_RUN_UUID_PROPERTY, uuid.toString());
		hasAnalysisRunEdge.setProperty(
				TitanElementNames.ANALYSIS_RUN_START_TIME_PROPERTY, startTime);

		Session session = CassandraConnection.getAnalysisSession();
		PreparedStatement preparedStatement = CassandraConnection
				.getPreparedStatement(session, "createAnalysisRun",
						"INSERT INTO "
								+ CassandraElementNames.RUN_SETTINGS_TABLE
								+ " (uuid, " + "file_includes, file_excludes, "
								+ "location_includes, location_excludes, "
								+ "ignore_hidden) "
								+ "VALUES (?, ?, ?, ?, ?, ?)");
		BoundStatement bound = preparedStatement.bind(uuid,
				fileSearchConfiguration.getFileIncludes(),
				fileSearchConfiguration.getFileExcludes(),
				fileSearchConfiguration.getLocationIncludes(),
				fileSearchConfiguration.getLocationExcludes(),
				fileSearchConfiguration.isIgnoreHidden());
		session.execute(bound);

		graph.commit();

		return new AnalysisRunInformation(analysisProjectUUID, uuid, startTime,
				duration, description, fileSearchConfiguration);
	}

	@Override
	public AnalysisRunInformation readAnalysisRun(UUID projectUUID, UUID runUUID)
			throws AnalysisStoreException {
		TitanGraph graph = TitanConnection.getGraph();
		Vertex run = findAnalysisRunVertex(graph, projectUUID, runUUID);
		if (run == null) {
			return null;
		}
		UUID uuidRead = UUID.fromString((String) run
				.getProperty(TitanElementNames.ANALYSIS_RUN_UUID_PROPERTY));
		if (!runUUID.equals(uuidRead)) {
			throw new AnalysisStoreException("Anaysis run for '" + runUUID
					+ "' was not found, but a vertex with uuid='" + uuidRead
					+ "'. Database is inconsistent!");
		}
		Date startTime = (Date) run
				.getProperty(TitanElementNames.ANALYSIS_RUN_START_TIME_PROPERTY);
		long duration = (long) run
				.getProperty(TitanElementNames.ANALYSIS_RUN_DURATION_PROPERTY);
		String description = (String) run
				.getProperty(TitanElementNames.ANALYSIS_RUN_DESCRIPTION_PROPERTY);
		FileSearchConfiguration fileSearchConfiguration = readSearchConfiguration(runUUID);
		return new AnalysisRunInformation(projectUUID, runUUID, startTime,
				duration, description, fileSearchConfiguration);
	}

	private Vertex findAnalysisRunVertex(TitanGraph graph, UUID projectUUID,
			UUID runUUID) throws AnalysisStoreException {
		Vertex projectVertex = findAnalysisProjectVertex(graph, projectUUID);
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
		return runIterator.next();
	}

	@Override
	public AnalysisRunInformation readLastAnalysisRun(UUID projectUUID)
			throws AnalysisStoreException {
		List<AnalysisRunInformation> allRunInformation = readAllRunInformation(projectUUID);
		Date time = null;
		AnalysisRunInformation lastRun = null;
		for (AnalysisRunInformation runInformation : allRunInformation) {
			if ((lastRun == null)
					|| (runInformation.getStartTime().compareTo(time) < 0)) {
				lastRun = runInformation;
				time = runInformation.getStartTime();
			}
		}
		return lastRun;
	}

	@Override
	@Deprecated
	public void removeAnalysisRun(UUID projectUUID, UUID uuid)
			throws AnalysisStoreException {
		// try {
		// File analysisRunDirectory = getAnalysisRunDirectory(projectUUID,
		// uuid);
		// FileUtilities.deleteFileOrDir(analysisRunDirectory);
		// } catch (IOException e) {
		// throw new AnalysisStoreException(
		// "Could not delete analysis run with UUID '"
		// + uuid.toString() + "' for analysis with UUID '"
		// + projectUUID.toString() + "'!", e);
		// }
	}

	@Override
	public FileSearchConfiguration readSearchConfiguration(UUID analysisRunUUID)
			throws AnalysisStoreException {
		Session session = CassandraConnection.getAnalysisSession();
		PreparedStatement preparedStatement = CassandraConnection
				.getPreparedStatement(
						session,
						"readSearchConfiguration",
						"SELECT file_includes, file_excludes, location_includes, location_excludes, ignore_hidden FROM "
								+ CassandraElementNames.RUN_SETTINGS_TABLE
								+ " WHERE uuid=?");
		BoundStatement boundStatement = preparedStatement.bind(analysisRunUUID);
		ResultSet resultSet = session.execute(boundStatement);
		Row result = resultSet.one();
		if (result == null) {
			return null;
		}
		List<String> fileIncludes = result.getList("file_includes",
				String.class);
		List<String> fileExcludes = result.getList("file_excludes",
				String.class);
		List<String> locationIncludes = result.getList("location_includes",
				String.class);
		List<String> locationExcludes = result.getList("location_excludes",
				String.class);
		boolean ignoreHidden = result.getBool("ignore_hidden");
		return new FileSearchConfiguration(locationIncludes, locationExcludes,
				fileIncludes, fileExcludes, ignoreHidden);
	}

	private void storeAnalysisInformation(TitanGraph graph, Vertex treeNode,
			AnalysisInformation analysis) throws AnalysisStoreException {
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

	@Override
	public void storeAnalysisFileTree(UUID projectUUID, UUID runUUID,
			AnalysisFileTree fileTree) throws AnalysisStoreException {
		TitanGraph graph = TitanConnection.getGraph();
		Vertex analysisRunVertex = findAnalysisRunVertex(graph, projectUUID,
				runUUID);
		addContentTreeVertex(graph, fileTree, analysisRunVertex,
				TitanElementNames.ANALYZED_CONTENT_TREE_LABEL);
		addFileTreeVertex(graph, fileTree, analysisRunVertex,
				TitanElementNames.ANALYZED_FILE_TREE_LABEL);
	}

	private Vertex addContentTreeVertex(TitanGraph graph,
			AnalysisFileTree fileTree, Vertex parentVertex, String edgeLabel)
			throws AnalysisStoreException {
		Iterable<Vertex> vertices = graph
				.query()
				.has(TitanElementNames.TREE_ELEMENT_HASH,
						fileTree.getHashId().toString()).vertices();
		Iterator<Vertex> iterator = vertices.iterator();
		if (iterator.hasNext()) {
			Vertex exstingTreeElementVertex = iterator.next();
			Edge edge = parentVertex.addEdge(edgeLabel,
					exstingTreeElementVertex);
			edge.setProperty(TitanElementNames.TREE_ELEMENT_HASH,
					exstingTreeElementVertex
							.getProperty(TitanElementNames.TREE_ELEMENT_HASH));
			edge.setProperty(
					TitanElementNames.TREE_ELEMENT_IS_FILE,
					exstingTreeElementVertex
							.getProperty(TitanElementNames.TREE_ELEMENT_IS_FILE));
			graph.commit();
			return exstingTreeElementVertex;
		} else {
			Vertex vertex = graph.addVertex(null);
			vertex.setProperty(TitanElementNames.VERTEX_TYPE,
					VertexType.TREE_ELEMENT.name());
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
					addContentTreeVertex(graph, child, vertex,
							TitanElementNames.CONTAINS_FILE_LABEL);
				} else {
					addContentTreeVertex(graph, child, vertex,
							TitanElementNames.CONTAINS_DIRECTORY_LABEL);
				}
			}
			addMetadata(vertex, fileTree);
			graph.commit();
			if (fileTree.isFile()) {
				for (AnalysisInformation analyzedCode : fileTree.getAnalyses()) {
					storeAnalysisInformation(graph, vertex, analyzedCode);
				}
			}
			return vertex;
		}
	}

	private Vertex addFileTreeVertex(TitanGraph graph,
			AnalysisFileTree fileTree, Vertex parentVertex, String edgeLabel)
			throws AnalysisStoreException {
		Vertex vertex = graph.addVertex(null);
		vertex.setProperty(TitanElementNames.VERTEX_TYPE,
				VertexType.TREE_ELEMENT.name());
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
		Edge contentEdge = vertex.addEdge(TitanElementNames.HAS_CONTENT_LABEL,
				contentVertex);
		// XXX
		graph.commit();

		if (fileTree.isFile()) {
			for (AnalysisInformation analyzedCode : fileTree.getAnalyses()) {
				storeAnalysisInformation(graph, vertex, analyzedCode);
			}
		}

		for (AnalysisFileTree child : fileTree.getChildren()) {
			if (child.isFile()) {
				addFileTreeVertex(graph, child, vertex,
						TitanElementNames.CONTAINS_FILE_LABEL);
			} else {
				addFileTreeVertex(graph, child, vertex,
						TitanElementNames.CONTAINS_DIRECTORY_LABEL);
			}
		}

		return vertex;
	}

	private void addMetadata(Vertex vertex, AnalysisFileTree fileTreeNode)
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

	@Override
	public AnalysisFileTree readAnalysisFileTree(UUID projectUUID, UUID runUUID)
			throws AnalysisStoreException {
		AnalysisFileTree analysisFileTree = readCachedAnalysisFileTree(
				projectUUID, runUUID);
		if (analysisFileTree != null) {
			return analysisFileTree;
		}
		analysisFileTree = createAnalysisFileTree(projectUUID, runUUID);
		cacheAnalysisFileTree(projectUUID, runUUID, analysisFileTree);
		return analysisFileTree;
	}

	private AnalysisFileTree readCachedAnalysisFileTree(UUID projectUUID,
			UUID runUUID) {
		Session session = CassandraConnection.getAnalysisSession();
		PreparedStatement preparedStatement = CassandraConnection
				.getPreparedStatement(
						session,
						"readCachedAnalysisFileTree",
						"SELECT persisted_tree FROM "
								+ CassandraElementNames.ANALYSIS_FILE_TREE_CACHE
								+ " WHERE uuid=?");
		BoundStatement boundStatement = preparedStatement.bind(runUUID);
		ResultSet resultSet = session.execute(boundStatement);
		Row result = resultSet.one();
		if (result == null) {
			return null;
		}
		ByteBuffer byteBuffer = result.getBytes("persisted_tree");
		try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				byteBuffer.array(), byteBuffer.position(), byteBuffer.limit())) {
			try (ObjectInputStream objectInputStream = new ObjectInputStream(
					byteArrayInputStream)) {
				AnalysisFileTree object = (AnalysisFileTree) objectInputStream
						.readObject();
				return object;
			}
		} catch (IOException | ClassNotFoundException e) {
			logger.warn("Could not read already cached file tree with uuid '"
					+ runUUID + "'.", e);
			return null;
		}
	}

	private AnalysisFileTree createAnalysisFileTree(UUID projectUUID,
			UUID runUUID) throws AnalysisStoreException {
		TitanGraph graph = TitanConnection.getGraph();
		Iterable<Vertex> runVertices = graph.query()
				.has(TitanElementNames.ANALYSIS_RUN_UUID_PROPERTY, runUUID)
				.vertices();
		Iterator<Vertex> runVertexIterator = runVertices.iterator();
		if (!runVertexIterator.hasNext()) {
			return null;
		}
		Vertex runVertex = runVertexIterator.next();
		Iterable<Vertex> analysisVertices = runVertex.query()
				.labels(TitanElementNames.ANALYZED_FILE_TREE_LABEL).vertices();
		Iterator<Vertex> analysisVertexIterator = analysisVertices.iterator();
		if (!analysisVertexIterator.hasNext()) {
			return null;
		}
		Vertex treeVertex = analysisVertexIterator.next();
		AnalysisFileTree tree = convertToAnalysisFileTree(treeVertex, null);
		return tree;
	}

	private AnalysisFileTree convertToAnalysisFileTree(Vertex treeVertex,
			AnalysisFileTree parent) throws AnalysisStoreException {
		String name = (String) treeVertex
				.getProperty(TitanElementNames.TREE_ELEMENT_NAME);
		boolean isFile = (boolean) treeVertex
				.getProperty(TitanElementNames.TREE_ELEMENT_IS_FILE);
		Object serializedSourceCodeLocation = treeVertex
				.getProperty(TitanElementNames.TREE_ELEMENT_SOURCE_CODE_LOCATION);
		SourceCodeLocation sourceCodeLocation = serializedSourceCodeLocation != null ? SourceCodeLocationCreator
				.createFromSerialization(PropertiesUtils
						.fromString(serializedSourceCodeLocation.toString()))
				: null;
		Iterable<Vertex> contentVertices = treeVertex.query()
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
			analyses = new ArrayList<AnalysisInformation>();
			Iterable<Vertex> analysisVertices = treeVertex.query()
					.direction(Direction.OUT)
					.labels(TitanElementNames.HAS_ANALYSIS_LABEL).vertices();
			Iterator<Vertex> analysisVertexIterator = analysisVertices
					.iterator();
			while (analysisVertexIterator.hasNext()) {
				Vertex analysisVertex = analysisVertexIterator.next();
				AnalysisInformation analysis = readAnalysisInformation(hash,
						analysisVertex);
				analyses.add(analysis);
			}
		} else {
			analyses = null;
		}
		AnalysisFileTree hashIdFileTree = new AnalysisFileTree(parent, name,
				HashId.fromString(hash), isFile, sourceCodeLocation, analyses);
		Iterable<Vertex> vertices = treeVertex
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

	private AnalysisInformation readAnalysisInformation(String hashId,
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

		return new AnalysisInformation(HashId.fromString(hashId), startTime,
				duration, successful, languageName, languageVersion,
				analyzerMessage);
	}

	private void cacheAnalysisFileTree(UUID projectUUID, UUID runUUID,
			AnalysisFileTree analysisFileTree) {
		Session session = CassandraConnection.getAnalysisSession();
		PreparedStatement preparedStatement = CassandraConnection
				.getPreparedStatement(
						session,
						"cacheAnalysisFileTree",
						"INSERT INTO "
								+ CassandraElementNames.ANALYSIS_FILE_TREE_CACHE
								+ " (uuid, persisted_tree) VALUES (?, ?)");
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
			try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					byteArrayOutputStream)) {
				objectOutputStream.writeObject(analysisFileTree);
				BoundStatement boundStatement = preparedStatement.bind(runUUID);
				boundStatement.setBytes("persisted_tree",
						ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
				session.execute(boundStatement);
			}
		} catch (IOException e) {
			logger.warn("Could not cache analysis file tree with uuid '"
					+ runUUID + "'.", e);
		}
	}
}
