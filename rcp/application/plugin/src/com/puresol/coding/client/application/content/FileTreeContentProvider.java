package com.puresol.coding.client.application.content;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.puresol.trees.FileTree;

public class FileTreeContentProvider implements ITreeContentProvider {

	private FileTree fileTree;

	@Override
	public void dispose() {
		// intentionally left blank
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		fileTree = (FileTree) newInput;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return new String[] { fileTree.getName() };
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		List<FileTree> children;
		if (parentElement instanceof String) {
			children = fileTree.getChildren();
		} else {
			FileTree input = (FileTree) parentElement;
			children = input.getChildren();
		}
		return children.toArray();
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof String) {
			return null;
		} else {
			FileTree input = (FileTree) element;
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
		FileTree input = (FileTree) element;
		return input.hasChildren();
	}

}
