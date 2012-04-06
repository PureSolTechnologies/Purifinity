package com.puresol.coding.client.content;

import com.puresol.coding.analysis.api.Analysis;
import com.puresol.trees.FileTree;

public class AnalysisNavigatorTreeNodeElement {

    private final AnalysisNavigatorTreeNodeElement parent;
    private final Analysis analysis;
    private final FileTree sourceFile;
    private final String treeNodeName;

    public AnalysisNavigatorTreeNodeElement(
	    AnalysisNavigatorTreeNodeElement parent, Analysis analysis,
	    FileTree sourceFile, String treeNodeName) {
	super();
	this.parent = parent;
	this.analysis = analysis;
	this.sourceFile = sourceFile;
	this.treeNodeName = treeNodeName;
    }

    public AnalysisNavigatorTreeNodeElement getParent() {
	return parent;
    }

    public Analysis getAnalysis() {
	return analysis;
    }

    public FileTree getSourceFile() {
	return sourceFile;
    }

    public String getTreeNodeName() {
	return treeNodeName;
    }

}
