package com.puresol.utils;

/**
 * This class is a simple implementation of a stop watch used for debugging and
 * performance tests.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class StopWatch {

	private long start = 0;
	private long stop = 0;

	public void start() {
		stop = 0;
		start = System.nanoTime();
	}

	public void stop() {
		stop = System.nanoTime();

	}

	public double getSeconds() {
		if (stop == 0) {
			stop();
		}
		return (double) (stop - start) / 1e9;
	}

	@Override
	public String toString() {
		return getSeconds() + "s";
	}
}
