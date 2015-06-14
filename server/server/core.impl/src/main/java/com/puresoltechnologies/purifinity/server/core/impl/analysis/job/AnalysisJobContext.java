package com.puresoltechnologies.purifinity.server.core.impl.analysis.job;

import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisRunFileTree;

public class AnalysisJobContext {

	private final AnalysisProject analysisProject;
	private final AnalysisRunInformation analysisRunInformation;
	private final Map<SourceCodeLocation, HashId> sourceCodeLocations = new HashMap<>();
	private AnalysisRunFileTree analysisRunFileTree;

	public AnalysisJobContext(AnalysisProject analysisProject,
			AnalysisRunInformation analysisRunInformation) {
		super();
		this.analysisProject = analysisProject;
		this.analysisRunInformation = analysisRunInformation;
	}

	public AnalysisProject getAnalysisProject() {
		return analysisProject;
	}

	public AnalysisRunInformation getAnalysisRunInformation() {
		return analysisRunInformation;
	}

	public void addStoredFile(SourceCodeLocation sourceCodeLocation,
			HashId hashId) {
		sourceCodeLocations.put(sourceCodeLocation, hashId);
	}

	public Map<SourceCodeLocation, HashId> getStoredFiles() {
		return sourceCodeLocations;
	}

	public void setAnalysisRunFileTree(AnalysisRunFileTree analysisRunFileTree) {
		this.analysisRunFileTree = analysisRunFileTree;
	}

	public AnalysisRunFileTree getAnalysisRunFileTree() {
		return analysisRunFileTree;
	}

}
