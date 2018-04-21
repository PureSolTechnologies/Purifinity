package com.puresoltechnologies.toolshed.client.parts;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import com.puresoltechnologies.javafx.perspectives.PartHeaderToolBar;
import com.puresoltechnologies.javafx.perspectives.parts.AbstractViewer;
import com.puresoltechnologies.javafx.perspectives.parts.PartOpenMode;
import com.puresoltechnologies.javafx.reactive.ReactiveFX;
import com.puresoltechnologies.javafx.utils.ResourceUtils;
import com.puresoltechnologies.toolshed.client.Topics;
import com.puresoltechnologies.toolshed.client.profiles.Profile;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class OpenProfileViewer extends AbstractViewer {

    private BorderPane borderPane;
    private Label fileLable;
    private Button openProfileButton;

    public OpenProfileViewer() {
	super("Choose Profile", PartOpenMode.AUTO_AND_MANUAL);
    }

    @Override
    public Optional<PartHeaderToolBar> getToolBar() {
	return Optional.empty();
    }

    @Override
    public void initialize() {
	borderPane = new BorderPane();

	fileLable = new Label("Open Profile:");

	ToolBar toolBar = new ToolBar();
	try {
	    ImageView folderImage = ResourceUtils.getImageView(this, "/icons/FatCow_Icons16x16/folder.png");
	    openProfileButton = new Button("Open Profile", folderImage);
	} catch (IOException e) {
	    openProfileButton = new Button("Open Profile");
	}
	toolBar.getItems().add(openProfileButton);
	openProfileButton.setOnAction(event -> {
	    FileChooser fileChooser = new FileChooser();
	    fileChooser.setTitle("Choose raw profile file");
	    fileChooser.getExtensionFilters().add(new ExtensionFilter("Raw profile file", "profile.raw"));
	    File rawProfileFile = fileChooser.showOpenDialog(null);
	    if (rawProfileFile != null) {
		openRawProfile(rawProfileFile);
	    }
	    event.consume();
	});
	borderPane.setTop(toolBar);
	borderPane.setCenter(fileLable);
    }

    private void openRawProfile(File rawProfileFile) {
	try {
	    ReactiveFX.getStore().publish(Topics.PROFILE_OPENED, rawProfileFile);
	    Profile profile = new Profile(rawProfileFile);
	    profile.read();
	    ReactiveFX.getStore().publish(Topics.NEW_PROFILE, profile);
	} catch (IOException e) {
	    Alert error = new Alert(AlertType.ERROR);
	    error.setTitle("Could not open profile.");
	    error.setContentText(e.getMessage());
	    error.showAndWait();
	    return;
	}
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
