package com.puresoltechnologies.purifinity.analysis.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.trees.SearchVisitor;
import com.puresoltechnologies.trees.TreeSearchCriterion;
import com.puresoltechnologies.trees.TreeVisitor;
import com.puresoltechnologies.trees.TreeWalker;
import com.puresoltechnologies.trees.WalkingAction;

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
     * @param information
     *            is an {@link AnalysisRunInformation} object containing the run
     *            information.
     * @param fileTree
     *            is the {@link AnalysisFileTree} associated with this run.
     */
    public AnalysisRun(@JsonProperty("information") AnalysisRunInformation information,
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
			    internalPaths.put(tree.getPathFile(false).getPath(), information);
			} else {
			    failedFiles.add(information);
			}
		    }
		}
		return WalkingAction.PROCEED;
	    }
	};
	if (fileTree != null) {
	    TreeWalker.walk(visitor, fileTree);
	}

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

    /**
     * XXX using hashIds here is not possible due to bugs... It is no
     * understood, why at the moment.
     * 
     * @param hashId
     *            is the {@link HashId} f the node to be found.
     * @return An {@link AnalysisFileTree} is returned referencing the found
     *         node. <code>null</code> is returned in case no node was found.
     */
    public AnalysisFileTree findTreeNode(HashId hashId) {
	SearchVisitor<AnalysisFileTree> searchVisitor = new SearchVisitor<AnalysisFileTree>(
		new TreeSearchCriterion<AnalysisFileTree>() {
		    @Override
		    public boolean accepted(AnalysisFileTree node) {
			if (node.getHashId().equals(hashId)) {
			    return true;
			}
			return false;
		    }
		});
	TreeWalker.walk(searchVisitor, fileTree);
	List<AnalysisFileTree> searchResult = searchVisitor.getSearchResult();
	if (searchResult.isEmpty()) {
	    return null;
	}
	return searchResult.get(0);
    }

    @Override
    public String toString() {
	return getInformation().getStartTime().toString() + " (" + getInformation().getRunId() + ")";
    }
}