package com.puresol.coding.client.content;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.trees.FileTree;

public class AnalysisNavigatorTreeNodeElement {

    private final AnalysisNavigatorTreeNodeElement parent;
    private final ProjectAnalyzer analyser;
    private final FileTree sourceFile;
    private final String treeNodeName;

    public AnalysisNavigatorTreeNodeElement(
	    AnalysisNavigatorTreeNodeElement parent, ProjectAnalyzer analyser,
	    FileTree sourceFile, String treeNodeName) {
	super();
	this.parent = parent;
	this.analyser = analyser;
	this.sourceFile = sourceFile;
	this.treeNodeName = treeNodeName;
    }

    public AnalysisNavigatorTreeNodeElement getParent() {
	return parent;
    }

    public ProjectAnalyzer getAnalyser() {
	return analyser;
    }

    public FileTree getSourceFile() {
	return sourceFile;
    }

    public String getTreeNodeName() {
	return treeNodeName;
    }

}
