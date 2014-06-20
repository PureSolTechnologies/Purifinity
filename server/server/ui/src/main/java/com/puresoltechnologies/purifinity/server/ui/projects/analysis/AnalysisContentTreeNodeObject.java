package com.puresoltechnologies.purifinity.server.ui.projects.analysis;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;

public class AnalysisContentTreeNodeObject {

    private final AnalysisFileTree analysisFileTree;

    public AnalysisContentTreeNodeObject(AnalysisFileTree analysisFileTree) {
	super();
	this.analysisFileTree = analysisFileTree;
    }

    public AnalysisFileTree getAnalysisFileTree() {
	return analysisFileTree;
    }

    public String getName() {
	return analysisFileTree.getName();
    }
}
