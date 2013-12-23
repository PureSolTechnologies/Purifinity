package com.puresoltechnologies.purifinity.framework.store.db.analysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Cluster;
import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.trees.api.TreePrinter;
import com.puresoltechnologies.commons.trees.api.TreeVisitor;
import com.puresoltechnologies.commons.trees.api.TreeWalker;
import com.puresoltechnologies.commons.trees.api.WalkingAction;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.analysis.api.AnalyzedCode;
import com.puresoltechnologies.purifinity.analysis.api.HashIdFileTree;
import com.puresoltechnologies.purifinity.framework.analysis.impl.AnalysisProjectImpl;
import com.puresoltechnologies.purifinity.framework.analysis.impl.AnalysisRunImpl;
import com.puresoltechnologies.purifinity.framework.commons.utils.FileUtilities;
import com.puresoltechnologies.purifinity.framework.commons.utils.PersistenceUtils;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.DirectoryStore;
import com.puresoltechnologies.purifinity.framework.store.api.DirectoryStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.DirectoryStoreFactory;

public class AnalysisStoreImpl implements AnalysisStore {

	private static final Logger logger = LoggerFactory
			.getLogger(AnalysisStoreImpl.class);

	private static final String SETTINGS_FILE = "analysis_settings.persist";

	private static final String PROJECT_DIRECTORY_FLAG = ".analysis";
	private static final String RUN_DIRECTORY_FLAG = ".analysis_run";

	private static final String ANALYZED_FILES_FILE = "analyzed_files.persist";
	private static final String FAILED_FILES_FILE = "failed_files.persist";
	private static final String TREE_FILE = "code_tree.persist";
	private static final String SEARCH_CONFIGURATION_FILE = "search_configuration.persist";
	private static final String ANALYSIS_RUN_PROPERTIES_FILE = "analysis_run.properties";

	
	private final Cluster
	
	public AnalysisStoreImpl() {
	}

	@Override
	public List<AnalysisProject> getAnalysisProjects()
			throws AnalysisStoreException {
		List<AnalysisProject> projects = new ArrayList<AnalysisProject>();
		File[] files = storageDirectory.listFiles();
		if (files != null) {
			for (File analysisDirectory : files) {
				if (isAnalysisProjectDirectory(analysisDirectory)) {
					AnalysisProject analysis = openProject(analysisDirectory);
					projects.add(analysis);
				}
			}
		}
		return projects;
	}

	private AnalysisProject openProject(File analysisDirectory)
			throws AnalysisStoreException {
		try {
			try (FileInputStream fileInputStream = new FileInputStream(
					new File(analysisDirectory, SETTINGS_FILE))) {
				try (ObjectInputStream objectInputStream = new ObjectInputStream(
						fileInputStream)) {
					AnalysisProjectSettings settings = (AnalysisProjectSettings) objectInputStream
							.readObject();
					return loadProperties(analysisDirectory, settings);
				} catch (ClassNotFoundException e) {
					throw new RuntimeException(e);
				}
			}
		} catch (IOException e) {
			throw new AnalysisStoreException("Could not open the analysis!", e);
		}
	}

	private AnalysisProject loadProperties(File analysisDirectory,
			AnalysisProjectSettings settings) throws IOException {
		try (FileReader reader = new FileReader(new File(analysisDirectory,
				"analysis.properties"))) {
			Properties properties = new Properties();
			properties.load(reader);
			UUID uuid = UUID.fromString(properties.get("uuid").toString());
			Date creationTime = new Date(Long.valueOf((String) properties
					.get("creation.time")));
			return new AnalysisProjectImpl(uuid, creationTime, settings);
		}
	}

	@Override
	public AnalysisProject loadAnalysis(UUID uuid)
			throws AnalysisStoreException {
		File analysisDirectory = new File(storageDirectory, uuid.toString());
		return openProject(analysisDirectory);
	}

	@Override
	public AnalysisProject createAnalysis(AnalysisProjectSettings settings)
			throws AnalysisStoreException {
		try {
			UUID uuid = UUID.randomUUID();
			Date creationTime = new Date();
			File analysisDirectory = getAnalysisProjectDirectory(uuid);
			if (!analysisDirectory.exists()) {
				analysisDirectory.mkdirs();
			}
			new File(analysisDirectory, PROJECT_DIRECTORY_FLAG).createNewFile();
			PersistenceUtils.store(new File(analysisDirectory, SETTINGS_FILE),
					settings);
			saveProperties(uuid, creationTime, analysisDirectory);
			return new AnalysisProjectImpl(uuid, creationTime, settings);
		} catch (IOException e) {
			throw new AnalysisStoreException(
					"Could not store files for analysis!", e);
		}
	}

	private void saveProperties(UUID uuid, Date creationTime,
			File analysisDirectory) throws IOException {
		Properties properties = new Properties();
		properties.put("uuid", uuid.toString());
		properties.put("creation.time", String.valueOf(creationTime.getTime()));
		FileWriter writer = new FileWriter(new File(analysisDirectory,
				"analysis.properties"));
		try {
			properties.store(writer, "");
		} finally {
			writer.close();
		}
	}

	@Override
	public void removeAnalysis(UUID uuid) throws AnalysisStoreException {
		try {
			File analysisDirectory = new File(storageDirectory, uuid.toString());
			FileUtilities.deleteFileOrDir(analysisDirectory);
		} catch (IOException e) {
			throw new AnalysisStoreException(
					"Could not delete analysis with UUID '" + uuid.toString()
							+ "'!", e);
		}
	}

	@Override
	public void updateSettings(UUID uuid, AnalysisProjectSettings settings)
			throws AnalysisStoreException {
		try {
			File analysisDirectory = getAnalysisProjectDirectory(uuid);
			PersistenceUtils.store(new File(analysisDirectory, SETTINGS_FILE),
					settings);
		} catch (IOException e) {
			throw new AnalysisStoreException("Could not store new settings!", e);
		}
	}

	@Override
	public List<AnalysisRunInformation> getAllRunInformation(UUID projectUUID)
			throws AnalysisStoreException {
		List<AnalysisRunInformation> analysisInformation = new ArrayList<AnalysisRunInformation>();
		File analysisDirectory = getAnalysisProjectDirectory(projectUUID);
		File[] files = analysisDirectory.listFiles();
		if (files != null) {
			for (File runDirectory : files) {
				if (isAnalysisRunDirectory(runDirectory)) {
					AnalysisRun analysisRun = openAnalysisRun(projectUUID,
							UUID.fromString(runDirectory.getName()));
					if (analysisRun != null) {
						analysisInformation.add(analysisRun.getInformation());
					}
				}
			}
		}
		return analysisInformation;
	}

	@Override
	public AnalysisRun loadAnalysisRun(UUID projectUUID, UUID uuid)
			throws AnalysisStoreException {
		return openAnalysisRun(projectUUID, uuid);
	}

	@Override
	public AnalysisRun loadLastAnalysisRun(UUID projectUUID)
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
		try {
			File analysisRunDirectory = getAnalysisRunDirectory(projectUUID,
					uuid);
			FileUtilities.deleteFileOrDir(analysisRunDirectory);
		} catch (IOException e) {
			throw new AnalysisStoreException(
					"Could not delete analysis run with UUID '"
							+ uuid.toString() + "' for analysis with UUID '"
							+ projectUUID.toString() + "'!", e);
		}
	}

	/**
	 * This method opens the project directory and loads all information files.
	 * 
	 * @return
	 * @throws DirectoryStoreException
	 */
	private AnalysisRun openAnalysisRun(UUID analysisProjectUUID,
			UUID analysisRunUUID) throws AnalysisStoreException {
		try {
			File runDirectory = getAnalysisRunDirectory(analysisProjectUUID,
					analysisRunUUID);
			if (!runDirectory.exists()) {
				throw new IOException("Analysis run directory '" + runDirectory
						+ "' does not exist!");
			}
			AnalysisRunInformation analysisRunInformation = loadAnalysisRunInformation(
					analysisProjectUUID, analysisRunUUID);
			// loadAnalysisResultInformation();
			return new AnalysisRunImpl(analysisProjectUUID, analysisRunUUID,
					analysisRunInformation.getStartTime(),
					analysisRunInformation.getDuration());
		} catch (IOException e) {
			throw new AnalysisStoreException("Could not open analysis run!", e);
		}
	}

	@Override
	public FileSearchConfiguration readSearchConfiguration(
			UUID analysisProjectUUID, UUID analysisRunUUID)
			throws AnalysisStoreException {
		try {
			File runDirectory = getAnalysisRunDirectory(analysisProjectUUID,
					analysisRunUUID);
			FileSearchConfiguration searchConfig = new FileSearchConfiguration();
			try (FileInputStream fileInputStream = new FileInputStream(
					new File(runDirectory, SEARCH_CONFIGURATION_FILE))) {
				try (ObjectInputStream objectOutputStream = new ObjectInputStream(
						fileInputStream)) {
					FileSearchConfiguration config = (FileSearchConfiguration) objectOutputStream
							.readObject();
					searchConfig.setLocationExcludes(config
							.getLocationExcludes());
					searchConfig.setLocationIncludes(config
							.getLocationIncludes());
					searchConfig.setFileExcludes(config.getFileExcludes());
					searchConfig.setFileIncludes(config.getFileIncludes());
					return config;
				}
			} catch (IOException e) {
				throw new AnalysisStoreException(
						"Could not write analysis run search configuration.", e);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void writeSearchConfiguration(UUID analysisProjectUUID,
			UUID analysisRunUUID,
			FileSearchConfiguration fileSearchConfiguration)
			throws AnalysisStoreException {
		File runDirectory = getAnalysisRunDirectory(analysisProjectUUID,
				analysisRunUUID);
		try (FileOutputStream fileOutputStream = new FileOutputStream(new File(
				runDirectory, SEARCH_CONFIGURATION_FILE))) {

			try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					fileOutputStream)) {
				objectOutputStream.writeObject(fileSearchConfiguration);
			}
		} catch (IOException e) {
			throw new AnalysisStoreException(
					"Could not write analysis run search configuration.", e);
		}
	}

	@Override
	public AnalysisRunInformation loadAnalysisRunInformation(
			UUID analysisProjectUUID, UUID analysisRunUUID)
			throws AnalysisStoreException {
		File runDirectory = getAnalysisRunDirectory(analysisProjectUUID,
				analysisRunUUID);
		Properties properties = new Properties();
		try (FileReader reader = new FileReader(new File(runDirectory,
				ANALYSIS_RUN_PROPERTIES_FILE))) {
			properties.load(reader);
			analysisRunUUID = UUID
					.fromString(properties.get("uuid").toString());
			Date creationTime = new Date(Long.valueOf(properties.get(
					"creation.time").toString()));
			long timeOfRun = Long.valueOf(properties.get("run.duration")
					.toString());
			return new AnalysisRunInformation(analysisProjectUUID,
					analysisRunUUID, creationTime, timeOfRun, "");
		} catch (IOException e) {
			throw new AnalysisStoreException(
					"Could not load analysis run information.", e);
		}
	}

	@Override
	public void saveAnalysisRunInformation(UUID projectUUID, UUID uuid,
			Date creationTime, long timeOfRun) throws AnalysisStoreException {
		try {
			File runDirectory = getAnalysisRunDirectory(projectUUID, uuid);
			Properties properties = new Properties();
			properties.put("uuid", uuid.toString());
			properties.put("creation.time",
					String.valueOf(creationTime.getTime()));
			properties.put("run.duration", String.valueOf(timeOfRun));
			File propertiesFile = new File(runDirectory,
					ANALYSIS_RUN_PROPERTIES_FILE);
			try (FileWriter writer = new FileWriter(propertiesFile)) {
				properties.store(writer, "Analysis run properties");
			}
		} catch (IOException e) {
			throw new AnalysisStoreException(
					"Could not store analysis run information.", e);
		}
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
		try {
			File runDirectory = getAnalysisRunDirectory(analysisProjectUUID,
					analysisRunUUID);
			persist(analyzedFiles, new File(runDirectory, ANALYZED_FILES_FILE));
			persist(failedSources, new File(runDirectory, FAILED_FILES_FILE));
			persist(fileTree, new File(runDirectory, TREE_FILE));
			if (fileTree != null) {
				File contentFile = new File(runDirectory, "content.txt");
				PrintStream stream = new PrintStream(contentFile);
				try {
					TreePrinter printer = new TreePrinter(stream);
					printer.println(fileTree);
				} finally {
					stream.close();
				}
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
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
