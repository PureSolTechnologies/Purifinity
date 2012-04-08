package com.puresol.coding.client.content;

import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.LabelProvider;

import com.puresol.coding.analysis.api.AnalysisRunInformation;

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
	return format.format(information.getTime());
    }

}
