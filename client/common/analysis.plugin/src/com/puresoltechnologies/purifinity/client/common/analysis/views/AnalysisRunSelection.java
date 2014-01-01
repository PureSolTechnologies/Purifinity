package com.puresoltechnologies.purifinity.client.common.analysis.views;

import org.eclipse.jface.viewers.ISelection;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;

/**
 * This class represents a single analysis run selection.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisRunSelection implements ISelection {

	private final AnalysisProject analysisProject;
	private final AnalysisRun analysisRun;

	public AnalysisRunSelection(AnalysisProject analysisProject,
			AnalysisRun analysisRun) {
		super();
		this.analysisProject = analysisProject;
		this.analysisRun = analysisRun;
	}

	public AnalysisProject getAnalysisProject() {
		return analysisProject;
	}

	public AnalysisRun getAnalysisRun() {
		return analysisRun;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

}
