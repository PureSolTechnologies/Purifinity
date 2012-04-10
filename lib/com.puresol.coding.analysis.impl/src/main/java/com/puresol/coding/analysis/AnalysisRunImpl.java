package com.puresol.coding.analysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.analysis.api.AnalysisInformation;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalysisRunInformation;
import com.puresol.coding.analysis.api.AnalysisStoreException;
import com.puresol.coding.analysis.api.AnalyzedFile;
import com.puresol.coding.analysis.api.FileAnalysis;
import com.puresol.trees.FileTree;
import com.puresol.trees.FileTreeConverter;
import com.puresol.utils.DirectoryUtilities;
import com.puresol.utils.FileSearch;
import com.puresol.utils.FileSearchConfiguration;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.HashAlgorithm;
import com.puresol.utils.HashId;

public class AnalysisRunImpl extends Job implements Serializable, AnalysisRun {

    private static final long serialVersionUID = 6413809660830217670L;

    private static final String DIRECTORY_FLAG = ".analysis_run";
    private static final String FILE_CONTENT_FILE = "content.txt";

    private static final Logger logger = LoggerFactory
	    .getLogger(AnalysisRunImpl.class);

    public static boolean isAnalysisDirectory(File directory) {
	return new File(directory, DIRECTORY_FLAG).exists();
    }

    /**
     * This method opens an existing project analyzer via its workspace
     * directory.
     * 
     * @param runDirectory
     *            is the directory where the persisted results can be found.
     * @return
     * @throws AnalysisStoreException
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
	    AnalysisInformation analysisInformation, UUID uuid,
	    File sourceDirectory, FileSearchConfiguration searchConfiguration)
	    throws AnalysisStoreException {
	AnalysisRunImpl projectAnalyser = new AnalysisRunImpl(runDirectory);
	projectAnalyser.create(sourceDirectory, analysisInformation, uuid,
		searchConfiguration);
	return projectAnalyser;
    }

    private static final String SOURCE_DIRECTORY_KEY = "ProjectAnalyzer.projectDirectory";

    private static final String ANALYZED_FILES_FILE = "analyzed_files.persist";
    private static final String FAILED_FILES_FILE = "failed_files.persist";
    private static final String SEARCH_CONFIGURATION_FILE = "search_configuration.persist";
    private static final String ANALYSIS_RUN_PROPERTIES_FILE = "analysis_run.properties";
    private static final String ANALYSIS_INFORMATION_FILE = "analysis_information.persist";

    private final File runDirectory;

    private transient final FileAnalysisFactory analyzerFactory = FileAnalysisFactory
	    .createFactory();

    private final List<AnalyzedFile> analyzedFiles = new ArrayList<AnalyzedFile>();
    private final List<File> failedFiles = new ArrayList<File>();
    private FileTree fileTree = new FileTree(null, "");
    private FileSearchConfiguration searchConfig;

    private AnalysisInformation analysisInformation;
    private UUID uuid;
    private Date creationTime;
    private long timeOfRun;
    private File sourceDirectory;

    /**
     * This constructor is used to create a new analysis run. All setup
     * information is set and is immutable.
     * 
     * @param name
     * @param runDirectory
     * @param searchConfiguration
     */
    private AnalysisRunImpl(File runDirectory) {
	super("Analysis Run");
	this.runDirectory = runDirectory;
    }

    /**
     * This methods creates a new project directory.
     * 
     * @param sourceDirectory
     * @param searchConfiguration
     * @param name
     * @param uuid2
     * @return
     * @throws AnalysisStoreException
     * @throws IOException
     */
    void create(File sourceDirectory, AnalysisInformation analysisInformation,
	    UUID uuid, FileSearchConfiguration searchConfiguration)
	    throws AnalysisStoreException {
	try {
	    this.sourceDirectory = sourceDirectory;
	    this.analysisInformation = analysisInformation;
	    this.uuid = uuid;
	    this.searchConfig = searchConfiguration;
	    this.creationTime = new Date();
	    this.timeOfRun = 0;
	    DirectoryUtilities.checkAndCreateDirectory(runDirectory);
	    new File(runDirectory, DIRECTORY_FLAG).createNewFile();
	    saveProperties();
	    writeSearchConfiguration();
	    storeProjectInformation();
	} catch (IOException e) {
	    throw new AnalysisStoreException("Could not create analysis run!",
		    e);
	}
    }

    private void saveProperties() throws IOException {
	Properties properties = new Properties();
	properties.put("uuid", uuid.toString());
	properties.put("creation.time", String.valueOf(creationTime.getTime()));
	properties.put("run.time", String.valueOf(timeOfRun));
	properties.put(AnalysisRunImpl.class.getSimpleName() + ".name",
		getName());
	properties.put(SOURCE_DIRECTORY_KEY, sourceDirectory.toString());
	FileWriter writer = new FileWriter(new File(runDirectory,
		ANALYSIS_RUN_PROPERTIES_FILE));
	try {
	    properties.store(writer, "");
	} finally {
	    writer.close();
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

    /**
     * This method opens the project directory and loads all information files.
     * 
     * @return
     * @throws AnalysisStoreException
     */
    void open() throws AnalysisStoreException {
	try {
	    if (!runDirectory.exists()) {
		throw new IOException("Analysis run directory '" + runDirectory
			+ "' does not exist!");
	    }
	    loadProperties();
	    readSearchConfiguration();
	    loadProjectInformation();
	} catch (IOException e) {
	    throw new AnalysisStoreException("Could not open analysis run!", e);
	}
    }

    private void loadProperties() throws IOException {
	Properties properties = new Properties();
	FileReader reader = new FileReader(new File(runDirectory,
		ANALYSIS_RUN_PROPERTIES_FILE));
	try {
	    properties.load(reader);
	    uuid = UUID.fromString(properties.get("uuid").toString());
	    creationTime = new Date(Long.valueOf(properties
		    .get("creation.time").toString()));
	    timeOfRun = Long.valueOf(properties.get("run.time").toString());
	    sourceDirectory = new File(
		    properties.getProperty(SOURCE_DIRECTORY_KEY));
	    setName(properties.getProperty(
		    AnalysisRunImpl.class.getSimpleName() + ".name", "unnamed"));
	} finally {
	    reader.close();
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
		    searchConfig.setDirectoryExcludes(config
			    .getDirectoryExcludes());
		    searchConfig.setDirectoryIncludes(config
			    .getDirectoryIncludes());
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

    private void loadProjectInformation() throws IOException {
	@SuppressWarnings("unchecked")
	List<AnalyzedFile> analyzed = (List<AnalyzedFile>) restore(new File(
		runDirectory, ANALYZED_FILES_FILE));
	analyzedFiles.addAll(analyzed);
	@SuppressWarnings("unchecked")
	List<File> failed = (List<File>) restore(new File(runDirectory,
		FAILED_FILES_FILE));
	failedFiles.addAll(failed);
	analysisInformation = restore(new File(runDirectory,
		ANALYSIS_INFORMATION_FILE));
	createFileTree();
    }

    private void createFileTree() {
	List<File> files = new ArrayList<File>();
	for (AnalyzedFile analyzedFile : analyzedFiles) {
	    files.add(analyzedFile.getFile());
	}
	for (File failedFile : failedFiles) {
	    files.add(failedFile);
	}
	fileTree = FileTreeConverter.convertFileListToTree(
		analysisInformation.getName(), files);
    }

    private void storeProjectInformation() {
	try {
	    persist(analysisInformation, new File(runDirectory,
		    ANALYSIS_INFORMATION_FILE));
	    persist(analyzedFiles, new File(runDirectory, ANALYZED_FILES_FILE));
	    persist(failedFiles, new File(runDirectory, FAILED_FILES_FILE));
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	}
    }

    @Override
    protected IStatus run(IProgressMonitor monitor) {
	try {
	    reset();
	    IStatus retVal = analyzeFiles(monitor);
	    saveProperties();
	    storeProjectInformation();
	    monitor.done();
	    return retVal;
	} catch (IOException e) {
	    return new Status(IStatus.ERROR, AnalysisRunImpl.class.getName(),
		    "Could not finish analysis run!", e);
	}
    }

    /**
     * This method resets the values for a reanalysis.
     */
    private void reset() {
	analyzedFiles.clear();
	failedFiles.clear();
    }

    private IStatus analyzeFiles(final IProgressMonitor monitor) {
	fileTree = FileSearch.getFileTree(sourceDirectory, searchConfig);

	List<File> files = getFileListFromFileTree();
	int processors = Runtime.getRuntime().availableProcessors();
	ExecutorService threadPool = Executors.newFixedThreadPool(processors);
	monitor.beginTask("Analyze files", files.size());
	for (int index = 0; index < files.size(); index++) {
	    final File file = files.get(index);
	    Runnable callable = new Runnable() {

		@Override
		public void run() {
		    analyzeFile(file);
		    logger.info("Finsihed " + file);
		    monitor.worked(1);
		}
	    };
	    threadPool.execute(callable);
	    if (monitor.isCanceled()) {
		threadPool.shutdownNow();
		try {
		    while (!threadPool.awaitTermination(10, TimeUnit.SECONDS)) {
		    }
		} catch (InterruptedException e) {
		}
		return Status.CANCEL_STATUS;
	    }
	}
	threadPool.shutdown();
	try {
	    while (!threadPool.awaitTermination(10, TimeUnit.SECONDS)) {
	    }
	} catch (InterruptedException e) {
	}
	return Status.OK_STATUS;
    }

    private List<File> getFileListFromFileTree() {
	List<File> files = new ArrayList<File>();
	for (FileTree fileTreeNode : fileTree) {
	    File file = fileTreeNode.getPathFile(false);
	    if (new File(sourceDirectory, file.getPath()).isFile()) {
		files.add(file);
	    }
	}
	return files;
    }

    /**
     * This method analyzes a single file. The file is added to faildFiles if
     * there was a analyzer found, but the analyzer had issues to analyze the
     * file, which might have two reasons:
     * 
     * 1) The file is not valid.
     * 
     * 2) The analyzer is buggy.
     * 
     * In either way, these files needs to be tracked and recorded.
     * 
     * @param file
     *            is the file to be analyzed.
     */
    private void analyzeFile(File file) {
	try {
	    HashId hashId = FileUtilities.createHashId(new File(
		    sourceDirectory, file.getPath()), HashAlgorithm.SHA256);
	    if (storedAlready(hashId)) {
		if (wasAnalyzed(hashId)) {
		    FileAnalysis analysis = getAnalysis(hashId);
		    analyzedFiles.add(new AnalyzedFile(hashId, file, analysis
			    .getTimeStamp(), analysis.getTimeOfRun(), analysis
			    .getLanguage().getName(), analysis.getLanguage()
			    .getVersion()));
		} else {
		    failedFiles.add(file);
		}
	    } else {
		storeFile(file, hashId);
		FileAnalyzerImpl fileAnalyzer = new FileAnalyzerImpl(
			sourceDirectory, getFileStoreDirectory(runDirectory,
				hashId), file, hashId);
		fileAnalyzer.analyze();
		if (fileAnalyzer.isAnalyzed()) {
		    AnalyzedFile analyzedFile = fileAnalyzer.getAnalyzedFile();
		    analyzedFiles.add(analyzedFile);
		} else {
		    failedFiles.add(file);
		    logger.warn("File " + file + " could be analyzed.");
		}
	    }
	} catch (Exception e) {
	    failedFiles.add(file);
	    logger.error(e.getMessage(), e);
	}
    }

    @Override
    public List<AnalyzedFile> getAnalyzedFiles() {
	return analyzedFiles;
    }

    @Override
    public FileTree getFileTree() {
	return fileTree;
    }

    @Override
    public List<File> getFailedFiles() {
	return failedFiles;
    }

    @Override
    public FileAnalysis getAnalysis(HashId hashId) {
	File fileStoreDirectory = getFileStoreDirectory(runDirectory, hashId);
	File analyzerFile = AnalyzedFileHelper
		.getAnalyzerFile(fileStoreDirectory);
	return analyzerFactory.restore(analyzerFile);
    }

    @Override
    public AnalyzedFile findAnalyzedFile(File file) {
	for (AnalyzedFile analyzedFile : analyzedFiles) {
	    if (analyzedFile.getFile().equals(file)) {
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
	return new AnalysisRunInformation(analysisInformation, uuid,
		creationTime, timeOfRun, "<Not implemented, yet!>");
    }

    public static boolean isAnalysisRunDirectory(File runDirectory) {
	return new File(runDirectory, DIRECTORY_FLAG).exists();
    }

    @Override
    public InputStream loadFile(HashId hashId) throws AnalysisStoreException {
	try {
	    File file = getFileStoreDirectory(new File(runDirectory, "files"),
		    hashId);
	    return new FileInputStream(new File(file, FILE_CONTENT_FILE));
	} catch (FileNotFoundException e) {
	    throw new AnalysisStoreException("Could not load file with id '"
		    + hashId.toString() + "'!", e);
	}
    }

    private void storeFile(File file, HashId hashId) throws IOException {
	File fileStoreDirectory = getFileStoreDirectory(runDirectory, hashId);
	if (!fileStoreDirectory.exists()) {
	    if (!fileStoreDirectory.mkdirs()) {
		throw new IOException("Could not create directory '"
			+ fileStoreDirectory.getPath() + "'");
	    }
	}
	File contentFile = new File(fileStoreDirectory, FILE_CONTENT_FILE);
	FileUtilities.copy(new File(sourceDirectory, file.getPath()),
		contentFile);
    }

    private boolean storedAlready(HashId hashId) {
	File fileStoreDirectory = getFileStoreDirectory(runDirectory, hashId);
	return fileStoreDirectory.exists();
    }

    private boolean wasAnalyzed(HashId hashId) {
	File fileStoreDirectory = getFileStoreDirectory(runDirectory, hashId);
	return AnalyzedFileHelper.getAnalyzerFile(fileStoreDirectory).exists();
    }

    static File getFileStoreDirectory(File runDirectory, HashId hashId) {
	String hash = hashId.getHash();
	String subDir1 = hash.substring(0, 2);
	String subDir2 = hash.substring(2, 4);
	String subDir3 = hash.substring(4);
	return new File(new File(new File(new File(
		new File(runDirectory, ".."), "files"), subDir1), subDir2),
		subDir3);
    }
}