package com.puresoltechnologies.toolshed.client.parts;

import java.util.Optional;

import com.puresoltechnologies.javafx.charts.tree.TreeChartView;
import com.puresoltechnologies.javafx.charts.tree.TreeDataNode;
import com.puresoltechnologies.javafx.perspectives.PartHeaderToolBar;
import com.puresoltechnologies.javafx.perspectives.parts.AbstractViewer;
import com.puresoltechnologies.javafx.perspectives.parts.PartOpenMode;
import com.puresoltechnologies.javafx.reactive.ReactiveFX;
import com.puresoltechnologies.toolshed.client.Topics;
import com.puresoltechnologies.toolshed.client.profiles.Profile;
import com.puresoltechnologies.toolshed.client.profiles.ProfileEntry;

import io.reactivex.disposables.Disposable;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class MethodProfileTreeGraphViewer extends AbstractViewer {

    private final Disposable storeDisposable;
    private final Disposable storeDisposable2;
    private BorderPane borderPane;
    private TreeChartView treeChartView;
    private Profile profile;

    public MethodProfileTreeGraphViewer() {
	super("Method Profile Tree Graph", PartOpenMode.AUTO_AND_MANUAL);
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
	treeChartView = new TreeChartView();
	borderPane.setCenter(treeChartView);
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
	TreeDataNode rootNode = new TreeDataNode(profileEntry.getClassName() + ":" + profileEntry.getMethodName(),
		profileEntry.getTotalTime());
	treeChartView.setTreeData(rootNode);
    }

    public void setProfile(Profile profile) {
	this.profile = profile;
    }

}
