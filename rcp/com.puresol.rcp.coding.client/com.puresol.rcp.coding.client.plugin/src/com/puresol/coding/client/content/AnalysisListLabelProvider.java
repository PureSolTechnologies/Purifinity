package com.puresol.coding.client.content;

import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.LabelProvider;

import com.puresol.coding.analysis.api.AnalysisInformation;

/**
 * This is a simple label provider for AnalysisInformation list controls.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisListLabelProvider extends LabelProvider {

    @Override
    public String getText(Object element) {
	AnalysisInformation information = (AnalysisInformation) element;
	SimpleDateFormat format = new SimpleDateFormat();
	return information.getName() + " ("
		+ format.format(information.getCreationTime()) + ")";
    }

}
