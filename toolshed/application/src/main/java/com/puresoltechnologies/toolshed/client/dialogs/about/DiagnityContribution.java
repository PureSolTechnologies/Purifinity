package com.puresoltechnologies.toolshed.client.dialogs.about;

import java.io.IOException;
import java.util.Optional;

import com.puresoltechnologies.javafx.extensions.dialogs.AboutDialogContribution;
import com.puresoltechnologies.javafx.utils.ResourceUtils;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;

public class DiagnityContribution implements AboutDialogContribution {

    @Override
    public String getName() {
	return "Diagnity";
    }

    @Override
    public Optional<Image> getImage() {
	try {
	    return Optional.of(ResourceUtils.getImage(this, "/puresol-logo-green_small.png"));
	} catch (IOException e) {
	    return Optional.empty();
	}
    }

    @Override
    public Node getContent() {
	return new TextArea(
		"PureSol Technologies' Diagnity\n(c) 2018 PureSol Technologies (http://puresol-technologies.com)");
    }

}
