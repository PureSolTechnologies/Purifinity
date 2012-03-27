package com.puresol.coding.client.editors;

import java.io.IOException;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import swing2swt.layout.BorderLayout;

import com.puresol.coding.client.Activator;
import com.puresol.coding.client.controls.ParserTreeViewer;
import com.puresol.coding.client.controls.ScrollableFileViewer;

public class FileAnalysisEditor extends EditorPart {

    private static final ILog logger = Activator.getDefault().getLog();
    private ScrollableFileViewer fileViewer;
    private ParserTreeViewer treeViewer;

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
	try {
	    parent.setLayout(new BorderLayout(0, 0));

	    Composite composite = new Composite(parent, SWT.NONE);
	    composite.setLayoutData(BorderLayout.NORTH);
	    composite.setLayout(new FillLayout(SWT.HORIZONTAL));

	    Button btnNewButton = new Button(composite, SWT.NONE);
	    btnNewButton.setText("Refresh");

	    TabFolder tabFolder = new TabFolder(parent, SWT.NONE);
	    tabFolder.setLayoutData(BorderLayout.CENTER);

	    TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
	    tbtmNewItem.setText("Original File");

	    fileViewer = new ScrollableFileViewer(tabFolder);
	    tbtmNewItem.setControl(fileViewer);

	    TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
	    tbtmNewItem_1.setText("Analyzer Tree");

	    treeViewer = new ParserTreeViewer(tabFolder);
	    tbtmNewItem_1.setControl(treeViewer);

	    FileAnalysisEditorInput editorInput = (FileAnalysisEditorInput) getEditorInput();
	    fileViewer.setFileAndUpdateContent(editorInput.getAnalysisFile()
		    .getSourceFile());
	    treeViewer.setContentAndUpdateContent(
		    editorInput.getAnalysisFile(),
		    editorInput.getProjectAnalyzer());
	} catch (IOException e) {
	    logger.log(new Status(Status.ERROR, FileAnalysisEditor.class
		    .getName(), e.getMessage(), e));
	}
    }

    @Override
    public void setFocus() {
	fileViewer.setFocus();
    }

}
