package com.puresol.coding.client.content;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.puresol.coding.analysis.api.HashIdFileTree;

public class AnalysisContentTreeContentProvider implements ITreeContentProvider {

    private HashIdFileTree fileTree;

    @Override
    public void dispose() {
	// intentionally left blank
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	fileTree = (HashIdFileTree) newInput;
    }

    @Override
    public Object[] getElements(Object inputElement) {
	return new String[] { fileTree.getName() };
    }

    @Override
    public Object[] getChildren(Object parentElement) {
	List<HashIdFileTree> children;
	if (parentElement instanceof String) {
	    children = fileTree.getChildren();
	} else {
	    HashIdFileTree input = (HashIdFileTree) parentElement;
	    children = input.getChildren();
	}
	return children.toArray();
    }

    @Override
    public Object getParent(Object element) {
	if (element instanceof String) {
	    return null;
	} else {
	    HashIdFileTree input = (HashIdFileTree) element;
	    if (input.getParent() == null) {
		return fileTree.getName();
	    }
	    return input.getParent();
	}
    }

    @Override
    public boolean hasChildren(Object element) {
	if (element instanceof String) {
	    return fileTree.hasChildren();
	}
	HashIdFileTree input = (HashIdFileTree) element;
	return input.hasChildren();
    }

}
