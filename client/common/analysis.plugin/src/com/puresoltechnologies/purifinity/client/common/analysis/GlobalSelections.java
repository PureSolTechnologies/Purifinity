package com.puresoltechnologies.purifinity.client.common.analysis;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;

import com.puresoltechnologies.purifinity.client.common.analysis.views.AnalysisProjectSelection;
import com.puresoltechnologies.purifinity.client.common.analysis.views.AnalysisRunSelection;
import com.puresoltechnologies.purifinity.client.common.analysis.views.AnalysisSelection;

public class GlobalSelections implements ISelectionListener {

	private static final GlobalSelections INSTANCE = new GlobalSelections();

	public static GlobalSelections getInstance() {
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

	public AnalysisProjectSelection getAnalysisProjectSelection() {
		return analysisProjectSelection;
	}

	public AnalysisRunSelection getAnalysisRunSelection() {
		return analysisRunSelection;
	}

	public AnalysisSelection getAnalysisSelection() {
		return analysisSelection;
	}

}
