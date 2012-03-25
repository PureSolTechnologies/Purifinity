package com.puresol.coding.client.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import swing2swt.layout.BorderLayout;

public class FileAnalysisEditor extends EditorPart {
    private Text text;

    public FileAnalysisEditor() {
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
	parent.setLayout(new BorderLayout(0, 0));

	Composite composite = new Composite(parent, SWT.NONE);
	composite.setLayoutData(BorderLayout.NORTH);
	composite.setLayout(new FillLayout(SWT.HORIZONTAL));

	Button btnNewButton = new Button(composite, SWT.NONE);
	btnNewButton.setText("New Button");

	TabFolder tabFolder = new TabFolder(parent, SWT.NONE);
	tabFolder.setLayoutData(BorderLayout.CENTER);

	TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
	tbtmNewItem.setText("New Item");

	text = new Text(tabFolder, SWT.BORDER);
	tbtmNewItem.setControl(text);

	TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
	tbtmNewItem_1.setText("New Item");

	Composite composite_2 = new Composite(tabFolder, SWT.NONE);
	tbtmNewItem_1.setControl(composite_2);
	// TODO Auto-generated method stub
    }

    @Override
    public void setFocus() {
	text.setFocus();
    }

}
