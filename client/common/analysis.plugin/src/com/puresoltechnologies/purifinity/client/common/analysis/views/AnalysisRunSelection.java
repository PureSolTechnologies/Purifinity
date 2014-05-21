package com.puresoltechnologies.purifinity.client.common.analysis.views;

import java.util.UUID;

import org.eclipse.jface.viewers.ISelection;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;

/**
 * This class represents a single analysis run selection.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisRunSelection implements ISelection {

	private final AnalysisProject analysisProject;
	private final AnalysisRun analysisRun;
	private final UUID analysisProjectUUID;
	private final UUID analysisRunUUID;

	public AnalysisRunSelection(AnalysisProject analysisProject,
			AnalysisRun analysisRun) {
		super();
		this.analysisProject = analysisProject;
		this.analysisRun = analysisRun;
		analysisProjectUUID = analysisProject == null ? null : analysisProject
				.getInformation().getUUID();
		analysisRunUUID = analysisRun == null ? null : analysisRun
				.getInformation().getUUID();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((analysisProjectUUID == null) ? 0 : analysisProjectUUID
						.hashCode());
		result = prime * result
				+ ((analysisRunUUID == null) ? 0 : analysisRunUUID.hashCode());
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
		AnalysisRunSelection other = (AnalysisRunSelection) obj;
		if (analysisProjectUUID == null) {
			if (other.analysisProjectUUID != null)
				return false;
		} else if (!analysisProjectUUID.equals(other.analysisProjectUUID))
			return false;
		if (analysisRunUUID == null) {
			if (other.analysisRunUUID != null)
				return false;
		} else if (!analysisRunUUID.equals(other.analysisRunUUID))
			return false;
		return true;
	}

}
