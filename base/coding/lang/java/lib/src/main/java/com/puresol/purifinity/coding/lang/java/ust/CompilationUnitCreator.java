package com.puresol.purifinity.coding.lang.java.ust;

import java.util.ArrayList;
import java.util.List;

import com.puresol.purifinity.uhura.parser.ParserTree;
import com.puresol.purifinity.uhura.ust.CompilationUnit;
import com.puresol.purifinity.uhura.ust.Production;
import com.puresol.purifinity.uhura.ust.UniversalSyntaxTree;

public class CompilationUnitCreator {

	public static CompilationUnit create(ParserTree parserTree) {
		List<UniversalSyntaxTree> ustChildren = new ArrayList<>();
		for (ParserTree child : parserTree.getChildren()) {
			ustChildren.add(createNode(child));
		}
		return new CompilationUnit(parserTree.getName(), ustChildren);
	}

	private static UniversalSyntaxTree createNode(ParserTree node) {
		List<UniversalSyntaxTree> ustChildren = new ArrayList<>();
		for (ParserTree child : node.getChildren()) {
			ustChildren.add(createNode(child));
		}
		return new Production(node.getName(), node.getName(), ustChildren);
	}
}
