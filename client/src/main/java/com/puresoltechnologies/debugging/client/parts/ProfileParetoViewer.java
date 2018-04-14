package com.puresoltechnologies.debugging.client.parts;

import java.util.Optional;

import com.puresoltechnologies.debugging.client.tables.ProfileParetoTable;
import com.puresoltechnologies.javafx.perspectives.PartHeaderToolBar;
import com.puresoltechnologies.javafx.perspectives.parts.AbstractViewer;
import com.puresoltechnologies.javafx.perspectives.parts.PartOpenMode;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class ProfileParetoViewer extends AbstractViewer {

    private static final long serialVersionUID = 4144855423662815232L;

    private BorderPane borderPane;

    public ProfileParetoViewer() {
	super("Profile Pareto", PartOpenMode.AUTO_AND_MANUAL);
    }

    @Override
    public Optional<PartHeaderToolBar> getToolBar() {
	return Optional.empty();
    }

    @Override
    public void initialize() {
	borderPane = new BorderPane();
	ProfileParetoTable table = new ProfileParetoTable();
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

}
