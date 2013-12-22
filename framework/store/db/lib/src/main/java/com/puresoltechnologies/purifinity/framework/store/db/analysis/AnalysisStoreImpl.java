package com.puresoltechnologies.purifinity.framework.store.db.analysis;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.framework.analysis.impl.AnalysisProjectImpl;
import com.puresoltechnologies.purifinity.framework.commons.utils.FileUtilities;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;

public class AnalysisStoreImpl implements AnalysisStore {

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
					AnalysisProject analysis = AnalysisProjectImpl
							.open(analysisDirectory);
					projects.add(analysis);
				}
			}
		}
		return projects;
	}

	@Override
	public AnalysisProject loadAnalysis(UUID uuid)
			throws AnalysisStoreException {
		File analysisDirectory = new File(storageDirectory, uuid.toString());
		return AnalysisProjectImpl.open(analysisDirectory);
	}

	@Override
	public AnalysisProject createAnalysis(AnalysisProjectSettings settings)
			throws AnalysisStoreException {
		UUID uuid = UUID.randomUUID();
		return AnalysisProjectImpl.create(
				new File(storageDirectory, uuid.toString()), uuid, settings);
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
}
