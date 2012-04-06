package com.puresol.coding.client.editors;

import java.io.File;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.puresol.coding.analysis.api.Analysis;

public class DirectoryAnalysisEditorInput implements IEditorInput {

    private final File directory;
    private final Analysis analysis;

    public DirectoryAnalysisEditorInput(File directory, Analysis analysis) {
	super();
	this.directory = directory;
	this.analysis = analysis;
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
	return directory.getName();
    }

    @Override
    public IPersistableElement getPersistable() {
	return null;
    }

    @Override
    public String getToolTipText() {
	return directory.getPath();
    }

    public final File getDirectory() {
	return directory;
    }

    public final Analysis getAnalysis() {
	return analysis;
    }
}
