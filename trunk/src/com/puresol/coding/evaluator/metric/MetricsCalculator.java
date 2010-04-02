package com.puresol.coding.evaluator.metric;

import java.io.File;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.swingx.progress.ProgressObservable;
import javax.swingx.progress.ProgressObserver;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.evaluator.QualityLevel;

public class MetricsCalculator implements ProgressObservable {

	private ProgressObserver observer;
	private final ProjectAnalyser analyser;
	private final Hashtable<File, ArrayList<CodeRange>> codeRanges = new Hashtable<File, ArrayList<CodeRange>>();
	private final Hashtable<CodeRange, CodeRangeMetrics> metrics = new Hashtable<CodeRange, CodeRangeMetrics>();

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
		calculateMetrics();
	}

	private void getAllCodeRanges() {
		for (File file : analyser.getFiles()) {
			ArrayList<CodeRange> ranges = new ArrayList<CodeRange>();
			for (CodeRange codeRange : analyser.getCodeRanges(file)) {
				ranges.add(codeRange);
			}
			codeRanges.put(file, ranges);
		}
	}

	private void calculateMetrics() {
		if (observer != null) {
			observer.setRange(0, codeRanges.keySet().size());
		}
		int index = 0;
		for (File file : codeRanges.keySet()) {
			index++;
			if (observer != null) {
				observer.setStatus(index);
			}
			ArrayList<CodeRange> ranges = codeRanges.get(file);
			for (CodeRange codeRange : ranges) {
				if (Thread.interrupted()) {
					return;
				}
				if (observer != null) {
					observer.setStatus(index);
				}
				calculateMetric(codeRange);
			}
		}
		if (observer != null) {
			observer.finish();
		}
	}

	private void calculateMetric(CodeRange codeRange) {
		metrics.put(codeRange, new CodeRangeMetrics(codeRange));
	}

	public QualityLevel getQualityLevel(File file) {
		QualityLevel level = QualityLevel.HIGH;
		ArrayList<CodeRange> ranges = codeRanges.get(file);
		if (ranges != null) {
			for (CodeRange range : ranges) {
				QualityLevel rangeQualityLevel = metrics.get(range)
						.getQualityLevel();
				level = QualityLevel.getMinLevel(level, rangeQualityLevel);
			}
		}
		return level;
	}

	public CodeRangeMetrics getMetrics(CodeRange codeRange) {
		return metrics.get(codeRange);
	}
}
