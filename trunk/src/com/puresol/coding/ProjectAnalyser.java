package com.puresol.coding;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.i18n4j.FileSearch;

import org.apache.log4j.Logger;

import com.puresol.coding.java.JavaAnalyser;

public class ProjectAnalyser {

	private static final Logger logger = Logger
			.getLogger(ProjectAnalyser.class);

	private String pattern;
	private Hashtable<File, Analyser> analysis = new Hashtable<File, Analyser>();

	public ProjectAnalyser(String pattern) {
		this.pattern = pattern;
	}

	public void update() {
		analysis.clear();
		analyse();
	}

	public void analyse() {
		List<File> files = FileSearch.find(pattern);
		for (File file : files) {
			analyse(file);
		}
	}

	public void analyse(File file) {
		try {
			analysis.put(file, findAnalyser(file));
		} catch (LanguageNotSupportedException e) {
			logger
					.warn("File '"
							+ file.getPath()
							+ "' could not be analysed due to containing no supported language.");
		}
	}

	private Analyser findAnalyser(File file)
			throws LanguageNotSupportedException {
		if (JavaAnalyser.isSuitable(file)) {
			return new JavaAnalyser(file);
		} else {
			throw new LanguageNotSupportedException(
					"No coding language found for file " + file.getPath());
		}
	}

	public Set<File> getFiles() {
		return analysis.keySet();
	}

	public ArrayList<CodeRange> getCodeRanges(File file) {
		return analysis.get(file).getCodeRanges();
	}

	public static void main(String[] args) {
		ProjectAnalyser analyser = new ProjectAnalyser(
				"src/com/puresol/coding/SLOCMetric.java");
		analyser.analyse();
		int sloc = 0;
		Set<File> files = analyser.getFiles();
		for (File file : files) {
			ArrayList<CodeRange> ranges = analyser.getCodeRanges(file);
			for (CodeRange range : ranges) {
				HalsteadMetric halsteadMetric = new HalsteadMetric(range);
				halsteadMetric.printOperators();
				halsteadMetric.printOperands();
				halsteadMetric.print();
				McCabeMetric mcCabeMetric = new McCabeMetric(range);
				mcCabeMetric.print();
				SLOCMetric slocMetric = new SLOCMetric(range);
				slocMetric.print();
				sloc += slocMetric.getPhyLOC();
				MaintainabilityIndex mi = new MaintainabilityIndex(range);
				mi.print();
			}
		}
		CoCoMo cocomo = new CoCoMo(sloc);
		cocomo.print();
	}
}
