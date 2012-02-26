package com.puresol.coding.metrics.sloc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.evaluator.Result;
import com.puresol.math.statistics.Statistics;

public class SLOCResult implements Serializable {

    private static final long serialVersionUID = 1143172056453964480L;

    private final List<Result> results = new ArrayList<Result>();
    private final int phyLOC;
    private final int proLOC;
    private final int comLOC;
    private final int blLOC;
    private final Statistics lineStatistics;

    public SLOCResult(int phyLOC, int proLOC, int comLOC, int blLOC,
	    Statistics lineStatistics) {
	super();
	this.phyLOC = phyLOC;
	this.proLOC = proLOC;
	this.comLOC = comLOC;
	this.blLOC = blLOC;
	this.lineStatistics = lineStatistics;
	createResultsList();
    }

    private void createResultsList() {
	results.add(new Result("phyLOC", "Physical lines of code", phyLOC, ""));
	results.add(new Result("proLOC", "Productive lines of code", proLOC, ""));
	results.add(new Result("comLOC", "Comment lines of code", comLOC, ""));
	results.add(new Result("blLOC", "Blank lines of code", blLOC, ""));
	results.add(new Result("AvgLL", "Average line length", lineStatistics
		.getAvg(), ""));
	results.add(new Result("MedLL", "Median line length", lineStatistics
		.getMedian(), ""));
	results.add(new Result("StdDevLL", "Standard deviation line length",
		lineStatistics.getStdDev(), ""));
	results.add(new Result("MaxLL", "Maximum line length", lineStatistics
		.getMax(), ""));
	results.add(new Result("MinLL", "Minimum line length", lineStatistics
		.getMin(), ""));
    }

    public int getPhyLOC() {
	return phyLOC;
    }

    public int getProLOC() {
	return proLOC;
    }

    public int getComLOC() {
	return comLOC;
    }

    public int getBlLOC() {
	return blLOC;
    }

    public Statistics getLineStatistics() {
	return lineStatistics;
    }

    public List<Result> getResults() {
	return results;
    }

}
