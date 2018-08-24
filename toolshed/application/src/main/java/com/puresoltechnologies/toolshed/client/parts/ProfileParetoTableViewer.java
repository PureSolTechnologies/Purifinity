package com.puresoltechnologies.toolshed.client.parts;

import java.util.Optional;

import com.puresoltechnologies.javafx.perspectives.PartHeaderToolBar;
import com.puresoltechnologies.javafx.perspectives.parts.AbstractViewer;
import com.puresoltechnologies.javafx.perspectives.parts.PartOpenMode;
import com.puresoltechnologies.javafx.reactive.ReactiveFX;
import com.puresoltechnologies.toolshed.client.Topics;
import com.puresoltechnologies.toolshed.client.profiles.Profile;
import com.puresoltechnologies.toolshed.client.tables.ProfileParetoTable;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class ProfileParetoTableViewer extends AbstractViewer implements Consumer<Profile> {

    private Disposable storeDisposable;
    private BorderPane borderPane;
    private ProfileParetoTable table;

    public ProfileParetoTableViewer() {
	super("Profile Pareto", PartOpenMode.AUTO_AND_MANUAL);
    }

    @Override
    public Optional<PartHeaderToolBar> getToolBar() {
	return Optional.empty();
    }

    @Override
    public void initialize() {
	borderPane = new BorderPane();
	table = new ProfileParetoTable();
	borderPane.setCenter(table);

	storeDisposable = ReactiveFX.getStore().subscribe(Topics.NEW_PROFILE, this);
    }

    @Override
    public void close() {
	storeDisposable.dispose();
    }

    @Override
    public Node getContent() {
	return borderPane;
    }

    @Override
    public void accept(Profile profile) throws Exception {
	table.setItems(FXCollections.observableList(profile.getEntries()));
    }

}
