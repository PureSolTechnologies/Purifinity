package com.puresol.coding.client.content;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.puresol.coding.analysis.ProjectAnalyzer;
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
	List<ProjectAnalyzer> analyzers = model.getAnalyzers();
	List<AnalysisNavigatorTreeNodeElement> elements = new ArrayList<AnalysisNavigatorTreeNodeElement>();
	for (ProjectAnalyzer analyzer : analyzers) {
	    elements.add(new AnalysisNavigatorTreeNodeElement(null, analyzer,
		    analyzer.getFileTree(), analyzer.getName()));
	}
	return elements.toArray(new AnalysisNavigatorTreeNodeElement[elements
		.size()]);
    }

    @Override
    public Object[] getChildren(Object parentElement) {
	AnalysisNavigatorTreeNodeElement nodeElement = (AnalysisNavigatorTreeNodeElement) parentElement;
	ProjectAnalyzer analyzer = nodeElement.getAnalyser();
	FileTree fileTree = nodeElement.getSourceFile();
	List<AnalysisNavigatorTreeNodeElement> elements = new ArrayList<AnalysisNavigatorTreeNodeElement>();
	for (FileTree child : fileTree.getChildren()) {
	    elements.add(new AnalysisNavigatorTreeNodeElement(nodeElement,
		    analyzer, child, child.getName()));
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
