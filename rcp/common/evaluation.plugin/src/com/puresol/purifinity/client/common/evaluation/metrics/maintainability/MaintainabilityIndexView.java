package com.puresol.purifinity.client.common.evaluation.metrics.maintainability;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.widgets.Composite;

import com.puresol.purifinity.client.common.evaluation.views.AbstractEvaluationView;
import com.puresol.purifinity.client.common.ui.actions.RefreshAction;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;

public class MaintainabilityIndexView extends AbstractEvaluationView {

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());

		initializeToolBar();
		super.createPartControl(parent);
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
		toolbarManager.add(new RefreshAction(this));
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showEvaluation(HashIdFileTree path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void export() {
		// TODO Auto-generated method stub

	}

	@Override
	public void print(Printer printer, String printJobName) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void updateEvaluation() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
