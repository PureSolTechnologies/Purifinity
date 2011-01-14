package com.puresol.gui;

import javax.i18n4java.Translator;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.puresol.utils.Property;
import com.puresol.utils.PropertyHandler;

public class PropertyEditor extends JPanel implements TreeSelectionListener {

	private static final long serialVersionUID = 7873839972939582017L;

	private static final Translator translator = Translator
			.getTranslator(PropertyEditor.class);

	private final PropertyHandler propertyHandler;

	private JTree tree;
	private InputField content;
	private TreePath oldPath = null;

	public PropertyEditor(PropertyHandler propertyHandler) {
		super();
		this.propertyHandler = propertyHandler.clone();
		initUI();
	}

	private void initUI() {
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

		DefaultMutableTreeNode top = new DefaultMutableTreeNode(
				translator.i18n("Properties"));
		addTreeNodes(top);
		tree = new JTree(top);
		tree.addTreeSelectionListener(this);

		content = new InputField();

		add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, new JScrollPane(
				tree), new JScrollPane(content)));
	}

	private void addTreeNodes(DefaultMutableTreeNode top) {
		for (Property property : propertyHandler.getProperties()) {
			addProperty(top, property, 0);
		}
	}

	private void addProperty(DefaultMutableTreeNode node, Property property,
			int currentPosition) {
		String splits[] = property.getName().split("\\.");
		if (splits.length <= currentPosition) {
			return;
		}
		for (int index = 0; index < node.getChildCount(); index++) {
			if (splits[currentPosition].equals(node.getChildAt(index)
					.toString())) {
				addProperty((DefaultMutableTreeNode) node.getChildAt(index),
						property, currentPosition + 1);
				return;
			}
		}
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(
				splits[currentPosition]);
		node.add(newNode);
		addProperty(newNode, property, currentPosition + 1);
	}

	public PropertyHandler getPropertyHandler() {
		return propertyHandler;
	}

	public void selectionChanged(TreePath treePath) {
		if (treePath == null) {
			content.setType(null);
			return;
		}
		if (oldPath != null) {
			String property = convertPathToPropertyName(oldPath);
			propertyHandler
					.setProperty(property, content.getValue().toString());
			oldPath = null;
		}
		if (!((TreeNode) treePath.getLastPathComponent()).isLeaf()) {
			content.setType(null);
			return;
		}
		String property = convertPathToPropertyName(treePath);
		content.setValue(propertyHandler.getPropertyDefinition(property)
				.getType(), propertyHandler.getProperty(property));
		oldPath = treePath;
	}

	private String convertPathToPropertyName(TreePath path) {
		String property = "";
		for (Object o : path.getPath()) {
			if (!property.isEmpty()) {
				property += ".";
			}
			property += o.toString();
		}
		property = property.substring(property.indexOf(".") + 1);
		return property;
	}

	@Override
	public void valueChanged(TreeSelectionEvent o) {
		if (o.getSource() == tree) {
			selectionChanged(o.getPath());
		}
	}
}
