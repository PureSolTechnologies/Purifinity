package com.puresol.coding.client.views;

import java.io.File;

import org.eclipse.jface.viewers.ISelection;

import com.puresol.coding.analysis.api.Analysis;
import com.puresol.coding.analysis.api.AnalysisRun;

/**
 * This class represents a global file analysis selection. This selection is
 * processed by every GUI element which is related to this global selection.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FileAnalysisSelection implements ISelection {

    private final Analysis analysis;
    private final AnalysisRun analysisRun;
    private final File file;

    public FileAnalysisSelection(Analysis analysis, AnalysisRun analysisRun,
	    File file) {
	super();
	this.analysis = analysis;
	this.analysisRun = analysisRun;
	this.file = file;
    }

    public Analysis getAnalysis() {
	return analysis;
    }

    public AnalysisRun getAnalysisRun() {
	return analysisRun;
    }

    public File getFile() {
	return file;
    }

    @Override
    public boolean isEmpty() {
	return true;
    }

}
