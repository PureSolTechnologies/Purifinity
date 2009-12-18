package com.puresol.coding;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.i18n4j.FileSearch;

import org.apache.log4j.Logger;

public class ProjectAnalyser {

	private static final Logger logger = Logger
			.getLogger(ProjectAnalyser.class);

	private File projectDirectory;
	private String pattern;
	private Hashtable<File, Analyser> analysers = new Hashtable<File, Analyser>();

	public ProjectAnalyser(File projectDirectory, String pattern) {
		this.projectDirectory = projectDirectory;
		this.pattern = pattern;
	}

	public void update() {
		analysers = new Hashtable<File, Analyser>();
		analyseFiles();
		calculateStatistics();
	}

	private void analyseFiles() {
		List<File> files = FileSearch.find(projectDirectory.getPath() + "/"
				+ pattern);
		for (File file : files) {
			analyseFile(file);
		}
	}

	private void analyseFile(File file) {
		try {
			if ((file.isFile()) && (!file.getPath().contains("/."))) {
				String fileString = file.getPath().toString().substring(
						projectDirectory.getPath().length());
				file = new File(fileString);
				analysers.put(file, AnalyserFactory.createAnalyser(
						projectDirectory, file));
			}
		} catch (LanguageNotSupportedException e) {
			logger
					.warn("File '"
							+ file.getPath()
							+ "' could not be analysed due to containing no supported language.");
		}
	}

	private void calculateStatistics() {

	}

	public File getProjectDirectory() {
		return projectDirectory;
	}

	public Set<File> getFiles() {
		return analysers.keySet();
	}

	public Analyser getAnalyser(File file) {
		return analysers.get(file);
	}

	public ArrayList<CodeRange> getCodeRanges(File file) {
		Analyser analyser = analysers.get(file);
		if (analyser == null) {
			return null;
		}
		return analysers.get(file).getCodeRanges();
	}

	public CodeRangeMetrics getMetrics(File file, CodeRange codeRange) {
		Analyser analyser = analysers.get(file);
		if (analyser == null) {
			return null;
		}
		return analyser.getMetrics(codeRange);
	}
}
