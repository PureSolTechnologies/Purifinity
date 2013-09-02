package com.puresol.purifinity.client.common.evaluation.views;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.part.ViewPart;

import com.puresol.purifinity.client.common.analysis.views.FileAnalysisSelection;
import com.puresol.purifinity.client.common.evaluation.utils.EvaluationsTarget;

/**
 * This abstract class is a parent class for all views which contain a kind of
 * evaluation result. For this class there is no specialization whether the
 * evaluation is a metric or a defect. It is also not specified, yet, whether
 * the presentation of the evaluation is a graph, table or anything else.
 * 
 * @author Rick-Rainer Ludwig
 */
public abstract class AbstractEvaluationView extends ViewPart implements
		ISelectionListener, EvaluationsTarget {

	/**
	 * This field contains the selection service which is used for subscription.
	 */
	private ISelectionService selectionService;
	/**
	 * This field contains the analysis which was selected throught the service.
	 * This analysis is the basis to load evaluations from.
	 */
	private FileAnalysisSelection analysisSelection;

	@Override
	public void dispose() {
		if (selectionService != null) {
			selectionService.removeSelectionListener(this);
		}
		super.dispose();
	}

	@Override
	public void createPartControl(Composite parent) {
		IWorkbenchPartSite site = getSite();
		IWorkbenchWindow workbenchWindow = site.getWorkbenchWindow();
		selectionService = workbenchWindow.getSelectionService();
		selectionService.addSelectionListener(this);
	}

	@Override
	public final void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof FileAnalysisSelection) {
			analysisSelection = (FileAnalysisSelection) selection;
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
	 * @return A {@link FileAnalysisSelection} object is returned containing the
	 *         reference to the selection-
	 */
	protected final FileAnalysisSelection getAnalysisSelection() {
		return analysisSelection;
	}

}
