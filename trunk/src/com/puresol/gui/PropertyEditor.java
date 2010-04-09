package com.puresol.gui;

import javax.i18n4j.Translator;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swingx.Panel;
import javax.swingx.ScrollPane;
import javax.swingx.Tree;
import javax.swingx.connect.Slot;

import com.puresol.utils.Property;
import com.puresol.utils.PropertyHandler;

public class PropertyEditor extends Panel {

    private static final long serialVersionUID = 7873839972939582017L;

    private static final Translator translator = Translator
	    .getTranslator(PropertyEditor.class);

    private final PropertyHandler propertyHandler;

    private Tree tree;
    private InputField content;
    private TreePath oldPath = null;

    public PropertyEditor(PropertyHandler propertyHandler) {
	super();
	this.propertyHandler = propertyHandler.clone();
	initUI();
    }

    private void initUI() {
	setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

	DefaultMutableTreeNode top = new DefaultMutableTreeNode(translator
		.i18n("Properties"));
	addTreeNodes(top);
	tree = new Tree(top);
	tree.connect("valueChanged", this, "selectionChanged");

	content = new InputField();

	add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, new ScrollPane(
		tree), new ScrollPane(content)));
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

    @Slot
    public void selectionChanged() {
	TreePath path = tree.getSelectionPath();
	if (path == null) {
	    content.setType(null);
	    return;
	}
	if (oldPath != null) {
	    String property = convertPathToPropertyName(oldPath);
	    propertyHandler
		    .setProperty(property, content.getValue().toString());
	    oldPath = null;
	}
	if (!((TreeNode) path.getLastPathComponent()).isLeaf()) {
	    content.setType(null);
	    return;
	}
	String property = convertPathToPropertyName(path);
	content.setValue(propertyHandler.getPropertyDefinition(property)
		.getType(), propertyHandler.getProperty(property));
	oldPath = path;
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
}
