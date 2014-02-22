package com.puresoltechnologies.purifinity.framework.analysis.impl;

import static com.puresoltechnologies.commons.misc.ParameterChecks.checkNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.trees.api.TreeVisitor;
import com.puresoltechnologies.commons.trees.api.TreeWalker;
import com.puresoltechnologies.commons.trees.api.WalkingAction;
import com.puresoltechnologies.parsers.api.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
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
		AnalysisFileTree analysisFileTree = analysisStore.readAnalysisFileTree(
				projectUUID, runUUID);
		return new AnalysisRunImpl(information, analysisFileTree);
	}

	private final AnalysisRunInformation information;
	private final AnalysisFileTree fileTree;
	private final List<AnalysisInformation> successfulFiles = new ArrayList<>();
	private final List<AnalysisInformation> failedFiles = new ArrayList<>();
	private final Map<String, AnalysisInformation> internalPaths = new HashMap<>();
	private final Map<HashId, AnalysisFileTree> hashIds = new HashMap<>();

	/**
	 * This constructor is used to create a new analysis run. All setup
	 * information is set and is immutable.
	 * 
	 * @param name
	 * @param runDirectory
	 * @param searchConfiguration
	 */
	public AnalysisRunImpl(UUID analysisProjectUUID, UUID uuid, Date startTime,
			long duration, AnalysisFileTree fileTree,
			FileSearchConfiguration fileSearchConfiguration,
			Map<HashId, SourceCodeLocation> sourceCodeLocations) {
		this(new AnalysisRunInformation(analysisProjectUUID, uuid, startTime,
				duration, "", fileSearchConfiguration), fileTree);
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
			AnalysisFileTree fileTree) {
		super();
		checkNotNull("information", information);
		checkNotNull("fileTree", fileTree);
		this.information = information;
		this.fileTree = fileTree;
		populateFields();
	}

	private void populateFields() {
		TreeVisitor<AnalysisFileTree> visitor = new TreeVisitor<AnalysisFileTree>() {
			@Override
			public WalkingAction visit(AnalysisFileTree tree) {
				hashIds.put(tree.getHashId(), tree);
				if (tree.isFile()) {
					for (AnalysisInformation information : tree.getAnalyses()) {
						if (information.isSuccessful()) {
							successfulFiles.add(information);
							internalPaths.put(
									tree.getPathFile(false).getPath(),
									information);
						} else {
							failedFiles.add(information);
						}
					}
				}
				return WalkingAction.PROCEED;
			}
		};
		TreeWalker.walk(visitor, fileTree);
	}

	@Override
	public List<AnalysisInformation> getAnalyzedFiles() {
		return successfulFiles;
	}

	@Override
	public AnalysisFileTree getFileTree() {
		return fileTree;
	}

	@Override
	public List<AnalysisInformation> getFailedFiles() {
		return failedFiles;
	}

	@Override
	public AnalysisInformation findAnalyzedCode(String internalPath) {
		return internalPaths.get(internalPath);
	}

	@Override
	public AnalysisRunInformation getInformation() {
		return information;
	}

	@Override
	public AnalysisFileTree findTreeNode(HashId hashId) {
		return hashIds.get(hashId);
	}

	@Override
	public String toString() {
		return getInformation().getStartTime().toString() + " ("
				+ getInformation().getUUID() + ")";
	}
}