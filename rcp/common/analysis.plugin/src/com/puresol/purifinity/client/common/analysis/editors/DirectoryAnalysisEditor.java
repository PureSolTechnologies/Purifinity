package com.puresol.purifinity.client.common.analysis.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

import com.puresol.purifinity.client.common.analysis.Activator;
import com.puresol.purifinity.client.common.ui.editors.AbstractPureSolTechnologiesEditor;

public class DirectoryAnalysisEditor extends AbstractPureSolTechnologiesEditor {

	public DirectoryAnalysisEditor() {
		super(Activator.getDefault());
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
		super.createPartControl(parent);
		DirectoryAnalysisEditorInput editorInput = (DirectoryAnalysisEditorInput) getEditorInput();
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}

}
