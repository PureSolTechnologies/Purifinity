package com.puresol.coding.client.controls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.puresol.coding.client.Activator;
import com.puresol.coding.client.editors.NotAnalyzedEditor;

/**
 * This is a simple text element which show a text file.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ScrollableFileViewer extends Composite {

    private static final ILog logger = Activator.getDefault().getLog();

    private final Text text = new Text(this, SWT.BORDER | SWT.H_SCROLL
	    | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);

    public ScrollableFileViewer(Composite parent) {
	this(parent, null);
    }

    public ScrollableFileViewer(Composite parent, InputStream file) {
	super(parent, SWT.NONE);
	setLayout(new FillLayout());
	text.setEditable(false);
	text.setFont(new Font(getDisplay(), "Courier", 12, SWT.NONE));
	updateContent(file);
    }

    private void updateContent(InputStream file) {
	Color red = new Color(getDisplay(), new RGB(255, 0, 0));
	try {
	    Color black = new Color(getDisplay(), new RGB(0, 0, 0));
	    try {
		try {
		    if (file == null) {
			text.setForeground(red);
			text.setText("FILE DOES NOT EXIST!");
		    } else {
			StringBuilder builder = new StringBuilder();
			text.setForeground(black);
			BufferedReader reader = new BufferedReader(
				new InputStreamReader(file));
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
		    }
		} catch (IOException e) {
		    logger.log(new Status(Status.ERROR, NotAnalyzedEditor.class
			    .getName(), e.getMessage(), e));
		    text.setForeground(red);
		    text.setText("ERROR READING FILE!");
		}
	    } finally {
		black.dispose();
	    }
	} finally {
	    red.dispose();
	}
    }

    /**
     * This method sets a new file and updates the content.
     * 
     * @param file
     */
    public void setStreamAndUpdateContent(InputStream file) {
	updateContent(file);
    }

}
