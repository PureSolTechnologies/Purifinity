package com.puresol.coding.client.application.editors;

import java.io.File;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.puresol.coding.analysis.api.AnalysisRun;

public class NotAnalyzedEditorInput implements IEditorInput {

    private final File file;
    private final AnalysisRun analysisRun;

    public NotAnalyzedEditorInput(File file, AnalysisRun analysisRun) {
	super();
	this.file = file;
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
	return file.getName();
    }

    @Override
    public IPersistableElement getPersistable() {
	return null;
    }

    @Override
    public String getToolTipText() {
	return file.getPath();
    }

    public final File getFile() {
	return file;
    }

    public final AnalysisRun getAnalysisRun() {
	return analysisRun;
    }
}
