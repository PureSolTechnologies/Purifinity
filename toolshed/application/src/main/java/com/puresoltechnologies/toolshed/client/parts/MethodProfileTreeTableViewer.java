package com.puresoltechnologies.toolshed.client.parts;

import java.util.Optional;

import com.puresoltechnologies.javafx.perspectives.PartHeaderToolBar;
import com.puresoltechnologies.javafx.perspectives.parts.AbstractViewer;
import com.puresoltechnologies.javafx.perspectives.parts.PartOpenMode;
import com.puresoltechnologies.javafx.reactive.ReactiveFX;
import com.puresoltechnologies.javafx.utils.FXThreads;
import com.puresoltechnologies.toolshed.client.Topics;
import com.puresoltechnologies.toolshed.client.profiles.Profile;
import com.puresoltechnologies.toolshed.client.profiles.ProfileEntry;
import com.puresoltechnologies.toolshed.client.profiles.graph.CodeGraphEdge;
import com.puresoltechnologies.toolshed.client.profiles.graph.InvokesEdge;
import com.puresoltechnologies.toolshed.client.profiles.graph.MethodVertex;
import com.puresoltechnologies.toolshed.client.tables.ProfileTreeTable;

import io.reactivex.disposables.Disposable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.BorderPane;

public class MethodProfileTreeTableViewer extends AbstractViewer {

    private Disposable storeDisposable;
    private Disposable storeDisposable2;
    private BorderPane borderPane;
    private Spinner<Integer> depthSpinner;
    private ProfileTreeTable treeTable;
    private Profile profile = null;
    private ProfileEntry profileEntry = null;

    public MethodProfileTreeTableViewer() {
	super("Method Profile Tree", PartOpenMode.AUTO_AND_MANUAL);
    }

    @Override
    public Optional<PartHeaderToolBar> getToolBar() {
	return Optional.empty();
    }

    @Override
    public void initialize() {
	borderPane = new BorderPane();

	ToolBar toolBar = new ToolBar();
	Label depthLabel = new Label("Invokation depth");
	depthSpinner = new Spinner<>(1, 10, 1);
	depthSpinner.valueProperty().addListener((element, oldValue, newValue) -> calculateTree());
	toolBar.getItems().addAll(depthLabel, depthSpinner);

	treeTable = new ProfileTreeTable();
	treeTable.setShowRoot(true);
	borderPane.setTop(toolBar);
	borderPane.setCenter(treeTable);

	storeDisposable = ReactiveFX.getStore().subscribe(Topics.NEW_PROFILE, this::setProfile);
	storeDisposable2 = ReactiveFX.getStore().subscribe(Topics.PROFILE_ENTRY_SELECTED, this::setProfileEntry);
    }

    @Override
    public void close() {
	storeDisposable.dispose();
	storeDisposable2.dispose();
    }

    @Override
    public Node getContent() {
	return borderPane;
    }

    public void setProfileEntry(ProfileEntry profileEntry) {
	this.profileEntry = profileEntry;
	calculateTree();
    }

    private void calculateTree() {
	if (profileEntry != null) {
	    FXThreads.runAsync(() -> {
		int depth = depthSpinner.getValue();
		TreeItem<ProfileEntry> rootNode = new TreeItem<ProfileEntry>(profileEntry);
		rootNode.setExpanded(true);
		MethodVertex method = profile.findMethod(profileEntry);
		calculateTreeNode(rootNode, method, depth);
		FXThreads.runOnFXThread(() -> treeTable.setRoot(rootNode));
	    });
	}
    }

    private void calculateTreeNode(TreeItem<ProfileEntry> node, MethodVertex method, int depth) {
	if (depth == 0) {
	    return;
	}
	if (method != null) {
	    for (CodeGraphEdge codeGraphEdge : method.getEdges()) {
		if (codeGraphEdge instanceof InvokesEdge) {
		    InvokesEdge edge = (InvokesEdge) codeGraphEdge;
		    MethodVertex invokedMethod = (MethodVertex) edge.getVertices().getSecond();
		    ProfileEntry invokedEntry = profile.findEntry(invokedMethod);
		    if (invokedEntry != null) {
			TreeItem<ProfileEntry> childTreeItem = new TreeItem<>(invokedEntry);
			childTreeItem.setExpanded(true);
			node.getChildren().add(childTreeItem);
			calculateTreeNode(childTreeItem, invokedMethod, depth - 1);
		    }
		}
	    }
	}
    }

    public void setProfile(Profile profile) {
	this.profile = profile;
    }

}
