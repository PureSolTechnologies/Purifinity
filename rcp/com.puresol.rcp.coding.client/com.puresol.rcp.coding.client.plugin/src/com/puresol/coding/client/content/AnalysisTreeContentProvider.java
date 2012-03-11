package com.puresol.coding.client.content;

import java.io.File;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.puresol.coding.analysis.ProjectAnalyzer;

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
	List<ProjectAnalyzer> analyzers = model.getAnalyzers();
	return analyzers.toArray(new ProjectAnalyzer[analyzers.size()]);
    }

    @Override
    public Object[] getChildren(Object parentElement) {
	if (parentElement instanceof ProjectAnalyzer) {
	    ProjectAnalyzer analyzer = (ProjectAnalyzer) parentElement;
	    File directory = analyzer.getWorkspaceDirectory();
	    return directory.listFiles();
	}
	File directory = (File) parentElement;
	return directory.listFiles();
    }

    @Override
    public Object getParent(Object element) {
	if (element instanceof ProjectAnalyzer) {
	    return null;
	}
	File file = (File) element;
	for (ProjectAnalyzer analyzer : model.getAnalyzers()) {
	    if (analyzer.getWorkspaceDirectory().equals(file)) {
		return analyzer;
	    }
	}
	return file.getParentFile();
    }

    @Override
    public boolean hasChildren(Object element) {
	if (element instanceof ProjectAnalyzer) {
	    return true;
	}
	File directory = (File) element;
	return directory.isDirectory();
    }

}
