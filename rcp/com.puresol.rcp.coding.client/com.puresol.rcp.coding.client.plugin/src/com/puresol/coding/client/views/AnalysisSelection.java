package com.puresol.coding.client.views;

import java.io.File;

import org.eclipse.jface.viewers.ISelection;

import com.puresol.coding.analysis.api.Analysis;

/**
 * This class contains the selection of AnalysisNavigator.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisSelection implements ISelection {

    private final Analysis analysis;
    private final File sourceFile;

    public AnalysisSelection(Analysis analysis, File sourceFile) {
	super();
	this.analysis = analysis;
	this.sourceFile = sourceFile;
    }

    public Analysis getAnalysis() {
	return analysis;
    }

    public File getSourceFile() {
	return sourceFile;
    }

    @Override
    public boolean isEmpty() {
	return false;
    }

}
