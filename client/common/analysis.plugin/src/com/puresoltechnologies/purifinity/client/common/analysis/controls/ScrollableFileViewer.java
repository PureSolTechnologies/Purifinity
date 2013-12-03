package com.puresoltechnologies.purifinity.client.common.analysis.controls;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.puresoltechnologies.purifinity.uhura.source.SourceCode;
import com.puresoltechnologies.purifinity.uhura.source.SourceCodeLine;

/**
 * This is a simple text element which show a text file.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ScrollableFileViewer extends Composite {

	private final Text text = new Text(this, SWT.BORDER | SWT.H_SCROLL
			| SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);

	public ScrollableFileViewer(Composite parent) {
		this(parent, null);
	}

	public ScrollableFileViewer(Composite parent, SourceCode sourceCode) {
		super(parent, SWT.NONE);
		setLayout(new FillLayout());
		text.setEditable(false);
		text.setFont(new Font(getDisplay(), "Courier", 12, SWT.NONE));
		updateContent(sourceCode);
	}

	private void updateContent(SourceCode sourceCode) {
		Color red = new Color(getDisplay(), new RGB(255, 0, 0));
		try {
			Color black = new Color(getDisplay(), new RGB(0, 0, 0));
			try {
				StringBuilder builder = new StringBuilder();
				text.setForeground(black);
				if (sourceCode != null) {
					for (SourceCodeLine line : sourceCode.getLines()) {
						builder.append(line.getLine());
					}
				}
				text.setText(builder.toString());
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
	 * @param sourceCode
	 */
	public void setStreamAndUpdateContent(SourceCode sourceCode) {
		updateContent(sourceCode);
	}

}
