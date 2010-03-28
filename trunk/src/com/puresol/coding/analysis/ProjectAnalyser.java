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
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.i18n4j.FileSearch;
import javax.swingx.progress.ProgressObservable;
import javax.swingx.progress.ProgressObserver;

import org.apache.log4j.Logger;

import com.puresol.coding.lang.LanguageNotSupportedException;
import com.puresol.utils.Files;

public class ProjectAnalyser implements ProgressObservable {

	private static final Logger logger = Logger
			.getLogger(ProjectAnalyser.class);

	private final File directory;
	private final String pattern;
	private final Hashtable<File, Analyser> analysers = new Hashtable<File, Analyser>();
	private ProgressObserver progressMonitor = null;
	private AnalyserFactory analyserFactory = AnalyserFactory.createFactory();

	public ProjectAnalyser(File directory, String pattern) {
		this.directory = directory;
		this.pattern = pattern;
	}

	public void run() {
		clear();
		analyseFiles();
	}

	private void clear() {
		analysers.clear();
	}

	private void analyseFiles() {
		List<File> files = FileSearch.find(directory, pattern);
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
			if ((Files.addPaths(directory, file).isFile())
					&& (!file.getPath().contains("/."))) {
				analysers.put(file, analyserFactory.create(directory,
						file));
			}
		} catch (LanguageNotSupportedException e) {
			logger
					.warn("File '"
							+ file.getPath()
							+ "' could not be analysed due to containing no supported language.");
		} catch (FileNotFoundException e) {
			logger.warn("File '" + file.getPath() + "' is not existing!");
		}
	}

	public File getProjectDirectory() {
		return directory;
	}

	public ArrayList<File> getFiles() {
		return new ArrayList<File>(analysers.keySet());
	}

	public Analyser getAnalyser(File file) {
		return analysers.get(file);
	}

	public ArrayList<CodeRange> getCodeRanges(File file) {
		if (analysers == null) {
			return new ArrayList<CodeRange>();
		}
		if (file == null) {
			return new ArrayList<CodeRange>();
		}
		if (!analysers.containsKey(file)) {
			return new ArrayList<CodeRange>();
		}
		Analyser analyser = analysers.get(file);
		if (analyser == null) {
			return new ArrayList<CodeRange>();
		}
		return analysers.get(file).getCodeRanges();
	}

	@Override
	public void setMonitor(ProgressObserver observer) {
		progressMonitor = observer;
	}

}
