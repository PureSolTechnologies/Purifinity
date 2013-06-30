package com.puresol.purifinity.client.common.analysis.contents;

import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.puresol.purifinity.client.common.branding.ClientImages;
import com.puresol.purifinity.coding.analysis.api.AnalysisProject;

/**
 * This is a simple label provider for AnalysisInformation list controls.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisListLabelProvider extends LabelProvider {

    @Override
    public String getText(Object element) {
	AnalysisProject project = (AnalysisProject) element;
	SimpleDateFormat format = new SimpleDateFormat();
	return project.getSettings().getName() + " ("
		+ format.format(project.getInformation().getCreationTime())
		+ ")";
    }

    @Override
    public Image getImage(Object element) {
	return ClientImages.getImage(ClientImages.ANALYSIS_16x16);

    }
}
