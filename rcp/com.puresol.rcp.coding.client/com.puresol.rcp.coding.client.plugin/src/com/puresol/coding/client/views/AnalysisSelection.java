package com.puresol.coding.client.views;

import org.eclipse.jface.viewers.ISelection;

import com.puresol.coding.analysis.api.Analysis;
import com.puresol.coding.analysis.api.AnalysisInformation;

/**
 * This class contains the selection of an {@link Analysis}.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisSelection implements ISelection {

    private final AnalysisInformation information;

    public AnalysisSelection(AnalysisInformation information) {
	super();
	this.information = information;
    }

    public AnalysisInformation getInformation() {
	return information;
    }

    @Override
    public boolean isEmpty() {
	return false;
    }

}
