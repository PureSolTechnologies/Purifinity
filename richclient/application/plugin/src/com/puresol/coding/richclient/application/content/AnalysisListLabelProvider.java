package com.puresol.coding.richclient.application.content;

import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ResourceManager;

import com.puresol.coding.analysis.api.AnalysisInformation;
import com.puresol.coding.richclient.application.ClientImages;

/**
 * This is a simple label provider for AnalysisInformation list controls.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisListLabelProvider extends LabelProvider {

	private static final Image analysisImage = ResourceManager.getPluginImage(
			"com.puresol.coding.richclient.application.plugin",
			ClientImages.ANALYSIS_16x16);

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
