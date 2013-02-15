package com.puresol.coding.richclient.application.parts;

import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class AnalysisSummeryPart {
	private final Text text;

	@Inject
	public AnalysisSummeryPart(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FormLayout());

		text = new Text(composite, SWT.BORDER);
		FormData fd_text = new FormData();
		fd_text.bottom = new FormAttachment(100, -10);
		fd_text.right = new FormAttachment(100, -10);
		fd_text.top = new FormAttachment(0, 10);
		fd_text.left = new FormAttachment(0, 10);
		text.setLayoutData(fd_text);

	}
}
