package com.puresol.coding.client.application.editors;

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

import com.puresol.coding.analysis.api.CodeStore;
import com.puresol.coding.analysis.api.CodeStoreException;
import com.puresol.coding.analysis.api.CodeStoreFactory;
import com.puresol.coding.client.application.Activator;
import com.puresol.coding.client.application.controls.FileMetricsControl;
import com.puresol.coding.client.application.controls.ParserTreeControl;
import com.puresol.coding.client.application.controls.ScrollableFileViewer;
import com.puresol.uhura.source.SourceCode;
import com.puresol.utils.HashId;

public class FileAnalysisEditor extends EditorPart {

	private static final ILog logger = Activator.getDefault().getLog();

	public static final String ID = "com.puresol.coding.client.FileAnalysisEditor";

	private ScrollableFileViewer fileViewer;
	private ParserTreeControl treeViewer;
	private FileMetricsControl metricsControl;

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

			Composite buttonArea = new Composite(parent, SWT.NONE);
			buttonArea.setLayoutData(BorderLayout.NORTH);
			buttonArea.setLayout(new FillLayout(SWT.HORIZONTAL));

			Button refreshButton = new Button(buttonArea, SWT.NONE);
			refreshButton.setText("Refresh");

			TabFolder tabFolder = new TabFolder(parent, SWT.NONE);
			tabFolder.setLayoutData(BorderLayout.CENTER);

			TabItem fileViewerTab = new TabItem(tabFolder, SWT.NONE);
			fileViewerTab.setText("Original File");

			fileViewer = new ScrollableFileViewer(tabFolder);
			fileViewerTab.setControl(fileViewer);

			TabItem treeViewerTab = new TabItem(tabFolder, SWT.NONE);
			treeViewerTab.setText("Parser Tree");

			treeViewer = new ParserTreeControl(tabFolder);
			treeViewerTab.setControl(treeViewer);

			TabItem metricsViewerTab = new TabItem(tabFolder, SWT.NONE);
			metricsViewerTab.setText("Metrics");

			FileAnalysisEditorInput editorInput = (FileAnalysisEditorInput) getEditorInput();

			metricsControl = new FileMetricsControl(tabFolder, SWT.NONE,
					editorInput.getAnalysisRun(), editorInput.getAnalyzedCode());
			metricsViewerTab.setControl(metricsControl);

			HashId hashId = editorInput.getAnalyzedCode().getHashId();
			CodeStore codeStore = CodeStoreFactory.getFactory().getInstance();
			SourceCode sourceCode = codeStore.loadContent(hashId);
			fileViewer.setStreamAndUpdateContent(sourceCode);
			treeViewer
					.setContentAndUpdateContent(editorInput.getAnalyzedCode(),
							editorInput.getAnalysisRun());
		} catch (IOException e) {
			logger.log(new Status(Status.ERROR, FileAnalysisEditor.class
					.getName(), e.getMessage(), e));
		} catch (CodeStoreException e) {
			logger.log(new Status(Status.ERROR, FileAnalysisEditor.class
					.getName(), e.getMessage(), e));
		}
	}

	@Override
	public void setFocus() {
		fileViewer.setFocus();
	}

}
