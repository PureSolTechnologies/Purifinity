package com.puresoltechnologies.purifinity.server.plugin.c11.ust;

import java.util.ArrayList;
import java.util.List;

import com.puresoltechnologies.parsers.parser.ParseTreeNode;
import com.puresoltechnologies.parsers.ust.CompilationUnit;
import com.puresoltechnologies.parsers.ust.UniversalSyntaxTree;
import com.puresoltechnologies.parsers.ust.UnspecialistProduction;

public class TranslationUnitCreator {

	public static CompilationUnit create(ParseTreeNode parserTree) {
		List<UniversalSyntaxTree> ustChildren = new ArrayList<>();
		for (ParseTreeNode child : parserTree.getChildren()) {
			ustChildren.add(createNode(child));
		}
		return new CompilationUnit(parserTree.getName(), parserTree.getText(),
				ustChildren);
	}

	private static UniversalSyntaxTree createNode(ParseTreeNode node) {
		List<UniversalSyntaxTree> ustChildren = new ArrayList<>();
		for (ParseTreeNode child : node.getChildren()) {
			ustChildren.add(createNode(child));
		}
		return new UnspecialistProduction(node.getName(), node.getName(),
				ustChildren);
	}
}
