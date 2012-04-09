package com.puresol.coding.client.content;

import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.puresol.coding.analysis.api.AnalysisInformation;
import com.puresol.coding.client.Activator;
import com.puresol.coding.client.ClientImages;

/**
 * This is a simple label provider for AnalysisInformation list controls.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisListLabelProvider extends LabelProvider {

    private final Image folderImage = Activator.getDefault().getImageRegistry()
	    .get(ClientImages.FOLDER);

    @Override
    public String getText(Object element) {
	AnalysisInformation information = (AnalysisInformation) element;
	SimpleDateFormat format = new SimpleDateFormat();
	return information.getName() + " ("
		+ format.format(information.getCreationTime()) + ")";
    }

    @Override
    public Image getImage(Object element) {
	return folderImage;

    }
}
