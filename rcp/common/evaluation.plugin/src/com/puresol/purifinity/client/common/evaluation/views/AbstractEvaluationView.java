package com.puresol.purifinity.client.common.evaluation.views;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;

import com.puresol.purifinity.client.common.analysis.views.AnalysisSelection;
import com.puresol.purifinity.client.common.evaluation.Activator;
import com.puresol.purifinity.client.common.evaluation.utils.EvaluationsTarget;
import com.puresol.purifinity.client.common.ui.views.AbstractPureSolTechnologiesView;

/**
 * This abstract class is a parent class for all views which contain a kind of
 * evaluation result. For this class there is no specialization whether the
 * evaluation is a metric or a defect. It is also not specified, yet, whether
 * the presentation of the evaluation is a graph, table or anything else.
 * 
 * @author Rick-Rainer Ludwig
 */
public abstract class AbstractEvaluationView extends
		AbstractPureSolTechnologiesView implements ISelectionListener,
		EvaluationsTarget {

	/**
	 * This field contains the selection service which is used for subscription.
	 */
	private ISelectionService selectionService;
	/**
	 * This field contains the analysis which was selected throught the service.
	 * This analysis is the basis to load evaluations from.
	 */
	private AnalysisSelection analysisSelection;

	public AbstractEvaluationView() {
		super(Activator.getDefault());
	}

	@Override
	public void dispose() {
		if (selectionService != null) {
			selectionService.removeSelectionListener(this);
		}
		super.dispose();
	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		IWorkbenchPartSite site = getSite();
		IWorkbenchWindow workbenchWindow = site.getWorkbenchWindow();
		selectionService = workbenchWindow.getSelectionService();
		selectionService.addSelectionListener(this);
	}

	@Override
	public final void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (part == this) {
			return;
		}
		if (selection instanceof AnalysisSelection) {
			analysisSelection = (AnalysisSelection) selection;
			updateEvaluation();
		}
	}

	/**
	 * This method is a helper to get the current tool bar manager for the view.
	 * 
	 * @return An {@link IToolBarManager} object is returned.
	 */
	protected final IToolBarManager getToolBarManager() {
		return getViewSite().getActionBars().getToolBarManager();
	}

	/**
	 * This method is called every time the evaluation needs an update due to a
	 * new selection.
	 * 
	 * {@link AbstractEvaluationView#getAnalysisSelection()} is used to retrieve
	 * the current selection.
	 */
	protected abstract void updateEvaluation();

	/**
	 * This method returns the current selection.
	 * 
	 * @return A {@link AnalysisSelection} object is returned containing the
	 *         reference to the selection-
	 */
	protected final AnalysisSelection getAnalysisSelection() {
		return analysisSelection;
	}

}
