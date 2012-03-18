package com.puresol.coding.client.content;

import org.eclipse.jface.viewers.LabelProvider;

import com.puresol.coding.analysis.AnalyzedFile;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.trees.FileTree;

public class AnalysisLabelProvider extends LabelProvider {

    @Override
    public String getText(Object element) {
	AnalysisNavigatorTreeNodeElement nodeElement = (AnalysisNavigatorTreeNodeElement) element;
	String text = nodeElement.getTreeNodeName();
	ProjectAnalyzer analyser = nodeElement.getAnalyser();
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
