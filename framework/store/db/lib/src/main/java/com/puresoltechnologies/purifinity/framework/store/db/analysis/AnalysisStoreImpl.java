package com.puresoltechnologies.purifinity.framework.store.db.analysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.trees.api.TreeVisitor;
import com.puresoltechnologies.commons.trees.api.TreeWalker;
import com.puresoltechnologies.commons.trees.api.WalkingAction;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalyzedCode;
import com.puresoltechnologies.purifinity.analysis.domain.HashIdFileTree;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.DirectoryStore;
import com.puresoltechnologies.purifinity.framework.store.api.DirectoryStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.DirectoryStoreFactory;
import com.puresoltechnologies.purifinity.framework.store.db.CassandraConnection;
import com.puresoltechnologies.purifinity.framework.store.db.TitanConnection;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

public class AnalysisStoreImpl implements AnalysisStore {

	private static final Logger logger = LoggerFactory
			.getLogger(AnalysisStoreImpl.class);

	public AnalysisStoreImpl() {
	}

	@Override
	public AnalysisProjectInformation createAnalysisProject(
			AnalysisProjectSettings settings) throws AnalysisStoreException {
		UUID uuid = UUID.randomUUID();
		Date creationTime = new Date();
		createAnalysisProjectVertex(uuid, creationTime);
		insertProjectAnalysisSettings(settings, uuid);
		return new AnalysisProjectInformation(uuid, creationTime);
	}

	private void createAnalysisProjectVertex(UUID uuid, Date creationTime) {
		TitanGraph graph = TitanConnection.getGraph();
		Vertex vertex = graph.addVertex(null);
		vertex.setProperty(TitanConnection.ANALYSIS_PROJECT_UUID_PROPERTY,
				uuid.toString());
		vertex.setProperty(TitanConnection.CREATION_TIME_PROPERTY, creationTime);
		graph.commit();
	}

	private void insertProjectAnalysisSettings(
			AnalysisProjectSettings settings, UUID uuid) {
		String name = settings.getName();
		String description = settings.getDescription();
		FileSearchConfiguration fileSearchConfiguration = settings
				.getFileSearchConfiguration();

		Session session = CassandraConnection.getAnalysisSession();
		PreparedStatement preparedStatement = session.prepare("INSERT INTO "
				+ CassandraConnection.ANALYSIS_PROJECT_SETTINGS_TABLE
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
		Iterable<Vertex> vertices = graph.query()
				.has(TitanConnection.ANALYSIS_PROJECT_UUID_PROPERTY).vertices();
		Iterator<Vertex> vertexIterator = vertices.iterator();
		while (vertexIterator.hasNext()) {
			Vertex vertex = vertexIterator.next();
			UUID uuid = (UUID) vertex
					.getProperty(TitanConnection.ANALYSIS_PROJECT_UUID_PROPERTY);
			Date creationTime = (Date) vertex
					.getProperty(TitanConnection.CREATION_TIME_PROPERTY);
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
				.getProperty(TitanConnection.CREATION_TIME_PROPERTY);
		return new AnalysisProjectInformation(uuid, creationTime);
	}

	private Vertex findAnalysisProjectVertex(TitanGraph graph, UUID uuid)
			throws AnalysisStoreException {
		Iterable<Vertex> vertices = graph.getVertices(
				TitanConnection.ANALYSIS_PROJECT_UUID_PROPERTY, uuid);
		Iterator<Vertex> vertexIterator = vertices.iterator();
		if (!vertexIterator.hasNext()) {
			throw new AnalysisStoreException(
					"Could not find a project with UUID '" + uuid + "'.");
		}
		Vertex vertex = vertexIterator.next();
		if (vertexIterator.hasNext()) {
			throw new AnalysisStoreException(
					"Find multiple projects with UUID '" + uuid + "'.");
		}
		return vertex;
	}

	@Override
	public void removeAnalysisProject(UUID uuid) throws AnalysisStoreException {
		TitanGraph graph = TitanConnection.getGraph();
		Vertex vertex = findAnalysisProjectVertex(graph, uuid);
		vertex.remove();
	}

	@Override
	public AnalysisProjectSettings readAnalysisProjectSettings(
			UUID analysisProjectUUID) throws AnalysisStoreException {
		Session session = CassandraConnection.getAnalysisSession();
		ResultSet resultSet = session
				.execute("SELECT name, description, file_includes, file_excludes, location_includes, location_excludes, ignore_hidden, repository_location FROM "
						+ CassandraConnection.ANALYSIS_PROJECT_SETTINGS_TABLE
						+ " WHERE uuid=" + analysisProjectUUID.toString());
		Row result = resultSet.one();
		if (result == null) {
			return null;
		}
		if (!resultSet.isExhausted()) {
			throw new RuntimeException(
					"Multiple results where found for uuid '"
							+ analysisProjectUUID + "'.");
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
		insertProjectAnalysisSettings(settings, uuid);
	}

	@Override
	public List<AnalysisRunInformation> readAllRunInformation(UUID projectUUID)
			throws AnalysisStoreException {
		TitanGraph graph = TitanConnection.getGraph();
		Vertex projectVertex = findAnalysisProjectVertex(graph, projectUUID);
		Iterable<Vertex> analysisRuns = projectVertex.query()
				.direction(Direction.OUT).labels("hasAnalysisRun").vertices();
		Iterator<Vertex> runIterator = analysisRuns.iterator();
		List<AnalysisRunInformation> allRunInformation = new ArrayList<>();
		while (runIterator.hasNext()) {
			Vertex run = runIterator.next();
			UUID uuid = UUID.fromString((String) run
					.getProperty(TitanConnection.ANALYSIS_RUN_UUID_PROPERTY));
			Date startTime = (Date) run
					.getProperty(TitanConnection.ANALYSIS_RUN_START_TIME_PROPERTY);
			long duration = (long) run
					.getProperty(TitanConnection.ANALYSIS_RUN_DURATION_PROPERTY);
			String description = (String) run
					.getProperty(TitanConnection.ANALYSIS_RUN_DESCRIPTION_PROPERTY);
			FileSearchConfiguration fileSearchConfiguration = readSearchConfiguration(
					projectUUID, uuid);
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

		Vertex runVertex = graph.addVertex(uuid);
		runVertex.setProperty(TitanConnection.ANALYSIS_RUN_UUID_PROPERTY,
				uuid.toString());
		runVertex.setProperty(TitanConnection.CREATION_TIME_PROPERTY,
				creationTime);
		runVertex.setProperty(TitanConnection.ANALYSIS_RUN_START_TIME_PROPERTY,
				startTime);
		runVertex.setProperty(TitanConnection.ANALYSIS_RUN_DURATION_PROPERTY,
				duration);
		runVertex.setProperty(
				TitanConnection.ANALYSIS_RUN_DESCRIPTION_PROPERTY, description);

		Edge hasAnalysisRunEdge = projectVertex.addEdge("hasAnalysisRun",
				runVertex);
		hasAnalysisRunEdge.setProperty(
				TitanConnection.ANALYSIS_RUN_UUID_PROPERTY, uuid.toString());
		hasAnalysisRunEdge.setProperty(
				TitanConnection.ANALYSIS_RUN_START_TIME_PROPERTY, startTime);

		graph.commit();

		Session session = CassandraConnection.getAnalysisSession();
		PreparedStatement preparedStatement = session.prepare("INSERT INTO "
				+ CassandraConnection.RUN_SETTINGS_TABLE + " (uuid, "
				+ "file_includes, file_excludes, "
				+ "location_includes, location_excludes, " + "ignore_hidden) "
				+ "VALUES (?, ?, ?, ?, ?, ?)");
		BoundStatement bound = preparedStatement.bind(uuid,
				fileSearchConfiguration.getFileIncludes(),
				fileSearchConfiguration.getFileExcludes(),
				fileSearchConfiguration.getLocationIncludes(),
				fileSearchConfiguration.getLocationExcludes(),
				fileSearchConfiguration.isIgnoreHidden());
		session.execute(bound);

		return new AnalysisRunInformation(analysisProjectUUID, uuid, startTime,
				duration, description, fileSearchConfiguration);
	}

	@Override
	public AnalysisRunInformation loadAnalysisRun(UUID projectUUID, UUID uuid)
			throws AnalysisStoreException {
		TitanGraph graph = TitanConnection.getGraph();
		Vertex projectVertex = findAnalysisProjectVertex(graph, projectUUID);
		Iterable<Vertex> analysisRuns = projectVertex.query()
				.direction(Direction.OUT).labels("hasAnalysisRun")
				.has(TitanConnection.ANALYSIS_RUN_UUID_PROPERTY, uuid)
				.vertices();
		Iterator<Vertex> runIterator = analysisRuns.iterator();
		if (!runIterator.hasNext()) {
			return null;
		}
		Vertex run = runIterator.next();
		if (runIterator.hasNext()) {
			throw new AnalysisStoreException(
					"Multiple analysis runs found for '" + uuid
							+ "'. Database is inconsistent!");
		}
		UUID uuidRead = UUID.fromString((String) run
				.getProperty(TitanConnection.ANALYSIS_RUN_UUID_PROPERTY));
		if (!uuid.equals(uuidRead)) {
			throw new AnalysisStoreException("Anaysis run for '" + uuid
					+ "' was not found, but a vertex with uuid='" + uuidRead
					+ "'. Database is inconsistent!");
		}
		Date startTime = (Date) run
				.getProperty(TitanConnection.ANALYSIS_RUN_START_TIME_PROPERTY);
		long duration = (long) run
				.getProperty(TitanConnection.ANALYSIS_RUN_DURATION_PROPERTY);
		String description = (String) run
				.getProperty(TitanConnection.ANALYSIS_RUN_DESCRIPTION_PROPERTY);
		FileSearchConfiguration fileSearchConfiguration = readSearchConfiguration(
				projectUUID, uuid);
		return new AnalysisRunInformation(projectUUID, uuid, startTime,
				duration, description, fileSearchConfiguration);
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
	public FileSearchConfiguration readSearchConfiguration(
			UUID analysisProjectUUID, UUID analysisRunUUID)
			throws AnalysisStoreException {
		Session session = CassandraConnection.getAnalysisSession();
		ResultSet resultSet = session
				.execute("SELECT file_includes, file_excludes, location_includes, location_excludes, ignore_hidden FROM "
						+ CassandraConnection.RUN_SETTINGS_TABLE
						+ " WHERE uuid=" + analysisRunUUID.toString());
		Row result = resultSet.one();
		if (result == null) {
			return null;
		}
		if (!resultSet.isExhausted()) {
			throw new RuntimeException(
					"Multiple results where found for uuid '"
							+ analysisProjectUUID + "'.");
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
	@Deprecated
	public void storeAnalysisResultInformation(UUID analysisProjectUUID,
			UUID analysisRunUUID, List<AnalyzedCode> analyzedFiles,
			List<AnalyzedCode> failedSources, HashIdFileTree fileTree) {
		// try {
		// File runDirectory = getAnalysisRunDirectory(analysisProjectUUID,
		// analysisRunUUID);
		// persist(analyzedFiles, new File(runDirectory, ANALYZED_FILES_FILE));
		// persist(failedSources, new File(runDirectory, FAILED_FILES_FILE));
		// persist(fileTree, new File(runDirectory, TREE_FILE));
		// if (fileTree != null) {
		// File contentFile = new File(runDirectory, "content.txt");
		// PrintStream stream = new PrintStream(contentFile);
		// try {
		// TreePrinter printer = new TreePrinter(stream);
		// printer.println(fileTree);
		// } finally {
		// stream.close();
		// }
		// }
		// } catch (IOException e) {
		// logger.error(e.getMessage(), e);
		// }
	}

	@Override
	@Deprecated
	public void storeModules(HashIdFileTree fileTree) {
		DirectoryStoreFactory moduleStoreFactory = DirectoryStoreFactory
				.getFactory();
		final DirectoryStore moduleStore = moduleStoreFactory.getInstance();
		TreeVisitor<HashIdFileTree> visitor = new TreeVisitor<HashIdFileTree>() {

			@Override
			public WalkingAction visit(HashIdFileTree tree) {
				try {
					if (!moduleStore.isAvailable(tree.getHashId())) {
						List<HashId> files = new ArrayList<HashId>();
						List<HashId> directories = new ArrayList<HashId>();
						for (HashIdFileTree child : tree.getChildren()) {
							if (child.isFile()) {
								files.add(child.getHashId());
							} else {
								directories.add(child.getHashId());
							}
						}
						Collections.sort(files);
						Collections.sort(directories);
						moduleStore.createPackage(tree.getHashId(), files,
								directories);
					}
					return WalkingAction.PROCEED;
				} catch (DirectoryStoreException e) {
					logger.error("Could not create module store entry for '"
							+ tree.getHashId() + "'.");
					return WalkingAction.ABORT;
				}
			}
		};
		TreeWalker.walk(visitor, fileTree);
	}
}
