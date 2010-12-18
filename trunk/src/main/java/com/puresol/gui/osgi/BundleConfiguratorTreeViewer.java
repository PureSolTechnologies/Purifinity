package com.puresol.gui.osgi;

import java.util.ArrayList;
import java.util.List;

import javax.i18n4java.Translator;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import javax.swingx.BorderLayoutWidget;
import javax.swingx.ScrollPane;
import javax.swingx.Tree;
import javax.swingx.connect.Signal;
import javax.swingx.connect.Slot;

public class BundleConfiguratorTreeViewer extends BorderLayoutWidget implements
		TreeModel {

	private static final long serialVersionUID = -7273574448972584594L;

	private static final Translator translator = Translator
			.getTranslator(BundleConfiguratorTreeViewer.class);

	private final List<TreeModelListener> treeModelListeners = new ArrayList<TreeModelListener>();
	private BundleConfiguratorTree configuratorTree;
	private Tree tree;

	public BundleConfiguratorTreeViewer() {
		super();
		initUI();
	}

	public BundleConfiguratorTreeViewer(BundleConfiguratorTree ast) {
		super();
		initUI();
		setConfiguratorTree(ast);
	}

	private void initUI() {
		tree = new Tree(this);
		tree.setEditable(false);
		tree.setBorder(new TitledBorder(translator.i18n("Configurator Tree")));
		tree.setEditable(true);
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setShowsRootHandles(true);
		tree.connect("valueChanged", this, "valueChanged", TreePath.class);
		setCenter(new ScrollPane(tree));
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

	@Slot
	@Signal
	void valueChanged(TreePath treePath) {
		connectionManager.emitSignal("valueChanged", treePath);
	}
}
