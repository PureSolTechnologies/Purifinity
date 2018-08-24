package com.puresoltechnologies.toolshed.server.api.system;

/**
 * @see https://stackoverflow.com/questions/3769405/determining-cpu-utilization
 * 
 * @author Rick-Rainer Ludwig
 */
public class SystemLoad {

    private final int numCPUs;
    private final double loadAvg;
    private final double[] loadAvgs;

    public SystemLoad(int numCPUs, double loadAvg, double[] loadAvgs) {
	super();
	this.numCPUs = numCPUs;
	this.loadAvg = loadAvg;
	this.loadAvgs = loadAvgs;
    }

    public int getNumCPUs() {
	return numCPUs;
    }

    public double getLoadAvg() {
	return loadAvg;
    }

    public double[] getLoadAvgs() {
	return loadAvgs;
    }

}
