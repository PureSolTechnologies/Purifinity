package com.puresoltechnologies.purifinity.framework.store.db.analysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import com.tinkerpop.blueprints.Vertex;

public class AnalysisStoreImpl implements AnalysisStore {

	private static final Logger logger = LoggerFactory
			.getLogger(AnalysisStoreImpl.class);

	public AnalysisStoreImpl() {
	}

	@Override
	public List<AnalysisProjectInformation> getAllAnalysisProjectInformation()
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
			AnalysisProjectInformation information = readAnalysisProjectInformation(uuid);
			projects.add(information);
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
	public AnalysisProjectInformation createAnalysisProject(
			AnalysisProjectSettings settings) throws AnalysisStoreException {
		UUID uuid = UUID.randomUUID();
		Date creationTime = new Date();
		createAnalysisProjectVertex(uuid, creationTime);
		insertProjectAnalysisSettings(settings, uuid);
		return new AnalysisProjectInformation(uuid, creationTime);
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

	private void createAnalysisProjectVertex(UUID uuid, Date creationTime) {
		TitanGraph graph = TitanConnection.getGraph();
		Vertex vertex = graph.addVertex(null);
		vertex.setProperty(TitanConnection.ANALYSIS_PROJECT_UUID_PROPERTY,
				uuid.toString());
		vertex.setProperty(TitanConnection.CREATION_TIME_PROPERTY, creationTime);
		graph.commit();
	}

	@Override
	public void removeAnalysisProject(UUID uuid) throws AnalysisStoreException {
		TitanGraph graph = TitanConnection.getGraph();
		Vertex vertex = findAnalysisProjectVertex(graph, uuid);
		vertex.remove();
	}

	@Override
	public void updateAnalysisProjectSettings(UUID uuid,
			AnalysisProjectSettings settings) throws AnalysisStoreException {
		insertProjectAnalysisSettings(settings, uuid);
	}

	@Override
	public List<AnalysisRunInformation> getAllRunInformation(UUID projectUUID)
			throws AnalysisStoreException {
		// List<AnalysisRunInformation> analysisInformation = new
		// ArrayList<AnalysisRunInformation>();
		// File analysisDirectory = getAnalysisProjectDirectory(projectUUID);
		// File[] files = analysisDirectory.listFiles();
		// if (files != null) {
		// for (File runDirectory : files) {
		// if (isAnalysisRunDirectory(runDirectory)) {
		// AnalysisRun analysisRun = openAnalysisRun(projectUUID,
		// UUID.fromString(runDirectory.getName()));
		// if (analysisRun != null) {
		// analysisInformation.add(analysisRun.getInformation());
		// }
		// }
		// }
		// }
		// return analysisInformation;
		return null;
	}

	@Override
	public AnalysisRunInformation loadAnalysisRun(UUID projectUUID, UUID uuid)
			throws AnalysisStoreException {
		return openAnalysisRun(projectUUID, uuid);
	}

	@Override
	public AnalysisRunInformation loadLastAnalysisRun(UUID projectUUID)
			throws AnalysisStoreException {
		List<AnalysisRunInformation> allRunInformation = getAllRunInformation(projectUUID);
		UUID uuid = null;
		Date time = null;
		for (AnalysisRunInformation runInformation : allRunInformation) {
			if ((uuid == null)
					|| (runInformation.getStartTime().compareTo(time) < 0)) {
				uuid = runInformation.getUUID();
				time = runInformation.getStartTime();
			}
		}
		if (uuid != null) {
			return loadAnalysisRun(projectUUID, uuid);
		} else {
			return null;
		}
	}

	@Override
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

	/**
	 * This method opens the project directory and loads all information files.
	 * 
	 * @return
	 * @throws DirectoryStoreException
	 */
	private AnalysisRunInformation openAnalysisRun(UUID analysisProjectUUID,
			UUID analysisRunUUID) throws AnalysisStoreException {
		// try {
		// File runDirectory = getAnalysisRunDirectory(analysisProjectUUID,
		// analysisRunUUID);
		// if (!runDirectory.exists()) {
		// throw new IOException("Analysis run directory '" + runDirectory
		// + "' does not exist!");
		// }
		// AnalysisRunInformation analysisRunInformation =
		// loadAnalysisRunInformation(
		// analysisProjectUUID, analysisRunUUID);
		// // loadAnalysisResultInformation();
		// return new AnalysisRunImpl(analysisProjectUUID, analysisRunUUID,
		// analysisRunInformation.getStartTime(),
		// analysisRunInformation.getDuration());
		// } catch (IOException e) {
		// throw new AnalysisStoreException("Could not open analysis run!", e);
		// }
		return null;
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
	public FileSearchConfiguration readSearchConfiguration(
			UUID analysisProjectUUID, UUID analysisRunUUID)
			throws AnalysisStoreException {
		// try {
		// File runDirectory = getAnalysisRunDirectory(analysisProjectUUID,
		// analysisRunUUID);
		// FileSearchConfiguration searchConfig = new FileSearchConfiguration();
		// try (FileInputStream fileInputStream = new FileInputStream(
		// new File(runDirectory, SEARCH_CONFIGURATION_FILE))) {
		// try (ObjectInputStream objectOutputStream = new ObjectInputStream(
		// fileInputStream)) {
		// FileSearchConfiguration config = (FileSearchConfiguration)
		// objectOutputStream
		// .readObject();
		// searchConfig.setLocationExcludes(config
		// .getLocationExcludes());
		// searchConfig.setLocationIncludes(config
		// .getLocationIncludes());
		// searchConfig.setFileExcludes(config.getFileExcludes());
		// searchConfig.setFileIncludes(config.getFileIncludes());
		// return config;
		// }
		// } catch (IOException e) {
		// throw new AnalysisStoreException(
		// "Could not write analysis run search configuration.", e);
		// }
		// } catch (ClassNotFoundException e) {
		// throw new RuntimeException(e);
		// }
		return null;
	}

	@Override
	public void writeSearchConfiguration(UUID analysisProjectUUID,
			UUID analysisRunUUID,
			FileSearchConfiguration fileSearchConfiguration)
			throws AnalysisStoreException {
		// File runDirectory = getAnalysisRunDirectory(analysisProjectUUID,
		// analysisRunUUID);
		// try (FileOutputStream fileOutputStream = new FileOutputStream(new
		// File(
		// runDirectory, SEARCH_CONFIGURATION_FILE))) {
		//
		// try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
		// fileOutputStream)) {
		// objectOutputStream.writeObject(fileSearchConfiguration);
		// }
		// } catch (IOException e) {
		// throw new AnalysisStoreException(
		// "Could not write analysis run search configuration.", e);
		// }
	}

	@Override
	public void saveAnalysisRunInformation(UUID projectUUID,
			AnalysisRunInformation anRunInformation)
			throws AnalysisStoreException {
		// try {
		// File runDirectory = getAnalysisRunDirectory(projectUUID, uuid);
		// Properties properties = new Properties();
		// properties.put("uuid", uuid.toString());
		// properties.put("creation.time",
		// String.valueOf(creationTime.getTime()));
		// properties.put("run.duration", String.valueOf(timeOfRun));
		// File propertiesFile = new File(runDirectory,
		// ANALYSIS_RUN_PROPERTIES_FILE);
		// try (FileWriter writer = new FileWriter(propertiesFile)) {
		// properties.store(writer, "Analysis run properties");
		// }
		// } catch (IOException e) {
		// throw new AnalysisStoreException(
		// "Could not store analysis run information.", e);
		// }
	}

	private void loadAnalysisResultInformation(UUID projectUUID, UUID uuid)
			throws IOException {
		// File runDirectory = getAnalysisRunDirectory(projectUUID, uuid);
		// @SuppressWarnings("unchecked")
		// List<AnalyzedCode> analyzed = (List<AnalyzedCode>) restore(new File(
		// runDirectory, ANALYZED_FILES_FILE));
		// analyzedFiles.addAll(analyzed);
		// @SuppressWarnings("unchecked")
		// List<AnalyzedCode> failed = (List<AnalyzedCode>) restore(new File(
		// runDirectory, FAILED_FILES_FILE));
		// failedSources.addAll(failed);
		// fileTree = (HashIdFileTree) restore(new File(runDirectory,
		// TREE_FILE));
	}

	@Override
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

	private static <T> void persist(T object, File file) throws IOException {
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				new FileOutputStream(file));
		try {
			objectOutputStream.writeObject(object);
		} finally {
			objectOutputStream.close();
		}
	}

	private static <T> T restore(File file) throws IOException {
		ObjectInputStream objectOutputStream = new ObjectInputStream(
				new FileInputStream(file));
		try {
			@SuppressWarnings("unchecked")
			T t = (T) objectOutputStream.readObject();
			return t;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Could not restore class from file '"
					+ file + "'!", e);
		} finally {
			objectOutputStream.close();
		}
	}

}
