package com.puresoltechnologies.purifinity.client.common.analysis.views;

import org.eclipse.jface.viewers.ISelection;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;

/**
 * This class contains the selection of an {@link AnalysisProject}.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisProjectSelection implements ISelection {

    private final AnalysisProject analysis;

    public AnalysisProjectSelection(AnalysisProject analysis) {
	super();
	this.analysis = analysis;
    }

    public AnalysisProject getAnalysisProject() {
	return analysis;
    }

    @Override
    public boolean isEmpty() {
	return false;
    }

}
