package com.puresol.coding.client.views;

import java.io.File;

import org.eclipse.jface.viewers.ISelection;

import com.puresol.coding.analysis.ProjectAnalyzer;

/**
 * This class contains the selection of AnalysisNavigator.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisSelection implements ISelection {

    private final ProjectAnalyzer analyzer;
    private final File sourceFile;

    public AnalysisSelection(ProjectAnalyzer analyzer, File sourceFile) {
	super();
	this.analyzer = analyzer;
	this.sourceFile = sourceFile;
    }

    public ProjectAnalyzer getAnalyzer() {
	return analyzer;
    }

    public File getSourceFile() {
	return sourceFile;
    }

    @Override
    public boolean isEmpty() {
	return false;
    }

}
