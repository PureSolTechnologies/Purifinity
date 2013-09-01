package com.puresol.purifinity.client.common.analysis.views;

import org.eclipse.jface.viewers.ISelection;

import com.puresol.purifinity.coding.analysis.api.AnalysisProject;
import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;

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
public class FileAnalysisSelection implements ISelection {

	private final AnalysisProject analysis;
	private final AnalysisRun analysisRun;
	private final HashIdFileTree fileTreeNode;

	public FileAnalysisSelection(AnalysisProject analysis,
			AnalysisRun analysisRun, HashIdFileTree fileTreeNode) {
		super();
		this.analysis = analysis;
		this.analysisRun = analysisRun;
		this.fileTreeNode = fileTreeNode;
	}

	public AnalysisProject getAnalysis() {
		return analysis;
	}

	public AnalysisRun getAnalysisRun() {
		return analysisRun;
	}

	public HashIdFileTree getFileTreeNode() {
		return fileTreeNode;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

}
