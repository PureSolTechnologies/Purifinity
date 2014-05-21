package com.puresoltechnologies.purifinity.client.common.evaluation.views;

import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.client.common.analysis.views.AnalysisProjectSelection;
import com.puresoltechnologies.purifinity.client.common.analysis.views.AnalysisRunSelection;
import com.puresoltechnologies.purifinity.client.common.analysis.views.AnalysisSelection;
import com.puresoltechnologies.purifinity.client.common.evaluation.Activator;
import com.puresoltechnologies.purifinity.client.common.evaluation.utils.EvaluationsTarget;
import com.puresoltechnologies.purifinity.client.common.ui.views.AbstractPureSolTechnologiesView;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluationStoreException;

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
	private AnalysisSelection oldAnalysisSelection;

	public AbstractEvaluationView() {
		super(Activator.getDefault());
	}

	@Override
	public void dispose() {
		if (selectionService != null) {
			selectionService.removeSelectionListener(this);
			selectionService = null;
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
		if (selection instanceof AnalysisProjectSelection) {
			AnalysisProjectSelection analysisProjectSelection = (AnalysisProjectSelection) selection;
			if (hasSelectionChanged(analysisProjectSelection)) {
				setAnalysisSelection(new AnalysisSelection(
						analysisProjectSelection.getAnalysisProject(), null,
						null));
			}
		} else if (selection instanceof AnalysisRunSelection) {
			AnalysisRunSelection analysisRunSelection = (AnalysisRunSelection) selection;
			if (hasSelectionChanged(analysisRunSelection)) {
				setAnalysisSelection(new AnalysisSelection(
						analysisRunSelection.getAnalysisProject(),
						analysisRunSelection.getAnalysisRun(), null));
			}
		} else if (selection instanceof AnalysisSelection) {
			AnalysisSelection analysisSelection = (AnalysisSelection) selection;
			if (hasSelectionChanged(analysisSelection)) {
				setAnalysisSelection(analysisSelection);
			}
		}
	}

	private boolean hasSelectionChanged(
			AnalysisProjectSelection analysisProjectSelection) {
		return hasChanged(analysisProjectSelection.getAnalysisProject());
	}

	private boolean hasChanged(AnalysisProject newAnalysisProject) {
		AnalysisProject analysisProject = analysisSelection == null ? null
				: analysisSelection.getAnalysisProject();
		if ((analysisProject == null) && (newAnalysisProject == null)) {
			return false;
		}
		if (((analysisProject == null) && (newAnalysisProject != null)) || //
				((analysisProject != null) && (newAnalysisProject == null))) {
			return true;
		}
		if (!analysisProject.getInformation().getUUID()
				.equals(newAnalysisProject.getInformation().getUUID())) {
			return true;
		}
		return false;
	}

	private boolean hasSelectionChanged(
			AnalysisRunSelection analysisRunSelection) {
		if (hasChanged(analysisRunSelection.getAnalysisProject())) {
			return true;
		}
		return hasChanged(analysisRunSelection.getAnalysisRun());
	}

	private boolean hasChanged(AnalysisRun newAnalysisRun) {
		AnalysisRun analysisRun = analysisSelection == null ? null
				: analysisSelection.getAnalysisRun();
		if ((analysisRun == null) && (newAnalysisRun == null)) {
			return false;
		}
		if (((analysisRun == null) && (newAnalysisRun != null)) || //
				((analysisRun != null) && (newAnalysisRun == null))) {
			return true;
		}
		if (!analysisRun.getInformation().getUUID()
				.equals(newAnalysisRun.getInformation().getUUID())) {
			return true;
		}
		return false;
	}

	private boolean hasSelectionChanged(AnalysisSelection analysisSelection) {
		if (hasChanged(analysisSelection.getAnalysisProject())) {
			return true;
		}
		if (hasChanged(analysisSelection.getAnalysisRun())) {
			return true;
		}
		return hasChanged(analysisSelection.getFileTreeNode());
	}

	private boolean hasChanged(AnalysisFileTree newFileTreeNode) {
		AnalysisFileTree analysisFileTree = analysisSelection == null ? null
				: analysisSelection.getFileTreeNode();
		if ((analysisFileTree == null) && (newFileTreeNode == null)) {
			return false;
		}
		if (((analysisFileTree == null) && (newFileTreeNode != null)) || //
				((analysisFileTree != null) && (newFileTreeNode == null))) {
			return true;
		}
		if (!analysisFileTree.getHashId().equals(newFileTreeNode.getHashId())) {
			return true;
		}
		return false;
	}

	protected void setAnalysisSelection(AnalysisSelection selection) {
		try {
			analysisSelection = selection;
			if (hasFullAnalysisSelection() && hasFullViewSettings()) {
				if (hasChangedAnalysisSelection() || hasChangedViewSettings()) {
					oldAnalysisSelection = analysisSelection;
					updateView();
				}
			} else {
				oldAnalysisSelection = analysisSelection;
				clear();
			}
		} catch (EvaluationStoreException e) {
			Activator activator = Activator.getDefault();
			activator.getLog().log(
					new Status(Status.ERROR, activator.getBundle()
							.getSymbolicName(),
							"Could not handle new selection."));
		}
	}

	private boolean hasChangedAnalysisSelection() {
		return (oldAnalysisSelection == null)
				|| (!oldAnalysisSelection.equals(analysisSelection));
	}

	private boolean hasFullAnalysisSelection() {
		return (analysisSelection != null)
				&& (analysisSelection.getAnalysisProject() != null)
				&& (analysisSelection.getAnalysisRun() != null)
				&& (analysisSelection.getFileTreeNode() != null);
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
	 * This method returns the current selection.
	 * 
	 * @return A {@link AnalysisSelection} object is returned containing the
	 *         reference to the selection-
	 */
	protected final AnalysisSelection getAnalysisSelection() {
		return analysisSelection;
	}

	/**
	 * This method is called every time the evaluation needs an update due to a
	 * new analysis selection and changed settings.
	 * 
	 * {@link AbstractEvaluationView#getAnalysisSelection()} is used to retrieve
	 * the current selection.
	 * 
	 * @throws EvaluationStoreException
	 *             is thrown in cases the evaluation store has issues.
	 */
	protected abstract void updateView() throws EvaluationStoreException;

	/**
	 * This method is called in cases of deleted analysis selections to clear
	 * the view's content.
	 */
	protected abstract void clear();

	/**
	 * Checks whether all settings were set already or not.
	 * 
	 * @return <code>true</code> is returned if all necessary settings were
	 *         applied to fill the view. <code>false</code> is returned
	 *         otherwise.
	 */
	protected abstract boolean hasFullViewSettings();

	/**
	 * This method checks whether the view settings have changed or not. It was
	 * checked before with {@link #hasFullViewSettings()} whether the settings
	 * are complete.
	 * 
	 * @return <code>true</code> is returned in cases the view settings changed.
	 *         <code>false</code> is returned otherwise.
	 */
	protected abstract boolean hasChangedViewSettings();

}
