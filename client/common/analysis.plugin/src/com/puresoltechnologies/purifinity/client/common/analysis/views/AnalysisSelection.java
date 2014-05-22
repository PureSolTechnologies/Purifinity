package com.puresoltechnologies.purifinity.client.common.analysis.views;

import java.util.UUID;

import org.eclipse.jface.viewers.ISelection;

import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;

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
	private final UUID projectUUID;
	private final UUID runUUID;
	private final HashId parentTreeNode;

	public AnalysisSelection(AnalysisProject analysisProject,
			AnalysisRun analysisRun, AnalysisFileTree fileTreeNode) {
		super();
		this.analysisProject = analysisProject;
		this.analysisRun = analysisRun;
		this.fileTreeNode = fileTreeNode;
		projectUUID = analysisProject == null ? null : analysisProject
				.getInformation().getUUID();
		runUUID = analysisRun == null ? null : analysisRun.getInformation()
				.getRunUUID();
		parentTreeNode = fileTreeNode == null ? null : fileTreeNode.getHashId();
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
				+ ((parentTreeNode == null) ? 0 : parentTreeNode.hashCode());
		result = prime * result
				+ ((projectUUID == null) ? 0 : projectUUID.hashCode());
		result = prime * result + ((runUUID == null) ? 0 : runUUID.hashCode());
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
		if (parentTreeNode == null) {
			if (other.parentTreeNode != null)
				return false;
		} else if (!parentTreeNode.equals(other.parentTreeNode))
			return false;
		if (projectUUID == null) {
			if (other.projectUUID != null)
				return false;
		} else if (!projectUUID.equals(other.projectUUID))
			return false;
		if (runUUID == null) {
			if (other.runUUID != null)
				return false;
		} else if (!runUUID.equals(other.runUUID))
			return false;
		return true;
	}
}
