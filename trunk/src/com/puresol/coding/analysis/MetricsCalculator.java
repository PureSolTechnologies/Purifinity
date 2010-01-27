package com.puresol.coding.analysis;

import java.io.File;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.swingx.progress.ProgressObservable;
import javax.swingx.progress.ProgressObserver;

import org.apache.log4j.Logger;

import com.puresol.coding.CodeRange;
import com.puresol.coding.ProjectAnalyser;
import com.puresol.coding.lang.LanguageNotSupportedException;

public class MetricsCalculator implements ProgressObservable {

    private static final Logger logger =
	    Logger.getLogger(MetricsCalculator.class);

    private ProgressObserver observer;
    private final ProjectAnalyser analyser;
    private final Hashtable<File, ArrayList<CodeRange>> codeRanges =
	    new Hashtable<File, ArrayList<CodeRange>>();
    private final Hashtable<CodeRange, CodeRangeMetrics> metrics =
	    new Hashtable<CodeRange, CodeRangeMetrics>();

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
	try {
	    metrics.put(codeRange, MetricsFactory.create(codeRange));
	} catch (LanguageNotSupportedException e) {
	    logger.warn(e.getMessage());
	}
    }

    public QualityLevel getQualityLevel(File file) {
	QualityLevel level = QualityLevel.HIGH;
	ArrayList<CodeRange> ranges = codeRanges.get(file);
	if (ranges == null) {
	    return level;
	}
	for (CodeRange range : codeRanges.get(file)) {
	    QualityLevel qualityInReport =
		    metrics.get(range).getQualityLevel();
	    if (level.getLevel() > qualityInReport.getLevel()) {
		level = qualityInReport;
	    }
	}
	return level;
    }

    public CodeRangeMetrics getMetrics(CodeRange codeRange) {
	return metrics.get(codeRange);
    }
}
