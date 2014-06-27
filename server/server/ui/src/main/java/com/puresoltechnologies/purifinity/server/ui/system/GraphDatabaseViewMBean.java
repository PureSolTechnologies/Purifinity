package com.puresoltechnologies.purifinity.server.ui.system;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.mindmap.DefaultMindmapNode;
import org.primefaces.model.mindmap.MindmapNode;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisStoreService;

@ManagedBean
@ViewScoped
public class GraphDatabaseViewMBean implements Serializable {

    private static final long serialVersionUID = -1320250964374319241L;

    private MindmapNode root;

    private MindmapNode selectedNode;

    @Inject
    private AnalysisStoreService analysisStore;

    private List<AnalysisProject> projects;

    @PostConstruct
    public void construct() {
	try {
	    projects = analysisStore.readAllAnalysisProjects();
	} catch (AnalysisStoreException e) {
	    throw new RuntimeException("Could not load project information.", e);
	}

	root = new DefaultMindmapNode("Projects", "All created projects",
		"ffff00", false);

	for (AnalysisProject project : projects) {
	    MindmapNode node = new DefaultMindmapNode(project.getSettings()
		    .getName(), project, "00ff00", true);
	    root.addNode(node);
	}
    }

    public MindmapNode getRoot() {
	return root;
    }

    public MindmapNode getSelectedNode() {
	return selectedNode;
    }

    public void setSelectedNode(MindmapNode selectedNode) {
	this.selectedNode = selectedNode;
    }

    public void onNodeSelect(SelectEvent event) {
	try {
	    MindmapNode node = (MindmapNode) event.getObject();

	    // populate if not already loaded
	    if (node.getChildren().isEmpty()) {
		Object data = node.getData();

		if (data instanceof AnalysisProject) {
		    AnalysisProject project = (AnalysisProject) data;
		    createRunNodes(node, project);
		}
		if (data instanceof AnalysisRun) {
		    AnalysisRun run = (AnalysisRun) data;
		    createFileTree(node, run);
		}
	    }
	} catch (AnalysisStoreException e) {
	    throw new RuntimeException(e);
	}
    }

    private void createRunNodes(MindmapNode node, AnalysisProject project)
	    throws AnalysisStoreException {
	List<AnalysisRunInformation> runs = analysisStore
		.readAllRunInformation(project.getInformation().getUUID());
	for (AnalysisRunInformation runInformation : runs) {
	    AnalysisRun run = analysisStore.readAnalysisRun(runInformation);
	    node.addNode(new DefaultMindmapNode(run.getInformation()
		    .getStartTime().toString(), run, "ff0000", true));
	}
    }

    private void createFileTree(MindmapNode node, AnalysisRun run) {
	AnalysisFileTree rootDirectory = run.getFileTree();
	createDirectoryNode(node, rootDirectory);
    }

    private void createDirectoryNode(MindmapNode node,
	    AnalysisFileTree directory) {
	DefaultMindmapNode newNode = new DefaultMindmapNode(
		directory.getName(), directory, "0000ff", true);
	node.addNode(newNode);
	for (AnalysisFileTree child : directory.getChildren()) {
	    if (child.isFile()) {
		DefaultMindmapNode fileNode = new DefaultMindmapNode(
			child.getName(), child, "00ffff", false);
		newNode.addNode(fileNode);
	    } else {
		createDirectoryNode(newNode, child);
	    }
	}

    }

    public void onNodeDblselect(SelectEvent event) {
	this.selectedNode = (MindmapNode) event.getObject();
    }

    public String getDetails() {
	Object data = selectedNode.getData();
	if (data instanceof AnalysisProject) {
	    AnalysisProject project = (AnalysisProject) data;
	    return "Project: " + project.getSettings().getName();
	} else {
	    return data.toString();
	}
    }
}