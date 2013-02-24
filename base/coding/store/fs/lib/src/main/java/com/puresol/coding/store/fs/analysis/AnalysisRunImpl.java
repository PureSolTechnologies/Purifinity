package com.puresol.coding.store.fs.analysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.analysis.api.AnalysisProject;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalysisRunInformation;
import com.puresol.coding.analysis.api.AnalysisStoreException;
import com.puresol.coding.analysis.api.AnalyzedCode;
import com.puresol.coding.analysis.api.CodeStoreException;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.analysis.api.ModuleStoreException;
import com.puresol.coding.analysis.api.RepositoryLocation;
import com.puresol.uhura.source.CodeLocation;
import com.puresol.utils.DirectoryUtilities;
import com.puresol.utils.FileSearchConfiguration;
import com.puresol.utils.progress.AbstractProgressObservable;

public class AnalysisRunImpl extends AbstractProgressObservable<AnalysisRun>
	implements AnalysisRun {

    private static final long serialVersionUID = 6413809660830217670L;

    private static final String DIRECTORY_FLAG = ".analysis_run";

    private static final Logger logger = LoggerFactory
	    .getLogger(AnalysisRunImpl.class);

    /**
     * This method returns the storage directory of the given analysis run.
     * 
     * @param analysisRun
     * @return
     */
    public static File getStorageDirectory(AnalysisRun analysisRun) {
	File analysisStorageDirectory = AnalysisStoreImpl
		.getStorageDirectory(analysisRun.getInformation()
			.getAnalysisProject().getInformation().getUUID());
	return new File(analysisStorageDirectory, analysisRun.getInformation()
		.getUUID().toString());
    }

    /**
     * This method opens an existing project analyzer via its workspace
     * directory.
     * 
     * @param runDirectory
     *            is the directory where the persisted results can be found.
     * @return
     * @throws ModuleStoreException
     */
    public static AnalysisRun open(File runDirectory)
	    throws AnalysisStoreException {
	AnalysisRunImpl projectAnalyser = new AnalysisRunImpl(runDirectory);
	projectAnalyser.open();
	return projectAnalyser;
    }

    /**
     * This method creates a new project analyzer with a new workspace
     * associated.
     * 
     * @param projectDirectory
     *            is the directory which is to scan for files and to be
     *            analyzed.
     * @param runDirectory
     *            is the directory to put the persisted results to.
     * @throws AnalysisStoreException
     * @throws IOException
     */
    public static AnalysisRun create(File runDirectory,
	    AnalysisProject analysisProject, UUID uuid,
	    RepositoryLocation repositorySource,
	    FileSearchConfiguration searchConfiguration)
	    throws AnalysisStoreException {
	AnalysisRunImpl projectAnalyser = new AnalysisRunImpl(runDirectory);
	projectAnalyser.create(repositorySource, analysisProject, uuid,
		searchConfiguration);
	return projectAnalyser;
    }

    private static final String ANALYZED_FILES_FILE = "analyzed_files.persist";
    private static final String FAILED_FILES_FILE = "failed_files.persist";
    private static final String SEARCH_CONFIGURATION_FILE = "search_configuration.persist";
    private static final String ANALYSIS_RUN_PROPERTIES_FILE = "analysis_run.properties";
    private static final String ANALYSIS_INFORMATION_FILE = "analysis_information.persist";
    private static final String REPOSITORY_LOCATION__FILE = "repository_location.persist";

    private final File runDirectory;

    private final List<AnalyzedCode> analyzedFiles = new ArrayList<AnalyzedCode>();
    private final List<CodeLocation> failedSources = new ArrayList<CodeLocation>();

    private FileSearchConfiguration searchConfig;

    private AnalysisProject analysisProject;
    private UUID uuid;
    private Date creationTime;
    private long timeOfRun;
    private RepositoryLocation repositoryLocation;

    /**
     * This constructor is used to create a new analysis run. All setup
     * information is set and is immutable.
     * 
     * @param name
     * @param runDirectory
     * @param searchConfiguration
     */
    private AnalysisRunImpl(File runDirectory) {
	super();
	this.runDirectory = runDirectory;
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
    void create(RepositoryLocation repositorySource,
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

    /**
     * This method opens the project directory and loads all information files.
     * 
     * @return
     * @throws ModuleStoreException
     */
    void open() throws AnalysisStoreException {
	try {
	    if (!runDirectory.exists()) {
		throw new IOException("Analysis run directory '" + runDirectory
			+ "' does not exist!");
	    }
	    loadAnalysisRunInformation();
	    readSearchConfiguration();
	    loadAnalysisResultInformation();
	} catch (IOException e) {
	    throw new AnalysisStoreException("Could not open analysis run!", e);
	}
    }

    private void readSearchConfiguration() throws IOException {
	try {
	    searchConfig = new FileSearchConfiguration();
	    FileInputStream fileInputStream = new FileInputStream(new File(
		    runDirectory, SEARCH_CONFIGURATION_FILE));
	    try {
		ObjectInputStream objectOutputStream = new ObjectInputStream(
			fileInputStream);
		try {
		    FileSearchConfiguration config = (FileSearchConfiguration) objectOutputStream
			    .readObject();
		    searchConfig.setLocationExcludes(config
			    .getLocationExcludes());
		    searchConfig.setLocationIncludes(config
			    .getLocationIncludes());
		    searchConfig.setFileExcludes(config.getFileExcludes());
		    searchConfig.setFileIncludes(config.getFileIncludes());
		} finally {
		    objectOutputStream.close();
		}
	    } finally {
		fileInputStream.close();
	    }
	} catch (ClassNotFoundException e) {
	    throw new RuntimeException(e);
	}
    }

    private void writeSearchConfiguration() throws IOException {
	FileOutputStream fileOutputStream = new FileOutputStream(new File(
		runDirectory, SEARCH_CONFIGURATION_FILE));
	try {
	    ObjectOutputStream objectOutputStream = new ObjectOutputStream(
		    fileOutputStream);
	    try {
		objectOutputStream.writeObject(searchConfig);
	    } finally {
		objectOutputStream.close();
	    }
	} finally {
	    fileOutputStream.close();
	}
    }

    private void loadAnalysisRunInformation() throws IOException {
	Properties properties = new Properties();
	FileReader reader = new FileReader(new File(runDirectory,
		ANALYSIS_RUN_PROPERTIES_FILE));
	try {
	    properties.load(reader);
	    uuid = UUID.fromString(properties.get("uuid").toString());
	    creationTime = new Date(Long.valueOf(properties
		    .get("creation.time").toString()));
	    timeOfRun = Long.valueOf(properties.get("run.time").toString());
	} finally {
	    reader.close();
	}
	analysisProject = restore(new File(runDirectory,
		ANALYSIS_INFORMATION_FILE));
	repositoryLocation = restore(new File(runDirectory,
		REPOSITORY_LOCATION__FILE));
    }

    private void saveAnalysisRunInformation() throws IOException {
	Properties properties = new Properties();
	properties.put("uuid", uuid.toString());
	properties.put("creation.time", String.valueOf(creationTime.getTime()));
	properties.put("run.time", String.valueOf(timeOfRun));
	File propertiesFile = new File(runDirectory,
		ANALYSIS_RUN_PROPERTIES_FILE);
	FileWriter writer = new FileWriter(propertiesFile);
	try {
	    properties.store(writer, "Analysis run properties");
	} finally {
	    writer.close();
	}
	persist(repositoryLocation, new File(runDirectory,
		REPOSITORY_LOCATION__FILE));
	persist(analysisProject, new File(runDirectory,
		ANALYSIS_INFORMATION_FILE));
    }

    private void loadAnalysisResultInformation() throws IOException {
	@SuppressWarnings("unchecked")
	List<AnalyzedCode> analyzed = (List<AnalyzedCode>) restore(new File(
		runDirectory, ANALYZED_FILES_FILE));
	analyzedFiles.addAll(analyzed);
	@SuppressWarnings("unchecked")
	List<CodeLocation> failed = (List<CodeLocation>) restore(new File(
		runDirectory, FAILED_FILES_FILE));
	failedSources.addAll(failed);
	// hashIdFileTree = restore(new File(runDirectory, FILE_TREE));

    }

    private void storeAnalysisResultInformation() {
	try {
	    persist(analyzedFiles, new File(runDirectory, ANALYZED_FILES_FILE));
	    persist(failedSources, new File(runDirectory, FAILED_FILES_FILE));
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	}
    }

    @Override
    public Boolean call() throws Exception {
	try {
	    reset();
	    saveAnalysisRunInformation();
	    boolean retVal = analyzeFiles();
	    storeAnalysisResultInformation();
	    fireDone("Finished successfully.", retVal);
	    return retVal;
	} catch (Exception e) {
	    fireDone("Finished with error: '" + e.getMessage() + "'", false);
	    throw e;
	}
    }

    /**
     * This method resets the values for a reanalysis.
     */
    private void reset() {
	analyzedFiles.clear();
	failedSources.clear();
    }

    private boolean analyzeFiles() throws CodeStoreException {
	List<Future<AnalyzedCode>> futures = startAllAnalysisThreads();
	waitForAnalysisThreads(futures);
	return true;
    }

    private List<Future<AnalyzedCode>> startAllAnalysisThreads() {
	repositoryLocation.setCodeSearchConfiguration(searchConfig);
	List<CodeLocation> sourceFiles = repositoryLocation.getSourceCodes();

	int processors = Runtime.getRuntime().availableProcessors();
	ExecutorService threadPool = Executors.newFixedThreadPool(processors);
	fireStarted("Analyze files", sourceFiles.size());
	List<Future<AnalyzedCode>> futures = new ArrayList<Future<AnalyzedCode>>();
	for (int index = 0; index < sourceFiles.size(); index++) {
	    final CodeLocation sourceFile = sourceFiles.get(index);
	    Callable<AnalyzedCode> callable = new AnalysisRunCallable(
		    sourceFile);
	    futures.add(threadPool.submit(callable));
	}
	threadPool.shutdown();
	return futures;
    }

    private void waitForAnalysisThreads(List<Future<AnalyzedCode>> futures) {
	while (futures.size() > 0) {
	    Iterator<Future<AnalyzedCode>> iterator = futures.iterator();
	    while (iterator.hasNext()) {
		Future<AnalyzedCode> future = iterator.next();
		if (future.isDone()) {
		    futures.remove(future);
		    fireUpdateWork("Finished a file.", 1);
		    try {
			AnalyzedCode result = future.get();
			if (result.getStartTime() != null) {
			    analyzedFiles.add(result);
			} else {
			    failedSources.add(result.getSourceLocation());
			}
			fireUpdateWork("Finished '"
				+ result.getSourceLocation()
					.getHumanReadableLocationString()
				+ "'.", 0);
		    } catch (CancellationException e) {
			logger.debug("Job was cancelled.", e);
		    } catch (InterruptedException e) {
			logger.warn("Job was interrupted.", e);
		    } catch (ExecutionException e) {
			logger.error(
				"Job was aborted with execution exception.", e);
		    }
		}
	    }
	    try {
		Thread.sleep(1000);
	    } catch (InterruptedException e) {
		// intentionally left blank
	    }
	}
    }

    @Override
    public List<AnalyzedCode> getAnalyzedCodes() {
	return analyzedFiles;
    }

    @Override
    public HashIdFileTree getFileTree() {
	return null;
    }

    @Override
    public List<CodeLocation> getFailedCodeLocations() {
	return failedSources;
    }

    @Override
    public AnalyzedCode findAnalyzedCode(File file) {
	for (AnalyzedCode analyzedFile : analyzedFiles) {
	    if (analyzedFile.getSourceLocation().equals(file)) {
		return analyzedFile;
	    }
	}
	return null;
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

    @Override
    public AnalysisRunInformation getInformation() {
	return new AnalysisRunInformation(analysisProject, uuid, creationTime,
		timeOfRun, "<Not implemented, yet!>");
    }

    public static boolean isAnalysisRunDirectory(File runDirectory) {
	return new File(runDirectory, DIRECTORY_FLAG).exists();
    }

}