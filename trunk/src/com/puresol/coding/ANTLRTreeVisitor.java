/***************************************************************************
 *
 *   ANTLRTreeVisitor.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding;

import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

public class ANTLRTreeVisitor {

	private CommonTree tree = null;

	public ANTLRTreeVisitor(CommonTree tree) {
		this.tree = tree;
	}

	public CommonTree getTree() {
		return tree;
	}

	private void print(Tree tree, int layer) {
		for (int child = 0; child < tree.getChildCount(); child++) {
			for (int index = 0; index < layer - 1; index++) {
				System.out.print("|");
			}
			Tree childTree = tree.getChild(child);
			System.out.print("\\");
			System.out.print("-");
			System.out.println(childTree.getText());
			print(childTree, layer + 1);
		}
	}

	public void print() {
		print(tree, 1);
	}
}
