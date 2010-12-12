package com.puresol.coding.metrics.sloc;

import com.puresol.statistics.Statistics;

public class SLOCResult {

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

}
