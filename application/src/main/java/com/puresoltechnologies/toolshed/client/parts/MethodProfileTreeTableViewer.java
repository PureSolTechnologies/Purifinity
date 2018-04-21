package com.puresoltechnologies.toolshed.client.parts;

import java.util.Optional;

import com.puresoltechnologies.javafx.perspectives.PartHeaderToolBar;
import com.puresoltechnologies.javafx.perspectives.parts.AbstractViewer;
import com.puresoltechnologies.javafx.perspectives.parts.PartOpenMode;
import com.puresoltechnologies.javafx.reactive.ReactiveFX;
import com.puresoltechnologies.toolshed.client.Topics;
import com.puresoltechnologies.toolshed.client.profiles.Profile;
import com.puresoltechnologies.toolshed.client.profiles.ProfileEntry;
import com.puresoltechnologies.toolshed.client.profiles.graph.CodeGraphEdge;
import com.puresoltechnologies.toolshed.client.profiles.graph.InvokesEdge;
import com.puresoltechnologies.toolshed.client.profiles.graph.MethodVertex;
import com.puresoltechnologies.toolshed.client.tables.ProfileTreeTable;

import io.reactivex.disposables.Disposable;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.BorderPane;

public class MethodProfileTreeTableViewer extends AbstractViewer {

    private final Disposable storeDisposable;
    private final Disposable storeDisposable2;
    private BorderPane borderPane;
    private ProfileTreeTable treeTable;
    private Profile profile;

    public MethodProfileTreeTableViewer() {
	super("Method Profile Tree", PartOpenMode.AUTO_AND_MANUAL);
	storeDisposable = ReactiveFX.getStore().subscribe(Topics.NEW_PROFILE, this::setProfile);
	storeDisposable2 = ReactiveFX.getStore().subscribe(Topics.PROFILE_ENTRY_SELECTED, this::setProfileEntry);
    }

    @Override
    public Optional<PartHeaderToolBar> getToolBar() {
	return Optional.empty();
    }

    @Override
    public void initialize() {
	borderPane = new BorderPane();

	treeTable = new ProfileTreeTable();
	treeTable.setShowRoot(true);
	borderPane.setCenter(treeTable);
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
	TreeItem<ProfileEntry> treeItem = new TreeItem<ProfileEntry>(profileEntry);
	treeTable.setRoot(treeItem);
	MethodVertex method = profile.findMethod(profileEntry);
	if (method != null) {
	    for (CodeGraphEdge codeGraphEdge : method.getEdges()) {
		if (codeGraphEdge instanceof InvokesEdge) {
		    InvokesEdge edge = (InvokesEdge) codeGraphEdge;
		    MethodVertex invokedMethod = (MethodVertex) edge.getVertices().getSecond();
		    ProfileEntry invokedEntry = profile.findEntry(invokedMethod);
		    if (invokedEntry != null) {
			TreeItem<ProfileEntry> childTreeItem = new TreeItem<>(invokedEntry);
			treeItem.getChildren().add(childTreeItem);
		    }
		}
	    }
	}
    }

    public void setProfile(Profile profile) {
	this.profile = profile;
    }

}
