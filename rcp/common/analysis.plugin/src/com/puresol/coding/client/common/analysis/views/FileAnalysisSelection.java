package com.puresol.coding.client.common.analysis.views;

import org.eclipse.jface.viewers.ISelection;

import com.puresol.coding.analysis.api.AnalysisProject;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.HashIdFileTree;

/**
 * This class represents a global file analysis selection. This selection is
 * processed by every GUI element which is related to this global selection.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FileAnalysisSelection implements ISelection {

    private final AnalysisProject analysis;
    private final AnalysisRun analysisRun;
    private final HashIdFileTree file;

    public FileAnalysisSelection(AnalysisProject analysis,
	    AnalysisRun analysisRun, HashIdFileTree file) {
	super();
	this.analysis = analysis;
	this.analysisRun = analysisRun;
	this.file = file;
    }

    public AnalysisProject getAnalysis() {
	return analysis;
    }

    public AnalysisRun getAnalysisRun() {
	return analysisRun;
    }

    public HashIdFileTree getHashIdFile() {
	return file;
    }

    @Override
    public boolean isEmpty() {
	return true;
    }

}
