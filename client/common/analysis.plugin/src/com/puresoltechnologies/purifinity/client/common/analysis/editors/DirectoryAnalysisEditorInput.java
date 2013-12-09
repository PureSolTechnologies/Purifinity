package com.puresoltechnologies.purifinity.client.common.analysis.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.HashIdFileTree;

public class DirectoryAnalysisEditorInput implements IEditorInput {

    private final HashIdFileTree directory;
    private final AnalysisRun analysisRun;

    public DirectoryAnalysisEditorInput(HashIdFileTree directory,
	    AnalysisRun analysisRun) {
	super();
	this.directory = directory;
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
	return directory.getName();
    }

    @Override
    public IPersistableElement getPersistable() {
	return null;
    }

    @Override
    public String getToolTipText() {
	return directory.getPathFile(false).getPath();
    }

    public final HashIdFileTree getDirectory() {
	return directory;
    }

    public final AnalysisRun getAnalysisRun() {
	return analysisRun;
    }
}
