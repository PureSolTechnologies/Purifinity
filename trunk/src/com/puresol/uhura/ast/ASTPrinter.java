package com.puresol.uhura.ast;

import java.io.PrintStream;

public class ASTPrinter {

	private final PrintStream stream;

	public ASTPrinter(PrintStream stream) {
		super();
		this.stream = stream;
	}

	public void println(SyntaxTree rootNode) {
		println(rootNode, 0);
	}

	public void println(SyntaxTree node, int depth) {
		for (int i = 0; i < depth; i++) {
			stream.print("  |  ");
		}
		stream.print("  |__");
		stream.print("id: " + node.toString());
		stream.println();
		for (SyntaxTree childNode : node.getChildren()) {
			println(childNode, depth + 1);
		}
	}
}
