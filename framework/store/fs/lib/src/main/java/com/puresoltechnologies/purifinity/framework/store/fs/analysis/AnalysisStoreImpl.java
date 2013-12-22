package com.puresoltechnologies.purifinity.framework.store.fs.analysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.parsers.api.source.RepositoryLocation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.framework.analysis.impl.AnalysisProjectImpl;
import com.puresoltechnologies.purifinity.framework.analysis.impl.AnalysisRunImpl;
import com.puresoltechnologies.purifinity.framework.commons.utils.DirectoryUtilities;
import com.puresoltechnologies.purifinity.framework.commons.utils.FileUtilities;
import com.puresoltechnologies.purifinity.framework.commons.utils.PersistenceUtils;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;

public class AnalysisStoreImpl implements AnalysisStore {

	private static final String SETTINGS_FILE = "analysis_settings.persist";

	private static final String DIRECTORY_FLAG = ".analysis";

	/**
	 * Returns the base directory of the analysis store.
	 * 
	 * @return
	 */
	public static File getStorageDirectory() {
		File userHome = new File(System.getProperty("user.home"));
		if (!userHome.exists()) {
			throw new RuntimeException("Directory '" + userHome
					+ "' from property user.home does not exist!");
		}
		return new File(userHome, ".code.analysis.store");
	}

	/**
	 * Returns the directory where the analysis with the given UUID is to be
	 * stored.
	 * 
	 * @param uuid
	 * @return
	 */
	public static File getStorageDirectory(UUID uuid) {
		return new File(getStorageDirectory(), uuid.toString());
	}

	public static boolean isAnalysisDirectory(File directory) {
		return new File(directory, DIRECTORY_FLAG).exists();
	}

	private final File storageDirectory;

	public AnalysisStoreImpl() {
		storageDirectory = getStorageDirectory();
		if (storageDirectory.exists() && storageDirectory.isFile()) {
			throw new RuntimeException("Path '" + storageDirectory
					+ "' is not a directory!");
		}
		if (!storageDirectory.exists()) {
			storageDirectory.mkdirs();
		}
	}

	@Override
	public List<AnalysisProject> getAnalysisProjects()
			throws AnalysisStoreException {
		List<AnalysisProject> projects = new ArrayList<AnalysisProject>();
		File[] files = storageDirectory.listFiles();
		if (files != null) {
			for (File analysisDirectory : files) {
				if (isAnalysisDirectory(analysisDirectory)) {
					AnalysisProject analysis = open(analysisDirectory);
					projects.add(analysis);
				}
			}
		}
		return projects;
	}

	private AnalysisProject open(File analysisDirectory)
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
		return open(analysisDirectory);
	}

	@Override
	public AnalysisProject createAnalysis(AnalysisProjectSettings settings)
			throws AnalysisStoreException {
		try {
			UUID uuid = UUID.randomUUID();
			Date creationTime = new Date();
			File analysisDirectory = getStorageDirectory(uuid);
			if (!analysisDirectory.exists()) {
				analysisDirectory.mkdirs();
			}
			new File(analysisDirectory, DIRECTORY_FLAG).createNewFile();
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
			File analysisDirectory = getStorageDirectory(uuid);
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
		File analysisDirectory = getStorageDirectory(projectUUID);
		File[] files = analysisDirectory.listFiles();
		if (files != null) {
			for (File runDirectory : files) {
				if (AnalysisRunImpl.isAnalysisRunDirectory(runDirectory)) {
					AnalysisRun analysisRun = AnalysisRunImpl
							.open(runDirectory);
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
		File analysisDirectory = getStorageDirectory(projectUUID);
		File runDir = new File(analysisDirectory, uuid.toString());
		return AnalysisRunImpl.open(runDir);
	}

	@Override
	public AnalysisRun createAnalysisRun(UUID projectUUID)
			throws AnalysisStoreException {
		AnalysisProject analysisProject = loadAnalysis(projectUUID);
		UUID uuid = UUID.randomUUID();
		File analysisDirectory = getStorageDirectory(projectUUID);
		AnalysisRun run = create(new File(analysisDirectory, uuid.toString()),
				this, uuid, analysisProject.getSettings()
						.getRepositoryLocation(), analysisProject.getSettings()
						.getFileSearchConfiguration());
		return run;
	}

	/**
	 * This methods creates a new project directory.
	 * 
	 * @param repositorySource
	 * @param analysisProject
	 * @param uuid
	 * @param searchConfiguration
	 * @return
	 * @throws AnalysisStoreException
	 */
	AnalysisRun create(RepositoryLocation repositorySource,
			AnalysisProject analysisProject, UUID uuid,
			FileSearchConfiguration searchConfiguration)
			throws AnalysisStoreException {
		try {
			this.repositoryLocation = repositorySource;
			this.analysisProject = analysisProject;
			this.uuid = uuid;
			this.searchConfig = searchConfiguration;
			this.creationTime = new Date();
			this.timeOfRun = 0;
			DirectoryUtilities.checkAndCreateDirectory(runDirectory);
			new File(runDirectory, DIRECTORY_FLAG).createNewFile();
			saveAnalysisRunInformation();
			writeSearchConfiguration();
			storeAnalysisResultInformation();
		} catch (IOException e) {
			throw new AnalysisStoreException("Could not create analysis run!",
					e);
		}
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
			File analysisDirectory = getStorageDirectory(projectUUID);
			File analysisRunDirectory = new File(analysisDirectory,
					uuid.toString());
			FileUtilities.deleteFileOrDir(analysisRunDirectory);
		} catch (IOException e) {
			throw new AnalysisStoreException(
					"Could not delete analysis run with UUID '"
							+ uuid.toString() + "' for analysis with UUID '"
							+ projectUUID.toString() + "'!", e);
		}
	}

}
