package com.puresol.gui.osgi;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class BundleConfiguratorTreeViewer extends JTree implements TreeModel {

	private static final long serialVersionUID = -7273574448972584594L;

	private final List<TreeModelListener> treeModelListeners = new ArrayList<TreeModelListener>();
	private BundleConfiguratorTree configuratorTree;

	public BundleConfiguratorTreeViewer() {
		super();
		this.setModel(this);
		initUI();
	}

	public BundleConfiguratorTreeViewer(BundleConfiguratorTree ast) {
		super();
		this.setModel(this);
		initUI();
		setConfiguratorTree(ast);
	}

	private void initUI() {
		setEditable(false);
		setEditable(true);
		getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		setShowsRootHandles(true);
	}

	public void setConfiguratorTree(BundleConfiguratorTree parserTree) {
		this.configuratorTree = parserTree;
		refresh();
	}

	public void refresh() {
		for (TreeModelListener l : treeModelListeners) {
			l.treeStructureChanged(new TreeModelEvent(this,
					new Object[] { configuratorTree }));
		}
	}

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		treeModelListeners.add(l);
	}

	@Override
	public Object getChild(Object parent, int index) {
		return ((BundleConfiguratorTree) parent).getChildren().get(index);
	}

	@Override
	public int getChildCount(Object parent) {
		return ((BundleConfiguratorTree) parent).getChildren().size();
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		return ((BundleConfiguratorTree) parent).getChildren().indexOf(child);
	}

	@Override
	public Object getRoot() {
		return configuratorTree;
	}

	@Override
	public boolean isLeaf(Object node) {
		return ((BundleConfiguratorTree) node).getChildren().size() == 0;
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
