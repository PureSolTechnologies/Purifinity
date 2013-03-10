package com.puresol.coding.client.application.views;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.swt.widgets.List;
import org.eclipse.jface.action.MenuManager;

public class MetricSelectorView extends ViewPart {

	public static final String ID = "com.puresol.coding.client.application.views.MetricSelectorView"; //$NON-NLS-1$
	private Table table;

	public MetricSelectorView() {
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FormLayout());

		ToolBar toolBar = new ToolBar(container, SWT.FLAT | SWT.RIGHT);
		FormData fd_toolBar = new FormData();
		fd_toolBar.right = new FormAttachment(0, 584);
		fd_toolBar.top = new FormAttachment(0, 10);
		fd_toolBar.left = new FormAttachment(0, 10);
		toolBar.setLayoutData(fd_toolBar);

		ToolItem tltmRefresh = new ToolItem(toolBar, SWT.NONE);
		tltmRefresh.setImage(ResourceManager.getPluginImage(
				"com.puresol.coding.client.application.plugin",
				"icons/16x16/arrow_refresh.png"));
		tltmRefresh.setText("Refresh");

		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		FormData fd_table = new FormData();
		fd_table.right = new FormAttachment(toolBar, 0, SWT.RIGHT);
		fd_table.bottom = new FormAttachment(toolBar, 421, SWT.BOTTOM);
		fd_table.top = new FormAttachment(toolBar, 6);
		fd_table.left = new FormAttachment(toolBar, 0, SWT.LEFT);
		table.setLayoutData(fd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		createActions();
		initializeToolBar();
		initializeMenu();
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
}
