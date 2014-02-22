package com.puresoltechnologies.purifinity.client.common.analysis.views;

import java.util.UUID;

import org.eclipse.jface.viewers.ISelection;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;

/**
 * This class contains the selection of an {@link AnalysisProject}.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisProjectSelection implements ISelection {

	private final AnalysisProject analysisProject;
	private final UUID analysisProjectUUID;

	public AnalysisProjectSelection(AnalysisProject analysisProject) {
		super();
		this.analysisProject = analysisProject;
		analysisProjectUUID = analysisProject == null ? null : analysisProject
				.getInformation().getUUID();
	}

	public AnalysisProject getAnalysisProject() {
		return analysisProject;
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
		AnalysisProjectSelection other = (AnalysisProjectSelection) obj;
		if (analysisProjectUUID == null) {
			if (other.analysisProjectUUID != null)
				return false;
		} else if (!analysisProjectUUID.equals(other.analysisProjectUUID))
			return false;
		return true;
	}

}
