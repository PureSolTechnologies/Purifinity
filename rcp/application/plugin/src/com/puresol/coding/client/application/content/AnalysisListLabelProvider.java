package com.puresol.coding.client.application.content;

import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.puresol.coding.analysis.api.AnalysisInformation;
import com.puresol.coding.client.application.Activator;
import com.puresol.coding.client.application.ClientImages;

/**
 * This is a simple label provider for AnalysisInformation list controls.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisListLabelProvider extends LabelProvider {

    private final Image analysisImage = Activator.getDefault().getImageRegistry()
	    .get(ClientImages.ANALYSIS_16x16);

    @Override
    public String getText(Object element) {
	AnalysisInformation information = (AnalysisInformation) element;
	SimpleDateFormat format = new SimpleDateFormat();
	return information.getName() + " ("
		+ format.format(information.getCreationTime()) + ")";
    }

    @Override
    public Image getImage(Object element) {
	return analysisImage;

    }
}
