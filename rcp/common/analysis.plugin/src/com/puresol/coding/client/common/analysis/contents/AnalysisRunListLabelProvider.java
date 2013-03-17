package com.puresol.coding.client.common.analysis.contents;

import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.puresol.coding.analysis.api.AnalysisRunInformation;
import com.puresol.coding.client.common.analysis.Activator;
import com.puresol.coding.client.common.ui.ClientImages;

/**
 * This is a simple label provider for AnalysisRunInformation list controls.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisRunListLabelProvider extends LabelProvider {

	private final Image analysisRunImage = Activator.getDefault()
			.getImageRegistry().get(ClientImages.ANALYSIS_RUN_16x16);

	@Override
	public String getText(Object element) {
		AnalysisRunInformation information = (AnalysisRunInformation) element;
		SimpleDateFormat format = new SimpleDateFormat();
		return format.format(information.getStartTime());
	}

	@Override
	public Image getImage(Object element) {
		return analysisRunImage;

	}

}
