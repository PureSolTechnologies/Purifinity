package com.puresol.coding.client.views;

import org.eclipse.jface.viewers.ISelection;

import com.puresol.coding.analysis.api.AnalysisRunInformation;

public class AnalysisRunSelection implements ISelection {

    private final AnalysisRunInformation information;

    public AnalysisRunSelection(AnalysisRunInformation information) {
	super();
	this.information = information;
    }

    public AnalysisRunInformation getInformation() {
	return information;
    }

    @Override
    public boolean isEmpty() {
	return false;
    }

}
