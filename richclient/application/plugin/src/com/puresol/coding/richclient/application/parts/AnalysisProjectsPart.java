package com.puresol.coding.richclient.application.parts;

import javax.inject.Inject;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.core.services.log.Logger;
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
@SuppressWarnings("restriction")
public class AnalysisProjectsPart implements SelectionListener {

	private final Logger logger;

	private final EHandlerService handlerService;

	private final ECommandService commandService;

	private final Table projectsTable;
	private final TableViewer projectTableViewer;
	private final ToolItem refreshProjectsItem;
	private final ToolItem addAnalysisItem;
	private final ToolItem editAnalysisItem;
	private final ToolItem deleteAnalysisItem;

	@Inject
	public AnalysisProjectsPart(Composite parent, Logger logger,
			EHandlerService handlerService, ECommandService commandService) {
		this.logger = logger;
		this.handlerService = handlerService;
		this.commandService = commandService;

		Composite composite = new Composite(parent, SWT.BORDER);
		composite.setLayout(new FormLayout());

		ToolBar toolBar = new ToolBar(composite, SWT.FLAT | SWT.RIGHT);
		FormData fd_toolBar = new FormData();
		fd_toolBar.right = new FormAttachment(0, 436);
		fd_toolBar.top = new FormAttachment(0, 10);
		fd_toolBar.left = new FormAttachment(0, 10);
		toolBar.setLayoutData(fd_toolBar);

		refreshProjectsItem = new ToolItem(toolBar, SWT.NONE);
		refreshProjectsItem.setImage(ResourceManager.getPluginImage(
				"com.puresol.coding.richclient.application.plugin",
				"icons/16x16/arrow_refresh.png"));
		refreshProjectsItem.setText("Refresh");
		refreshProjectsItem.addSelectionListener(this);

		addAnalysisItem = new ToolItem(toolBar, SWT.NONE);
		addAnalysisItem.setImage(ResourceManager.getPluginImage(
				"com.puresol.coding.richclient.application.plugin",
				"icons/16x16/analysis_add.png"));
		addAnalysisItem.setText("Add...");
		addAnalysisItem.addSelectionListener(this);

		editAnalysisItem = new ToolItem(toolBar, SWT.NONE);
		editAnalysisItem.setImage(ResourceManager.getPluginImage(
				"com.puresol.coding.richclient.application.plugin",
				"icons/16x16/analysis_edit.png"));
		editAnalysisItem.setText("Edit...");
		editAnalysisItem.addSelectionListener(this);

		deleteAnalysisItem = new ToolItem(toolBar, SWT.NONE);
		deleteAnalysisItem.setImage(ResourceManager.getPluginImage(
				"com.puresol.coding.richclient.application.plugin",
				"icons/16x16/analysis_delete.png"));
		deleteAnalysisItem.setText("Delete...");
		deleteAnalysisItem.addSelectionListener(this);

		projectsTable = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		FormData fd_table = new FormData();
		fd_table.right = new FormAttachment(100, -10);
		fd_table.bottom = new FormAttachment(100, -10);
		fd_table.top = new FormAttachment(toolBar, 6);
		fd_table.left = new FormAttachment(toolBar, 0, SWT.LEFT);
		projectsTable.setLayoutData(fd_table);
		projectsTable.setHeaderVisible(true);
		projectsTable.setLinesVisible(true);

		projectTableViewer = new TableViewer(projectsTable);
		projectTableViewer
				.setContentProvider(new AnalysisListContentProvider());
		projectTableViewer.setLabelProvider(new AnalysisListLabelProvider());
	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		try {
			if (event.getSource() == refreshProjectsItem) {
				refreshAnalysisProjectsList();
			} else if (event.getSource() == addAnalysisItem) {
				addAnalysis();
			}
		} catch (ModuleStoreException e) {
			e.printStackTrace();
			// logger.log(LogService.LOG_ERROR,
			// "Could not read analysis projects.", e);
		}
	}

	private void refreshAnalysisProjectsList() throws ModuleStoreException {
		AnalysisStore store = AnalysisStoreFactory.getFactory().getInstance();
		if (store == null) {
			// logger.log(LogService.LOG_ERROR,
			// "Could not read analysis projects. No store implementation was found.");
			return;
		}
		projectTableViewer.setInput(store.getAllAnalysisInformation());
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent event) {
		widgetSelected(event);
	}

	private void addAnalysis() {
		Command command = commandService
				.getCommand("com.puresol.coding.richclient.application.plugin.command.OpenPerspective");
		ExecutionEvent event = new ExecutionEvent();
		try {
			command.executeWithChecks(event);
		} catch (ExecutionException e) {
			logger.error(e);
		} catch (NotDefinedException e) {
			logger.error(e);
		} catch (NotEnabledException e) {
			logger.error(e);
		} catch (NotHandledException e) {
			logger.error(e);
		}
	}
}