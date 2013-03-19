package com.puresol.coding.client.common.evaluation.views;

import java.util.List;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.part.ViewPart;

import com.puresol.coding.client.common.evaluation.contents.AvailableEvaluatorsTableViewer;
import com.puresol.coding.client.common.ui.actions.RefreshAction;
import com.puresol.coding.client.common.ui.actions.Refreshable;
import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.evaluation.api.Evaluators;

public class MetricSelectorView extends ViewPart implements Refreshable {

    private Table table;
    private AvailableEvaluatorsTableViewer viewer;

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
	container.setLayout(new FillLayout(SWT.HORIZONTAL));

	table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
	table.setHeaderVisible(true);
	table.setLinesVisible(true);

	viewer = new AvailableEvaluatorsTableViewer(table);

	initializeToolBar();
	initializeMenu();

	refresh();
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
	List<EvaluatorFactory> metrics = Evaluators.createInstance()
		.getAllMetrics();
	viewer.setInput(metrics);
	if (metrics.size() > 0) {
	    table.select(0);
	}
    }
}
