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

	private String pattern;
	private Hashtable<File, Analyser> analysers = new Hashtable<File, Analyser>();

	public ProjectAnalyser(String pattern) {
		this.pattern = pattern;
	}

	public void update() {
		analysers = new Hashtable<File, Analyser>();
		analyse();
	}

	private void analyse() {
		List<File> files = FileSearch.find(pattern);
		for (File file : files) {
			analyseFile(file);
		}
	}

	private void analyseFile(File file) {
		try {
			if (file.isFile()) {
				if (!file.getPath().contains("/.")) {
					analysers.put(file, AnalyserFactory.createAnalyser(file));
				}
			}
		} catch (LanguageNotSupportedException e) {
			logger
					.warn("File '"
							+ file.getPath()
							+ "' could not be analysed due to containing no supported language.");
		}
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
