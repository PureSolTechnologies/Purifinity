package com.puresol.coding.client.common.analysis.contents;

import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.puresol.coding.analysis.api.AnalysisRunInformation;
import com.puresol.coding.client.common.branding.ClientImages;

/**
 * This is a simple label provider for AnalysisRunInformation list controls.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisRunListLabelProvider extends LabelProvider {

    @Override
    public String getText(Object element) {
	AnalysisRunInformation information = (AnalysisRunInformation) element;
	SimpleDateFormat format = new SimpleDateFormat();
	return format.format(information.getStartTime());
    }

    @Override
    public Image getImage(Object element) {
	return ClientImages.getImage(ClientImages.ANALYSIS_RUN_16x16);
    }

}
