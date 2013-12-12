package com.puresoltechnologies.purifinity.client.common.analysis.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

import com.puresoltechnologies.parsers.api.source.SourceCode;
<<<<<<< HEAD
import com.puresoltechnologies.purifinity.analysis.api.FileStore;
import com.puresoltechnologies.purifinity.analysis.api.FileStoreException;
import com.puresoltechnologies.purifinity.analysis.api.FileStoreFactory;
import com.puresoltechnologies.purifinity.analysis.api.HashIdFileTree;
=======
>>>>>>> 22bb20bf218d5d810e936dd668128ce7c35efbf9
import com.puresoltechnologies.purifinity.client.common.analysis.Activator;
import com.puresoltechnologies.purifinity.client.common.analysis.controls.ScrollableFileViewer;
import com.puresoltechnologies.purifinity.client.common.ui.editors.AbstractPureSolTechnologiesEditor;

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
