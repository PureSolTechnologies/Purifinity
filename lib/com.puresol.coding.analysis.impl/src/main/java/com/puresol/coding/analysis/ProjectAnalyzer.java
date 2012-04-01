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

import com.puresol.coding.analysis.api.Analysis;
import com.puresol.trees.FileTree;
import com.puresol.utils.DirectoryUtilities;
import com.puresol.utils.FileSearch;
import com.puresol.utils.FileSearchConfiguration;

public class ProjectAnalyzer extends Job implements Serializable {

    private static final long serialVersionUID = 6413809660830217670L;

    private static final Logger logger = LoggerFactory
	    .getLogger(ProjectAnalyzer.class);

    private static final String PROJECT_DIRECTORY_KEY = "ProjectAnalyzer.projectDirectory";
    private static final String SETTINGS_FILE_HEADER = "***********************************************************************\n"
	    + "* These are the settings of the analysis workspace.                   *\n"
	    + "***********************************************************************\n"
	    + "\n";

    private static final String SETTINGS_FILENAME = "settings.properties";
    private static final String ANALYZED_FILES_FILENAME = "analzsed_files.persist";
    private static final String FAILED_FILES_FILENAME = "failed_files.persist";

    private final File SETTINGS_FILE;
    private final File ANALYZED_FILES_FILE;
    private final File FAILED_FILES_FILE;
    private final File workspaceDirectory;
    private final List<AnalyzedFile> analyzedFiles = new ArrayList<AnalyzedFile>();
    private final List<File> failedFiles = new ArrayList<File>();
    private FileTree fileTree = new FileTree(null, "");
    private transient final AnalyzerFactory analyzerFactory = AnalyzerFactory
	    .createFactory();
    private final FileSearchConfiguration searchConfig;

    private File projectDirectory;

    public ProjectAnalyzer(String name, File workspaceDirectory,
	    FileSearchConfiguration searchConfiguration) {
	super(name);
	this.workspaceDirectory = workspaceDirectory;
	this.searchConfig = searchConfiguration;

	SETTINGS_FILE = new File(workspaceDirectory, SETTINGS_FILENAME);
	ANALYZED_FILES_FILE = new File(workspaceDirectory,
		ANALYZED_FILES_FILENAME);
	FAILED_FILES_FILE = new File(workspaceDirectory, FAILED_FILES_FILENAME);
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
	return true;
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
	if (!loadProjectInformation()) {
	    return false;
	}
	return true;
    }

    /**
     * This method writes the project settings to the project directory.
     * 
     * @return
     */
    private boolean writeSettings() {
	try {
	    Properties properties = new Properties();
	    properties.put(ProjectAnalyzer.class.getSimpleName() + ".name",
		    getName());
	    properties.put(ProjectAnalyzer.class.getSimpleName()
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
		    ProjectAnalyzer.class.getSimpleName() + ".name", "unnamed"));
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
	    FileAnalyzer fileAnalyzer = new FileAnalyzer(projectDirectory,
		    workspaceDirectory, file);
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
    public List<AnalyzedFile> getAnalyzedFiles() {
	return analyzedFiles;
    }

    public FileTree getFileTree() {
	return fileTree;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.puresol.coding.analysis.IProjectAnalyzer#getFailedFiles()
     */
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
    public Analysis getAnalysis(AnalyzedFile file) {
	if (file == null) {
	    return null;
	}
	return analyzerFactory.restore(file.getAnalyzerFile());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.puresol.coding.analysis.IProjectAnalyzer#findAnalyzedFile(java.io
     * .File)
     */
    public AnalyzedFile findAnalyzedFile(File file) {
	for (AnalyzedFile analyzedFile : analyzedFiles) {
	    if (analyzedFile.getFile().equals(file)) {
		return analyzedFile;
	    }
	}
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.puresol.coding.analysis.IProjectAnalyzer#findAnalyzedFileBySourceFile
     * (java.io.File)
     */
    public AnalyzedFile findAnalyzedFileBySourceFile(File sourceFile) {
	for (AnalyzedFile analyzedFile : analyzedFiles) {
	    if (analyzedFile.getSourceFile().equals(sourceFile)) {
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

}