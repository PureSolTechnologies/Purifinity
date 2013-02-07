package com.puresol.coding.richclient.application.content;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.puresol.uhura.parser.ParserTree;

public class ParserTreeContentProvider implements ITreeContentProvider {

	private ParserTree tree = null;

	@Override
	public void dispose() {
		// intentionally left empty
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		tree = (ParserTree) newInput;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		List<ParserTree> children = tree.getChildren();
		return children.toArray(new Object[children.size()]);
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof String) {
			return new Object[] { tree };
		}
		ParserTree parent = (ParserTree) parentElement;
		List<ParserTree> children = parent.getChildren();
		return children.toArray(new ParserTree[children.size()]);
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof String) {
			return null;
		}
		ParserTree node = (ParserTree) element;
		ParserTree parent = node.getParent();
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
		ParserTree node = (ParserTree) element;
		return node.hasChildren();
	}

}
