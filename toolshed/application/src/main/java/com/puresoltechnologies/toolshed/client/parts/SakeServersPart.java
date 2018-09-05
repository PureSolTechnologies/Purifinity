package com.puresoltechnologies.toolshed.client.parts;

import java.net.URL;
import java.util.Optional;

import com.puresoltechnologies.javafx.perspectives.PartHeaderToolBar;
import com.puresoltechnologies.javafx.perspectives.parts.AbstractViewer;
import com.puresoltechnologies.javafx.perspectives.parts.PartOpenMode;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class SakeServersPart extends AbstractViewer {

    private URL url = null;

    private BorderPane borderPane = null;

    public SakeServersPart() {
	super("Sake Servers", PartOpenMode.AUTO_ONLY);
    }

    @Override
    public void initialize() {
	borderPane = new BorderPane();
    }

    @Override
    public void close() {
	// intentionally left empty
    }

    @Override
    public Optional<PartHeaderToolBar> getToolBar() {
	return Optional.empty();
    }

    @Override
    public Node getContent() {
	return borderPane;
    }

}
