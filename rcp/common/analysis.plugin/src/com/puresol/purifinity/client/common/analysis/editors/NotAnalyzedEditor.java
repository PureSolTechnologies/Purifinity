package com.puresol.purifinity.client.common.analysis.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import com.puresol.purifinity.client.common.analysis.controls.ScrollableFileViewer;
import com.puresol.purifinity.coding.analysis.api.FileStore;
import com.puresol.purifinity.coding.analysis.api.FileStoreException;
import com.puresol.purifinity.coding.analysis.api.FileStoreFactory;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresol.purifinity.uhura.source.SourceCode;

public class NotAnalyzedEditor extends EditorPart {

	private ScrollableFileViewer text;

	public NotAnalyzedEditor() {
		super();
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// intentionally left blank
	}

	@Override
	public void doSaveAs() {
		// intentionally left blank
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
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		text = new ScrollableFileViewer(parent);
		NotAnalyzedEditorInput editorInput = (NotAnalyzedEditorInput) getEditorInput();
		HashIdFileTree hashIdFile = editorInput.getAnalysisRun().getFileTree()
				.findFile(editorInput.getFile());
		FileStore fileStore = FileStoreFactory.getFactory().getInstance();
		try {
			SourceCode sourceCode = fileStore.readSourceCode(hashIdFile
					.getHashId());
			text.setStreamAndUpdateContent(sourceCode);
		} catch (FileStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setFocus() {
		text.setFocus();
	}

}