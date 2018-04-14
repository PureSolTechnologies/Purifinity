package com.puresoltechnologies.debugging.client.profiles;

public class ProfileMethodEntry {

    private final String className;
    private final String method;
    private final long invocations;
    private final long avgTime;
    private final long minTime;
    private final long maxTime;
    private final long sigmaTime;
    private final long totalTime;

    public ProfileMethodEntry(String className, String method, long invocations, long avgTime, long minTime, long maxTime,
	    long sigmaTime, long totalTime) {
	super();
	this.className = className;
	this.method = method;
	this.invocations = invocations;
	this.avgTime = avgTime;
	this.minTime = minTime;
	this.maxTime = maxTime;
	this.sigmaTime = sigmaTime;
	this.totalTime = totalTime;
    }

    public String getClassName() {
	return className;
    }

    public String getMethod() {
	return method;
    }

    public long getInvocations() {
	return invocations;
    }

    public long getAvgTime() {
	return avgTime;
    }

    public long getMinTime() {
	return minTime;
    }

    public long getMaxTime() {
	return maxTime;
    }

    public long getSigmaTime() {
	return sigmaTime;
    }

    public long getTotalTime() {
	return totalTime;
    }

}
