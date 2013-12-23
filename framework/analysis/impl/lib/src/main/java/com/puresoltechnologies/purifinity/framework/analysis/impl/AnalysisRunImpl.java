package com.puresoltechnologies.purifinity.framework.analysis.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.analysis.api.AnalyzedCode;
import com.puresoltechnologies.purifinity.analysis.api.HashIdFileTree;

/**
 * This class is an implementation of {@link AnalysisRun}.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AnalysisRunImpl implements AnalysisRun {

	private static final long serialVersionUID = 6413809660830217670L;

	private final List<AnalyzedCode> analyzedFiles = new ArrayList<>();
	private final List<AnalyzedCode> failedSources = new ArrayList<>();

	private HashIdFileTree fileTree;

	private final UUID analysisProjectUUID;
	private final UUID uuid;
	private final Date creationTime;
	private final long timeOfRun;

	/**
	 * This constructor is used to create a new analysis run. All setup
	 * information is set and is immutable.
	 * 
	 * @param name
	 * @param runDirectory
	 * @param searchConfiguration
	 */
	public AnalysisRunImpl(UUID analysisProjectUUID, UUID uuid,
			Date creationTime, long timeOfRun) {
		super();
		this.analysisProjectUUID = analysisProjectUUID;
		this.uuid = uuid;
		this.creationTime = creationTime;
		this.timeOfRun = timeOfRun;
	}

	@Override
	public List<AnalyzedCode> getAnalyzedFiles() {
		return analyzedFiles;
	}

	@Override
	public HashIdFileTree getFileTree() {
		return fileTree;
	}

	@Override
	public List<AnalyzedCode> getFailedFiles() {
		return failedSources;
	}

	@Override
	public AnalyzedCode findAnalyzedCode(String internalPath) {
		for (AnalyzedCode analyzedFile : analyzedFiles) {
			if (analyzedFile.getSourceLocation().getInternalLocation()
					.equals(internalPath)) {
				return analyzedFile;
			}
		}
		return null;
	}

	@Override
	public AnalysisRunInformation getInformation() {
		return new AnalysisRunInformation(analysisProjectUUID, uuid,
				creationTime, timeOfRun, "<Not implemented, yet!>");
	}

}