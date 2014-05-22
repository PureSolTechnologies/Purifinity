package com.puresoltechnologies.purifinity.server.core.impl.analysis.store;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisStoreService;
import com.puresoltechnologies.purifinity.server.database.cassandra.AnalysisStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraConnection;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraElementNames;
import com.puresoltechnologies.purifinity.server.database.titan.TitanElementNames;
import com.puresoltechnologies.purifinity.server.database.titan.VertexType;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.Event;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventLogger;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventSeverity;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventType;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

@Stateless
public class AnalysisStoreServiceBean implements AnalysisStoreService {

	public static final String COMPONENT_NAME = "AnalysisStoreService";

	@Inject
	private EventLogger eventLogger;

	@Inject
	@AnalysisStoreKeyspace
	private Session session;

	@Inject
	private AnalysisStoreCassandraUtils analysisStoreCassandraUtils;

	@Inject
	private AnalysisStoreFileTreeUtils analysisStoreFileTreeUtils;

	@Inject
	private AnalysisStoreContentTreeUtils analysisStoreContentTreeUtils;

	@Inject
	private AnalysisStoreCacheUtils analysisStoreCacheUtils;

	@Inject
	private BigTableUtils bigTableUtils;

	@Inject
	private TitanGraph graph;

	@Override
	public AnalysisProjectInformation createAnalysisProject(
			AnalysisProjectSettings settings) throws AnalysisStoreException {
		eventLogger
				.logEvent(new Event(COMPONENT_NAME, 0x01,
						EventType.USER_ACTION, EventSeverity.INFO,
						"Create project..."));
		UUID projectUUID = UUID.randomUUID();
		Date creationTime = new Date();
		createAnalysisProjectVertex(projectUUID, creationTime);
		storeProjectAnalysisSettings(projectUUID, settings);
		return new AnalysisProjectInformation(projectUUID, creationTime);
	}

	private void createAnalysisProjectVertex(UUID projectUUID, Date creationTime) {
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
			analysisStoreCassandraUtils.removeProjectSettings(projectUUID);
			graph.commit();
		} catch (AnalysisStoreException e) {
			graph.rollback();
			throw e;
		}
	}

	@Override
	public AnalysisProjectSettings readAnalysisProjectSettings(UUID projectUUID)
			throws AnalysisStoreException {
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
		try {
			Date creationTime = new Date();
			UUID runUUID = UUID.randomUUID();

			Vertex projectVertex = AnalysisStoreTitanUtils
					.findAnalysisProjectVertex(graph, analysisProjectUUID);

			AnalysisStoreTitanUtils.createAnalysisRunVertex(graph,
					projectVertex, runUUID, creationTime, startTime, duration,
					description);

			analysisStoreCassandraUtils.writeAnalysisRunSettings(runUUID,
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
	public AnalysisRunInformation readAnalysisRunInformation(UUID projectUUID,
			UUID runUUID) throws AnalysisStoreException {
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
		try {
			Vertex runVertex = AnalysisStoreTitanUtils.findAnalysisRunVertex(
					graph, projectUUID, runUUID);
			// delete file tree first
			Vertex fileTreeVertex = AnalysisStoreTitanUtils.findFileTreeVertex(
					projectUUID, runUUID, runVertex);
			if (fileTreeVertex != null) {
				analysisStoreFileTreeUtils.deleteFileTree(fileTreeVertex);
			}
			// remove analysis run vertex
			runVertex.remove();
			// clear caches
			analysisStoreCacheUtils
					.clearAnalysisRunCaches(projectUUID, runUUID);
			// remove run settings
			analysisStoreCassandraUtils.removeAnalysisRunSettings(projectUUID,
					runUUID);
			// remove analysis run results
			bigTableUtils.removeAnalysisRunResults(projectUUID, runUUID);
			// cleanup content tree
			analysisStoreContentTreeUtils
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
		PreparedStatement preparedStatement = CassandraConnection
				.getPreparedStatement(
						session,
						"SELECT file_includes, file_excludes, location_includes, location_excludes, ignore_hidden FROM "
								+ CassandraElementNames.ANALYSIS_RUN_SETTINGS_TABLE
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
		try {
			Vertex analysisRunVertex = AnalysisStoreTitanUtils
					.findAnalysisRunVertex(graph, projectUUID, runUUID);
			analysisStoreContentTreeUtils.addContentTree(this, graph, fileTree,
					analysisRunVertex);
			analysisStoreFileTreeUtils.addFileTree(this, graph, fileTree,
					analysisRunVertex);
			graph.commit();
		} catch (AnalysisStoreException e) {
			graph.rollback();
			throw e;
		}
	}

	@Override
	public AnalysisFileTree readAnalysisFileTree(UUID projectUUID, UUID runUUID)
			throws AnalysisStoreException {
		AnalysisFileTree analysisFileTree = analysisStoreCacheUtils
				.readCachedAnalysisFileTree(projectUUID, runUUID);
		if (analysisFileTree != null) {
			return analysisFileTree;
		}
		analysisFileTree = analysisStoreFileTreeUtils.createAnalysisFileTree(
				graph, projectUUID, runUUID);
		analysisStoreCacheUtils.cacheAnalysisFileTree(projectUUID, runUUID,
				analysisFileTree);
		return analysisFileTree;
	}

	@Override
	public AnalysisProject readAnalysisProject(UUID projectUUID)
			throws AnalysisStoreException {
		AnalysisProjectInformation information = readAnalysisProjectInformation(projectUUID);
		AnalysisProjectSettings settings = readAnalysisProjectSettings(projectUUID);
		return new AnalysisProject(information, settings);
	}

	@Override
	public AnalysisRun readAnalysisRun(UUID projectUUID, UUID runUUID)
			throws AnalysisStoreException {
		AnalysisRunInformation information = readAnalysisRunInformation(
				projectUUID, runUUID);
		AnalysisFileTree analysisFileTree = readAnalysisFileTree(projectUUID,
				runUUID);
		return new AnalysisRun(information, analysisFileTree);
	}

}
