package com.puresoltechnologies.purifinity.client.common.analysis;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;

import com.puresoltechnologies.purifinity.client.common.analysis.views.AnalysisProjectSelection;
import com.puresoltechnologies.purifinity.client.common.analysis.views.AnalysisRunSelection;
import com.puresoltechnologies.purifinity.client.common.analysis.views.AnalysisSelection;

/**
 * This class is used to capture the selection of analyses globally to provide a
 * facility to get the latest selection if needed (as it is used for newly
 * opened views).
 * 
 * This class is implemented as singleton and initialized in a startup class.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AnalysisSelections implements ISelectionListener {

	private static final AnalysisSelections INSTANCE = new AnalysisSelections();

	/**
	 * This method returns the singleton instance.
	 * 
	 * @return An {@link AnalysisSelections} object is returned.
	 */
	public static AnalysisSelections getInstance() {
		return INSTANCE;
	}

	private AnalysisProjectSelection analysisProjectSelection = null;
	private AnalysisRunSelection analysisRunSelection = null;
	private AnalysisSelection analysisSelection = null;

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (AnalysisProjectSelection.class.isAssignableFrom(selection
				.getClass())) {
			analysisProjectSelection = (AnalysisProjectSelection) selection;
			analysisRunSelection = null;
			analysisSelection = null;
		} else if (AnalysisRunSelection.class.isAssignableFrom(selection
				.getClass())) {
			analysisRunSelection = (AnalysisRunSelection) selection;
			analysisSelection = null;
		} else if (AnalysisSelection.class.isAssignableFrom(selection
				.getClass())) {
			analysisSelection = (AnalysisSelection) selection;
		}
	}

	/**
	 * Returns the last {@link AnalysisProjectSelection}.
	 * 
	 * @return An {@link AnalysisProjectSelection} object is returned.
	 */
	public AnalysisProjectSelection getAnalysisProjectSelection() {
		return analysisProjectSelection;
	}

	/**
	 * Returns the last {@link AnalysisRunSelection}.
	 * 
	 * @return An {@link AnalysisRunSelection} object is returned.
	 */
	public AnalysisRunSelection getAnalysisRunSelection() {
		return analysisRunSelection;
	}

	/**
	 * Returns the last {@link AnalysisSelection}.
	 * 
	 * @return An {@link AnalysisSelection} object is returned.
	 */
	public AnalysisSelection getAnalysisSelection() {
		return analysisSelection;
	}

}
