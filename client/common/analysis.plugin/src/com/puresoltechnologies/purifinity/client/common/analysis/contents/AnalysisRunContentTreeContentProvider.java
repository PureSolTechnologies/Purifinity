package com.puresoltechnologies.purifinity.client.common.analysis.contents;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;

public class AnalysisRunContentTreeContentProvider implements
		ITreeContentProvider {

	private AnalysisFileTree fileTree;

	@Override
	public void dispose() {
		// intentionally left blank
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		fileTree = (AnalysisFileTree) newInput;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return new String[] { fileTree.getName() };
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		List<AnalysisFileTree> children;
		if (parentElement instanceof String) {
			children = fileTree.getChildren();
		} else {
			AnalysisFileTree input = (AnalysisFileTree) parentElement;
			children = input.getChildren();
		}
		return children.toArray();
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof String) {
			return null;
		} else {
			AnalysisFileTree input = (AnalysisFileTree) element;
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
		AnalysisFileTree input = (AnalysisFileTree) element;
		return input.hasChildren();
	}

}
