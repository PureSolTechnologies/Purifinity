package com.puresol.coding.richclient.application.content;

import java.text.SimpleDateFormat;

import javax.inject.Inject;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.puresol.coding.analysis.api.AnalysisInformation;
import com.puresol.coding.richclient.application.ClientImages;

/**
 * This is a simple label provider for AnalysisInformation list controls.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisListLabelProvider extends LabelProvider {

	@Inject
	private ImageRegistry imageRegistry;
	private final Image analysisImage = imageRegistry
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
