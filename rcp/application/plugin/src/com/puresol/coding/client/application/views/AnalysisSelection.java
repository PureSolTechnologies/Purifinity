package com.puresol.coding.client.application.views;

import org.eclipse.jface.viewers.ISelection;

import com.puresol.coding.analysis.api.AnalysisProject;

/**
 * This class contains the selection of an {@link AnalysisProject}.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisSelection implements ISelection {

    private final AnalysisProject analysis;

    public AnalysisSelection(AnalysisProject analysis) {
	super();
	this.analysis = analysis;
    }

    public AnalysisProject getAnalysis() {
	return analysis;
    }

    @Override
    public boolean isEmpty() {
	return false;
    }

}
