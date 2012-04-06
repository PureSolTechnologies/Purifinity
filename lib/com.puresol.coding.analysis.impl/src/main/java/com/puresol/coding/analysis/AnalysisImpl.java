package com.puresol.coding.analysis;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.puresol.coding.analysis.api.Analysis;
import com.puresol.coding.analysis.api.AnalysisInformation;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalysisRunInformation;
import com.puresol.coding.analysis.api.AnalysisSettings;
import com.puresol.coding.analysis.api.AnalysisStoreException;

public class AnalysisImpl implements Analysis {

    private static final String DIRECTORY_FLAG = ".analysis";
    private static final String SETTINGS_FILE = "analysis_settings.persist";
    private static final String INFORMATION_FILE = "analysis_information.persist";

    public static boolean isAnalysisDirectory(File directory) {
	return new File(directory, DIRECTORY_FLAG).exists();
    }

    public static Analysis open(File analysisDirectory)
	    throws AnalysisStoreException {
	AnalysisImpl analysis = new AnalysisImpl(analysisDirectory);
	analysis.open();
	return analysis;
    }

    public static Analysis create(File analysisDirectory, UUID uuid,
	    AnalysisSettings settings) throws AnalysisStoreException {
	AnalysisImpl analysis = new AnalysisImpl(analysisDirectory);
	analysis.create(uuid, settings);
	return analysis;
    }

    private AnalysisInformation information;
    private AnalysisSettings settings;

    private final File analysisDirectory;

    private AnalysisImpl(File analysisDirectory) {
	super();
	this.analysisDirectory = analysisDirectory;
    }

    private void open() throws AnalysisStoreException {
	try {
	    settings = PersistenceUtils.restore(new File(analysisDirectory,
		    SETTINGS_FILE));
	    information = PersistenceUtils.restore(new File(analysisDirectory,
		    INFORMATION_FILE));
	} catch (IOException e) {
	    throw new AnalysisStoreException("Could not open the analysis!", e);
	}

    }

    private void create(UUID uuid, AnalysisSettings settings)
	    throws AnalysisStoreException {
	try {
	    this.settings = settings;
	    information = new AnalysisInformation(uuid, settings.getName(),
		    settings.getDescription(), new Date());
	    if (!analysisDirectory.exists()) {
		analysisDirectory.mkdirs();
	    }
	    new File(analysisDirectory, DIRECTORY_FLAG).createNewFile();
	    PersistenceUtils.store(
		    new File(analysisDirectory, INFORMATION_FILE), information);
	    PersistenceUtils.store(new File(analysisDirectory, SETTINGS_FILE),
		    settings);
	} catch (IOException e) {
	    throw new AnalysisStoreException(
		    "Could not store files for analysis!", e);
	}
    }

    @Override
    public AnalysisInformation getInformation() {
	return information;
    }

    @Override
    public List<AnalysisRunInformation> getAllRunInformation() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public AnalysisSettings getSettings() {
	return settings;
    }

    @Override
    public void updateSettings(AnalysisSettings settings) {
	// TODO Auto-generated method stub

    }

    @Override
    public AnalysisRun loadAnalysisRun(UUID uuid) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public AnalysisRun runAnalysis() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public AnalysisRun loadLastAnalysisRun() {
	// TODO Auto-generated method stub
	return null;
    }

}
