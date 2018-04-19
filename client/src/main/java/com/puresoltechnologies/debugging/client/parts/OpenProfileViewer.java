package com.puresoltechnologies.debugging.client.parts;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import com.puresoltechnologies.javafx.perspectives.PartHeaderToolBar;
import com.puresoltechnologies.javafx.perspectives.parts.AbstractViewer;
import com.puresoltechnologies.javafx.perspectives.parts.PartOpenMode;
import com.puresoltechnologies.javafx.reactive.ReactiveFX;
import com.puresoltechnologies.javafx.utils.ResourceUtils;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class OpenProfileViewer extends AbstractViewer {

    private static final long serialVersionUID = 4144855423662815232L;

    private BorderPane borderPane;
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
	    File result = fileChooser.showOpenDialog(null);
	    if (result != null) {
		ReactiveFX.getStore().publish("profile.raw", result);
	    }
	    event.consume();
	});
	borderPane.setTop(toolBar);
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
