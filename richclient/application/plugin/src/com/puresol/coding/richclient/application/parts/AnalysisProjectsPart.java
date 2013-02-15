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
 * This part shows the contents of an analysis project. The content is spit into
 * directories and source files.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AnalysisProjectsPart {
	private final Table table;

	@Inject
	public AnalysisProjectsPart(Composite parent) {
		Composite composite = new Composite(parent, SWT.BORDER);
		composite.setLayout(new FormLayout());

		ToolBar toolBar = new ToolBar(composite, SWT.FLAT | SWT.RIGHT);
		FormData fd_toolBar = new FormData();
		fd_toolBar.right = new FormAttachment(0, 436);
		fd_toolBar.top = new FormAttachment(0, 10);
		fd_toolBar.left = new FormAttachment(0, 10);
		toolBar.setLayoutData(fd_toolBar);

		ToolItem tltmNewItem = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem.setImage(ResourceManager.getPluginImage(
				"com.puresol.coding.richclient.application.plugin",
				"icons/16x16/arrow_refresh.png"));
		tltmNewItem.setText("Refresh");

		ToolItem tltmNewItem_1 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_1.setImage(ResourceManager.getPluginImage(
				"com.puresol.coding.richclient.application.plugin",
				"icons/16x16/analysis_add.png"));
		tltmNewItem_1.setText("Add...");

		ToolItem tltmNewItem_2 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_2.setImage(ResourceManager.getPluginImage(
				"com.puresol.coding.richclient.application.plugin",
				"icons/16x16/analysis_edit.png"));
		tltmNewItem_2.setText("Edit...");

		ToolItem tltmDelete = new ToolItem(toolBar, SWT.NONE);
		tltmDelete.setImage(ResourceManager.getPluginImage(
				"com.puresol.coding.richclient.application.plugin",
				"icons/16x16/analysis_delete.png"));
		tltmDelete.setText("Delete...");

		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		FormData fd_table = new FormData();
		fd_table.right = new FormAttachment(100, -10);
		fd_table.bottom = new FormAttachment(100, -10);
		fd_table.top = new FormAttachment(toolBar, 6);
		fd_table.left = new FormAttachment(0, 10);
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