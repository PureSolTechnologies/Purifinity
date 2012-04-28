package com.puresol.coding.client.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalyzedFile;

public class FileAnalysisEditorInput implements IEditorInput {

    private final AnalyzedFile analyzedFile;
    private final AnalysisRun analysisRun;

    public FileAnalysisEditorInput(AnalyzedFile analyzedFile,
	    AnalysisRun analysisRun) {
	super();
	this.analyzedFile = analyzedFile;
	this.analysisRun = analysisRun;
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

    public final AnalysisRun getAnalysisRun() {
	return analysisRun;
    }

}
