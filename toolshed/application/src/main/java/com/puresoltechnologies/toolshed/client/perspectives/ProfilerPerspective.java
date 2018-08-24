package com.puresoltechnologies.toolshed.client.perspectives;

import java.io.IOException;

import com.puresoltechnologies.javafx.perspectives.AbstractPerspective;
import com.puresoltechnologies.javafx.perspectives.PartSplit;
import com.puresoltechnologies.javafx.perspectives.PartStack;
import com.puresoltechnologies.javafx.perspectives.PerspectiveElement;
import com.puresoltechnologies.javafx.utils.ResourceUtils;
import com.puresoltechnologies.toolshed.client.parts.MethodProfileTreeGraphViewer;
import com.puresoltechnologies.toolshed.client.parts.MethodProfileTreeTableViewer;
import com.puresoltechnologies.toolshed.client.parts.OpenProfileViewer;
import com.puresoltechnologies.toolshed.client.parts.ProfileParetoTableViewer;

import javafx.geometry.Orientation;
import javafx.scene.image.Image;

public class ProfilerPerspective extends AbstractPerspective {

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

	PartSplit leftSplit = new PartSplit(Orientation.VERTICAL);
	PartSplit rightSplit = new PartSplit(Orientation.VERTICAL);

	container.addElement(leftSplit);
	container.addElement(rightSplit);
	container.setDividerPosition(0.5);

	PartStack upperLeftStack = new PartStack();
	upperLeftStack.openPart(new OpenProfileViewer());
	leftSplit.addElement(upperLeftStack);

	PartStack lowerLeftStack = new PartStack();
	lowerLeftStack.openPart(new ProfileParetoTableViewer());
	leftSplit.addElement(lowerLeftStack);
	leftSplit.setDividerPosition(0.25);

	PartStack upperRightStack = new PartStack();
	upperRightStack.openPart(new MethodProfileTreeTableViewer());
	rightSplit.addElement(upperRightStack);

	PartStack lowerRightStack = new PartStack();
	lowerRightStack.openPart(new MethodProfileTreeGraphViewer());
	rightSplit.addElement(lowerRightStack);
	rightSplit.setDividerPosition(0.5);

	return container;
    }

}
