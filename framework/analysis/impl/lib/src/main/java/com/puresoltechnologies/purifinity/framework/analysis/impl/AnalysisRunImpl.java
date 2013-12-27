package com.puresoltechnologies.purifinity.framework.analysis.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalyzedCode;
import com.puresoltechnologies.purifinity.analysis.domain.HashIdFileTree;

/**
 * This class is an implementation of {@link AnalysisRun}.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AnalysisRunImpl implements AnalysisRun {

	private static final long serialVersionUID = 6413809660830217670L;

	private final UUID analysisProjectUUID;
	private final UUID uuid;
	private final Date startTime;
	private final long timeOfRun;
	private final HashIdFileTree fileTree;
	private final List<AnalyzedCode> analyzedFiles = new ArrayList<>();
	private final List<AnalyzedCode> failedSources = new ArrayList<>();
	private final FileSearchConfiguration fileSearchConfiguration;

	/**
	 * This constructor is used to create a new analysis run. All setup
	 * information is set and is immutable.
	 * 
	 * @param name
	 * @param runDirectory
	 * @param searchConfiguration
	 */
	public AnalysisRunImpl(UUID analysisProjectUUID, UUID uuid,
			Date creationTime, long timeOfRun, HashIdFileTree fileTree,
			List<AnalyzedCode> analyzedFiles, List<AnalyzedCode> failedSources,
			FileSearchConfiguration fileSearchConfiguration) {
		super();
		this.analysisProjectUUID = analysisProjectUUID;
		this.uuid = uuid;
		this.startTime = creationTime;
		this.timeOfRun = timeOfRun;
		this.fileTree = fileTree;
		this.analyzedFiles.addAll(analyzedFiles);
		this.failedSources.addAll(failedSources);
		this.fileSearchConfiguration = fileSearchConfiguration;
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
		return new AnalysisRunInformation(analysisProjectUUID, uuid, startTime,
				timeOfRun, "<Not implemented, yet!>", fileSearchConfiguration);
	}

}