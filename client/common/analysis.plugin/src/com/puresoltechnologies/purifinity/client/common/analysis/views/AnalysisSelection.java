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

	public AnalysisSelection(AnalysisProject analysisProject,
			AnalysisRun analysisRun, AnalysisFileTree fileTreeNode) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((analysisProject == null) ? 0 : analysisProject.hashCode());
		result = prime * result
				+ ((analysisRun == null) ? 0 : analysisRun.hashCode());
		result = prime * result
				+ ((fileTreeNode == null) ? 0 : fileTreeNode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnalysisSelection other = (AnalysisSelection) obj;
		if (analysisProject == null) {
			if (other.analysisProject != null)
				return false;
		} else if (!analysisProject.equals(other.analysisProject))
			return false;
		if (analysisRun == null) {
			if (other.analysisRun != null)
				return false;
		} else if (!analysisRun.equals(other.analysisRun))
			return false;
		if (fileTreeNode == null) {
			if (other.fileTreeNode != null)
				return false;
		} else if (!fileTreeNode.equals(other.fileTreeNode))
			return false;
		return true;
	}

}
