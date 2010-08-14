package com.puresol.uhura.ast;

import java.io.PrintStream;

import com.puresol.uhura.grammar.NameReferenceTable;
import com.puresol.uhura.lexer.Token;

public class ASTPrinter {

	private final PrintStream stream;
	private final NameReferenceTable nameReference;

	public ASTPrinter(PrintStream stream) {
		super();
		this.stream = stream;
		this.nameReference = null;
	}

	public ASTPrinter(PrintStream stream, NameReferenceTable nameReference) {
		super();
		this.stream = stream;
		this.nameReference = nameReference;
	}

	public void println(SyntaxTree rootNode) {
		println(rootNode, 0);
	}

	public void println(SyntaxTree node, int depth) {
		for (int i = 0; i < depth; i++) {
			stream.print("  |  ");
		}
		stream.print("  |__");
		stream.print("id: " + node.getTypeId());
		if (nameReference != null) {
			stream.print(" ");
			stream.print(nameReference.getName(node.getTypeId()));

		}
		Token token = node.getToken();
		if (token != null) {
			stream.print(" ");
			stream.print(token.toString());
		}
		stream.println();
		for (SyntaxTree childNode : node.getChildren()) {
			println(childNode, depth + 1);
		}
	}
}
