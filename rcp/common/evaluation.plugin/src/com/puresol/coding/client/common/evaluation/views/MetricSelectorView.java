package com.puresol.coding.client.common.evaluation.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.part.ViewPart;

import com.puresol.coding.client.common.evaluation.contents.AvailableEvaluatorsTableViewer;
import com.puresol.coding.client.common.ui.actions.RefreshAction;
import com.puresol.coding.client.common.ui.actions.Refreshable;
import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.evaluation.api.Evaluators;

public class MetricSelectorView extends ViewPart implements Refreshable,
		ISelectionProvider, SelectionListener {

	private Table table;
	private AvailableEvaluatorsTableViewer viewer;

	private final List<ISelectionChangedListener> selectionChangedListener = new ArrayList<ISelectionChangedListener>();

	private MetricSelection metricSelection;

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
		table.addSelectionListener(this);

		viewer = new AvailableEvaluatorsTableViewer(table);

		initializeToolBar();
		initializeMenu();

		IWorkbenchPartSite site = getSite();
		site.setSelectionProvider(this);

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

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListener.add(listener);
	}

	@Override
	public ISelection getSelection() {
		return metricSelection;
	}

	@Override
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		selectionChangedListener.remove(listener);
	}

	@Override
	public void setSelection(ISelection selection) {
		metricSelection = (MetricSelection) selection;
		for (ISelectionChangedListener listener : selectionChangedListener) {
			listener.selectionChanged(new SelectionChangedEvent(this,
					metricSelection));
		}
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		if (e.getSource() == table) {
			StructuredSelection selection = (StructuredSelection) viewer
					.getSelection();
			EvaluatorFactory evaluatorFactory = (EvaluatorFactory) selection
					.getFirstElement();
			setSelection(new MetricSelection(evaluatorFactory));
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		widgetSelected(e);
	}
}
