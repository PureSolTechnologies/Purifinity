package com.puresol.coding.client.application.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import com.puresol.coding.client.application.controls.DirectoryMetricsControl;

public class DirectoryAnalysisEditor extends EditorPart {

    private DirectoryMetricsControl metricsControl;

    public DirectoryAnalysisEditor() {
	super();
    }

    @Override
    public void doSave(IProgressMonitor monitor) {
	// TODO Auto-generated method stub
    }

    @Override
    public void doSaveAs() {
	// TODO Auto-generated method stub
    }

    @Override
    public void init(IEditorSite site, IEditorInput input)
	    throws PartInitException {
	setSite(site);
	setInput(input);
	setPartName(input.getName());
    }

    @Override
    public boolean isDirty() {
	return false;
    }

    @Override
    public boolean isSaveAsAllowed() {
	return false;
    }

    @Override
    public void createPartControl(Composite parent) {
	DirectoryAnalysisEditorInput editorInput = (DirectoryAnalysisEditorInput) getEditorInput();
	metricsControl = new DirectoryMetricsControl(parent, SWT.NONE,
		editorInput.getAnalysisRun(), editorInput.getDirectory());
    }

    @Override
    public void setFocus() {
	// TODO Auto-generated method stub
    }

}
