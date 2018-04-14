package com.puresoltechnologies.debugging.client.perspectives;

import java.io.IOException;

import com.puresoltechnologies.debugging.client.parts.OpenProfileViewer;
import com.puresoltechnologies.debugging.client.parts.ProfileParetoViewer;
import com.puresoltechnologies.javafx.perspectives.AbstractPerspective;
import com.puresoltechnologies.javafx.perspectives.PartSplit;
import com.puresoltechnologies.javafx.perspectives.PartStack;
import com.puresoltechnologies.javafx.perspectives.PerspectiveElement;
import com.puresoltechnologies.javafx.utils.ResourceUtils;

import javafx.scene.image.Image;

public class ProfilerPerspective extends AbstractPerspective {

    private static final long serialVersionUID = -6687771688159189919L;

    public ProfilerPerspective() {
	super("Profiler");
    }

    @Override
    public Image getImage() {
	try {
	    return ResourceUtils.getImage(this, "/icons/FatCow_Icons16x16/profiles.png");
	} catch (IOException e) {
	    return null;
	}
    }

    @Override
    public PerspectiveElement createContent() {
	PartSplit container = new PartSplit();

	PartStack left = new PartStack();
	left.openPart(new OpenProfileViewer());
	container.addElement(left);

	PartStack right = new PartStack();
	right.openPart(new ProfileParetoViewer());
	container.addElement(right);
	container.setDividerPosition(0.5);
	return container;
    }

}
