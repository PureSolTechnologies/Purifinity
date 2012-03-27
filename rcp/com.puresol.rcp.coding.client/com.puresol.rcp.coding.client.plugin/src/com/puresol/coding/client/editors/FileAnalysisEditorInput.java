package com.puresol.coding.client.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.puresol.coding.analysis.AnalyzedFile;
import com.puresol.coding.analysis.ProjectAnalyzer;

public class FileAnalysisEditorInput implements IEditorInput {

    private final AnalyzedFile analyzedFile;
    private final ProjectAnalyzer projectAnalyzer;

    public FileAnalysisEditorInput(AnalyzedFile analyzedFile,
	    ProjectAnalyzer projectAnalyzer) {
	super();
	this.analyzedFile = analyzedFile;
	this.projectAnalyzer = projectAnalyzer;
    }

    @Override
    public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
	return null;
    }

    @Override
    public boolean exists() {
	return true;
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
	return null;
    }

    @Override
    public String getName() {
	return analyzedFile.getFile().getName();
    }

    @Override
    public IPersistableElement getPersistable() {
	return null;
    }

    @Override
    public String getToolTipText() {
	return analyzedFile.getFile().getPath();
    }

    public final AnalyzedFile getAnalysisFile() {
	return analyzedFile;
    }

    public final ProjectAnalyzer getProjectAnalyzer() {
	return projectAnalyzer;
    }

}
