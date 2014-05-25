package com.puresoltechnologies.purifinity.analysis.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.codehaus.jackson.annotate.JsonProperty;

import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.trees.TreeVisitor;
import com.puresoltechnologies.commons.trees.TreeWalker;
import com.puresoltechnologies.commons.trees.WalkingAction;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;

/**
 * This class is an implementation of {@link AnalysisRun}.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AnalysisRun implements Serializable {

	private static final long serialVersionUID = -3727921466916770165L;

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
	public AnalysisRun(UUID projectUUID, UUID uuid, Date startTime,
			long duration, AnalysisFileTree fileTree,
			FileSearchConfiguration fileSearchConfiguration,
			Map<HashId, SourceCodeLocation> sourceCodeLocations) {
		this(new AnalysisRunInformation(projectUUID, uuid, startTime, duration,
				"", fileSearchConfiguration), fileTree);
	}

	/**
	 * This constructor is used to create a new analysis run. All setup
	 * information is set and is immutable.
	 * 
	 * @param name
	 * @param runDirectory
	 * @param searchConfiguration
	 */
	public AnalysisRun(
			@JsonProperty("information") AnalysisRunInformation information,
			@JsonProperty("fileTree") AnalysisFileTree fileTree) {
		super();
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

	public List<AnalysisInformation> getAnalyzedFiles() {
		return successfulFiles;
	}

	public AnalysisFileTree getFileTree() {
		return fileTree;
	}

	public List<AnalysisInformation> getFailedFiles() {
		return failedFiles;
	}

	public AnalysisInformation findAnalyzedCode(String internalPath) {
		return internalPaths.get(internalPath);
	}

	public AnalysisRunInformation getInformation() {
		return information;
	}

	public AnalysisFileTree findTreeNode(HashId hashId) {
		return hashIds.get(hashId);
	}

	@Override
	public String toString() {
		return getInformation().getStartTime().toString() + " ("
				+ getInformation().getRunUUID() + ")";
	}
}