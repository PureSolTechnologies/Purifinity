package com.puresol.coding.client.content;

import org.eclipse.jface.viewers.LabelProvider;

import com.puresol.coding.analysis.api.Analysis;
import com.puresol.coding.analysis.api.AnalyzedFile;
import com.puresol.trees.FileTree;

public class AnalysisLabelProvider extends LabelProvider {

    @Override
    public String getText(Object element) {
	AnalysisNavigatorTreeNodeElement nodeElement = (AnalysisNavigatorTreeNodeElement) element;
	String text = nodeElement.getTreeNodeName();
	Analysis analyser = nodeElement.getAnalysis();
	FileTree sourceFile = nodeElement.getSourceFile();
	AnalyzedFile analyzedFile = analyser
		.findAnalyzedFileBySourceFile(sourceFile.getPathFile(true));
	if (analyzedFile != null) {
	    text += " [ok]";
	} else {
	    if (sourceFile.getPathFile(true).isFile())
		text += " [failed]";
	    else
		text += " [dir]";
	}
	return text;
    }
}
