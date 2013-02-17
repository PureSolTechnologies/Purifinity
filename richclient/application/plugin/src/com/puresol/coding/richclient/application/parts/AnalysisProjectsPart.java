package com.puresol.coding.richclient.application.parts;

import javax.inject.Inject;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;
import org.osgi.service.log.LogService;

import com.puresol.coding.analysis.api.AnalysisStore;
import com.puresol.coding.analysis.api.AnalysisStoreFactory;
import com.puresol.coding.analysis.api.ModuleStoreException;
import com.puresol.coding.richclient.application.content.AnalysisListContentProvider;
import com.puresol.coding.richclient.application.content.AnalysisListLabelProvider;

/**
 * This part shows the contents of an analysis project. The content is spit into
 * directories and source files.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AnalysisProjectsPart implements SelectionListener {

	@Inject
	private LogService logger;
	private final Table table;
	private final TableViewer tableViewer;

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
		tltmNewItem.addSelectionListener(this);

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
		fd_table.left = new FormAttachment(toolBar, 0, SWT.LEFT);
		table.setLayoutData(fd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		tableViewer = new TableViewer(table);
		tableViewer.setContentProvider(new AnalysisListContentProvider());
		tableViewer.setLabelProvider(new AnalysisListLabelProvider());
	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		try {
			AnalysisStore store = AnalysisStoreFactory.getFactory()
					.getInstance();
			if (store == null) {
				// logger.log(LogService.LOG_ERROR,
				// "Could not read analysis projects. No store implementation was found.");
				return;
			}
			tableViewer.setInput(store.getAllAnalysisInformation());
		} catch (ModuleStoreException e) {
			e.printStackTrace();
			// logger.log(LogService.LOG_ERROR,
			// "Could not read analysis projects.", e);
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent event) {
		widgetSelected(event);
	}
}