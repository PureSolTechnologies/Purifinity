package com.puresoltechnologies.purifinity.coding.lang.c11.ust;

import java.util.ArrayList;
import java.util.List;

import com.puresoltechnologies.parsers.api.ust.UniversalSyntaxTree;
import com.puresoltechnologies.parsers.impl.parser.ParserTree;
import com.puresoltechnologies.parsers.impl.ust.CompilationUnit;
import com.puresoltechnologies.parsers.impl.ust.UnspecialistProduction;

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
