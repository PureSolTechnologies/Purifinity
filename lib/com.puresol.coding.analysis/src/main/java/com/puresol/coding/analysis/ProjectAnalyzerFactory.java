/***************************************************************************
 *
 *   ProjectAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.analysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.utils.DirectoryUtilities;
import com.puresol.utils.FileSearch;
import com.puresol.utils.Persistence;
import com.puresol.utils.PersistenceException;

/**
 * This class is for analyzing a whole project. All source code found is checked
 * for a known language and parsed to CodeRange objects. These CodeRange objects
 * can be used for Evaluator and Metric calculations.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProjectAnalyzerFactory extends ProjectAnalyzer {

    private static final long serialVersionUID = -5080062306149072901L;

    private static final Logger logger = LoggerFactory
	    .getLogger(ProjectAnalyzerFactory.class);

    /*
     * ****************************************************************
     * Following are some constants which are general for workspace handling...
     * ****************************************************************
     */
    private static final String SETTINGS_FILENAME = "settings.properties";
    private static final String ANALYZED_FILES_FILENAME = "analzsed_files.persist";
    private static final String FAILED_FILES_FILENAME = "failed_files.persist";
    private static final String PROJECT_DIRECTORY_KEY = "ProjectAnalyzer.projectDirectory";
    private static final String SETTINGS_FILE_HEADER = "***********************************************************************\n"
	    + "* These are the settings of the analysis workspace.                   *\n"
	    + "***********************************************************************\n"
	    + "\n";

    /*
     * ****************************************************************
     * Static implementations...
     * ****************************************************************
     */

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
    public static ProjectAnalyzer create(File projectDirectory,
	    File workspaceDirectory) {
	ProjectAnalyzerFactory projectAnalyser = new ProjectAnalyzerFactory(
		workspaceDirectory);
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
    public static ProjectAnalyzer open(File workspaceDirectory) {
	ProjectAnalyzerFactory projectAnalyser = new ProjectAnalyzerFactory(
		workspaceDirectory);
	if (!projectAnalyser.openAndReadSettings()) {
	    return null;
	}
	return projectAnalyser;
    }

    /*
     * ****************************************************************
     * Class implementations follows...
     * ****************************************************************
     */

    private final File SETTINGS_FILE;
    private final File ANALYZED_FILES_FILE;
    private final File FAILED_FILES_FILE;
    private final File workspaceDirectory;
    private final List<AnalyzedFile> analyzedFiles = new ArrayList<AnalyzedFile>();
    private final List<File> failedFiles = new ArrayList<File>();
    private transient final AnalyzerFactory analyzerFactory = AnalyzerFactory
	    .createFactory();

    private File projectDirectory;

    /**
     * This constructor is private to force the project analyzer to be created
     * by static factory methods.
     * 
     * @param workspaceDirectory
     */
    private ProjectAnalyzerFactory(File workspaceDirectory) {
	super("Project Analyser Factory");
	this.workspaceDirectory = workspaceDirectory;
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
    private boolean createProjectDirectory(File projectDirectory) {
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
    private boolean openAndReadSettings() {
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
	    properties.put(getClass().getSimpleName() + ".projectDirectory",
		    projectDirectory.toString());
	    properties.store(new FileOutputStream(SETTINGS_FILE),
		    SETTINGS_FILE_HEADER);
	    return true;
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
	    analyzedFiles.addAll((List<AnalyzedFile>) Persistence
		    .restore(ANALYZED_FILES_FILE));
	    failedFiles.addAll((List<File>) Persistence
		    .restore(FAILED_FILES_FILE));
	    return true;
	} catch (PersistenceException e) {
	    logger.error(e.getMessage(), e);
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	}
	return false;
    }

    private void storeProjectInformation() {
	try {
	    Persistence.persist(analyzedFiles, ANALYZED_FILES_FILE);
	    Persistence.persist(failedFiles, FAILED_FILES_FILE);
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.puresol.coding.analysis.IProjectAnalyzer#run()
     */
    @Override
    protected IStatus run(IProgressMonitor monitor) {
	reset();
	analyzeFiles(monitor);
	storeProjectInformation();
	return Status.OK_STATUS;
    }

    /**
     * This method resets the values for a reanalysis.
     */
    private void reset() {
	analyzedFiles.clear();
	failedFiles.clear();
    }

    private void analyzeFiles(IProgressMonitor monitor) {
	List<File> files = FileSearch.find(projectDirectory, "*");
	monitor.beginTask("Analyze files", files.size());
	for (int index = 0; index < files.size(); index++) {
	    File file = files.get(index);
	    if (monitor != null) {
		monitor.worked(index);
	    }
	    analyzeFile(file);
	    if (Thread.interrupted()) {
		return;
	    }
	}
	monitor.done();
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
	    AnalyzedFile analyzedFile = fileAnalyzer.getAnalyzedFile();
	    if (!fileAnalyzer.isAnalyzed()) {
		return;
	    }
	    analyzedFiles.add(analyzedFile);
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
    @Override
    public File getWorkspaceDirectory() {
	return workspaceDirectory;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.puresol.coding.analysis.IProjectAnalyzer#getProjectDirectory()
     */
    @Override
    public File getProjectDirectory() {
	return projectDirectory;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.puresol.coding.analysis.IProjectAnalyzer#getAnalyzedFiles()
     */
    @Override
    public List<AnalyzedFile> getAnalyzedFiles() {
	return analyzedFiles;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.puresol.coding.analysis.IProjectAnalyzer#getFailedFiles()
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
    @Override
    public Analysis getAnalysis(AnalyzedFile file) {
	if (file == null) {
	    return null;
	}
	return analyzerFactory.restore(file.getAnalyzerFile());
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime
		* result
		+ ((ANALYZED_FILES_FILE == null) ? 0 : ANALYZED_FILES_FILE
			.hashCode());
	result = prime
		* result
		+ ((FAILED_FILES_FILE == null) ? 0 : FAILED_FILES_FILE
			.hashCode());
	result = prime * result
		+ ((SETTINGS_FILE == null) ? 0 : SETTINGS_FILE.hashCode());
	result = prime * result
		+ ((analyzedFiles == null) ? 0 : analyzedFiles.hashCode());
	result = prime * result
		+ ((failedFiles == null) ? 0 : failedFiles.hashCode());
	result = prime
		* result
		+ ((projectDirectory == null) ? 0 : projectDirectory.hashCode());
	result = prime
		* result
		+ ((workspaceDirectory == null) ? 0 : workspaceDirectory
			.hashCode());
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.puresol.coding.analysis.IProjectAnalyzer#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	ProjectAnalyzerFactory other = (ProjectAnalyzerFactory) obj;
	if (ANALYZED_FILES_FILE == null) {
	    if (other.ANALYZED_FILES_FILE != null)
		return false;
	} else if (!ANALYZED_FILES_FILE.equals(other.ANALYZED_FILES_FILE))
	    return false;
	if (FAILED_FILES_FILE == null) {
	    if (other.FAILED_FILES_FILE != null)
		return false;
	} else if (!FAILED_FILES_FILE.equals(other.FAILED_FILES_FILE))
	    return false;
	if (SETTINGS_FILE == null) {
	    if (other.SETTINGS_FILE != null)
		return false;
	} else if (!SETTINGS_FILE.equals(other.SETTINGS_FILE))
	    return false;
	if (analyzedFiles == null) {
	    if (other.analyzedFiles != null)
		return false;
	} else if (!analyzedFiles.equals(other.analyzedFiles))
	    return false;
	if (failedFiles == null) {
	    if (other.failedFiles != null)
		return false;
	} else if (!failedFiles.equals(other.failedFiles))
	    return false;
	if (projectDirectory == null) {
	    if (other.projectDirectory != null)
		return false;
	} else if (!projectDirectory.equals(other.projectDirectory))
	    return false;
	if (workspaceDirectory == null) {
	    if (other.workspaceDirectory != null)
		return false;
	} else if (!workspaceDirectory.equals(other.workspaceDirectory))
	    return false;
	return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.puresol.coding.analysis.IProjectAnalyzer#findAnalyzedFile(java.io
     * .File)
     */
    @Override
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
    @Override
    public AnalyzedFile findAnalyzedFileBySourceFile(File sourceFile) {
	for (AnalyzedFile analyzedFile : analyzedFiles) {
	    if (analyzedFile.getSourceFile().equals(sourceFile)) {
		return analyzedFile;
	    }
	}
	return null;
    }

}
