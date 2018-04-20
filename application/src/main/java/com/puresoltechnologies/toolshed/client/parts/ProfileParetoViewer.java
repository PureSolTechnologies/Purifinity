package com.puresoltechnologies.toolshed.client.parts;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.puresoltechnologies.javafx.perspectives.PartHeaderToolBar;
import com.puresoltechnologies.javafx.perspectives.parts.AbstractViewer;
import com.puresoltechnologies.javafx.perspectives.parts.PartOpenMode;
import com.puresoltechnologies.javafx.reactive.ReactiveFX;
import com.puresoltechnologies.toolshed.client.profiles.ProfileEntry;
import com.puresoltechnologies.toolshed.client.profiles.RawProfileReader;
import com.puresoltechnologies.toolshed.client.tables.ProfileParetoTable;

import io.reactivex.functions.Consumer;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class ProfileParetoViewer extends AbstractViewer implements Consumer<File> {

    private static final long serialVersionUID = 4144855423662815232L;

    private BorderPane borderPane;
    private ProfileParetoTable table;

    public ProfileParetoViewer() {
	super("Profile Pareto", PartOpenMode.AUTO_AND_MANUAL);
	ReactiveFX.getStore().subscribe("profile.raw", this);

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
    }

    @Override
    public void close() {
	// TODO Auto-generated method stub

    }

    @Override
    public Node getContent() {
	return borderPane;
    }

    @Override
    public void accept(File file) throws Exception {
	try (RawProfileReader rawProfileReader = new RawProfileReader(file)) {
	    List<ProfileEntry> entries = new ArrayList<>();
	    rawProfileReader.iterable().forEach(entries::add);
	    table.setItems(FXCollections.observableList(entries));
	}
    }

}
