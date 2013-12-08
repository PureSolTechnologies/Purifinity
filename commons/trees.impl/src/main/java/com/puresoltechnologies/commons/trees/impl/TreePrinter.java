package com.puresoltechnologies.commons.trees.impl;

import java.io.PrintStream;

/**
 * This class prints a tree to a specified PrintStream.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TreePrinter {

	private final PrintStream stream;

	public TreePrinter(PrintStream stream) {
		super();
		this.stream = stream;
	}

	public void println(Tree<? extends Tree<?>> rootNode) {
		println(rootNode, 0);
	}

	@SuppressWarnings("unchecked")
	public void println(Tree<? extends Tree<?>> node, int depth) {
		for (int i = 0; i < depth; i++) {
			stream.print("  |  ");
		}
		stream.print("  |__");
		stream.print("id: " + node.toString());
		stream.println();
		for (Tree<?> childNode : node.getChildren()) {
			println((Tree<? extends Tree<?>>) childNode, depth + 1);
		}
	}
}
