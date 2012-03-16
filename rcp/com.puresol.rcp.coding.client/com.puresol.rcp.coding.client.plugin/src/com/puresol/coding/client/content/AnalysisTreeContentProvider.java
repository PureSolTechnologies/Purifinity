package com.puresol.coding.client.content;

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
	return analyzers.toArray(new ProjectAnalyzer[analyzers.size()]);
    }

    @Override
    public Object[] getChildren(Object parentElement) {
	if (parentElement instanceof ProjectAnalyzer) {
	    ProjectAnalyzer analyzer = (ProjectAnalyzer) parentElement;
	    FileTree fileTree = analyzer.getFileTree();
	    return fileTree.getChildren().toArray();
	}
	FileTree fileTree = (FileTree) parentElement;
	return fileTree.getChildren().toArray();
    }

    @Override
    public Object getParent(Object element) {
	if ((model == null) || (element instanceof ProjectAnalyzer)) {
	    return null;
	}
	FileTree fileTree = (FileTree) element;
	for (ProjectAnalyzer analyzer : model.getAnalyzers()) {
	    if (analyzer.getFileTree().equals(fileTree)) {
		return analyzer;
	    }
	}
	return fileTree.getParent();
    }

    @Override
    public boolean hasChildren(Object element) {
	if (element instanceof ProjectAnalyzer) {
	    return true;
	}
	FileTree fileTree = (FileTree) element;
	return fileTree.hasChildren();
    }

}
