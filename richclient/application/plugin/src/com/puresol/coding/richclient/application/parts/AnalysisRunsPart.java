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
import org.eclipse.wb.swt.ResourceManager;

/**
 * This part shows the analysis projects. In a simple list.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisRunsPart {
	private final Table table;

	@Inject
	public AnalysisRunsPart(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FormLayout());

		ToolBar toolBar = new ToolBar(composite, SWT.FLAT | SWT.RIGHT);
		FormData fd_toolBar = new FormData();
		fd_toolBar.right = new FormAttachment(100, -10);
		fd_toolBar.top = new FormAttachment(0, 10);
		fd_toolBar.left = new FormAttachment(0, 10);
		toolBar.setLayoutData(fd_toolBar);

		ToolItem tltmRefresh = new ToolItem(toolBar, SWT.NONE);
		tltmRefresh.setImage(ResourceManager.getPluginImage(
				"com.puresol.coding.richclient.application.plugin",
				"icons/16x16/arrow_refresh.png"));
		tltmRefresh.setText("Refresh");

		ToolItem tltmAdd = new ToolItem(toolBar, SWT.NONE);
		tltmAdd.setImage(ResourceManager.getPluginImage(
				"com.puresol.coding.richclient.application.plugin",
				"icons/16x16/analysis_run_add.png"));
		tltmAdd.setText("Add...");

		ToolItem tltmEdit = new ToolItem(toolBar, SWT.NONE);
		tltmEdit.setImage(ResourceManager.getPluginImage(
				"com.puresol.coding.richclient.application.plugin",
				"icons/16x16/analysis_run_edit.png"));
		tltmEdit.setText("Edit...");

		ToolItem tltmDelete = new ToolItem(toolBar, SWT.NONE);
		tltmDelete.setImage(ResourceManager.getPluginImage(
				"com.puresol.coding.richclient.application.plugin",
				"icons/16x16/analysis_run_delete.png"));
		tltmDelete.setText("Delete...");

		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		FormData fd_table = new FormData();
		fd_table.right = new FormAttachment(toolBar, 0, SWT.RIGHT);
		fd_table.left = new FormAttachment(toolBar, 0, SWT.LEFT);
		fd_table.top = new FormAttachment(toolBar, 6);
		fd_table.bottom = new FormAttachment(100, -10);
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