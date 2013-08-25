package com.puresol.purifinity.coding.lang.c11.ust;

import java.util.ArrayList;
import java.util.List;

import com.puresol.purifinity.uhura.parser.ParserTree;
import com.puresol.purifinity.uhura.ust.CompilationUnit;
import com.puresol.purifinity.uhura.ust.UniversalSyntaxTree;
import com.puresol.purifinity.uhura.ust.UnspecialistProduction;

public class TranslationUnitCreator {

	public static CompilationUnit create(ParserTree parserTree) {
		List<UniversalSyntaxTree> ustChildren = new ArrayList<>();
		for (ParserTree child : parserTree.getChildren()) {
			ustChildren.add(createNode(child));
		}
		return new CompilationUnit(parserTree.getName(), parserTree.getText(),
				ustChildren);
	}

	private static UniversalSyntaxTree createNode(ParserTree node) {
		List<UniversalSyntaxTree> ustChildren = new ArrayList<>();
		for (ParserTree child : node.getChildren()) {
			ustChildren.add(createNode(child));
		}
		return new UnspecialistProduction(node.getName(), node.getName(),
				ustChildren);
	}
}
