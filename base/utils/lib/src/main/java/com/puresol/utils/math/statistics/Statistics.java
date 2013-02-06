/***************************************************************************
 *
 *   Statistics.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.utils.math.statistics;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Statistics implements Serializable {

    private static final long serialVersionUID = 951111394731975571L;

    private static final Logger logger = LoggerFactory
	    .getLogger(Statistics.class);

    private double min = 0.0;
    private double max = 0.0;
    private double avg = 0.0;
    private double median = 0.0;
    private double stdDev = 0.0;

    public Statistics(List<Double> data) {
	if (data.size() == 0) {
	    throw new IllegalArgumentException("No data was given!");
	}
	logger.trace("size of list: " + data.size());
	calculate(data);
    }

    private void calculate(List<Double> data) {
	Collections.sort(data);
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

    public double getMedian() {
	return median;
    }

    public double getAvg() {
	return avg;
    }

    public double getStdDev() {
	return stdDev;
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
}
