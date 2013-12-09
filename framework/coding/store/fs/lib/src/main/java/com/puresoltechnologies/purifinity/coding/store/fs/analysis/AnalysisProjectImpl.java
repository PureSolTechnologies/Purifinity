package com.puresoltechnologies.purifinity.coding.store.fs.analysis;

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

import com.puresoltechnologies.commons.utils.FileUtilities;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.coding.analysis.impl.PersistenceUtils;

public class AnalysisProjectImpl implements AnalysisProject {

	private static final long serialVersionUID = 461507961936256914L;

	private static final String DIRECTORY_FLAG = ".analysis";
	private static final String SETTINGS_FILE = "analysis_settings.persist";

	public static boolean isAnalysisDirectory(File directory) {
		return new File(directory, DIRECTORY_FLAG).exists();
	}

	public static AnalysisProject open(File analysisDirectory)
			throws AnalysisStoreException {
		AnalysisProjectImpl analysis = new AnalysisProjectImpl(
				analysisDirectory);
		analysis.open();
		return analysis;
	}

	public static AnalysisProject create(File analysisDirectory, UUID uuid,
			AnalysisProjectSettings settings) throws AnalysisStoreException {
		AnalysisProjectImpl analysis = new AnalysisProjectImpl(
				analysisDirectory);
		analysis.create(uuid, settings);
		return analysis;
	}

	private AnalysisProjectSettings settings;
	private UUID uuid;
	private Date creationTime;

	private final File analysisDirectory;

	private AnalysisProjectImpl(File analysisDirectory) {
		super();
		this.analysisDirectory = analysisDirectory;
	}

	private void open() throws AnalysisStoreException {
		try {
			FileInputStream fileInputStream = new FileInputStream(new File(
					analysisDirectory, SETTINGS_FILE));
			try {
				ObjectInputStream objectInputStream = new ObjectInputStream(
						fileInputStream);
				try {
					settings = (AnalysisProjectSettings) objectInputStream
							.readObject();
				} catch (ClassNotFoundException e) {
					throw new RuntimeException(e);
				} finally {
					objectInputStream.close();
				}
			} finally {
				fileInputStream.close();
			}

			loadProperties();
		} catch (IOException e) {
			throw new AnalysisStoreException("Could not open the analysis!", e);
		}

	}

	private void loadProperties() throws IOException {
		Properties properties = new Properties();
		FileReader reader = new FileReader(new File(analysisDirectory,
				"analysis.properties"));
		try {
			properties.load(reader);
			uuid = UUID.fromString(properties.get("uuid").toString());
			creationTime = new Date(Long.valueOf((String) properties
					.get("creation.time")));
		} finally {
			reader.close();
		}
	}

	private void create(UUID uuid, AnalysisProjectSettings settings)
			throws AnalysisStoreException {
		try {
			this.uuid = uuid;
			this.settings = settings;
			this.creationTime = new Date();
			if (!analysisDirectory.exists()) {
				analysisDirectory.mkdirs();
			}
			new File(analysisDirectory, DIRECTORY_FLAG).createNewFile();
			PersistenceUtils.store(new File(analysisDirectory, SETTINGS_FILE),
					settings);
			saveProperties();
		} catch (IOException e) {
			throw new AnalysisStoreException(
					"Could not store files for analysis!", e);
		}
	}

	private void saveProperties() throws IOException {
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
	public AnalysisProjectInformation getInformation() {
		return new AnalysisProjectInformation(uuid, creationTime);
	}

	@Override
	public List<AnalysisRunInformation> getAllRunInformation()
			throws AnalysisStoreException {
		List<AnalysisRunInformation> analysisInformation = new ArrayList<AnalysisRunInformation>();
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
	public AnalysisProjectSettings getSettings() {
		return settings;
	}

	@Override
	public void updateSettings(AnalysisProjectSettings settings)
			throws AnalysisStoreException {
		try {
			this.settings = settings;
			PersistenceUtils.store(new File(analysisDirectory, SETTINGS_FILE),
					settings);
		} catch (IOException e) {
			throw new AnalysisStoreException("Could not store new settings!", e);
		}
	}

	@Override
	public AnalysisRun loadAnalysisRun(UUID uuid) throws AnalysisStoreException {
		File runDir = new File(analysisDirectory, uuid.toString());
		return AnalysisRunImpl.open(runDir);
	}

	@Override
	public AnalysisRun createAnalysisRun() throws AnalysisStoreException,
			InterruptedException {
		UUID uuid = UUID.randomUUID();
		AnalysisRunImpl run = (AnalysisRunImpl) AnalysisRunImpl.create(
				new File(analysisDirectory, uuid.toString()), this, uuid,
				getSettings().getRepositoryLocation(), getSettings()
						.getFileSearchConfiguration());
		return run;
	}

	@Override
	public AnalysisRun loadLastAnalysisRun() throws AnalysisStoreException {
		List<AnalysisRunInformation> allRunInformation = getAllRunInformation();
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
			return loadAnalysisRun(uuid);
		} else {
			return null;
		}
	}

	@Override
	public void removeAnalysisRun(UUID uuid) throws AnalysisStoreException {
		try {
			File analysisRunDirectory = new File(analysisDirectory,
					uuid.toString());
			FileUtilities.deleteFileOrDir(analysisRunDirectory);
		} catch (IOException e) {
			throw new AnalysisStoreException(
					"Could not delete analysis run with UUID '"
							+ uuid.toString() + "' for analysis with UUID '"
							+ this.uuid.toString() + "'!", e);
		}
	}

}
