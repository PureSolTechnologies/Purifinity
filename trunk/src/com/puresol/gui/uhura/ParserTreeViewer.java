package com.puresol.gui.uhura;

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
import javax.swingx.Label;
import javax.swingx.ScrollPane;
import javax.swingx.Tree;

import com.puresol.uhura.ast.ParserTree;

public class ParserTreeViewer extends BorderLayoutWidget implements TreeModel {

	private static final long serialVersionUID = 3032479272552076138L;

	private static final Translator translator = Translator
			.getTranslator(ParserTreeViewer.class);

	private final List<TreeModelListener> treeModelListeners = new ArrayList<TreeModelListener>();
	private ParserTree parserTree;
	private Label information;
	private Tree tree;

	public ParserTreeViewer() {
		super();
		initUI();
	}

	public ParserTreeViewer(ParserTree ast) {
		super();
		initUI();
		setParserTree(ast);
	}

	private void initUI() {
		information = new Label();
		information.setBorder(new TitledBorder(translator.i18n("Information")));

		tree = new Tree(this);
		tree.setEditable(false);
		tree.setBorder(new TitledBorder(translator.i18n("Parser Tree")));
		tree.setEditable(true);
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setShowsRootHandles(true);

		setNorth(information);
		setCenter(new ScrollPane(tree));
	}

	public void setParserTree(ParserTree parserTree) {
		this.parserTree = parserTree;
		refresh();
	}

	public void refresh() {
		for (TreeModelListener l : treeModelListeners) {
			l.treeStructureChanged(new TreeModelEvent(this,
					new Object[] { parserTree }));
		}
		// if (parserTree == null) {
		// tree.setText("No information available yet!");
		// } else {
		// ParserTreeMetaData metaData = parserTree.getMetaData();
		// if (metaData != null) {
		// information.setText(metaData.getSourceName());
		// }
		// StringOutputStream outStream = new StringOutputStream();
		// new TreePrinter(new PrintStream(outStream)).println(parserTree);
		// tree.setText(outStream.toString());
		// }
	}

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		treeModelListeners.add(l);
	}

	@Override
	public Object getChild(Object parent, int index) {
		return ((ParserTree) parent).getChildren().get(index);
	}

	@Override
	public int getChildCount(Object parent) {
		return ((ParserTree) parent).getChildren().size();
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		return ((ParserTree) parent).getChildren().indexOf(child);
	}

	@Override
	public Object getRoot() {
		return parserTree;
	}

	@Override
	public boolean isLeaf(Object node) {
		return ((ParserTree) node).getChildren().size() == 0;
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
