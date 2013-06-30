package com.puresol.purifinity.client.common.ui.controls;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class FileFilterGroup extends Composite {

    private final Group group;
    private final Text excludes;
    private final Text includes;

    public FileFilterGroup(Composite parent, int style) {
	this(parent, style, true);
    }

    public FileFilterGroup(Composite parent, int style, boolean isFileFilter) {
	super(parent, style);
	setLayout(new FillLayout());
	group = new Group(this, SWT.NONE);
	if (isFileFilter) {
	    group.setText("File Filter");
	} else {
	    group.setText("Directory Filter");
	}
	group.setLayout(new FormLayout());

	Label includesLabel = new Label(group, SWT.NONE);
	FormData fd_includesLabel = new FormData();
	fd_includesLabel.left = new FormAttachment(0, 10);
	fd_includesLabel.top = new FormAttachment(0, 10);
	includesLabel.setLayoutData(fd_includesLabel);
	includesLabel.setText("Includes:");

	includes = new Text(group, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
	FormData fd_includes = new FormData();
	fd_includes.height = 120;
	fd_includes.left = new FormAttachment(0, 10);
	fd_includes.top = new FormAttachment(includesLabel, 6);
	fd_includes.right = new FormAttachment(100, -10);
	includes.setLayoutData(fd_includes);

	Label excludesLabel = new Label(group, SWT.NONE);
	fd_includes.bottom = new FormAttachment(0, 120);
	FormData fd_excludesLabel = new FormData();
	fd_excludesLabel.left = new FormAttachment(0, 10);
	fd_excludesLabel.top = new FormAttachment(includes, 108);
	fd_excludesLabel.top = new FormAttachment(includes, 6);
	excludesLabel.setLayoutData(fd_excludesLabel);
	excludesLabel.setText("Excludes:");

	excludes = new Text(group, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
	FormData fd_excludes = new FormData();
	fd_excludes.height = 120;
	fd_excludes.left = new FormAttachment(0, 10);
	fd_excludes.top = new FormAttachment(excludesLabel, 6);
	fd_excludes.right = new FormAttachment(100, -10);
	fd_excludes.bottom = new FormAttachment(100, -10);
	excludes.setLayoutData(fd_excludes);
    }

    public void setIncludes(String text) {
	includes.setText(text);
    }

    public void setExcludes(String text) {
	excludes.setText(text);
    }

    public String getIncludes() {
	return includes.getText();
    }

    public String getExcludes() {
	return excludes.getText();
    }

}
