package com.puresol.gui;

import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import javax.swingx.Tree;
import javax.swingx.connect.Signal;
import javax.swingx.connect.Slot;

/**
 * This is a special panel which implements a TreeModel and shows a Tree object
 * which is fed by the TreeModel of the panel. The tree to be shown is of type
 * Tree from the API package. The special implementation is specified by a
 * generic.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <T>
 *            needs to have the interface Tree<T> implemented.
 */
public class TreeViewer<T extends com.puresol.trees.Tree<T>> extends Tree {

	private static final long serialVersionUID = 3032479272552076138L;

	private final TreeViewerModel<T> treeModel;

	@SuppressWarnings("unchecked")
	public TreeViewer() {
		super(new TreeViewerModel<T>());
		treeModel = (TreeViewerModel<T>) getModel();
		initUI();
	}

	@SuppressWarnings("unchecked")
	public TreeViewer(T dataTree) {
		super(new TreeViewerModel<T>());
		treeModel = (TreeViewerModel<T>) getModel();
		initUI();
		treeModel.setDataTree(dataTree);
	}

	private void initUI() {
		setEditable(false);
		getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		setShowsRootHandles(true);

		connect("valueChanged", this, "sendSelectedValue", TreePath.class);
	}

	public T getSelection() {
		TreePath path = getSelectionPath();
		@SuppressWarnings("unchecked")
		T t = (T) path.getLastPathComponent();
		return t;
	}

	@Signal
	public void valueChanged(T t) {
		connectionManager.emitSignal("valueChanged", t);
	}

	@Slot
	public void sendSelectedValue(TreePath treePath) {
		@SuppressWarnings("unchecked")
		T t = (T) treePath.getLastPathComponent();
		valueChanged(t);
	}

	public void setDataTree(T dataTree) {
		treeModel.setDataTree(dataTree);
	}

}
