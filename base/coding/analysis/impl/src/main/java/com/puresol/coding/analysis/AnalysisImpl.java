package com.puresol.coding.analysis;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import com.puresol.coding.analysis.api.Analysis;
import com.puresol.coding.analysis.api.AnalysisInformation;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalysisRunInformation;
import com.puresol.coding.analysis.api.AnalysisSettings;
import com.puresol.coding.analysis.api.ModuleStoreException;
import com.puresol.utils.FileUtilities;

public class AnalysisImpl implements Analysis {

    private static final String DIRECTORY_FLAG = ".analysis";
    private static final String SETTINGS_FILE = "analysis_settings.persist";

    public static boolean isAnalysisDirectory(File directory) {
	return new File(directory, DIRECTORY_FLAG).exists();
    }

    public static Analysis open(File analysisDirectory)
	    throws ModuleStoreException {
	AnalysisImpl analysis = new AnalysisImpl(analysisDirectory);
	analysis.open();
	return analysis;
    }

    public static Analysis create(File analysisDirectory, UUID uuid,
	    AnalysisSettings settings) throws ModuleStoreException {
	AnalysisImpl analysis = new AnalysisImpl(analysisDirectory);
	analysis.create(uuid, settings);
	return analysis;
    }

    private AnalysisSettings settings;
    private UUID uuid;
    private Date creationTime;

    private final File analysisDirectory;

    private AnalysisImpl(File analysisDirectory) {
	super();
	this.analysisDirectory = analysisDirectory;
    }

    private void open() throws ModuleStoreException {
	try {
	    settings = PersistenceUtils.restore(new File(analysisDirectory,
		    SETTINGS_FILE));
	    loadProperties();
	} catch (IOException e) {
	    throw new ModuleStoreException("Could not open the analysis!", e);
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

    private void create(UUID uuid, AnalysisSettings settings)
	    throws ModuleStoreException {
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
	    throw new ModuleStoreException(
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
    public AnalysisInformation getInformation() {
	return new AnalysisInformation(uuid, settings.getName(),
		settings.getDescription(), creationTime);
    }

    @Override
    public List<AnalysisRunInformation> getAllRunInformation()
	    throws ModuleStoreException {
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
    public AnalysisSettings getSettings() {
	return settings;
    }

    @Override
    public void updateSettings(AnalysisSettings settings)
	    throws ModuleStoreException {
	try {
	    this.settings = settings;
	    PersistenceUtils.store(new File(analysisDirectory, SETTINGS_FILE),
		    settings);
	} catch (IOException e) {
	    throw new ModuleStoreException("Could not store new settings!",
		    e);
	}
    }

    @Override
    public AnalysisRun loadAnalysisRun(UUID uuid)
	    throws ModuleStoreException {
	File runDir = new File(analysisDirectory, uuid.toString());
	return AnalysisRunImpl.open(runDir);
    }

    @Override
    public AnalysisRun runAnalysis() throws ModuleStoreException,
	    InterruptedException {
	UUID uuid = UUID.randomUUID();
	AnalysisRunImpl run = (AnalysisRunImpl) AnalysisRunImpl.create(
		new File(analysisDirectory, uuid.toString()), getInformation(),
		uuid, getSettings().getRepositoryLocation(), getSettings()
			.getFileSearchConfiguration());
	run.schedule();
	run.join();
	return run;
    }

    @Override
    public AnalysisRun loadLastAnalysisRun() throws ModuleStoreException {
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
    public void removeAnalysisRun(UUID uuid) throws ModuleStoreException {
	try {
	    File analysisRunDirectory = new File(analysisDirectory,
		    uuid.toString());
	    FileUtilities.deleteFileOrDir(analysisRunDirectory);
	} catch (IOException e) {
	    throw new ModuleStoreException(
		    "Could not delete analysis run with UUID '"
			    + uuid.toString() + "' for analysis with UUID '"
			    + this.uuid.toString() + "'!", e);
	}
    }

}