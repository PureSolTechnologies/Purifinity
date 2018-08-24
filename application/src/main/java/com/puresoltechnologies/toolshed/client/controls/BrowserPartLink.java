package com.puresoltechnologies.toolshed.client.controls;

import java.net.MalformedURLException;
import java.net.URL;

import com.puresoltechnologies.javafx.perspectives.PerspectiveService;
import com.puresoltechnologies.toolshed.client.parts.BrowserPart;

import javafx.scene.control.Hyperlink;

public class BrowserPartLink extends Hyperlink {

    private URL url;

    public BrowserPartLink(URL url) {
	super(url.toString());
	this.url = url;
	initialize();
    }

    private void initialize() {
	textProperty().addListener((component, oldValue, newValue) -> {
	    try {
		url = new URL(newValue);
	    } catch (MalformedURLException e) {
		throw new IllegalArgumentException(e);
	    }
	});
	setOnAction(event -> {
	    BrowserPart browserPart = new BrowserPart(url);
	    PerspectiveService.openPart(browserPart);
	});
    }

    public void setText(URL url) {
	super.setText(url.toString());
    }

}
