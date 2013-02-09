package com.puresol.coding.metrics.sloc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.impl.evaluation.Result;
import com.puresol.utils.math.statistics.Statistics;

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
	if (lineStatistics != null) {
	    results.add(new Result("AvgLL", "Average line length",
		    lineStatistics.getAvg(), ""));
	    results.add(new Result("MedLL", "Median line length",
		    lineStatistics.getMedian(), ""));
	    results.add(new Result("StdDevLL",
		    "Standard deviation line length", lineStatistics
			    .getStdDev(), ""));
	    results.add(new Result("MaxLL", "Maximum line length",
		    lineStatistics.getMax(), ""));
	    results.add(new Result("MinLL", "Minimum line length",
		    lineStatistics.getMin(), ""));
	}
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

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder("PhyLOC:\t");
	builder.append(phyLOC);
	builder.append("\n");
	builder.append("ProLOC:\t");
	builder.append(proLOC);
	builder.append("\n");
	builder.append("ComLOC:\t");
	builder.append(comLOC);
	builder.append("\n");
	builder.append("BlLOC:\t");
	builder.append(blLOC);
	// TODO the statistics for the line length needs to be clean up to get
	// the correct line length for comments, too
	// builder.append("\n");
	// builder.append("\n");
	// builder.append("Line lengths:\t");
	// builder.append(lineStatistics.toString());
	return builder.toString();
    }

    /**
     * This method combines to different SLOC results into one. The line
     * statistics is dropped here due to the missing data to do it right and the
     * quite low meaning of the number for combined files or whole directories.
     * 
     * @param sloc1
     * @param sloc2
     * @return
     */
    public static SLOCResult combine(SLOCResult sloc1, SLOCResult sloc2) {

	int phyLOC = sloc1.getPhyLOC() + sloc2.getPhyLOC();
	int proLOC = sloc1.getProLOC() + sloc2.getProLOC();
	int comLOC = sloc1.getComLOC() + sloc2.getComLOC();
	int blLOC = sloc1.getBlLOC() + sloc2.getBlLOC();

	return new SLOCResult(phyLOC, proLOC, comLOC, blLOC, null);
    }

}
