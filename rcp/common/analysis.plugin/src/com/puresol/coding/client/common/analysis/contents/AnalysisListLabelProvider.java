package com.puresol.coding.client.common.analysis.contents;

import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.puresol.coding.analysis.api.AnalysisProject;
import com.puresol.coding.client.common.analysis.Activator;
import com.puresol.coding.client.common.ui.ClientImages;

/**
 * This is a simple label provider for AnalysisInformation list controls.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisListLabelProvider extends LabelProvider {

	private final Image analysisImage = Activator.getDefault()
			.getImageRegistry().get(ClientImages.ANALYSIS_16x16);

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
		return analysisImage;

	}
}
