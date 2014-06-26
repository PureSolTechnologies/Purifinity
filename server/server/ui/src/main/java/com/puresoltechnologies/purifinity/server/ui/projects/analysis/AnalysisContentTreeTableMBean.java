package com.puresoltechnologies.purifinity.server.ui.projects.analysis;

import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;

@ViewScoped
@ManagedBean
public class AnalysisContentTreeTableMBean {

    @Inject
    private AnalysisStore analysisStore;

    private UUID projectUUID;
    private AnalysisProject analysisProject;
    private UUID runUUID;
    private AnalysisRunInformation analysisRun;
    private TreeNode fileTree = null;

    @PostConstruct
    public void construct() {
	try {
	    Map<String, String> requestParameterMap = FacesContext
		    .getCurrentInstance().getExternalContext()
		    .getRequestParameterMap();
	    String projectUUIDString = requestParameterMap.get("project");
	    if (projectUUIDString == null) {
		throw new IllegalStateException(
			"URL has to have a project parameter containing the project UUID.");
	    }
	    projectUUID = UUID.fromString(projectUUIDString);
	    analysisProject = analysisStore.readAnalysisProject(projectUUID);
	    analysisRun = analysisStore.readLastAnalysisRun(projectUUID);
	    if (analysisRun != null) {
		runUUID = analysisRun.getRunUUID();
	    }
	} catch (AnalysisStoreException e) {
	    throw new RuntimeException(
		    "Could not read analysis project or analysis run information.",
		    e);
	}
    }

    public TreeNode getRoot() {
	try {
	    if ((fileTree == null) && (runUUID != null)) {
		AnalysisFileTree analysisFileTree = analysisStore
			.readAnalysisFileTree(projectUUID, runUUID);
		if (analysisFileTree != null) {
		    fileTree = new DefaultTreeNode(
			    new AnalysisContentTreeNodeObject(analysisFileTree));
		    addChildren(fileTree, analysisFileTree);
		    return fileTree;
		}
	    }
	    return new DefaultTreeNode();
	} catch (AnalysisStoreException e) {
	    throw new RuntimeException("Could not read analysis file tree.", e);
	}
    }

    private void addChildren(TreeNode fileTree,
	    AnalysisFileTree analysisFileTree) {
	for (AnalysisFileTree analysisFileTreeNode : analysisFileTree
		.getChildren()) {
	    DefaultTreeNode newTreeNode = new DefaultTreeNode(
		    new AnalysisContentTreeNodeObject(analysisFileTreeNode),
		    fileTree);
	    addChildren(newTreeNode, analysisFileTreeNode);
	}
    }
}
