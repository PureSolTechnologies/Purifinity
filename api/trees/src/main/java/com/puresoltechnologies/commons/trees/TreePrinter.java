package com.puresoltechnologies.commons.trees;

import java.io.PrintStream;

/**
 * This class prints a tree to a specified PrintStream.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TreePrinter {

	private final PrintStream stream;

	/**
	 * Constructor to instantiate this class.
	 * 
	 * @param stream
	 *            is the {@link PrintStream} to which trees are printed to.
	 */
	public TreePrinter(PrintStream stream) {
		super();
		this.stream = stream;
	}

	/**
	 * This method is used to print the tree provided into the
	 * {@link PrintStream} set in the constructor
	 * {@link #TreePrinter(PrintStream)}.
	 * 
	 * @param tree
	 *            is the {@link Tree} object to be printed.
	 */
	public <T extends Tree<T>> void println(T tree) {
		println(tree, 0);
	}

	private <T extends Tree<T>> void println(T node, int depth) {
		for (int i = 0; i < depth; i++) {
			stream.print("  |  ");
		}
		stream.print("  |__");
		stream.print("id: " + node.toString());
		stream.println();
		for (T childNode : node.getChildren()) {
			println(childNode, depth + 1);
		}
	}
}
