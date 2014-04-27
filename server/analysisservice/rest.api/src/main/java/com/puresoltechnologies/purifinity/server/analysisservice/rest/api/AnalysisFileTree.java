package com.puresoltechnologies.purifinity.server.analysisservice.rest.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AnalysisFileTree {

	private final List<AnalysisFileTree> children = new ArrayList<AnalysisFileTree>();

	private final AnalysisFileTree parent;
	private final String name;
	private final String hashId;
	private final boolean file;
	private final Properties sourceCodeLocation;
	private final List<AnalysisInformation> analyzedCodes = new ArrayList<>();

	public AnalysisFileTree(AnalysisFileTree parent, String name,
			String hashId, boolean file, Properties sourceCodeLocation,
			List<AnalysisInformation> analyzedCodes) {
		super();
		this.parent = parent;
		this.name = name;
		this.hashId = hashId;
		this.file = file;
		this.sourceCodeLocation = sourceCodeLocation;
		if (analyzedCodes != null) {
			this.analyzedCodes.addAll(analyzedCodes);
		}
		if (parent != null) {
			parent.children.add(this);
		}
	}

	public final AnalysisFileTree getParent() {
		return parent;
	}

	public final boolean hasChildren() {
		return !children.isEmpty();
	}

	public final List<AnalysisFileTree> getChildren() {
		return children;
	}

	public final String getName() {
		return name;
	}

	public final String getHashId() {
		return hashId;
	}

	public final boolean isFile() {
		return file;
	}

	public final Properties getSourceCodeLocation() {
		return sourceCodeLocation;
	}

	public final List<AnalysisInformation> getAnalyses() {
		return analyzedCodes;
	}

}
