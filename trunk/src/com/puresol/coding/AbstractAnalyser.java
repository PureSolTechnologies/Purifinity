/***************************************************************************
 *
 *   AbstractAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

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

	abstract protected void calculate();

	protected void clearAllMetrics() {
		metrics.clear();
	}

	protected void addMetrics(CodeRange codeRange,
			CodeRangeMetrics codeRangeMetrics) {
		metrics.put(codeRange, codeRangeMetrics);
	}

	public ArrayList<CodeRange> getCodeRanges() {
		return codeRanges;
	}
}
