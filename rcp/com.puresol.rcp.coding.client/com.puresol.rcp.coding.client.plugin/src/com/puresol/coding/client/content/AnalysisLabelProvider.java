package com.puresol.coding.client.content;

import org.eclipse.jface.viewers.LabelProvider;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.trees.FileTree;

public class AnalysisLabelProvider extends LabelProvider {

    @Override
    public String getText(Object element) {
	if (element == null)
	    return "NULL!!!";
	if (element instanceof ProjectAnalyzer) {
	    return ((ProjectAnalyzer) element).getWorkspaceDirectory()
		    .getName();
	}
	return ((FileTree) element).getName();
    }
}
