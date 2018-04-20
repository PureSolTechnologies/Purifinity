package com.puresoltechnologies.toolshed.client.parts;

import java.net.URL;
import java.util.Optional;

import com.puresoltechnologies.javafx.perspectives.PartHeaderToolBar;
import com.puresoltechnologies.javafx.perspectives.parts.AbstractViewer;
import com.puresoltechnologies.javafx.perspectives.parts.PartOpenMode;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class BrowserPart extends AbstractViewer {

    private static final long serialVersionUID = 8657679402319658639L;

    private URL url = null;

    private BorderPane borderPane = null;
    private TextField urlField = null;
    private WebView webView = null;
    private WebEngine webEngine = null;

    public BrowserPart() {
	super("Browser", PartOpenMode.AUTO_ONLY);
    }

    public BrowserPart(URL url) {
	super("Browser - " + url.toString(), PartOpenMode.AUTO_ONLY);
	this.url = url;
    }

    @Override
    public void initialize() {
	HBox urlBar = new HBox();
	Label urlLabel = new Label("URL:");
	urlField = new TextField();
	urlField.setEditable(false);
	urlBar.getChildren().addAll(urlLabel, urlField);
	borderPane = new BorderPane();
	webView = new WebView();
	webEngine = webView.getEngine();
	borderPane.setTop(urlBar);
	borderPane.setCenter(webView);
	if (url != null) {
	    browseTo(url);
	}
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

    private void browseTo(URL url) {
	this.url = url;
	urlField.setText(url.toString());
	webEngine.load(url.toString());
    }

}
