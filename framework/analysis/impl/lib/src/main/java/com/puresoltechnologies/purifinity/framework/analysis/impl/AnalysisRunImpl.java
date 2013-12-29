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
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreFactory;

/**
 * This class is an implementation of {@link AnalysisRun}.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AnalysisRunImpl implements AnalysisRun {

	private static final long serialVersionUID = 6413809660830217670L;

	public static AnalysisRunImpl readFromStore(UUID projectUUID, UUID runUUID)
			throws AnalysisStoreException {
		AnalysisStore analysisStore = AnalysisStoreFactory.getFactory()
				.getInstance();
		AnalysisRunInformation information = analysisStore.readAnalysisRun(
				projectUUID, runUUID);
		HashIdFileTree hashIdFileTree = analysisStore.readFileTree(projectUUID,
				runUUID);
		return new AnalysisRunImpl(information, fileTree, analyzedFiles,
				failedSources);
	}

	private final AnalysisRunInformation information;
	private final HashIdFileTree fileTree;
	private final List<AnalyzedCode> analyzedFiles = new ArrayList<>();
	private final List<AnalyzedCode> failedSources = new ArrayList<>();

	/**
	 * This constructor is used to create a new analysis run. All setup
	 * information is set and is immutable.
	 * 
	 * @param name
	 * @param runDirectory
	 * @param searchConfiguration
	 */
	public AnalysisRunImpl(UUID analysisProjectUUID, UUID uuid, Date startTime,
			long duration, HashIdFileTree fileTree,
			List<AnalyzedCode> analyzedFiles, List<AnalyzedCode> failedSources,
			FileSearchConfiguration fileSearchConfiguration) {
		this(new AnalysisRunInformation(analysisProjectUUID, uuid, startTime,
				duration, "", fileSearchConfiguration), fileTree,
				analyzedFiles, failedSources);
	}

	/**
	 * This constructor is used to create a new analysis run. All setup
	 * information is set and is immutable.
	 * 
	 * @param name
	 * @param runDirectory
	 * @param searchConfiguration
	 */
	public AnalysisRunImpl(AnalysisRunInformation information,
			HashIdFileTree fileTree, List<AnalyzedCode> analyzedFiles,
			List<AnalyzedCode> failedSources) {
		super();
		this.information = information;
		this.fileTree = fileTree;
		this.analyzedFiles.addAll(analyzedFiles);
		this.failedSources.addAll(failedSources);
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
		return information;
	}

}