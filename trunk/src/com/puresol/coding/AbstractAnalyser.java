package com.puresol.coding;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

abstract public class AbstractAnalyser implements Analyser {

	private File projectDirectory = null;
	private File file = null;

	private Hashtable<CodeRange, CodeRangeMetrics> metrics = new Hashtable<CodeRange, CodeRangeMetrics>();
	private ArrayList<CodeRange> codeRanges = null;

	public AbstractAnalyser(File projectDirectory, File file) {
		this.projectDirectory = projectDirectory;
		this.file = file;
	}

	public File getProjectDirectory() {
		return projectDirectory;
	}

	public File getFile() {
		return file;
	}

	public CodeRangeMetrics getMetrics(CodeRange codeRange) {
		return metrics.get(codeRange);
	}

	protected void setCodeRanges(ArrayList<CodeRange> codeRanges) {
		this.codeRanges = codeRanges;
		calculate();
	}

	private void calculate() {
		metrics.clear();
		for (CodeRange codeRange : codeRanges) {
			metrics.put(codeRange, new CodeRangeMetrics(codeRange));
		}
	}

	public ArrayList<CodeRange> getCodeRanges() {
		return codeRanges;
	}
}
