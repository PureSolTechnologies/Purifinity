package com.puresoltechnologies.toolshed.client.parts;

import java.util.Optional;

import com.puresoltechnologies.javafx.charts.tree.TreeMapView;
import com.puresoltechnologies.javafx.perspectives.PartHeaderToolBar;
import com.puresoltechnologies.javafx.perspectives.parts.AbstractViewer;
import com.puresoltechnologies.javafx.perspectives.parts.PartOpenMode;
import com.puresoltechnologies.javafx.reactive.ReactiveFX;
import com.puresoltechnologies.toolshed.client.Topics;
import com.puresoltechnologies.toolshed.client.profiles.Profile;
import com.puresoltechnologies.toolshed.client.profiles.ProfileEntry;

import io.reactivex.disposables.Disposable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;

public class MethodProfileTreeGraphViewer extends AbstractViewer {

    private Disposable storeDisposable;
    private Disposable storeDisposable2;
    private BorderPane borderPane;
    private Spinner<Integer> depthSpinner;
    private TreeMapView<ProfileTreeAreaChartNode> treeChartView;
    private Profile profile;

    public MethodProfileTreeGraphViewer() {
	super("Method Profile Tree Graph", PartOpenMode.AUTO_AND_MANUAL);
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
	depthSpinner.valueProperty().addListener((element, oldValue, newValue) -> treeChartView.setDepth(newValue));
	toolBar.getItems().addAll(depthLabel, depthSpinner);

	treeChartView = new TreeMapView<>();
	borderPane.setTop(toolBar);
	borderPane.setCenter(treeChartView);

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
	treeChartView
		.setTreeData(new ProfileTreeAreaChartNode(profile, profileEntry, profile.findMethod(profileEntry)));
    }

    public void setProfile(Profile profile) {
	this.profile = profile;
    }

}
