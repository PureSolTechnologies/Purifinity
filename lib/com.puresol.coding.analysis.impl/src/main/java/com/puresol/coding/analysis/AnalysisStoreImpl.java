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
import com.puresol.coding.analysis.api.AnalysisStoreException;
import com.puresol.utils.FileUtilities;

public class AnalysisStoreImpl implements AnalysisStore {

    private final File storageDirectory;

    public AnalysisStoreImpl() {
	File userHome = new File(System.getProperty("user.home"));
	if (!userHome.exists()) {
	    throw new RuntimeException("Directory '" + userHome
		    + "' from property user.home does not exist!");
	}
	storageDirectory = new File(userHome, ".code.analysis.store");
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
	    throws AnalysisStoreException {
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
    public Analysis loadAnalysis(UUID uuid) throws AnalysisStoreException {
	File analysisDirectory = new File(storageDirectory, uuid.toString());
	return AnalysisImpl.open(analysisDirectory);
    }

    @Override
    public Analysis createAnalysis(AnalysisSettings settings)
	    throws AnalysisStoreException {
	UUID uuid = UUID.randomUUID();
	return AnalysisImpl.create(new File(storageDirectory, uuid.toString()),
		uuid, settings);
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
