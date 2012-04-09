package com.puresol.coding.client.views;

import org.eclipse.jface.viewers.ISelection;

import com.puresol.coding.analysis.api.Analysis;

/**
 * This class contains the selection of an {@link Analysis}.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisSelection implements ISelection {

    private final Analysis analysis;

    public AnalysisSelection(Analysis analysis) {
	super();
	this.analysis = analysis;
    }

    public Analysis getAnalysis() {
	return analysis;
    }

    @Override
    public boolean isEmpty() {
	return false;
    }

}
