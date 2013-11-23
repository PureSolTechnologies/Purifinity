package com.puresol.purifinity.client.common.evaluation.views;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.puresol.purifinity.client.common.evaluation.Activator;
import com.puresol.purifinity.client.common.ui.actions.RefreshAction;
import com.puresol.purifinity.client.common.ui.actions.Refreshable;
import com.puresol.purifinity.client.common.ui.views.AbstractPureSolTechnologiesView;

public class DefectDensityMapView extends AbstractPureSolTechnologiesView
		implements Refreshable {

	public DefectDensityMapView() {
		super(Activator.getDefault());
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));

		initializeToolBar();
		initializeMenu();
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
		toolbarManager.add(new RefreshAction(this));
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

	@Override
	public void refresh() {
	}
}
