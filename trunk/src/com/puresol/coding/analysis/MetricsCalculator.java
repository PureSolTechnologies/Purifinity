package com.puresol.coding.analysis;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swingx.progress.ProgressObservable;
import javax.swingx.progress.ProgressObserver;

import com.puresol.coding.CodeRange;
import com.puresol.coding.ProjectAnalyser;

public class MetricsCalculator implements ProgressObservable {

	private ProgressObserver observer;
	private final ProjectAnalyser analyser;
	private final ArrayList<CodeRange> codeRanges = new ArrayList<CodeRange>();

	public MetricsCalculator(ProjectAnalyser analyser) {
		this.analyser = analyser;
	}

	@Override
	public void setMonitor(ProgressObserver observer) {
		this.observer = observer;
	}

	@Override
	public void run() {
		getAllCodeRanges();
	}

	private void getAllCodeRanges() {
		for (File file : analyser.getFiles()) {
			for (CodeRange codeRange : analyser.getCodeRanges(file)) {
				codeRanges.add(codeRange);
			}
		}
	}

}
