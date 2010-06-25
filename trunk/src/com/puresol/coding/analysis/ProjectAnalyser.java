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
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.i18n4j.FileSearch;
import javax.swingx.progress.ProgressObservable;
import javax.swingx.progress.ProgressObserver;

import org.apache.log4j.Logger;

import com.puresol.utils.FileUtilities;

/**
 * This class is for analysing a whole project. All source code found is checked
 * for a known language and parsed to CodeRange objects. These CodeRange objects
 * can be used for Evaluator and Metric calculations.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProjectAnalyser implements Serializable, ProgressObservable {

	private static final long serialVersionUID = -5080062306149072901L;

	private static final Logger logger = Logger
			.getLogger(ProjectAnalyser.class);

	private final File projectDirectory;
	private final File workspaceDirectory;
	private final Map<File, Analyser> analysers = new Hashtable<File, Analyser>();
	private final List<File> failedFiles = new ArrayList<File>();
	private transient final AnalyserFactory analyserFactory = AnalyserFactory
			.createFactory();

	private transient ProgressObserver progressMonitor = null;

	public ProjectAnalyser(File projectDirectory, File workspaceDirectory) {
		this.projectDirectory = projectDirectory;
		this.workspaceDirectory = workspaceDirectory;
		checkAndCreateWorkspaceDirectory();
	}

	private void checkAndCreateWorkspaceDirectory() {
		if (!workspaceDirectory.exists()) {
			workspaceDirectory.mkdirs();
		} else if (!workspaceDirectory.isDirectory()) {
			throw new IllegalArgumentException("'" + workspaceDirectory
					+ "' is not a directory!");
		}
	}

	@Override
	public void run() {
		clear();
		analyseFiles();
	}

	private void clear() {
		analysers.clear();
		failedFiles.clear();
	}

	private void analyseFiles() {
		List<File> files = FileSearch.find(projectDirectory, "**/*");
		if (progressMonitor != null) {
			progressMonitor.setRange(0, files.size());
			progressMonitor.setStatus(0);
			progressMonitor.showProgressPercent();
		}
		for (int index = 0; index < files.size(); index++) {
			File file = files.get(index);
			if (progressMonitor != null) {
				progressMonitor.setStatus(index);
				progressMonitor.setText(file.getPath());
			}
			analyseFile(file);
			if (Thread.interrupted()) {
				return;
			}
		}
		if (progressMonitor != null) {
			progressMonitor.finish();
		}
	}

	private void analyseFile(File file) {
		try {
			if ((FileUtilities.addPaths(projectDirectory, file).isFile())
					&& (!file.getPath().contains("/."))) {
				/*
				 * only non hidden files are analyzed...
				 */
				Analyser analyser = analyserFactory.create(file);
				if (analyser != null) {
					analyser.parse();
					analysers.put(file, analyser);
				} else {
					failedFiles.add(file);
				}
			}
		} catch (LanguageNotSupportedException e) {
			logger
					.warn("File '"
							+ file.getPath()
							+ "' could not be analysed due to containing no supported language.");
			failedFiles.add(file);
		} catch (FileNotFoundException e) {
			logger.warn("File '" + file.getPath() + "' is not existing!");
			failedFiles.add(file);
		} catch (AnalyserException e) {
			logger.warn("File '" + file.getPath() + "' is not parsable!");
			failedFiles.add(file);
		}
	}

	public File getProjectDirectory() {
		return projectDirectory;
	}

	public ArrayList<File> getFiles() {
		return new ArrayList<File>(analysers.keySet());
	}

	public List<File> getFailedFiles() {
		return failedFiles;
	}

	public Analyser getAnalyser(File file) {
		return analysers.get(file);
	}

	@Override
	public void setMonitor(ProgressObserver observer) {
		progressMonitor = observer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((analysers == null) ? 0 : analysers.hashCode());
		result = prime
				* result
				+ ((projectDirectory == null) ? 0 : projectDirectory.hashCode());
		result = prime * result
				+ ((failedFiles == null) ? 0 : failedFiles.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectAnalyser other = (ProjectAnalyser) obj;
		if (analysers == null) {
			if (other.analysers != null)
				return false;
		} else if (!analysers.equals(other.analysers))
			return false;
		if (projectDirectory == null) {
			if (other.projectDirectory != null)
				return false;
		} else if (!projectDirectory.equals(other.projectDirectory))
			return false;
		if (failedFiles == null) {
			if (other.failedFiles != null)
				return false;
		} else if (!failedFiles.equals(other.failedFiles))
			return false;
		return true;
	}

}
