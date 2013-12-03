package com.puresoltechnologies.purifinity.client.common.analysis.views;

import org.eclipse.jface.viewers.ISelection;

import com.puresoltechnologies.purifinity.coding.analysis.api.AnalysisRun;

/**
 * This class represents a single analysis run selection.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisRunSelection implements ISelection {

    private final AnalysisRun analysisRun;

    public AnalysisRunSelection(AnalysisRun analysisRun) {
	super();
	this.analysisRun = analysisRun;
    }

    public AnalysisRun getAnalysisRun() {
	return analysisRun;
    }

    @Override
    public boolean isEmpty() {
	return false;
    }

}
