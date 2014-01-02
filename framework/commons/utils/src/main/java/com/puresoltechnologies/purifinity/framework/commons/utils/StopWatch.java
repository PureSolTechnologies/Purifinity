package com.puresoltechnologies.purifinity.framework.commons.utils;

import java.io.Serializable;
import java.util.Date;

/**
 * This class is a simple implementation of a stop watch used for debugging and
 * performance tests.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class StopWatch implements Serializable {

	private static final long serialVersionUID = -8824942249939423671L;

	private Date startTime = null;
	private Date stopTime = null;
	private long start = 0;
	private long stop = 0;

	public void start() {
		stopTime = null;
		stop = 0;
		startTime = new Date();
		start = System.nanoTime();
	}

	public void stop() {
		stop = System.nanoTime();
		stopTime = new Date();
	}

	public double getSeconds() {
		return (stop - start) / 1000000000.0;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Date getStopTime() {
		return stopTime;
	}

	public long getMilliseconds() {
		return ((stop - start) / 1000000);
	}

	@Override
	public String toString() {
		return getSeconds() + "s";
	}
}
