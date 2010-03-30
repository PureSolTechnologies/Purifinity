package com.puresol.coding.analysis.evaluator;

import java.io.File;
import java.util.ArrayList;

import com.puresol.coding.analysis.ProjectAnalyser;

public abstract class AbstractEvaluator implements Evaluator {

	private final ProjectAnalyser analyser;
	private final ArrayList<File> files = new ArrayList<File>();

	public AbstractEvaluator(ProjectAnalyser analyser) {
		this.analyser = analyser;
	}

	public final ProjectAnalyser getProjectAnalyser() {
		return analyser;
	}

	public final void addFile(File file) {
		files.add(file);
	}

	public final ArrayList<File> getFiles() {
		return files;
	}
}
