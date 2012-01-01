package com.puresol.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class TreeViewerModel<T extends com.puresol.trees.Tree<T>> implements
		TreeModel {

	private final List<TreeModelListener> treeModelListeners = new ArrayList<TreeModelListener>();
	private T dataTree;

	public void setDataTree(T dataTree) {
		this.dataTree = dataTree;
		refresh();
	}

	public void refresh() {
		for (TreeModelListener l : treeModelListeners) {
			l.treeStructureChanged(new TreeModelEvent(this,
					new Object[] { dataTree }));
		}
	}

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		treeModelListeners.add(l);
	}

	@Override
	public Object getChild(Object parent, int index) {
		@SuppressWarnings("unchecked")
		T t = (T) parent;
		return t.getChildren().get(index);
	}

	@Override
	public int getChildCount(Object parent) {
		@SuppressWarnings("unchecked")
		T t = (T) parent;
		return t.getChildren().size();
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		@SuppressWarnings("unchecked")
		T t = (T) parent;
		return t.getChildren().indexOf(child);
	}

	@Override
	public Object getRoot() {
		return dataTree;
	}

	@Override
	public boolean isLeaf(Object node) {
		@SuppressWarnings("unchecked")
		T t = (T) node;
		return t.getChildren().size() == 0;
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		treeModelListeners.remove(l);
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		for (TreeModelListener l : treeModelListeners) {
			l.treeNodesChanged(new TreeModelEvent(this, path));
		}
	}

}
