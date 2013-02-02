package com.puresol.coding.richclient.application.parts;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * This part shows the analysis projects. In a simple list.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProjectsPart {

    @Inject
    public ProjectsPart(Composite parent) {
	Composite composite = new Composite(parent, SWT.BORDER);
	composite.setLayout(new RowLayout(SWT.HORIZONTAL));
	Label label = new Label(composite, SWT.BORDER);
	label.setText(getClass().getName());
	label.setVisible(true);
    }

    @PostConstruct
    public void postConstruct() {
	// TODO Your code here
    }

    @PreDestroy
    public void preDestroy() {
	// TODO Your code here
    }

}