package com.puresol.coding.metrics.sloc;

import java.io.File;
import java.util.Map;

/**
 * This class contains the project results for SLOCMetric.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProjectSLOCResult {

	private final Map<File, SLOCResult> fileResults;
	private SLOCResult projectResult;

	public ProjectSLOCResult(Map<File, SLOCResult> fileResults) {
		super();
		this.fileResults = fileResults;
		calculate();
	}

	private void calculate() {
		int blLOC = 0;
		int comLOC = 0;
		int proLOC = 0;
		int phyLOC = 0;
		for (File file : fileResults.keySet()) {
			SLOCResult slocResult = fileResults.get(file);
			blLOC += slocResult.getBlLOC();
			comLOC += slocResult.getBlLOC();
			proLOC += slocResult.getBlLOC();
			phyLOC += slocResult.getBlLOC();
		}
		projectResult = new SLOCResult(phyLOC, proLOC, comLOC, blLOC, null);
	}

	public SLOCResult getProjectResult() {
		return projectResult;
	}

	public Map<File, SLOCResult> getFileResults() {
		return fileResults;
	}
}
