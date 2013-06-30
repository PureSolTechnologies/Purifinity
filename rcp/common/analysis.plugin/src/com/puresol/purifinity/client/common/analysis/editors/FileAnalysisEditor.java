package com.puresol.purifinity.client.common.analysis.editors;

import java.io.IOException;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import com.puresol.purifinity.client.common.analysis.Activator;
import com.puresol.purifinity.client.common.analysis.controls.ParserTreeControl;
import com.puresol.purifinity.client.common.analysis.controls.ScrollableFileViewer;
import com.puresol.purifinity.client.common.branding.ClientImages;
import com.puresol.purifinity.coding.analysis.api.FileStore;
import com.puresol.purifinity.coding.analysis.api.FileStoreException;
import com.puresol.purifinity.coding.analysis.api.FileStoreFactory;
import com.puresol.purifinity.uhura.source.SourceCode;
import com.puresol.purifinity.utils.HashId;

public class FileAnalysisEditor extends EditorPart {

    private static final ILog logger = Activator.getDefault().getLog();

    private ScrollableFileViewer fileViewer;
    private ParserTreeControl treeViewer;
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
	FileAnalysisEditorInput fileAnalysisInput = (FileAnalysisEditorInput) input;
	setPartName(fileAnalysisInput.getAnalyzedCode().getSourceLocation()
		.getName());
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
	    Composite composite = new Composite(parent, SWT.NONE);
	    composite.setLayout(new FormLayout());

	    Composite buttonArea = new Composite(composite, SWT.NONE);
	    FormData fd_buttonArea = new FormData();
	    fd_buttonArea.top = new FormAttachment(0, 10);
	    fd_buttonArea.left = new FormAttachment(0, 10);
	    fd_buttonArea.bottom = new FormAttachment(0, 36);
	    fd_buttonArea.right = new FormAttachment(0, 105);
	    buttonArea.setLayoutData(fd_buttonArea);
	    buttonArea.setLayout(new FillLayout(SWT.HORIZONTAL));

	    Button refreshButton = new Button(buttonArea, SWT.NONE);
	    refreshButton.setImage(ClientImages
		    .getImage(ClientImages.ARROW_REFRESH_16x16));
	    refreshButton.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
		}
	    });
	    refreshButton.setText("Refresh");

	    TabFolder tabFolder = new TabFolder(composite, SWT.NONE);
	    FormData fd_tabFolder = new FormData();
	    fd_tabFolder.top = new FormAttachment(buttonArea, 6);
	    fd_tabFolder.bottom = new FormAttachment(100);
	    fd_tabFolder.right = new FormAttachment(100);
	    fd_tabFolder.left = new FormAttachment(0);
	    tabFolder.setLayoutData(fd_tabFolder);

	    TabItem fileViewerTab = new TabItem(tabFolder, SWT.NONE);
	    fileViewerTab.setText("Original File");

	    fileViewer = new ScrollableFileViewer(tabFolder);
	    fileViewerTab.setControl(fileViewer);

	    TabItem treeViewerTab = new TabItem(tabFolder, SWT.NONE);
	    treeViewerTab.setText("Parser Tree");

	    treeViewer = new ParserTreeControl(tabFolder);
	    treeViewerTab.setControl(treeViewer);
	    FormData fd_treeViewer = new FormData();
	    fd_treeViewer.bottom = new FormAttachment(0, 397);
	    fd_treeViewer.right = new FormAttachment(0, 590);
	    fd_treeViewer.top = new FormAttachment(0, 33);
	    fd_treeViewer.left = new FormAttachment(0);
	    FileAnalysisEditorInput editorInput = (FileAnalysisEditorInput) getEditorInput();
	    treeViewer.setLayoutData(fd_treeViewer);
	    treeViewer
		    .setContentAndUpdateContent(editorInput.getAnalyzedCode(),
			    editorInput.getAnalysisRun());

	    TabItem metricsViewerTab = new TabItem(tabFolder, SWT.NONE);
	    metricsViewerTab.setText("Metrics");

	    HashId hashId = editorInput.getAnalyzedCode().getHashId();
	    FileStore codeStore = FileStoreFactory.getFactory().getInstance();
	    SourceCode sourceCode = codeStore.readSourceCode(hashId);
	    fileViewer.setStreamAndUpdateContent(sourceCode);
	} catch (IOException e) {
	    logger.log(new Status(Status.ERROR, FileAnalysisEditor.class
		    .getName(), e.getMessage(), e));
	} catch (FileStoreException e) {
	    logger.log(new Status(Status.ERROR, FileAnalysisEditor.class
		    .getName(), e.getMessage(), e));
	}
    }

    @Override
    public void setFocus() {
	fileViewer.setFocus();
    }

}
