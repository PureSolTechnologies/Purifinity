package com.puresol.coding.analysis;

import java.io.File;

import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.ProjectAnalyser;

public class ProjectStatistics {

    private final ProjectAnalyser analyser;
    private int sloc = 0;

    public ProjectStatistics(ProjectAnalyser analyser) {
	this.analyser = analyser;
	calculate();
    }

    private void calculate() {
	for (File file : analyser.getFiles()) {
	    for (CodeRange codeRange : analyser.getCodeRanges(file)) {
		if (codeRange.getType() == CodeRangeType.FILE) {
		    sloc +=
			    codeRange.getTokenStream().get(
				    codeRange.getStop()).getStopLine();
		}
	    }
	}
    }

    public int getSLOC() {
	return sloc;
    }
}
