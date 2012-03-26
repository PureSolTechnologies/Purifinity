package com.puresol.coding.client.editors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import com.puresol.coding.client.Activator;

public class NotAnalyzedEditor extends EditorPart {

    private static final ILog logger = Activator.getDefault().getLog();

    private Text text;

    public NotAnalyzedEditor() {
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

    private void updateContent() {
	showBusy(true);
	NotAnalyzedEditorInput editorInput = (NotAnalyzedEditorInput) getEditorInput();
	File file = editorInput.getFile();
	if (!file.exists()) {
	    text.setForeground(new Color(text.getDisplay(), new RGB(255, 0, 0)));
	    text.setText("FILE DOES NOT EXIST!");
	} else {
	    try {
		StringBuilder builder = new StringBuilder();
		text.setForeground(new Color(text.getDisplay(),
			new RGB(0, 0, 0)));
		BufferedReader reader = new BufferedReader(new FileReader(file));
		try {
		    String line;
		    while ((line = reader.readLine()) != null) {
			builder.append(line);
			builder.append("\n");
		    }
		} finally {
		    reader.close();
		}
		text.setText(builder.toString());
	    } catch (IOException e) {
		logger.log(new Status(Status.ERROR, NotAnalyzedEditor.class
			.getName(), e.getMessage(), e));
		text.setForeground(new Color(text.getDisplay(), new RGB(255, 0,
			0)));
		text.setText("ERROR READING FILE!");
	    }
	}
	showBusy(false);
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

	text = new Text(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL
		| SWT.CANCEL | SWT.MULTI);
	updateContent();
    }

    @Override
    public void setFocus() {
	text.setFocus();
    }

}
