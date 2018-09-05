package com.puresoltechnologies.toolshed.client.perspectives;

import java.io.IOException;

import com.puresoltechnologies.javafx.perspectives.AbstractPerspective;
import com.puresoltechnologies.javafx.perspectives.PartSplit;
import com.puresoltechnologies.javafx.perspectives.PartStack;
import com.puresoltechnologies.javafx.perspectives.PerspectiveElement;
import com.puresoltechnologies.javafx.perspectives.parts.TaskProgressViewer;
import com.puresoltechnologies.javafx.utils.ResourceUtils;
import com.puresoltechnologies.toolshed.client.parts.SakeServersPart;

import javafx.scene.image.Image;

public class SakeMonitoringPerspective extends AbstractPerspective {

    public SakeMonitoringPerspective() {
	super("Sake Monitoring");
    }

    @Override
    public Image getImage() {
	try {
	    return ResourceUtils.getImage(this, "/icons/FatCow_Icons16x16/speedometer.png");
	} catch (IOException e) {
	    return null;
	}
    }

    @Override
    public PerspectiveElement createContent() {
	PartSplit container = new PartSplit();

	container.setDividerPosition(0.5);

	PartStack leftStack = new PartStack();
	leftStack.openPart(new SakeServersPart());
	container.addElement(leftStack);

	PartStack rightStack = new PartStack();
	rightStack.openPart(new TaskProgressViewer());
	container.addElement(rightStack);

	return container;
    }

}
