/***************************************************************************
 *
 *   Statistics.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresoltechnologies.commons.math.statistics;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * This is a simple statistics class for calculating some basic statistics.
 * 
 * @author Rick-Rainer Ludwig
 */
public class Statistics implements Serializable {

	private static final long serialVersionUID = 951111394731975571L;

	private int count = 0;
	private double min = 0.0;
	private double max = 0.0;
	private double avg = 0.0;
	private Double median = 0.0;
	private Double stdDev = 0.0;

	public Statistics(List<Double> data) {
		if (data.size() == 0) {
			throw new IllegalArgumentException("No data was given!");
		}
		calculate(data);
	}

	private Statistics(int count, double min, double max, double avg,
			Double median, Double stdDev) {
		this.count = count;
		this.min = min;
		this.max = max;
		this.avg = avg;
		this.median = median;
		this.stdDev = stdDev;
	}

	private void calculate(List<Double> data) {
		Collections.sort(data);
		count = data.size();
		min = data.get(0);
		max = data.get(data.size() - 1);
		calcMedian(data);
		calcAvg(data);
		calcStdDev(data);
	}

	private void calcMedian(List<Double> data) {
		if ((data.size() % 2) == 1) {
			median = data.get(data.size() / 2);
		} else {
			median = data.get(data.size() / 2 - 1);
			median += data.get(data.size() / 2);
			median /= 2.0;
		}
	}

	private void calcAvg(List<Double> data) {
		avg = 0.0;
		for (double d : data) {
			avg += d;
		}
		avg /= data.size();
	}

	private void calcStdDev(List<Double> data) {
		stdDev = 0.0;
		for (double d : data) {
			stdDev += Math.pow(d - avg, 2.0);
		}
		stdDev /= data.size() - 1;
		stdDev = Math.sqrt(stdDev);
	}

	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}

	public Double getMedian() {
		return median;
	}

	public double getAvg() {
		return avg;
	}

	public Double getStdDev() {
		return stdDev;
	}

	public int getCount() {
		return count;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("avg=");
		buffer.append(avg);
		buffer.append(",stddev=");
		buffer.append(stdDev);
		buffer.append(",min=");
		buffer.append(min);
		buffer.append(",max=");
		buffer.append(max);
		buffer.append(",median=");
		buffer.append(median);
		return buffer.toString();
	}

	public static Statistics combine(Statistics left, Statistics right) {
		int leftCount = left.getCount();
		int rightCount = right.getCount();
		double min = Math.min(left.getMin(), right.getMin());
		double max = Math.max(left.getMax(), right.getMax());
		double avg = (leftCount * left.getAvg() + rightCount * right.getAvg())
				/ (leftCount + rightCount);
		return new Statistics(leftCount + rightCount, min, max, avg, null, null);
	}
}
