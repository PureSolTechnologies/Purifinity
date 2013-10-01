package com.puresol.commons.math.statistics;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * This class takes data and uses a cloned copy of it to process more advanced
 * statistics like distributions.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ExtendedStatistics {

	private final Vector<Double> data;
	private final Statistics basicStats;

	private ExtendedStatistics(List<Double> data) {
		super();
		this.data = new Vector<Double>(data);
		Collections.sort(this.data);
		basicStats = new Statistics(data);
	}

	public boolean equals(Object obj) {
		return basicStats.equals(obj);
	}

	public double getMin() {
		return basicStats.getMin();
	}

	public double getMax() {
		return basicStats.getMax();
	}

	public double getMedian() {
		return basicStats.getMedian();
	}

	public double getAvg() {
		return basicStats.getAvg();
	}

	public double getStdDev() {
		return basicStats.getStdDev();
	}

	public int hashCode() {
		return basicStats.hashCode();
	}

	public String toString() {
		return basicStats.toString();
	}

}
