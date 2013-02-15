package com.puresol.coding.richclient.application.parts;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

/**
 * This part shows the analysis projects. In a simple list.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisRunsPart {
	private final Table table;
	private final Table table_1;

	@Inject
	public AnalysisRunsPart(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FormLayout());

		ToolBar toolBar = new ToolBar(composite, SWT.FLAT | SWT.RIGHT);
		FormData fd_toolBar = new FormData();
		toolBar.setLayoutData(fd_toolBar);

		ToolItem tltmRefresh = new ToolItem(toolBar, SWT.NONE);
		tltmRefresh.setText("Refresh");

		ToolItem tltmAdd = new ToolItem(toolBar, SWT.NONE);
		tltmAdd.setText("Add...");

		ToolItem tltmEdit = new ToolItem(toolBar, SWT.NONE);
		tltmEdit.setText("Edit...");

		ToolItem tltmDelete = new ToolItem(toolBar, SWT.NONE);
		tltmDelete.setText("Delete...");

		table_1 = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		fd_toolBar.left = new FormAttachment(table_1, 0, SWT.LEFT);
		fd_toolBar.right = new FormAttachment(table_1, 0, SWT.RIGHT);
		FormData fd_table_1 = new FormData();
		fd_table_1.left = new FormAttachment(0, 3);
		fd_table_1.bottom = new FormAttachment(0, 300);
		fd_table_1.top = new FormAttachment(0, 30);
		table_1.setLayoutData(fd_table_1);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);

		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		fd_toolBar.bottom = new FormAttachment(table, 24);
		fd_toolBar.top = new FormAttachment(table, 0, SWT.TOP);
		fd_table_1.right = new FormAttachment(table, -3);
		FormData fd_table = new FormData();
		fd_table.bottom = new FormAttachment(0, 278);
		fd_table.right = new FormAttachment(0, 898);
		fd_table.top = new FormAttachment(0, 3);
		fd_table.left = new FormAttachment(0, 450);
		table.setLayoutData(fd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
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