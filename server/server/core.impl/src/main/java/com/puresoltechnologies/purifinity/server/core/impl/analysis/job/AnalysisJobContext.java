package com.puresoltechnologies.purifinity.server.core.impl.analysis.job;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisRunFileTree;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileInformation;

public class AnalysisJobContext {

	private final Date startTime;
	private final AnalysisProject analysisProject;
	private final AnalysisRunInformation analysisRunInformation;
	private final Map<SourceCodeLocation, FileInformation> sourceCodeLocations = new HashMap<>();
	private AnalysisRunFileTree analysisRunFileTree;

	public AnalysisJobContext(Date startTime, AnalysisProject analysisProject,
			AnalysisRunInformation analysisRunInformation) {
		super();
		this.startTime = startTime;
		this.analysisProject = analysisProject;
		this.analysisRunInformation = analysisRunInformation;
	}

	public Date getStartTime() {
		return startTime;
	}

	public AnalysisProject getAnalysisProject() {
		return analysisProject;
	}

	public AnalysisRunInformation getAnalysisRunInformation() {
		return analysisRunInformation;
	}

	public void addStoredFile(SourceCodeLocation sourceCodeLocation,
			FileInformation fileInformation) {
		sourceCodeLocations.put(sourceCodeLocation, fileInformation);
	}

	public Map<SourceCodeLocation, FileInformation> getStoredFiles() {
		return sourceCodeLocations;
	}

	public void setAnalysisRunFileTree(AnalysisRunFileTree analysisRunFileTree) {
		this.analysisRunFileTree = analysisRunFileTree;
	}

	public AnalysisRunFileTree getAnalysisRunFileTree() {
		return analysisRunFileTree;
	}

}
