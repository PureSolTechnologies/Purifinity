package com.puresoltechnologies.purifinity.client.common.analysis.views;

import org.eclipse.jface.viewers.ISelection;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;

/**
 * This class represents a global file analysis selection. This selection is
 * processed by every GUI element which is related to this global selection.
 * 
 * The file analysis selection may be an actual file analysis, a directory
 * analysis or event a project selection.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisSelection implements ISelection {

	private final AnalysisProject analysisProject;
	private final AnalysisRun analysisRun;
	private final AnalysisFileTree fileTreeNode;

	public AnalysisSelection(AnalysisProject analysisProject, AnalysisRun analysisRun,
			AnalysisFileTree fileTreeNode) {
		super();
		this.analysisProject = analysisProject;
		this.analysisRun = analysisRun;
		this.fileTreeNode = fileTreeNode;
	}

	public AnalysisProject getAnalysisProject() {
		return analysisProject;
	}

	public AnalysisRun getAnalysisRun() {
		return analysisRun;
	}

	public AnalysisFileTree getFileTreeNode() {
		return fileTreeNode;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

}
