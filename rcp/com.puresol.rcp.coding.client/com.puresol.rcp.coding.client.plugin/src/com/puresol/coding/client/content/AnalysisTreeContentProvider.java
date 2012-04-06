package com.puresol.coding.client.content;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.puresol.coding.analysis.api.Analysis;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.trees.FileTree;

public class AnalysisTreeContentProvider implements ITreeContentProvider {

    private AnalysisNavigatorModel model = null;

    @Override
    public void dispose() {
	// intentionally left empty
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	model = (AnalysisNavigatorModel) newInput;
    }

    @Override
    public Object[] getElements(Object inputElement) {
	if (model == null) {
	    return new Object[0];
	}
	List<Analysis> analyses = model.getAnalyses();
	List<AnalysisNavigatorTreeNodeElement> elements = new ArrayList<AnalysisNavigatorTreeNodeElement>();
	for (Analysis analysis : analyses) {
	    AnalysisRun loadLastAnalysisRun = analysis.loadLastAnalysisRun();
	    if (loadLastAnalysisRun != null) {
		elements.add(new AnalysisNavigatorTreeNodeElement(null,
			analysis, loadLastAnalysisRun.getFileTree(), analysis
				.getInformation().getName()));
	    }
	}
	return elements.toArray(new AnalysisNavigatorTreeNodeElement[elements
		.size()]);
    }

    @Override
    public Object[] getChildren(Object parentElement) {
	AnalysisNavigatorTreeNodeElement nodeElement = (AnalysisNavigatorTreeNodeElement) parentElement;
	Analysis analysis = nodeElement.getAnalysis();
	FileTree fileTree = nodeElement.getSourceFile();
	List<AnalysisNavigatorTreeNodeElement> elements = new ArrayList<AnalysisNavigatorTreeNodeElement>();
	for (FileTree child : fileTree.getChildren()) {
	    elements.add(new AnalysisNavigatorTreeNodeElement(nodeElement,
		    analysis, child, child.getName()));
	}
	return elements.toArray(new AnalysisNavigatorTreeNodeElement[elements
		.size()]);
    }

    @Override
    public Object getParent(Object element) {
	AnalysisNavigatorTreeNodeElement nodeElement = (AnalysisNavigatorTreeNodeElement) element;
	return nodeElement.getParent();
    }

    @Override
    public boolean hasChildren(Object element) {
	AnalysisNavigatorTreeNodeElement nodeElement = (AnalysisNavigatorTreeNodeElement) element;
	return nodeElement.getSourceFile().hasChildren();
    }

}
