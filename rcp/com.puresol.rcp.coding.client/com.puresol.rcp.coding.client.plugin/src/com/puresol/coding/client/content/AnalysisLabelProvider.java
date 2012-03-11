package com.puresol.coding.client.content;

import java.io.File;

import org.eclipse.jface.viewers.LabelProvider;

import com.puresol.coding.analysis.ProjectAnalyzer;

public class AnalysisLabelProvider extends LabelProvider {

    @Override
    public String getText(Object element) {
	if (element instanceof ProjectAnalyzer) {
	    return ((ProjectAnalyzer) element).getWorkspaceDirectory()
		    .getName();
	}
	return ((File) element).getName();
    }
}
