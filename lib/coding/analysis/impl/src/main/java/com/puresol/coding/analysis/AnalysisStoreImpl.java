package com.puresol.coding.analysis;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.puresol.coding.analysis.api.Analysis;
import com.puresol.coding.analysis.api.AnalysisInformation;
import com.puresol.coding.analysis.api.AnalysisSettings;
import com.puresol.coding.analysis.api.AnalysisStore;
import com.puresol.coding.analysis.api.ModuleStoreException;
import com.puresol.utils.FileUtilities;

public class AnalysisStoreImpl implements AnalysisStore {

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
    public List<AnalysisInformation> getAllAnalysisInformation()
	    throws ModuleStoreException {
	List<AnalysisInformation> analysisInformation = new ArrayList<AnalysisInformation>();
	File[] files = storageDirectory.listFiles();
	for (File analysisDirectory : files) {
	    if (AnalysisImpl.isAnalysisDirectory(analysisDirectory)) {
		Analysis analysis = AnalysisImpl.open(analysisDirectory);
		analysisInformation.add(analysis.getInformation());
	    }
	}
	return analysisInformation;
    }

    @Override
    public Analysis loadAnalysis(UUID uuid) throws ModuleStoreException {
	File analysisDirectory = new File(storageDirectory, uuid.toString());
	return AnalysisImpl.open(analysisDirectory);
    }

    @Override
    public Analysis createAnalysis(AnalysisSettings settings)
	    throws ModuleStoreException {
	UUID uuid = UUID.randomUUID();
	return AnalysisImpl.create(new File(storageDirectory, uuid.toString()),
		uuid, settings);
    }

    @Override
    public void removeAnalysis(UUID uuid) throws ModuleStoreException {
	try {
	    File analysisDirectory = new File(storageDirectory, uuid.toString());
	    FileUtilities.deleteFileOrDir(analysisDirectory);
	} catch (IOException e) {
	    throw new ModuleStoreException(
		    "Could not delete analysis with UUID '" + uuid.toString()
			    + "'!", e);
	}
    }
}
