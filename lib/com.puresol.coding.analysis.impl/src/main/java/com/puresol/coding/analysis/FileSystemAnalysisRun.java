package com.puresol.coding.analysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalysisRunInformation;
import com.puresol.coding.analysis.api.AnalyzedFile;
import com.puresol.coding.analysis.api.FileAnalysis;
import com.puresol.trees.FileTree;
import com.puresol.utils.DirectoryUtilities;
import com.puresol.utils.FileSearch;
import com.puresol.utils.FileSearchConfiguration;

public class FileSystemAnalysisRun extends Job implements Serializable,
	AnalysisRun {

    private static final long serialVersionUID = 6413809660830217670L;

    private static final Logger logger = LoggerFactory
	    .getLogger(FileSystemAnalysisRun.class);

    /**
     * This method creates a new project analyzer with a new workspace
     * associated.
     * 
     * @param projectDirectory
     *            is the directory which is to scan for files and to be
     *            analyzed.
     * @param workspaceDirectory
     *            is the directory to put the persisted results to.
     */
    public static AnalysisRun create(String name, File projectDirectory,
	    File workspaceDirectory, FileSearchConfiguration searchConfiguration) {
	FileSystemAnalysisRun projectAnalyser = new FileSystemAnalysisRun(name,
		workspaceDirectory, searchConfiguration);
	if (!projectAnalyser.createProjectDirectory(projectDirectory)) {
	    return null;
	}
	return projectAnalyser;
    }

    /**
     * This method opens an existing project analyzer via its workspace
     * directory.
     * 
     * @param workspaceDirectory
     *            is the directory where the persisted results can be found.
     * @return
     */
    public static AnalysisRun open(File workspaceDirectory) {
	FileSystemAnalysisRun projectAnalyser = new FileSystemAnalysisRun(
		workspaceDirectory);
	if (!projectAnalyser.openAndReadSettings()) {
	    return null;
	}
	return projectAnalyser;
    }

    private static final String PROJECT_DIRECTORY_KEY = "ProjectAnalyzer.projectDirectory";
    private static final String SETTINGS_FILE_HEADER = "***********************************************************************\n"
	    + "* These are the settings of the analysis workspace.                   *\n"
	    + "***********************************************************************\n"
	    + "\n";

    private static final String SETTINGS_FILENAME = "settings.properties";
    private static final String ANALYZED_FILES_FILENAME = "analzsed_files.persist";
    private static final String FAILED_FILES_FILENAME = "failed_files.persist";
    private static final String SEARCH_CONFIGURATION_FILENAME = "search_configuration.persist";

    private final File SETTINGS_FILE;
    private final File ANALYZED_FILES_FILE;
    private final File FAILED_FILES_FILE;
    private final File SEARCH_CONFIGURATION_FILE;
    private final File workspaceDirectory;
    private final List<AnalyzedFile> analyzedFiles = new ArrayList<AnalyzedFile>();
    private final List<File> failedFiles = new ArrayList<File>();
    private FileTree fileTree = new FileTree(null, "");
    private transient final FileAnalysisFactory analyzerFactory = FileAnalysisFactory
	    .createFactory();
    private final FileSearchConfiguration searchConfig;

    private File projectDirectory;

    /**
     * This constructor is used to create a new analysis run. All setup
     * information is set and is immutable.
     * 
     * @param name
     * @param workspaceDirectory
     * @param searchConfiguration
     */
    private FileSystemAnalysisRun(String name, File workspaceDirectory,
	    FileSearchConfiguration searchConfiguration) {
	super(name);
	this.workspaceDirectory = workspaceDirectory;
	this.searchConfig = searchConfiguration;

	SETTINGS_FILE = new File(workspaceDirectory, SETTINGS_FILENAME);
	ANALYZED_FILES_FILE = new File(workspaceDirectory,
		ANALYZED_FILES_FILENAME);
	FAILED_FILES_FILE = new File(workspaceDirectory, FAILED_FILES_FILENAME);
	SEARCH_CONFIGURATION_FILE = new File(workspaceDirectory,
		SEARCH_CONFIGURATION_FILENAME);
    }

    /**
     * This constructor is used to open an existing analysis run.
     * 
     * @param workspaceDirectory
     */
    private FileSystemAnalysisRun(File workspaceDirectory) {
	this("", workspaceDirectory, new FileSearchConfiguration());
    }

    /**
     * This methods creates a new project directory.
     * 
     * @param projectDirectory
     * @return
     */
    boolean createProjectDirectory(File projectDirectory) {
	this.projectDirectory = projectDirectory;
	DirectoryUtilities.checkAndCreateDirectory(workspaceDirectory);
	if (!writeSettings()) {
	    return false;
	}
	if (!writeSearchConfiguration()) {
	    return false;
	}
	return true;
    }

    private boolean writeSearchConfiguration() {
	try {
	    FileOutputStream fileOutputStream = new FileOutputStream(
		    SEARCH_CONFIGURATION_FILE);
	    try {
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(
			fileOutputStream);
		try {
		    objectOutputStream.writeObject(searchConfig);
		    return true;
		} finally {
		    objectOutputStream.close();
		}
	    } finally {
		fileOutputStream.close();
	    }
	} catch (FileNotFoundException e) {
	    logger.error(e.getMessage(), e);
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	}
	return false;
    }

    /**
     * This method opens the project directory and loads all information files.
     * 
     * @return
     */
    boolean openAndReadSettings() {
	if (!workspaceDirectory.exists()) {
	    return false;
	}
	if (!readSettings()) {
	    return false;
	}
	if (!readSearchConfiguration()) {
	    return false;
	}
	if (!loadProjectInformation()) {
	    return false;
	}
	return true;
    }

    private boolean readSearchConfiguration() {
	try {
	    FileInputStream fileInputStream = new FileInputStream(
		    SEARCH_CONFIGURATION_FILE);
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
		    return true;
		} finally {
		    objectOutputStream.close();
		}
	    } finally {
		fileInputStream.close();
	    }
	} catch (FileNotFoundException e) {
	    logger.error(e.getMessage(), e);
	} catch (ClassNotFoundException e) {
	    logger.error(e.getMessage(), e);
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	}
	return false;
    }

    /**
     * This method writes the project settings to the project directory.
     * 
     * @return
     */
    private boolean writeSettings() {
	try {
	    Properties properties = new Properties();
	    properties.put(FileSystemAnalysisRun.class.getSimpleName()
		    + ".name", getName());
	    properties.put(FileSystemAnalysisRun.class.getSimpleName()
		    + ".projectDirectory", projectDirectory.toString());
	    FileOutputStream fileOutputStream = new FileOutputStream(
		    SETTINGS_FILE);
	    try {
		properties.store(fileOutputStream, SETTINGS_FILE_HEADER);
		return true;
	    } finally {
		fileOutputStream.close();
	    }
	} catch (FileNotFoundException e) {
	    logger.error(e.getMessage(), e);
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	}
	return false;
    }

    private boolean readSettings() {
	try {
	    Properties properties = new Properties();
	    properties.load(new FileInputStream(SETTINGS_FILE));
	    projectDirectory = new File(
		    properties.getProperty(PROJECT_DIRECTORY_KEY));
	    setName(properties.getProperty(
		    FileSystemAnalysisRun.class.getSimpleName() + ".name",
		    "unnamed"));
	    return true;
	} catch (FileNotFoundException e) {
	    logger.error(e.getMessage(), e);
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	}
	return false;
    }

    @SuppressWarnings("unchecked")
    private boolean loadProjectInformation() {
	try {
	    analyzedFiles
		    .addAll((List<AnalyzedFile>) restore(ANALYZED_FILES_FILE));
	    failedFiles.addAll((List<File>) restore(FAILED_FILES_FILE));
	    return true;
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	}
	return false;
    }

    private void storeProjectInformation() {
	try {
	    persist(analyzedFiles, ANALYZED_FILES_FILE);
	    persist(failedFiles, FAILED_FILES_FILE);
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	}
    }

    @Override
    protected IStatus run(IProgressMonitor monitor) {
	reset();
	IStatus retVal = analyzeFiles(monitor);
	storeProjectInformation();
	monitor.done();
	return retVal;
    }

    /**
     * This method resets the values for a reanalysis.
     */
    private void reset() {
	analyzedFiles.clear();
	failedFiles.clear();
    }

    private IStatus analyzeFiles(final IProgressMonitor monitor) {
	fileTree = FileSearch.getFileTree(projectDirectory, searchConfig);

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
	    if (new File(projectDirectory, file.getPath()).isFile()) {
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
	    FileAnalyzerImpl fileAnalyzer = new FileAnalyzerImpl(
		    projectDirectory, workspaceDirectory, file);
	    fileAnalyzer.analyze();
	    if (fileAnalyzer.isAnalyzed()) {
		AnalyzedFile analyzedFile = fileAnalyzer.getAnalyzedFile();
		analyzedFiles.add(analyzedFile);
	    } else {
		failedFiles.add(file);
		logger.warn("File " + file + " could be analyzed.");
	    }
	} catch (Exception e) {
	    failedFiles.add(file);
	    logger.error(e.getMessage(), e);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.puresol.coding.analysis.IProjectAnalyzer#getWorkspaceDirectory()
     */
    public File getWorkspaceDirectory() {
	return workspaceDirectory;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.puresol.coding.analysis.IProjectAnalyzer#getProjectDirectory()
     */
    public File getProjectDirectory() {
	return projectDirectory;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.puresol.coding.analysis.IProjectAnalyzer#getAnalyzedFiles()
     */
    /*
     * (non-Javadoc)
     * 
     * @see com.puresol.coding.analysis.ProjectAnalysis#getAnalyzedFiles()
     */
    @Override
    public List<AnalyzedFile> getAnalyzedFiles() {
	return analyzedFiles;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.puresol.coding.analysis.ProjectAnalysis#getFileTree()
     */
    @Override
    public FileTree getFileTree() {
	return fileTree;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.puresol.coding.analysis.IProjectAnalyzer#getFailedFiles()
     */
    /*
     * (non-Javadoc)
     * 
     * @see com.puresol.coding.analysis.ProjectAnalysis#getFailedFiles()
     */
    @Override
    public List<File> getFailedFiles() {
	return failedFiles;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.puresol.coding.analysis.IProjectAnalyzer#getAnalysis(com.puresol.
     * coding.analysis.AnalyzedFile)
     */
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.puresol.coding.analysis.ProjectAnalysis#getAnalysis(com.puresol.coding
     * .analysis.AnalyzedFile)
     */
    @Override
    public FileAnalysis getAnalysis(AnalyzedFile file) {
	if (file == null) {
	    return null;
	}
	return analyzerFactory
		.restore(AnalyzedFileHelper.getAnalyzerFile(file));
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

    private static <T> T restore(File file) throws FileNotFoundException,
	    IOException {
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
	// TODO Auto-generated method stub
	return null;
    }

}