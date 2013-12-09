package com.puresoltechnologies.purifinity.client.common.analysis.contents;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.puresoltechnologies.parsers.api.ust.UniversalSyntaxTree;

public class UniversalSyntaxTreeContentProvider implements ITreeContentProvider {

	private UniversalSyntaxTree tree = null;

	@Override
	public void dispose() {
		// intentionally left empty
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		tree = (UniversalSyntaxTree) newInput;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		List<UniversalSyntaxTree> children = tree.getChildren();
		return children.toArray(new Object[children.size()]);
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof String) {
			return new Object[] { tree };
		}
		UniversalSyntaxTree parent = (UniversalSyntaxTree) parentElement;
		List<UniversalSyntaxTree> children = parent.getChildren();
		return children.toArray(new UniversalSyntaxTree[children.size()]);
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof String) {
			return null;
		}
		UniversalSyntaxTree node = (UniversalSyntaxTree) element;
		UniversalSyntaxTree parent = node.getParent();
		if (parent == null) {
			return "Parser Tree";
		}
		return parent;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof String) {
			return (tree != null);
		}
		UniversalSyntaxTree node = (UniversalSyntaxTree) element;
		return node.hasChildren();
	}

}
