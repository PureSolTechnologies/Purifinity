package com.puresoltechnologies.purifinity.framework.analysis.impl.store;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.commons.misc.ProgressObserver;
import com.puresoltechnologies.commons.trees.api.TreeUtils;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.database.titan.utils.TitanConnection;
import com.puresoltechnologies.purifinity.database.titan.utils.TitanElementNames;
import com.puresoltechnologies.purifinity.database.titan.utils.VertexType;
import com.puresoltechnologies.purifinity.framework.analysis.impl.store.cassandra.AnalysisStoreCacheUtils;
import com.puresoltechnologies.purifinity.framework.analysis.impl.store.cassandra.AnalysisStoreCassandraUtils;
import com.puresoltechnologies.purifinity.framework.analysis.impl.store.cassandra.BigTableUtils;
import com.puresoltechnologies.purifinity.framework.analysis.impl.store.titan.AnalysisStoreContentTreeUtils;
import com.puresoltechnologies.purifinity.framework.analysis.impl.store.titan.AnalysisStoreFileTreeUtils;
import com.puresoltechnologies.purifinity.framework.analysis.impl.store.titan.AnalysisStoreTitanUtils;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.CassandraConnection;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.CassandraElementNames;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

public class AnalysisStoreImpl implements AnalysisStore {

	@Override
	public AnalysisProjectInformation createAnalysisProject(
			AnalysisProjectSettings settings) throws AnalysisStoreException {
		UUID projectUUID = UUID.randomUUID();
		Date creationTime = new Date();
		createAnalysisProjectVertex(projectUUID, creationTime);
		storeProjectAnalysisSettings(projectUUID, settings);
		return new AnalysisProjectInformation(projectUUID, creationTime);
	}

	private void createAnalysisProjectVertex(UUID projectUUID, Date creationTime) {
		TitanGraph graph = TitanConnection.getGraph();
		Vertex vertex = graph.addVertex(null);
		vertex.setProperty(TitanElementNames.VERTEX_TYPE,
				VertexType.ANALYSIS_PROJECT.name());
		vertex.setProperty(TitanElementNames.ANALYSIS_PROJECT_UUID_PROPERTY,
				projectUUID.toString());
		vertex.setProperty(TitanElementNames.CREATION_TIME_PROPERTY,
				creationTime);
		graph.commit();
	}

	private void storeProjectAnalysisSettings(UUID projectUUID,
			AnalysisProjectSettings settings) {
		String name = settings.getName();
		String description = settings.getDescription();
		FileSearchConfiguration fileSearchConfiguration = settings
				.getFileSearchConfiguration();

		Session session = CassandraConnection.getAnalysisSession();
		PreparedStatement preparedStatement = CassandraConnection
				.getPreparedStatement(session, "INSERT INTO "
						+ CassandraElementNames.ANALYSIS_PROJECT_SETTINGS_TABLE
						+ " (project_uuid, name, description, "
						+ "file_includes, file_excludes, "
						+ "location_includes, location_excludes, "
						+ "ignore_hidden, repository_location) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		BoundStatement bound = preparedStatement.bind(projectUUID, name,
				description, fileSearchConfiguration.getFileIncludes(),
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
		List<AnalysisProjectInformation> projects = new ArrayList<>();
		TitanGraph graph = TitanConnection.getGraph();
		if (graph == null) {
			return projects;
		}
		try {
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
		} finally {
			graph.rollback();
		}
	}

	@Override
	public AnalysisProjectInformation readAnalysisProjectInformation(
			UUID projectUUID) throws AnalysisStoreException {
		TitanGraph graph = TitanConnection.getGraph();
		try {
			Vertex vertex = AnalysisStoreTitanUtils.findAnalysisProjectVertex(
					graph, projectUUID);
			Date creationTime = (Date) vertex
					.getProperty(TitanElementNames.CREATION_TIME_PROPERTY);
			return new AnalysisProjectInformation(projectUUID, creationTime);
		} finally {
			graph.rollback();
		}
	}

	@Override
	public void removeAnalysisProject(UUID projectUUID)
			throws AnalysisStoreException {
		TitanGraph graph = TitanConnection.getGraph();
		try {
			// delete analysis runs first
			List<AnalysisRunInformation> runs = readAllRunInformation(projectUUID);
			for (AnalysisRunInformation run : runs) {
				removeAnalysisRun(projectUUID, run.getUUID());
			}
			// delete project
			Vertex vertex = AnalysisStoreTitanUtils.findAnalysisProjectVertex(
					graph, projectUUID);
			Iterable<Edge> edges = vertex.query().edges();
			if (edges.iterator().hasNext()) {
				// We do not expect incoming edges (never!) and also no outgoing
				// edges.
				throw new AnalysisStoreException(
						"Analysis project has still edges connected. Database is inconsistent!");
			}
			graph.removeVertex(vertex);
			AnalysisStoreCassandraUtils.removeProjectSettings(projectUUID);
			graph.commit();
		} catch (AnalysisStoreException e) {
			graph.rollback();
			throw e;
		}
	}

	@Override
	public AnalysisProjectSettings readAnalysisProjectSettings(UUID projectUUID)
			throws AnalysisStoreException {
		Session session = CassandraConnection.getAnalysisSession();
		PreparedStatement preparedStatement = CassandraConnection
				.getPreparedStatement(
						session,
						"SELECT name, description, file_includes, file_excludes, location_includes, location_excludes, ignore_hidden, repository_location FROM "
								+ CassandraElementNames.ANALYSIS_PROJECT_SETTINGS_TABLE
								+ " WHERE project_uuid=?");
		BoundStatement boundStatement = preparedStatement.bind(projectUUID);
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
	public void updateAnalysisProjectSettings(UUID projectUUID,
			AnalysisProjectSettings settings) throws AnalysisStoreException {
		storeProjectAnalysisSettings(projectUUID, settings);
	}

	@Override
	public List<AnalysisRunInformation> readAllRunInformation(UUID projectUUID)
			throws AnalysisStoreException {
		TitanGraph graph = TitanConnection.getGraph();
		try {
			Vertex projectVertex = AnalysisStoreTitanUtils
					.findAnalysisProjectVertex(graph, projectUUID);
			Iterable<Vertex> analysisRuns = projectVertex.query()
					.direction(Direction.OUT)
					.labels(TitanElementNames.HAS_ANALYSIS_RUN_LABEL)
					.vertices();
			Iterator<Vertex> runIterator = analysisRuns.iterator();
			List<AnalysisRunInformation> allRunInformation = new ArrayList<>();
			while (runIterator.hasNext()) {
				Vertex run = runIterator.next();
				UUID runUUID = UUID
						.fromString((String) run
								.getProperty(TitanElementNames.ANALYSIS_RUN_UUID_PROPERTY));
				Date startTime = (Date) run
						.getProperty(TitanElementNames.ANALYSIS_RUN_START_TIME_PROPERTY);
				long duration = (long) run
						.getProperty(TitanElementNames.ANALYSIS_RUN_DURATION_PROPERTY);
				String description = (String) run
						.getProperty(TitanElementNames.ANALYSIS_RUN_DESCRIPTION_PROPERTY);
				FileSearchConfiguration fileSearchConfiguration = readSearchConfiguration(runUUID);
				allRunInformation.add(new AnalysisRunInformation(projectUUID,
						runUUID, startTime, duration, description,
						fileSearchConfiguration));
			}
			return allRunInformation;
		} finally {
			graph.rollback();
		}
	}

	@Override
	public AnalysisRunInformation createAnalysisRun(UUID analysisProjectUUID,
			Date startTime, long duration, String description,
			FileSearchConfiguration fileSearchConfiguration)
			throws AnalysisStoreException {
		TitanGraph graph = TitanConnection.getGraph();
		try {
			Date creationTime = new Date();
			UUID runUUID = UUID.randomUUID();

			Vertex projectVertex = AnalysisStoreTitanUtils
					.findAnalysisProjectVertex(graph, analysisProjectUUID);

			AnalysisStoreTitanUtils.createAnalysisRunVertex(graph,
					projectVertex, runUUID, creationTime, startTime, duration,
					description);

			AnalysisStoreCassandraUtils.writeAnalysisRunSettings(runUUID,
					fileSearchConfiguration);

			graph.commit();

			return new AnalysisRunInformation(analysisProjectUUID, runUUID,
					startTime, duration, description, fileSearchConfiguration);
		} catch (AnalysisStoreException e) {
			graph.rollback();
			throw e;
		}
	}

	@Override
	public AnalysisRunInformation readAnalysisRun(UUID projectUUID, UUID runUUID)
			throws AnalysisStoreException {
		TitanGraph graph = TitanConnection.getGraph();
		try {
			Vertex run = AnalysisStoreTitanUtils.findAnalysisRunVertex(graph,
					projectUUID, runUUID);
			if (run == null) {
				return null;
			}
			UUID uuidRead = UUID.fromString((String) run
					.getProperty(TitanElementNames.ANALYSIS_RUN_UUID_PROPERTY));
			if (!runUUID.equals(uuidRead)) {
				throw new AnalysisStoreException("Anaysis run for '" + runUUID
						+ "' was not found, but a vertex with run_uuid='"
						+ uuidRead + "'. Database is inconsistent!");
			}
			Date startTime = (Date) run
					.getProperty(TitanElementNames.ANALYSIS_RUN_START_TIME_PROPERTY);
			long duration = (long) run
					.getProperty(TitanElementNames.ANALYSIS_RUN_DURATION_PROPERTY);
			String description = (String) run
					.getProperty(TitanElementNames.ANALYSIS_RUN_DESCRIPTION_PROPERTY);
			FileSearchConfiguration fileSearchConfiguration = readSearchConfiguration(runUUID);

			graph.commit();

			return new AnalysisRunInformation(projectUUID, runUUID, startTime,
					duration, description, fileSearchConfiguration);
		} finally {
			graph.rollback();
		}
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
	public void removeAnalysisRun(UUID projectUUID, UUID runUUID)
			throws AnalysisStoreException {
		TitanGraph graph = TitanConnection.getGraph();
		try {
			Vertex runVertex = AnalysisStoreTitanUtils.findAnalysisRunVertex(
					graph, projectUUID, runUUID);
			// delete file tree first
			Vertex fileTreeVertex = AnalysisStoreTitanUtils.findFileTreeVertex(
					projectUUID, runUUID, runVertex);
			if (fileTreeVertex != null) {
				AnalysisStoreFileTreeUtils.deleteFileTree(fileTreeVertex);
			}
			// remove analysis run vertex
			runVertex.remove();
			// clear caches
			AnalysisStoreCacheUtils
					.clearAnalysisRunCaches(projectUUID, runUUID);
			// remove run settings
			AnalysisStoreCassandraUtils.removeAnalysisRunSettings(projectUUID,
					runUUID);
			// remove analysis run results
			BigTableUtils.removeAnalysisRunResults(projectUUID, runUUID);
			// cleanup content tree
			AnalysisStoreContentTreeUtils
					.checkAndRemoveAnalysisRunContent(runVertex);
			graph.commit();
		} catch (AnalysisStoreException e) {
			graph.rollback();
			throw e;
		}
	}

	@Override
	public FileSearchConfiguration readSearchConfiguration(UUID runUUID)
			throws AnalysisStoreException {
		Session session = CassandraConnection.getAnalysisSession();
		PreparedStatement preparedStatement = CassandraConnection
				.getPreparedStatement(
						session,
						"SELECT file_includes, file_excludes, location_includes, location_excludes, ignore_hidden FROM "
								+ CassandraElementNames.RUN_SETTINGS_TABLE
								+ " WHERE run_uuid=?");
		BoundStatement boundStatement = preparedStatement.bind(runUUID);
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

	@Override
	public final void storeAnalysisFileTree(UUID projectUUID, UUID runUUID,
			AnalysisFileTree fileTree) throws AnalysisStoreException {
		storeAnalysisFileTree(null, projectUUID, runUUID, fileTree);
	}

	@Override
	public void storeAnalysisFileTree(
			ProgressObserver<AnalysisStore> progressObserver, UUID projectUUID,
			UUID runUUID, AnalysisFileTree fileTree)
			throws AnalysisStoreException {
		TitanGraph graph = TitanConnection.getGraph();
		try {
			if (progressObserver != null) {
				int nodeCount = TreeUtils.countNodes(fileTree);
				/*
				 * Node count 2x because of creation of content tree and file
				 * tree.
				 */
				progressObserver.started(this, "DB file tree creation",
						2 * nodeCount);
			}
			Vertex analysisRunVertex = AnalysisStoreTitanUtils
					.findAnalysisRunVertex(graph, projectUUID, runUUID);
			AnalysisStoreContentTreeUtils.addContentTree(this,
					progressObserver, graph, fileTree, analysisRunVertex);
			AnalysisStoreFileTreeUtils.addFileTree(this, progressObserver,
					graph, fileTree, analysisRunVertex);
			graph.commit();
			if (progressObserver != null) {
				progressObserver
						.done(this, "Finished file tree storage.", true);
			}
		} catch (AnalysisStoreException e) {
			graph.rollback();
			if (progressObserver != null) {
				progressObserver
						.done(this, "Failed to store file tree.", false);
			}
			throw e;
		}
	}

	@Override
	public AnalysisFileTree readAnalysisFileTree(UUID projectUUID, UUID runUUID)
			throws AnalysisStoreException {
		AnalysisFileTree analysisFileTree = AnalysisStoreCacheUtils
				.readCachedAnalysisFileTree(projectUUID, runUUID);
		if (analysisFileTree != null) {
			return analysisFileTree;
		}
		analysisFileTree = AnalysisStoreFileTreeUtils.createAnalysisFileTree(
				projectUUID, runUUID);
		AnalysisStoreCacheUtils.cacheAnalysisFileTree(projectUUID, runUUID,
				analysisFileTree);
		return analysisFileTree;
	}

	@Override
	public AnalysisProject readAnalysisProject(
			AnalysisProjectInformation information)
			throws AnalysisStoreException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnalysisRun readAnalysisRun(
			AnalysisRunInformation analysisRunInformation)
			throws AnalysisStoreException {
		// TODO Auto-generated method stub
		return null;
	}

}
