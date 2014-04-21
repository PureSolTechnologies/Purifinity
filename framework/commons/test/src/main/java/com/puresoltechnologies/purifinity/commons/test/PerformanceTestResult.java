package com.puresoltechnologies.purifinity.commons.test;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * This is a single result of a performance test run by
 * {@link PerformanceTester}.
 * 
 * @author Rick-Rainer Ludwig
 */
public class PerformanceTestResult<T> {

	private final Date startTime;
	private final Date stopTime;
	private final int numberOfThreads;
	private final int actionsPerThread;
	private final long durationInMiliseconds;
	private final Map<Integer, Map<Integer, T>> results;
	private final List<Throwable> throwables;

	public PerformanceTestResult(Date startTime, Date stopTime,
			int numberOfThreads, int actionsPerThread,
			Map<Integer, Map<Integer, T>> results, List<Throwable> throwables) {
		super();
		this.startTime = startTime;
		this.stopTime = stopTime;
		this.numberOfThreads = numberOfThreads;
		this.actionsPerThread = actionsPerThread;
		this.durationInMiliseconds = stopTime.getTime() - startTime.getTime();
		this.results = results;
		this.throwables = throwables;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Date getStopTime() {
		return stopTime;
	}

	public int getNumberOfThreads() {
		return numberOfThreads;
	}

	public int getActionsPerThread() {
		return actionsPerThread;
	}

	public long getDurationInMiliseconds() {
		return durationInMiliseconds;
	}

	/**
	 * This method returns the speed in actions per second.
	 * 
	 * @return
	 */
	public double getSpeed() {
		return numberOfThreads * actionsPerThread * 1000.0
				/ durationInMiliseconds;
	}

	public Map<Integer, Map<Integer, T>> getResults() {
		return results;
	}

	public boolean hadErrror() {
		return !throwables.isEmpty();
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Start: ");
		buffer.append(startTime);
		buffer.append("\n");
		buffer.append("Stop: ");
		buffer.append(stopTime);
		buffer.append("\n");
		buffer.append("Duration: ");
		buffer.append(durationInMiliseconds);
		buffer.append(" ms\n");
		buffer.append("Threads: ");
		buffer.append(numberOfThreads);
		buffer.append("\n");
		buffer.append("Actions per Thread: ");
		buffer.append(actionsPerThread);
		buffer.append("\n");
		buffer.append("Speed: ");
		buffer.append(getSpeed());
		buffer.append(" actions/s\n");
		return buffer.toString();
	}
}
