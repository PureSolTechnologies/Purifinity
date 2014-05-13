package com.puresoltechnologies.commons.trees.api;

import java.util.ArrayList;
import java.util.List;

import com.puresoltechnologies.commons.trees.Tree;

/**
 * This class is a simple Tree implementation for testing tree utility classes.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TreeImpl implements Tree<TreeImpl> {

	public static TreeImpl getSampleTree() {
		TreeImpl root = new TreeImpl("root");

		TreeImpl n1 = new TreeImpl(root, "n1");
		root.addChild(n1);

		TreeImpl n2 = new TreeImpl(root, "n2");
		root.addChild(n2);

		TreeImpl n3 = new TreeImpl(root, "n3");
		root.addChild(n3);

		TreeImpl n11 = new TreeImpl(n1, "n11");
		n1.addChild(n11);

		TreeImpl n12 = new TreeImpl(n1, "n12");
		n1.addChild(n12);

		TreeImpl n13 = new TreeImpl(n1, "n13");
		n1.addChild(n13);

		TreeImpl n21 = new TreeImpl(n2, "n21");
		n2.addChild(n21);

		TreeImpl n22 = new TreeImpl(n2, "n22");
		n2.addChild(n22);

		TreeImpl n23 = new TreeImpl(n2, "n23");
		n2.addChild(n23);

		TreeImpl n31 = new TreeImpl(n3, "n31");
		n3.addChild(n31);

		TreeImpl n32 = new TreeImpl(n3, "n32");
		n3.addChild(n32);

		TreeImpl n33 = new TreeImpl(n3, "n33");
		n3.addChild(n33);

		return root;
	}

	private final String name;
	private final TreeImpl parent;
	private final List<TreeImpl> children = new ArrayList<TreeImpl>();

	public TreeImpl(String name) {
		super();
		this.name = name;
		this.parent = null;
	}

	public TreeImpl(TreeImpl parent, String name) {
		super();
		this.name = name;
		this.parent = parent;
	}

	@Override
	public TreeImpl getParent() {
		return parent;
	}

	public void addChild(TreeImpl tree) {
		children.add(tree);
	}

	@Override
	public boolean hasChildren() {
		return !children.isEmpty();
	}

	@Override
	public List<TreeImpl> getChildren() {
		return children;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
