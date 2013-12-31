package com.puresoltechnologies.purifinity.client.common.analysis.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

import com.puresoltechnologies.parsers.api.source.SourceCode;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.client.common.analysis.Activator;
import com.puresoltechnologies.purifinity.client.common.analysis.controls.ScrollableFileViewer;
import com.puresoltechnologies.purifinity.client.common.ui.editors.AbstractPureSolTechnologiesEditor;
import com.puresoltechnologies.purifinity.framework.store.api.FileStore;
import com.puresoltechnologies.purifinity.framework.store.api.FileStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.FileStoreFactory;

public class NotAnalyzedEditor extends AbstractPureSolTechnologiesEditor {

	private ScrollableFileViewer text;

	public NotAnalyzedEditor() {
		super(Activator.getDefault());
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
		super.createPartControl(parent);

		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		text = new ScrollableFileViewer(parent);
		NotAnalyzedEditorInput editorInput = (NotAnalyzedEditorInput) getEditorInput();
		AnalysisFileTree hashIdFile = editorInput.getAnalysisRun()
				.getFileTree().findFile(editorInput.getFile());
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
