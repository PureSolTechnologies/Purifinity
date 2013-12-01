package com.puresoltechnologies.purifinity.client.common.analysis.editors;

import java.io.IOException;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

import com.puresoltechnologies.commons.utils.HashId;
import com.puresoltechnologies.purifinity.client.common.analysis.Activator;
import com.puresoltechnologies.purifinity.client.common.analysis.controls.ParserTreeControl;
import com.puresoltechnologies.purifinity.client.common.analysis.controls.ScrollableFileViewer;
import com.puresoltechnologies.purifinity.client.common.ui.editors.AbstractPureSolTechnologiesEditor;
import com.puresoltechnologies.purifinity.coding.analysis.api.FileStore;
import com.puresoltechnologies.purifinity.coding.analysis.api.FileStoreException;
import com.puresoltechnologies.purifinity.coding.analysis.api.FileStoreFactory;
import com.puresoltechnologies.purifinity.uhura.source.SourceCode;

public class FileAnalysisEditor extends AbstractPureSolTechnologiesEditor {

	private static final ILog logger = Activator.getDefault().getLog();

	private ScrollableFileViewer fileViewer;
	private ParserTreeControl treeViewer;

	public FileAnalysisEditor() {
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
		super.createPartControl(parent);

		try {
			Composite composite = new Composite(parent, SWT.NONE);
			composite.setLayout(new FormLayout());

			TabFolder tabFolder = new TabFolder(composite, SWT.NONE);
			FormData fd_tabFolder = new FormData();
			fd_tabFolder.top = new FormAttachment(0);
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
