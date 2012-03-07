package com.puresol.gui;

import javax.swing.JTree;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

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
public class TreeViewer<T extends com.puresol.trees.Tree<T>> extends JTree {

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
    }

    public T getSelection() {
	TreePath path = getSelectionPath();
	if (path == null) {
	    return null;
	}
	@SuppressWarnings("unchecked")
	T t = (T) path.getLastPathComponent();
	return t;
    }

    public void setTreeData(T dataTree) {
	treeModel.setDataTree(dataTree);
    }

}
