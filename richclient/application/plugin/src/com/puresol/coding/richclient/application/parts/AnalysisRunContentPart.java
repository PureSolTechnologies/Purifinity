package com.puresol.coding.richclient.application.parts;

import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;

public class AnalysisRunContentPart {

	@Inject
	public AnalysisRunContentPart(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FormLayout());

		Tree tree = new Tree(composite, SWT.BORDER);
		FormData fd_tree = new FormData();
		fd_tree.bottom = new FormAttachment(100, -10);
		fd_tree.right = new FormAttachment(100, -10);
		fd_tree.top = new FormAttachment(0, 10);
		fd_tree.left = new FormAttachment(0, 10);
		tree.setLayoutData(fd_tree);
	}
}
